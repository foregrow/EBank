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

import demo.app.entity.Delatnost;
import demo.app.service.DelatnostService;
import demo.app.web.dto.DelatnostDTO;


@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/delatnost")
public class DelatnostController {
	
	@Autowired
	DelatnostService ds;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DelatnostDTO>> getAll() {
		List<Delatnost> delatnosti = ds.findAll();
		List<DelatnostDTO> dtos = ds.getAllDTOs(delatnosti);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<DelatnostDTO> getById(@PathVariable long id){
		Delatnost d = ds.findOne(id);
		
		if(d == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		DelatnostDTO dto = ds.getDelatnostDTO(d);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> save(@RequestBody DelatnostDTO dto){
		Delatnost delatnost = new Delatnost();
		delatnost.setNaziv(dto.getNaziv());
		delatnost.setSifra(dto.getSifra());

		ds.save(delatnost);
		return new ResponseEntity<>(new DelatnostDTO(delatnost), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> update(@RequestBody DelatnostDTO dto){
		Delatnost delatnost = ds.findOne(dto.getId());
		if(delatnost == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		delatnost.setNaziv(dto.getNaziv());
		
		ds.save(delatnost);
		return new ResponseEntity<>(new DelatnostDTO(delatnost), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id){
		//admin brise
		Delatnost del = ds.findOne(id);
		if (del != null){
			ds.remove(id);
			List<Delatnost> delatnosti = ds.findAll();
			List<DelatnostDTO> dtos = ds.getAllDTOs(delatnosti);
			
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else		
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
	}


}
