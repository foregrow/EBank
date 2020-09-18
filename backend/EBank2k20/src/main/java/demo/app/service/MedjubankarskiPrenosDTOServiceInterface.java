package demo.app.service;

import java.util.List;


import demo.app.entity.MedjubankarskiPrenos;
import demo.app.web.dto.MedjubankarskiPrenosDTO;



public interface MedjubankarskiPrenosDTOServiceInterface {
	
	List<MedjubankarskiPrenosDTO> getAllDTOs(List<MedjubankarskiPrenos> md);
	
	MedjubankarskiPrenosDTO getMedjubankarskiPrenosDTO(MedjubankarskiPrenos m);
	
}
