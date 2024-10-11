package com.digi.digiHello9.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ville {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nb_habs")
	private Integer nbHabitants;
	@Column(nullable = true)
	private Long ID_REGION;
	
	@Column(name = "nom")
	private String nom ;
	@ManyToOne
	@JoinColumn(name = "id_dept")
	private Departement departement;
	
	public Ville() {
		super();
	}

	public Ville(String nom, Integer nbHabitants,Departement departement) {
		this.nom = nom;
		this.nbHabitants = nbHabitants;
		this.departement =departement;
	}
	
	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getNbHabitants() {
		return nbHabitants;
	}
	public void setNbHabitants(Integer nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

	public Long getId() {
		return id;
	}

	public void setId(long idVille) {
		this.id = idVille;
	}
	

}
