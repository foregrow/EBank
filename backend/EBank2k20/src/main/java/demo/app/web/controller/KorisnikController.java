package demo.app.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.entity.Korisnik;
import demo.app.service.KorisnikService;
import demo.app.web.dto.KorisnikDTO;


@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/korisnik")
public class KorisnikController {

	@Autowired
	private KorisnikService ks;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getAll() {
		List<Korisnik> korisnici = ks.findAll();
		
		List<KorisnikDTO> dtos = ks.getAllDTOs(korisnici);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
}
