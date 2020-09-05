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

import demo.app.entity.Banka;
import demo.app.entity.Delatnost;
import demo.app.entity.Klijent;
import demo.app.entity.Korisnik;
import demo.app.entity.Racun;
import demo.app.entity.Valuta;
import demo.app.enums.UlogaKorisnika;
import demo.app.service.BankaService;
import demo.app.service.DelatnostService;
import demo.app.service.KlijentService;
import demo.app.service.KorisnikService;
import demo.app.service.RacunService;
import demo.app.service.ValutaService;
import demo.app.util.PasswordBCrypt;
import demo.app.web.dto.KlijentDTO;

@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/klijent")
public class KlijentController {
	
	@Autowired
	KlijentService ks;
	
	@Autowired
	KorisnikService kos;
	
	@Autowired
	DelatnostService ds;
	
	@Autowired
	RacunService rs;
	
	@Autowired
	BankaService bs;
	
	@Autowired
	ValutaService vs;
	
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
		
		KlijentDTO dto = ks.getKlijentDTO(k);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/banka/{korIme}/{param}", method=RequestMethod.GET)
	public ResponseEntity<?> getOdobreniOrNeodobreniByBankaId(@PathVariable String korIme, @PathVariable
			int param){
		Korisnik kor = kos.findByKorisnickoIme(korIme);
		if(kor == null)
			return new ResponseEntity<>("Korisnik nije pronadjen!",HttpStatus.NOT_FOUND);
		List<Klijent> klijenti = null;
		switch(param) {
			case 0:
				klijenti = ks.getNeodobreniByBankaId(kor.getBanka().getId());
				break;
			case 1:
				klijenti = ks.getOdobreniByBankaId(kor.getBanka().getId());
				break;
			default:
				break;
		}
		
		List<KlijentDTO> dtos = ks.getAllDTOs(klijenti);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/zahtev/{bid}/{vid}",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> save(@PathVariable long bid, @PathVariable long vid, @RequestBody KlijentDTO dto){
		Banka banka = bs.findOne(bid);
		Valuta valuta = vs.findOne(vid);
		if(banka == null)
			return new ResponseEntity<>("Nepostojeca banka!",HttpStatus.NOT_FOUND);
		if(valuta == null)
			return new ResponseEntity<>("Nepostojeca valuta!",HttpStatus.NOT_FOUND);
		
		Korisnik k = new Korisnik();
		String korIme = kos.createKorIme(dto.getIme(), dto.getPrezime());
		k.setKorisnickoIme(korIme);
		k.setUloga(UlogaKorisnika.KORISNIK);
		k.setLozinka(PasswordBCrypt.hashPassword(dto.getIme()));
		
		Klijent kl = new Klijent();
		kl.setAdresa(dto.getAdresa());
		if(dto.getDelatnost() != null) {
			Delatnost del = ds.findOne(dto.getDelatnost().getId());
			kl.setDelatnost(del);
		}
		kl.setIme(dto.getIme());
		kl.setPrezime(dto.getPrezime());
		kl.setJmbg(dto.getJmbg());
		kl.setOdobren(false);
		kl.setTelefon(dto.getTelefon());
		kl.setTipKlijenta(dto.getTipKlijenta());
		
		Racun racun = new Racun();
		racun.setBrojRacuna(rs.createBrojRacuna(banka));
		racun.setBanka(banka);
		racun.setDatumKreiranja(new Date());
		racun.setIzbrisan(false);
		racun.setOdobren(false);
		racun.setValuta(valuta);
		racun.setStanje(0);
		ks.zahtevZaOtvaranjeRacuna(kl, racun, k);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> update(@RequestBody KlijentDTO dto){
		Klijent klijent = ks.findOne(dto.getId());
		if(klijent == null)
			return new ResponseEntity<>("Greska prilikom prihvatanja zahteva", HttpStatus.NOT_FOUND);
		ks.prihvatanjeZahtevaKlijenta(klijent);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id){
		
		Klijent klijent = ks.findOne(id);
		if (klijent != null){
			ks.remove(id);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else		
			return new ResponseEntity<>("Greska prilikom brisanja zahteva!",HttpStatus.NOT_FOUND);
		
	}
	

}
