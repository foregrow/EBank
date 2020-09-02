package demo.app.service;

import java.util.List;

import demo.app.entity.Drzava;
import demo.app.web.dto.DrzavaDTO;



public interface DrzavaDTOServiceInterface {
	
	List<DrzavaDTO> getAllDTOs(List<Drzava> drzave);
	
	DrzavaDTO getDrzavaDTO(Drzava drzave);
	
}
