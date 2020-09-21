package demo.app.service;

import java.util.List;

import demo.app.entity.Poruka;
import demo.app.web.dto.PorukaDTO;



public interface PorukaDTOServiceInterface {
	
	List<PorukaDTO> getAllDTOs(List<Poruka> poruke);
	
	PorukaDTO getPorukaDTO(Poruka poruka);
	
}
