package demo.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.entity.Klijent;
import demo.app.service.KlijentService;
import demo.app.web.dto.KlijentDTO;

@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/klijent")
public class KlijentController {
	
	@Autowired
	KlijentService ks;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KlijentDTO>> getAll() {
		List<Klijent> klijenti = ks.findAll();	
		List<KlijentDTO> dtos = ks.getAllDTOs(klijenti);		
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

}
