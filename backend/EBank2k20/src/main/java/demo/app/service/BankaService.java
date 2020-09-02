package demo.app.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Banka;
import demo.app.repository.BankaRepository;
import demo.app.web.dto.BankaDTO;


@Service
@Transactional(readOnly = true)
public class BankaService  implements BankaServiceInterface {

	@Autowired
	BankaRepository br;
	
	@Override
	public List<Banka> findAll() {
		return br.findAll();
	}
	@Override
	public Banka findOne(long id) {
		return br.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public Banka save(Banka ban) {
		return br.save(ban);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		br.deleteById(id);
	}
	@Override
	public List<BankaDTO> getAllDTOs(List<Banka> banke) {
		List<BankaDTO> dtos = new ArrayList<>();
		for (Banka b : banke) {
			BankaDTO dto = new BankaDTO(b);
			dto.setRacuniListFromSet(b.getRacuni()); //pretvara set tipa Racun u listu tipa RacunDTO
			dto.setKursneListeListFromSet(b.getKursneListe());
			dto.setIzvrsiociListFromSet(b.getIzvrsioci());
			dtos.add(dto);
		}
		return dtos;
	}
	@Override
	public BankaDTO getBankaDTO(Banka b) {
		BankaDTO dto = new BankaDTO(b);
		dto.setRacuniListFromSet(b.getRacuni()); 
		dto.setKursneListeListFromSet(b.getKursneListe());
		dto.setIzvrsiociListFromSet(b.getIzvrsioci());
		return dto;
	}

}
