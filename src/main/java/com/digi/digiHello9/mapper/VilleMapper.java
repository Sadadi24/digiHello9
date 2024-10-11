package com.digi.digiHello9.mapper;

import com.digi.digiHello9.dto.VilleDto;
import com.digi.digiHello9.model.Departement;
import com.digi.digiHello9.model.Ville;
import org.springframework.stereotype.Component;

@Component
public class VilleMapper {
    public VilleDto toDto(Ville ville) {
        if (ville == null) {
            return null;
        }

        VilleDto dto = new VilleDto();
        dto.setCodeVille(ville.getDepartement().getCode());
        dto.setNombreHabitants(ville.getNbHabitants());

        if (ville.getDepartement() != null) {
            dto.setCodeDepartement(ville.getDepartement().getCode());
            dto.setNomDepartement(ville.getDepartement().getNom());
        }

        return dto;
    }

    public Ville toEntity(VilleDto dto) {
        if (dto == null) {
            return null;
        }

        Ville ville = new Ville();
        ville.setDepartement(new Departement(dto.getCodeDepartement(), dto.getNomDepartement()));
        ville.setNom(dto.getNomDepartement());
        ville.setNbHabitants(dto.getNombreHabitants());

        return ville;
    }
}
