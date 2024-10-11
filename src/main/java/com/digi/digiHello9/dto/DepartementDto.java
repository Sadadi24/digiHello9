package com.digi.digiHello9.dto;

public class DepartementDto {
    private String codeDepartement;
    private String nomDepartement;
    private int nombreHabitants;

    // Constructeurs
    public DepartementDto() {}

    public DepartementDto(String codeDepartement, String nomDepartement, int nombreHabitants) {
        this.codeDepartement = codeDepartement;
        this.nomDepartement = nomDepartement;
        this.nombreHabitants = nombreHabitants;
    }

    // Getters et Setters
    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public int getNombreHabitants() {
        return nombreHabitants;
    }

    public void setNombreHabitants(int nombreHabitants) {
        this.nombreHabitants = nombreHabitants;
    }

}
