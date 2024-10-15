package com.digi.digiHello9.dto;

public class VilleDto {
    private String codeVille;
    private int nombreHabitants;
    private String codeDepartement;
    private String nomVille;

    // Constructeurs
    public VilleDto() {}

    public VilleDto(String codeVille, int nombreHabitants, String codeDepartement, String nomVille) {
        this.codeVille = codeVille;
        this.nombreHabitants = nombreHabitants;
        this.codeDepartement = codeDepartement;
        this.nomVille = nomVille;
    }

    // Getters et Setters
    public String getCodeVille() {
        return codeVille;
    }

    public void setCodeVille(String codeVille) {
        this.codeVille = codeVille;
    }

    public int getNombreHabitants() {
        return nombreHabitants;
    }

    public void setNombreHabitants(int nombreHabitants) {
        this.nombreHabitants = nombreHabitants;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }
}

