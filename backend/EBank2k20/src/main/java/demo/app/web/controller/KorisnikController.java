package demo.app.web.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.entity.Korisnik;
import demo.app.service.KorisnikService;
import demo.app.web.dto.KorisnikDTO;


@RestController
@RequestMapping(value="api/korisnici")
public class KorisnikController {

	@Autowired
	private KorisnikService ks;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getAll() {
		List<Korisnik> korisnici = ks.findAll();
		
		List<KorisnikDTO> dtos = new ArrayList<>();
		for (Korisnik s : korisnici)
			dtos.add(new KorisnikDTO(s));
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
}
