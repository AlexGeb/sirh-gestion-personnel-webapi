package dta.sgp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dta.sgp.entites.Departement;


public interface DepartementRepository extends JpaRepository<Departement, Integer> {

}
