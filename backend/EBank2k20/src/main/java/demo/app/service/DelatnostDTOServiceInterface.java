package demo.app.service;

import java.util.List;


import demo.app.entity.Delatnost;
import demo.app.web.dto.DelatnostDTO;

public interface DelatnostDTOServiceInterface {

	List<DelatnostDTO> getAllDTOs(List<Delatnost> delatnosti);
	
	DelatnostDTO getDelatnostDTO(Delatnost del);
	
}
