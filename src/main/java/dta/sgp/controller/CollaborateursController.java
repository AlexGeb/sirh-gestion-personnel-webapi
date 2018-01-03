package dta.sgp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{matricule}")
	public boolean modifyCollab(@PathVariable String matricule,@RequestBody Collaborateur collaborateur) {
		Optional<Collaborateur> optCollab = this.collaborateursRepo.findByMatricule(matricule);
		if (optCollab.isPresent()) {
			collaborateur.setId(optCollab.get().getId());
			collaborateursRepo.save(collaborateur);
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{matricule}/banque")
	public boolean modifyBanqueOfCollab(@PathVariable String matricule,@RequestBody BanqueInfo banqueInfo) {
		Optional<Collaborateur> optCollab = this.collaborateursRepo.findByMatricule(matricule);
		if (optCollab.isPresent()) {
			Collaborateur collaborateur = optCollab.get();
			collaborateur.setBanqueInfo(banqueInfo);
			collaborateursRepo.save(collaborateur);
			return true;
		} else {
			return false;
		}
	}
}
