package demo.app.web.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.entity.Korisnik;
import demo.app.entity.Racun;
import demo.app.service.KorisnikService;
import demo.app.service.RacunService;
import demo.app.web.dto.RacunDTO;


@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/racun")
public class RacunController {
	
	@Autowired
	RacunService rs;
	
	@Autowired
	KorisnikService ks;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RacunDTO>> getAll() {
		List<Racun> racuni = rs.findAll();	
		List<RacunDTO> dtos = rs.getAllAktivniDTOs(racuni);		
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/izvrsilac/{korIme}",method = RequestMethod.GET)
	public ResponseEntity<?> getAllNeodobreniByBanka(@PathVariable String korIme) {
		Korisnik kor = ks.findByKorisnickoIme(korIme);
		if(kor == null || kor.getBanka() == null)
			return new ResponseEntity<>("Greska!",HttpStatus.NOT_FOUND);
		
		List<Racun> racuni = rs.getByBankaIdAndOdobrenAndIzbrisan(kor.getBanka().getId(),false,false);
		
		List<RacunDTO> dtos = rs.getAllDTOs(racuni);		
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	


}
