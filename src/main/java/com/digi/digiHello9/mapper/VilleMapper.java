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
        dto.setNomVille(ville.getNom());
        dto.setCodeDepartement(ville.getDepartement().getCode());

        return dto;
    }

    public Ville toEntity(VilleDto dto) {
        if (dto == null) {
            return null;
        }

        Ville ville = new Ville();
        ville.setNom(dto.getNomVille());
        ville.setNbHabitants(dto.getNombreHabitants());

        return ville;
    }
}
