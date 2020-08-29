package demo.app.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.entity.Banka;
import demo.app.service.BankaService;
import demo.app.web.dto.BankaDTO;

@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/banka")
public class BankaController {
	
	@Autowired
	BankaService bs;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BankaDTO>> getAll() {
		List<Banka> banke = bs.findAll();
		
		List<BankaDTO> dtos = new ArrayList<>();
		for (Banka b : banke)
			dtos.add(new BankaDTO(b));
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<BankaDTO> getById(@PathVariable long id){
		Banka b = bs.findOne(id);
		if(b == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(new BankaDTO(b), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> save(){
	
		return null;
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<?> update(){
		
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id){
		//admin brise
		Banka banka = bs.findOne(id);
		if (banka != null){
			bs.remove(id);
			List<Banka> banke = bs.findAll();
			List<BankaDTO> dtos = new ArrayList<>();
			for(Banka b : banke)
				dtos.add(new BankaDTO(b));
			
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else		
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
	}


}
