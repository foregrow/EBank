package demo.app.web.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.entity.Valuta;
import demo.app.service.ValutaService;
import demo.app.web.dto.ValutaDTO;


@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/valuta")
public class ValutaComponent {
	
	@Autowired
	ValutaService vs;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ValutaDTO>> getAll() {
		List<Valuta> valute = vs.findAll();	
		List<ValutaDTO> dtos = vs.getAllDTOs(valute);		
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ValutaDTO> getById(@PathVariable long id){
		Valuta v = vs.findOne(id);
		
		if(v == null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
		ValutaDTO dto = vs.getValutaDTO(v);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id){
		//admin brise
		Valuta valuta = vs.findOne(id);
		if (valuta != null){
			vs.remove(id);
			List<Valuta> valute = vs.findAll();
			List<ValutaDTO> dtos = vs.getAllDTOs(valute);
			
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else		
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
	}


}
