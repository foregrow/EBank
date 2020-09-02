package demo.app.service;

import java.util.List;

import demo.app.entity.Valuta;
import demo.app.web.dto.ValutaDTO;



public interface ValutaServiceInterface {

	List<Valuta> findAll();
	
	Valuta findOne(long id);
	
	Valuta save(Valuta valuta);
	
	void remove(long id);
	
	List<ValutaDTO> getAllDTOs(List<Valuta> valute);
	
	ValutaDTO getValutaDTO(Valuta valuta);
	
}
