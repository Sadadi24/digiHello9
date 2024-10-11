package com.digi.digiHello9.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Departement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String code;
    
    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<Ville> villes = new ArrayList<>();
    
    public Departement() {
	}
    

	public Departement(String nom, String code) {
		super();
		this.nom = nom;
		this.code = code;
	}


	// Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public List<Ville> getVilles() {
        return villes;
    }
    
    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }
}

