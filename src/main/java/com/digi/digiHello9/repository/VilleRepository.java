package com.digi.digiHello9.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.digi.digiHello9.model.Ville;

public interface VilleRepository extends JpaRepository<Ville, Long> {


    List<Ville> findAll();

    Optional<Ville> findById(Long  id);


    Optional<Ville> findByNom(String nom);

    List<Ville> findByNomIsStartingWith(String nom);

    List<Ville> findByNbHabitantsIsGreaterThan(Integer nb);

    // Recherche par population entre min et max
    List<Ville> findByNbHabitantsBetween(int min, int max);

    // Recherche par département et population minimum
    public List<Ville> findByDepartementCodeAndNbHabitantsGreaterThan(String departement_code, Integer nbHabitants);
    // public List<Ville> findByDepartementAndGreaterThan(Departement departement,int min);

    // Recherche par département et population entre min et max
    // public List<Ville> findByDepartementAndNbHabitantsBetween(String departementCode, int min, int max);


}
