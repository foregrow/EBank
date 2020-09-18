package demo.app.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.entity.Drzava;
import demo.app.entity.Racun;
import demo.app.entity.Valuta;
import demo.app.service.DrzavaService;
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
	RacunService rs;
	
	@Autowired
	DrzavaService ds;
	
	@Autowired
	ValutaService vs;
	
	@Autowired
	MedjubankarskiPrenosService mps;
	
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
		if(dto.getRacunDuznika().getStanje() - dto.getIznos() < 0)
			return new ResponseEntity<>("Nemate dovoljno novca na racunu!", HttpStatus.NOT_FOUND);
		Racun racunDuznika = rs.findOne(dto.getRacunDuznika().getId());
		
		
		if(racunDuznika.getBanka().getId() == racunPrimaoca.getBanka().getId())
			ns.saveTransakcija(dto,racunDuznika,racunPrimaoca,drzava,valuta);
		else {
			mps.saveMedjubankarskiPrenos(dto,racunDuznika,racunPrimaoca,valuta);
		}
			
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	


}
