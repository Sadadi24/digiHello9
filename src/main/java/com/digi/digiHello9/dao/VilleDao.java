package com.digi.digiHello9.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digi.digiHello9.model.Ville;

@Service
public class VilleDao {
    @PersistenceContext
    private EntityManager em;

    public List<Ville> findAll() {
        TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v", Ville.class);
        return query.getResultList();
    }

    public Optional<Ville> findById(Integer id) {
        Ville ville = em.find(Ville.class, id);
        return Optional.ofNullable(ville);
    }

    public Optional<Ville> findByNom(String nom) {
        try {
            TypedQuery<Ville> query = em.createQuery(
                "SELECT v FROM Ville v WHERE v.nom = :nom", 
                Ville.class
            );
            query.setParameter("nom", nom);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public Ville save(Ville ville) {
    	if(ville.getId()== null) {
    		 em.persist(ville);
    		
    	}else {
    		 em.merge(ville);
    	}
       
        return ville;
    }

    @Transactional
    public void deleteById(Integer id) {
        findById(id).ifPresent(ville -> em.remove(ville));
    }
}