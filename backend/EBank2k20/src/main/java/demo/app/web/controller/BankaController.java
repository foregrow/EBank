package demo.app.web.controller;



import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
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
import demo.app.service.BankaService;
import demo.app.service.IzvestajIObradeService;
import demo.app.service.NalogService;
import demo.app.service.KorisnikService;
import demo.app.web.dto.BankaDTO;

@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/banka")
public class BankaController {
	
	@Autowired
	BankaService bs;
	
	@Autowired
	NalogService ns;
	
	@Autowired
	KorisnikService ks;
	
	@Autowired
	IzvestajIObradeService is;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BankaDTO>> getAll() throws FileNotFoundException, ParseException {
		//JAVA TECHIE UHUHUU
		List<Banka> banke = bs.findAll();	
		List<BankaDTO> dtos = bs.getAllDTOs(banke);		
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<BankaDTO> getById(@PathVariable long id){
		Banka b = bs.findOne(id);
		
		if(b == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		BankaDTO dto = bs.getBankaDTO(b);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> save(@RequestBody BankaDTO dto){
		Banka banka = new Banka();
		banka.setAdresa(dto.getAdresa());
		banka.setEmail(dto.getEmail());
		banka.setNaziv(dto.getNaziv());
		banka.setFax(dto.getFax());
		banka.setSifra(dto.getSifra());
		banka.setSwift(dto.getSwift());
		banka.setTelefon(dto.getTelefon());
		banka.setWeb(dto.getWeb());
		
		bs.save(banka);
		return new ResponseEntity<>(new BankaDTO(banka), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> update(@RequestBody BankaDTO dto){
		Banka banka = bs.findOne(dto.getId());
		if(banka == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		banka.setAdresa(dto.getAdresa());
		banka.setEmail(dto.getEmail());
		banka.setNaziv(dto.getNaziv());
		banka.setFax(dto.getFax());
		banka.setTelefon(dto.getTelefon());
		banka.setWeb(dto.getWeb());
		
		bs.save(banka);
		return new ResponseEntity<>(new BankaDTO(banka), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id){
		//admin brise
		Banka banka = bs.findOne(id);
		if (banka != null){
			bs.remove(id);
			List<Banka> banke = bs.findAll();
			List<BankaDTO> dtos = bs.getAllDTOs(banke);
			
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else		
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value="/bankeKlijenta/{korIme}", method=RequestMethod.GET)
	public ResponseEntity<?> bankeUKojimaKlijentImaRacun(@PathVariable String korIme){
		Korisnik kor = ks.findByKorisnickoIme(korIme);
		if(kor == null || kor.getKlijent() == null)
			return new ResponseEntity<>("Greska!",HttpStatus.NOT_FOUND);
					
		List<Banka> banke = new ArrayList<Banka>();
		banke = bs.bankeUKojimaKlijentImaRacun(kor.getKlijent().getId());
		List<BankaDTO> dtos = bs.getAllDTOs(banke);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
		
		
	}


}
