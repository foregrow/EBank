package demo.app.web.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import demo.app.entity.Drzava;
import demo.app.entity.Korisnik;
import demo.app.entity.Nalog;
import demo.app.entity.Racun;
import demo.app.entity.Valuta;
import demo.app.service.DrzavaService;
import demo.app.service.IzvestajIObradeService;
import demo.app.service.KorisnikService;
import demo.app.service.MedjubankarskiPrenosService;
import demo.app.service.NalogService;
import demo.app.service.RacunService;
import demo.app.service.ValutaService;
import demo.app.web.dto.NalogDTO;


@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/nalog")
public class NalogController {
	
	@Autowired
	NalogService ns;
	
	@Autowired
	KorisnikService ks;
	
	@Autowired
	RacunService rs;
	
	@Autowired
	DrzavaService ds;
	
	@Autowired
	ValutaService vs;
	
	@Autowired
	MedjubankarskiPrenosService mps;
	
	@Autowired
	IzvestajIObradeService is;
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> save(@RequestBody NalogDTO dto){
		Racun racunPrimaoca = rs.getByBrojRacuna(dto.getRacunPrimaoca().getBrojRacuna());
		if(racunPrimaoca == null)
			return new ResponseEntity<>("Nepostojeci racun primaoca!", HttpStatus.NOT_FOUND);
		Drzava drzava = ds.findOne(dto.getDrzava().getId());
		if(drzava == null)
			return new ResponseEntity<>("Nepostojeca drzava!", HttpStatus.NOT_FOUND);
		Valuta valuta = vs.findOne(dto.getValuta().getId());
		if(valuta == null)
			return new ResponseEntity<>("Nepostojeca valuta!", HttpStatus.NOT_FOUND);
		if((dto.getRacunDuznika().getStanje() - dto.getRacunDuznika().getRezervisanIznos()) - dto.getIznos() < 0)
			return new ResponseEntity<>("Nemate dovoljno novca na racunu!", HttpStatus.NOT_FOUND);
		Racun racunDuznika = rs.findOne(dto.getRacunDuznika().getId());
		
		
		if(racunDuznika.getBanka().getId() == racunPrimaoca.getBanka().getId())
			ns.saveTransakcija(dto,racunDuznika,racunPrimaoca,drzava,valuta,2);
		else {
			mps.saveMedjubankarskiPrenos(dto,racunDuznika,racunPrimaoca,drzava,valuta);
		}
			
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/importFiles")
	public ResponseEntity<?> upload(@RequestParam("files") MultipartFile[] files) throws IOException {
		
		List<Nalog> nalozi = new ArrayList<Nalog>();
		for(MultipartFile file : files) {
			String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
			if(extension.equals("xml")) {
				Nalog n = is.importNalog(file.getOriginalFilename());
				nalozi.add(n);
			}
			
		}
		List<NalogDTO> dtos = new ArrayList<NalogDTO>();
		for(Nalog n : nalozi) {
			dtos.add(new NalogDTO(n));
		}
			
		
		
		return new ResponseEntity<>(dtos,HttpStatus.OK); 
	}


}
