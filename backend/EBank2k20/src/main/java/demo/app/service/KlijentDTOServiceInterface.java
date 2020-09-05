package demo.app.service;

import java.util.List;

import demo.app.entity.Klijent;
import demo.app.web.dto.KlijentDTO;

public interface KlijentDTOServiceInterface {
	
	List<KlijentDTO> getAllDTOs(List<Klijent> klijenti);
	
	KlijentDTO getKlijentDTO (Klijent klijent);

}
