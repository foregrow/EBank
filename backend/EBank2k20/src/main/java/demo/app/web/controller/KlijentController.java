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

import demo.app.entity.Klijent;
import demo.app.entity.Korisnik;
import demo.app.service.KlijentService;
import demo.app.service.KorisnikService;
import demo.app.web.dto.KlijentDTO;

@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/klijent")
public class KlijentController {
	
	@Autowired
	KlijentService ks;
	
	@Autowired
	KorisnikService kos;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KlijentDTO>> getAll() {
		List<Klijent> klijenti = ks.findAll();	
		List<KlijentDTO> dtos = ks.getAllDTOs(klijenti);		
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/korisnik/{korIme}", method=RequestMethod.GET)
	public ResponseEntity<KlijentDTO> getByKorIme(@PathVariable String korIme){
		Korisnik kor = kos.findByKorisnickoIme(korIme);
		if(kor == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
		Klijent k = ks.getByKorisnikId(kor.getId());
		
		if(k == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
		KlijentDTO dto = ks.getKlijentDTOs(k);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
