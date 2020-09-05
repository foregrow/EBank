package demo.app.service;

import java.util.List;

import demo.app.entity.UkidanjeRacuna;
import demo.app.web.dto.UkidanjeRacunaDTO;



public interface UkidanjeRacunaDTOServiceInterface {
	
	List<UkidanjeRacunaDTO> getAllDTOs(List<UkidanjeRacuna> ukidanjeRacuna);
	
	UkidanjeRacunaDTO getUkidanjeRacunaDTO(UkidanjeRacuna ur);
	
}
