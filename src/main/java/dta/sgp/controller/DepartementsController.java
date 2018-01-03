package dta.sgp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dta.sgp.entites.Departement;
import dta.sgp.repository.DepartementRepository;

@RestController
@RequestMapping("/api/departements")
public class DepartementsController {
	
	private final DepartementRepository departementRepo;

	/**
	 * @param departementRepo
	 */
	@Autowired
	DepartementsController( DepartementRepository departementRepo) {
		this.departementRepo = departementRepo;
	}
	
	@GetMapping
	public List<Departement> listerDepartements() {
		return this.departementRepo.findAll();
	}
}
