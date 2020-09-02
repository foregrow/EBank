package demo.app.service;

import java.util.List;

import demo.app.entity.Banka;
import demo.app.web.dto.BankaDTO;

public interface BankaDTOServiceInterface {
	
	List<BankaDTO> getAllDTOs(List<Banka> banke);
	
	BankaDTO getBankaDTO(Banka banka);
	
}
