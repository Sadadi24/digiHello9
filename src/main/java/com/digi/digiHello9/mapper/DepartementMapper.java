package com.digi.digiHello9.mapper;

import com.digi.digiHello9.dto.DepartementDto;
import com.digi.digiHello9.model.Departement;
import com.digi.digiHello9.model.Ville;

public class DepartementMapper {

    public DepartementDto toDto(Departement departement) {
        if (departement == null) {
            return null;
        }

        DepartementDto dto = new DepartementDto();
        dto.setCodeDepartement(departement.getCode());
        dto.setNomDepartement(departement.getNom());

        // Calculer le nombre total d'habitants du département
        int totalHabitats = departement.getVilles().stream()
                .mapToInt(Ville::getNbHabitants)
                .sum();
        dto.setNombreHabitants(totalHabitats);

        return dto;
    }

    public Departement toEntity(DepartementDto dto) {
        if (dto == null) {
            return null;
        }

        Departement departement = new Departement();
        departement.setCode(dto.getCodeDepartement());
        departement.setNom(dto.getNomDepartement());

        return departement;
    }
}
