package com.digi.digiHello9.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digi.digiHello9.model.Departement;
import com.digi.digiHello9.model.Ville;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
	
	
	
	
}

