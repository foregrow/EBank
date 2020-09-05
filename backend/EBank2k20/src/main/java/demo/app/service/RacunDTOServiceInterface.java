package demo.app.service;

import java.util.List;


import demo.app.entity.Racun;
import demo.app.web.dto.RacunDTO;



public interface RacunDTOServiceInterface {
	
	List<RacunDTO> getAllDTOs(List<Racun> racuni);
	
	List<RacunDTO> getAllAktivniDTOs(List<Racun> racuni);
	
	RacunDTO getRacunDTO(Racun r);
	
	
}
