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

import demo.app.entity.Klijent;
import demo.app.entity.Korisnik;
import demo.app.entity.Racun;
import demo.app.service.KlijentService;
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
	
	@Autowired
	KlijentService kls;
	
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
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> update(@RequestBody RacunDTO dto){
		//izvrsialc odobrio racun
		
		Racun racun = rs.findOne(dto.getId());
		if(racun == null)
			return new ResponseEntity<>("Greska prilikom odobravanja zahteva", HttpStatus.NOT_FOUND);
		System.out.println(racun.getId());
		racun.setOdobren(true);
		rs.save(racun);
		
		return new ResponseEntity<>("Uspesno odobravanje zahteva!",HttpStatus.OK);
	}
	
	@RequestMapping(value="/{racunId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long racunId){
		//izvrsilac odbio racun
		Racun racun = rs.findOne(racunId);
		if(racun == null || racun.getKlijent() == null)
			return new ResponseEntity<>("Greska prilikom odbijanja zahteva!",HttpStatus.NOT_FOUND);
		Klijent klijent = kls.findOne(racun.getKlijent().getId());
		if(klijent != null) {
			klijent.getRacuni().remove(racun);
			kls.save(klijent);
			
			System.out.println("racun id: " +racun.getId());
			rs.remove(racun);			
		}
		
		return new ResponseEntity<>("Uspesno odbijanje zahteva!",HttpStatus.OK);
			
	}
	
	@RequestMapping(value="/korisnikRacunAktivacija/{korIme}",method = RequestMethod.GET)
	public ResponseEntity<?> getAllRacunAktivacijaUToku(@PathVariable String korIme) {
		Korisnik kor = ks.findByKorisnickoIme(korIme);
		Klijent k = kls.findOne(kor.getKlijent().getId());
		
		List<Racun> racuni = rs.getByKlijentIdAndOdobrenAndIzbrisan(k.getId(), false, false);
		List<RacunDTO> dtos = rs.getAllDTOs(racuni);
		
		return new ResponseEntity<>(dtos,HttpStatus.OK);
		
	}
	

	
	


}
