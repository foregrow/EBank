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

import demo.app.entity.Drzava;
import demo.app.service.DrzavaService;
import demo.app.web.dto.DrzavaDTO;


@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/drzava")
public class DrzavaController {
	
	
	@Autowired
	DrzavaService ds;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DrzavaDTO>> getAll() {
		List<Drzava> drzave = ds.findAll();	
		List<DrzavaDTO> dtos = ds.getAllDTOs(drzave);		
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<DrzavaDTO> getById(@PathVariable long id){
		Drzava dr = ds.findOne(id);
		
		if(dr == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
		DrzavaDTO dto = ds.getDrzavaDTO(dr);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> save(@RequestBody DrzavaDTO dto){

		Drzava drzava = new Drzava();
		drzava.setNaziv(dto.getNaziv());
		drzava.setSifra(dto.getSifra());

		ds.save(drzava);
		return new ResponseEntity<>(new DrzavaDTO(drzava), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id){
		//admin brise
		Drzava drzava = ds.findOne(id);
		if (drzava != null){
			ds.remove(id);
			List<Drzava> drzave = ds.findAll();
			List<DrzavaDTO> dtos = ds.getAllDTOs(drzave);
			
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else		
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
	}


}
