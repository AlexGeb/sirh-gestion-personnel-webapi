package dta.sgp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dta.sgp.entites.BanqueInfo;
import dta.sgp.entites.Collaborateur;
import dta.sgp.repository.CollaborateurRepository;
import dta.sgp.repository.DepartementRepository;

@RestController
@RequestMapping("/api/collaborateurs")
public class CollaborateursController {

	private final CollaborateurRepository collaborateursRepo;
	private final DepartementRepository departementRepo;

	/**
	 * @param collaborateursRepo
	 * @param departementRepo
	 */
	@Autowired
	CollaborateursController(CollaborateurRepository collaborateursRepo, DepartementRepository departementRepo) {
		this.collaborateursRepo = collaborateursRepo;
		this.departementRepo = departementRepo;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Collaborateur> getCollaboByDept(
			@RequestParam(value = "departement", required = false) Integer dept_id) {
		if (dept_id == null)
			return this.collaborateursRepo.findAll();
		return this.collaborateursRepo.findByDepartement(departementRepo.findOne(dept_id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{matricule}")
	public Collaborateur getCollabByMatricule(@PathVariable String matricule) {
		return this.collaborateursRepo.findByMatricule(matricule).orElse(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{matricule}/banque")
	public BanqueInfo getBanqueOfCollabByMatricule(@PathVariable String matricule) {
		Optional<Collaborateur> optCollab = this.collaborateursRepo.findByMatricule(matricule);
		if (optCollab.isPresent()) {
			return optCollab.get().getBanqueInfo();
		} else {
			return null;
		}
	}
}
