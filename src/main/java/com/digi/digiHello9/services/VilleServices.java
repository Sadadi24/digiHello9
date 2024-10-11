package com.digi.digiHello9.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.digi.digiHello9.dto.VilleDto;
import com.digi.digiHello9.mapper.VilleMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.digi.digiHello9.dao.VilleDao;
import com.digi.digiHello9.model.Departement;
import com.digi.digiHello9.model.Ville;
import com.digi.digiHello9.repository.VilleRepository;

import jakarta.transaction.Transactional;


@Service
public class VilleServices {
   // @Autowired
   // private VilleDao villeDao;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private VilleMapper villeMapper;


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
    public Stream<VilleDto> extractVilles() {
         return villeRepository.findAll().stream().map(ville ->villeMapper.toDto(ville));
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
    public Ville extractVille(Long idVille) {
        return villeRepository.findById(idVille)
                .orElseThrow(() -> new RuntimeException("Ville non trouvée avec l'id : " + idVille));
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
    public Ville extractVille(String nom) {
        return villeRepository.findByNom(nom)
                .orElseThrow(() -> new RuntimeException("Ville non trouvée avec le nom : " + nom));
    }

    @Transactional
    public Stream<VilleDto> insertVille(@RequestBody Ville nvVille) {
        villeRepository.save(nvVille);
        return extractVilles();

    }


    @Transactional
    public Stream<VilleDto> supprimerVille(Long idVille) {
        villeRepository.deleteById(idVille);
        return extractVilles();

    }

}
