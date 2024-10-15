package com.digi.digiHello9.services;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.digi.digiHello9.dto.VilleDto;
import com.digi.digiHello9.exception.CustomException;
import com.digi.digiHello9.mapper.VilleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    private Map<String, List<Ville>> villesParDepartement = new HashMap<>();


    public Ville extractVille(Long idVille) throws CustomException {
        return villeRepository.findById(idVille)
                .orElseThrow(() -> new CustomException("Ville non trouvée avec l'id " ));
    }


    public Ville extractVille(String nom) throws CustomException {
        List<Ville> resultat = new ArrayList<>();
        for (List<Ville> villes : villesParDepartement.values()) {
            for (Ville v : villes) {
                if (v.getNom().toLowerCase().startsWith(nom.toLowerCase())) {
                    resultat.add(v);
                }
            }
        }
        if (resultat.isEmpty()) {
            throw new CustomException("Aucune ville dont le nom commence par " + nom + " n'a été trouvée");
        }

        return villeRepository.findByNom(nom)
                .orElseThrow(() -> new RuntimeException("Ville non trouvée avec le nom : " + nom));
    }

    @Transactional
    public ResponseEntity<VilleDto> insertVille(@RequestBody Ville nvVille) throws CustomException {

        validerVille(nvVille);

        String cle = nvVille.getDepartement().getCode() + "-" + nvVille.getNom();
        List<Ville> villes = villesParDepartement.getOrDefault(nvVille.getDepartement().getCode(), new ArrayList<>());

        if (villes.stream().anyMatch(v -> v.getNom().equals(nvVille.getNom()))) {
            throw new CustomException("Une ville avec ce nom existe déjà dans ce département");
        }
        villeRepository.save(nvVille);
        return ResponseEntity.ok(villeMapper.toDto(nvVille));

    }


    @Transactional
    public  ResponseEntity<String> supprimerVille(Long idVille) {
        villeRepository.deleteById(idVille);
        return ResponseEntity.ok(idVille.toString());

    }

    public Stream<VilleDto> extractVilles() throws CustomException {
        return villeRepository.findAll().stream().map(villeMapper::toDto);
    }

    private void validerVille(Ville ville) throws CustomException {
        if (ville.getNbHabitants()< 10) {
            throw new CustomException("La ville doit avoir au moins 10 habitants");
        }
        if (ville.getNom().length() < 2) {
            throw new CustomException("Le nom de la ville doit contenir au moins 2 lettres");
        }
        if (ville.getDepartement().getCode() == null || ville.getDepartement().getCode().length() != 2) {
            throw new CustomException("Le code département doit obligatoirement avoir 2 caractères");
        }
    }
}
