package demo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Klijent;
import demo.app.repository.KlijentRepository;
import demo.app.web.dto.KlijentDTO;

@Service
@Transactional(readOnly = true)
public class KlijentService implements KlijentServiceInterface,KlijentDTOServiceInterface{
	
	@Autowired
	KlijentRepository kr;
	
	@Override
	public List<Klijent> findAll(){
		return kr.findAll();
	}


	@Override
	public Klijent findOne(long id) {
		
		return kr.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public Klijent save(Klijent klijent) {
	
		return kr.save(klijent);
	}

	
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		
		kr.deleteById(id);
	}
	
	@Override
	public Klijent getByKorisnikId(long id) {
		return kr.getByKorisnikId(id);
	}
	
	@Override
	public List<KlijentDTO> getAllDTOs(List<Klijent> klijenti) {
		List<KlijentDTO> dtos = new ArrayList<>();
		for(Klijent k : klijenti) {
			KlijentDTO dto = new KlijentDTO(k);
			dto.setRacuniAktivniListFromSet(k.getRacuni());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public KlijentDTO getKlijentDTO(Klijent klijent) {
		KlijentDTO dto = new KlijentDTO(klijent);
		dto.setRacuniAktivniListFromSet(klijent.getRacuni());
		return dto;
	}


	
	
}
