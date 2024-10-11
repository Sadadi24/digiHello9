package com.digi.digiHello9.controller;


import com.digi.digiHello9.dto.VilleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.digi.digiHello9.model.Ville;
import com.digi.digiHello9.services.VilleServices;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping
public class VilleController {
  
   @Autowired
   private VilleServices villeServices;



   // Méthode GET pour récupérer la liste des villes
   @GetMapping("/villes")
   @Operation(
           summary = "Liste des villes",
           description = "Récupère la liste complète des villes disponibles"
   )
   @ApiResponses(value = {
           @ApiResponse(
                   responseCode = "200",
                   description = "Retourne la liste des villes",
                   content = @Content(
                           mediaType = "application/json",
                           array = @ArraySchema(schema = @Schema(implementation = VilleDto.class))
                   )
           ),
           @ApiResponse(
                   responseCode = "400",
                   description = "Si une règle métier n'est pas respectée",
                   content = @Content
           )
   })
   public Stream<VilleDto> getVilles() {
       return villeServices.extractVilles();
   }
    @Operation(
            summary = "Rechercher une ville par ID",
            description = "Récupère les détails d'une ville à partir de son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ville trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Ville.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ville non trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
   @GetMapping("/ville/{id}")
   public ResponseEntity<Ville> getVilleById(@PathVariable Long id) {
	   try {
           return ResponseEntity.ok(villeServices.extractVille(id));
       } catch (EntityNotFoundException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
           }
       
   
   }
    @Operation(
            summary = "Rechercher une ville par nom",
            description = "Récupère les détails d'une ville à partir de son nom"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ville trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Ville.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ville non trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/ville/{nom}")
    public ResponseEntity<Ville> getVilleByNom(@PathVariable String nom) {
        try {
            Ville ville = villeServices.extractVille(nom);
            if (ville != null) {
                return ResponseEntity.ok(ville);
            }

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return null;
    }




    @PutMapping("/ville/update/{id}")
    @Operation(
            summary = "Mettre à jour une ville",
            description = "Met à jour les informations d'une ville existante à partir de son ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ville mise à jour avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = VilleDto.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données invalides pour la mise à jour de la ville",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ville non trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Ville> updateVille(@PathVariable Long id, @RequestBody @Valid Ville newVille) {
        Ville ville = villeServices.extractVille(id);
        if (ville == null) {
            return ResponseEntity.notFound().build();

        }
        ville.setNbHabitants(newVille.getNbHabitants()==null?ville.getNbHabitants():newVille.getNbHabitants());

        ville.setNom(newVille.getNom()==null?ville.getNom():newVille.getNom());
        ville.setDepartement(newVille.getDepartement()==null?ville.getDepartement():newVille.getDepartement());
        villeServices.insertVille(newVille);


        return ResponseEntity.ok(ville);
    }
    @Operation(
            summary = "Ajouter une nouvelle ville",
            description = "Crée une nouvelle ville et retourne la liste mise à jour des villes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ville créée avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = VilleDto.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données invalides pour la création de la ville",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
   @PostMapping("ville/add")
   public  ResponseEntity<Stream<VilleDto>>  insertVille(@RequestBody @Valid Ville ville) {
	   return new ResponseEntity<>(villeServices.insertVille(ville), HttpStatus.CREATED);
	}


    @Operation(
            summary = "Supprimer une ville",
            description = "Supprime une ville par son ID et retourne la liste mise à jour des villes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ville supprimée avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = VilleDto.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ville non trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
   @DeleteMapping("/ville/delete/{id}")
   public ResponseEntity<Stream<VilleDto>> deleteVilleById(@PathVariable Long id) {
	   return ResponseEntity.ok(villeServices.supprimerVille(id));
 
   }
   
   
   
   
}



