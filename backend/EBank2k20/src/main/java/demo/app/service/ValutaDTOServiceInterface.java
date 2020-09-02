package demo.app.service;

import java.util.List;

import demo.app.entity.Valuta;
import demo.app.web.dto.ValutaDTO;



public interface ValutaDTOServiceInterface {
	
	List<ValutaDTO> getAllDTOs(List<Valuta> valute);
	
	ValutaDTO getValutaDTO(Valuta valuta);
	
}
