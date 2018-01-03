package dta.sgp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dta.sgp.entites.Collaborateur;
import dta.sgp.entites.Departement;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer> {
	List<Collaborateur> findByDepartement(Departement dept);
	Optional<Collaborateur> findByMatricule(String matricule);
}
