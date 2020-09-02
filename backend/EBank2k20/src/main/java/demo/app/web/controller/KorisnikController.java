package demo.app.web.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.entity.Banka;
import demo.app.entity.Korisnik;
import demo.app.enums.UlogaKorisnika;
import demo.app.service.BankaService;
import demo.app.service.KorisnikService;
import demo.app.util.PasswordBCrypt;
import demo.app.web.dto.KorisnikDTO;


@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/korisnik")
public class KorisnikController {

	@Autowired
	KorisnikService ks;
	
	@Autowired
	BankaService bs;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getAll() {
		List<Korisnik> korisnici = ks.findAll();
		
		List<KorisnikDTO> dtos = ks.getAllDTOs(korisnici);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> getById(@PathVariable long id){
		Korisnik kor = ks.findOne(id);
		
		if(kor == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
		KorisnikDTO dto = ks.getKorisnikDTO(kor);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<KorisnikDTO> save(@RequestBody KorisnikDTO korDTO){
	
		Korisnik kor = new Korisnik();
		
		kor.setKorisnickoIme(korDTO.getKorisnickoIme());
		kor.setLozinka(PasswordBCrypt.hashPassword(korDTO.getLozinka()));
		kor.setUloga(korDTO.getUloga());
		if(korDTO.getUloga().equals(UlogaKorisnika.IZVRSILAC)) {
			if(korDTO.getBanka() == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			Banka banka = bs.findOne(korDTO.getBanka().getId());
			kor.setBanka(banka);
		}
		
		ks.save(kor);
		return new ResponseEntity<>(new KorisnikDTO(kor), HttpStatus.OK);
	}
	
}
