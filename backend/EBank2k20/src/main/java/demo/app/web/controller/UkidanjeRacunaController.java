package demo.app.web.controller;



import java.util.Date;
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

import demo.app.entity.Korisnik;
import demo.app.entity.Racun;
import demo.app.entity.UkidanjeRacuna;
import demo.app.service.KorisnikService;
import demo.app.service.RacunService;
import demo.app.service.UkidanjeRacunaService;
import demo.app.web.dto.UkidanjeRacunaDTO;

@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/ukidanjeRacuna")
public class UkidanjeRacunaController {
	
	@Autowired
	UkidanjeRacunaService urs;
	@Autowired
	RacunService rs;
	@Autowired
	KorisnikService ks;
	
	@RequestMapping(value="/izvrsilac/{korIme}/{param}",method = RequestMethod.GET)
	public ResponseEntity<List<UkidanjeRacunaDTO>> getAllForBanka(@PathVariable String korIme,@PathVariable int param) {
		Korisnik kor = ks.findByKorisnickoIme(korIme);
		if(kor == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		List<UkidanjeRacuna> ukidanjaRacuna = null;
		switch (param) {
		case 0: //u toku ukidanja
			ukidanjaRacuna = urs.findAllByBankaIdUToku(kor.getBanka().getId());
			break;
		case 1: //ukinuti racuni
			ukidanjaRacuna = urs.findAllByBankaIdUkinuti(kor.getBanka().getId());
			break;
		default:
			break;
		}
		List<UkidanjeRacunaDTO> dtos = urs.getAllDTOs(ukidanjaRacuna);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/klijent/{korIme}",method = RequestMethod.GET)
	public ResponseEntity<List<UkidanjeRacunaDTO>> getAllUTokuForKlijent(@PathVariable String korIme) {
		Korisnik kor = ks.findByKorisnickoIme(korIme);
		if(kor == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		List<UkidanjeRacuna> ukidanjaRacunaUtoku = urs.findAllByKlijentIdWhereZavrsenoFalse(kor.getKlijent().getId());
		List<UkidanjeRacunaDTO> dtos = urs.getAllDTOs(ukidanjaRacunaUtoku);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	/*@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<BankaDTO> getById(@PathVariable long id){
		Banka b = bs.findOne(id);
		
		if(b == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		BankaDTO dto = bs.getBankaDTO(b);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}*/
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> save(@RequestBody UkidanjeRacunaDTO dto){
		//klijent pravi zahtev za ukidanje racuna
		if(dto.getRacunZaUkidanje() == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		UkidanjeRacuna ukRacuna = urs.findByRacunZaUkidanjeId(dto.getRacunZaUkidanje().getId());
		if(ukRacuna != null)
			return new ResponseEntity<>("Zahtev za ukidanje ovog racuna vec postoji!",HttpStatus.NOT_FOUND);
		Racun racunZaUkidanje = rs.findOne(dto.getRacunZaUkidanje().getId());
		Racun racunZaPrenosNovca= null;
		if(dto.getRacunZaPrenosNovca() != null)
			racunZaPrenosNovca = rs.findOne(dto.getRacunZaPrenosNovca().getId());
		
		UkidanjeRacuna ur = new UkidanjeRacuna();
		ur.setObrazlozenje(dto.getObrazlozenje());
		ur.setZavrseno(false);
		Date now = new Date();
		ur.setDatumKreiranja(now);
		ur.setRacunZaUkidanje(racunZaUkidanje);
		if(racunZaPrenosNovca != null)
			ur.setRacunZaPrenosNovca(racunZaPrenosNovca);
		
		urs.save(ur);
		List<UkidanjeRacuna> ukidanjaUtoku = urs.findAllByKlijentIdWhereZavrsenoFalse(racunZaUkidanje.getKlijent().getId());
		List<UkidanjeRacunaDTO> dtos = urs.getAllDTOs(ukidanjaUtoku);
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> update(@RequestBody UkidanjeRacunaDTO dto){
		UkidanjeRacuna ur = urs.findOne(dto.getId());
		if(ur == null)
			return new ResponseEntity<>("Greska prilikom ukidanja racuna", HttpStatus.NOT_FOUND);
		urs.ukidanjeRacunaAccepted(ur);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id){
		//admin brise
		UkidanjeRacuna ur = urs.findOne(id);
		if (ur != null){
			urs.remove(id);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else		
			return new ResponseEntity<>("Greska prilikom brisanja zahteva!",HttpStatus.NOT_FOUND);
		
	}


}
