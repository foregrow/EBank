package demo.app.service;

import java.util.List;

import demo.app.entity.Banka;
import demo.app.web.dto.BankaDTO;

public interface BankaServiceInterface {

	List<Banka> findAll();
	
	Banka findOne(long id);
	
	Banka save(Banka bank);
	
	void remove(long id);
	
	List<BankaDTO> getAllDTOs(List<Banka> banke);
	
	BankaDTO getBankaDTO(Banka banka);
	
}
