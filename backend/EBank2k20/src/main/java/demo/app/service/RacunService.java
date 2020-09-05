package demo.app.service;

import java.util.ArrayList;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Banka;
import demo.app.entity.Racun;
import demo.app.repository.RacunRepository;
import demo.app.web.dto.RacunDTO;


@Service
@Transactional(readOnly = true)
public class RacunService  implements RacunServiceInterface, RacunDTOServiceInterface {

	@Autowired
	RacunRepository rr;
	
	@Override
	public List<Racun> findAll() {
		return rr.findAll();
	}
	@Override
	public Racun findOne(long id) {
		return rr.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public Racun save(Racun r) {
		return rr.save(r);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		rr.deleteById(id);
	}
	@Override
	public List<RacunDTO> getAllAktivniDTOs(List<Racun> rl) {
		List<RacunDTO> dtos = new ArrayList<>();
		for (Racun r : rl) {
			if(!r.isIzbrisan() && r.isOdobren()) {
				RacunDTO dto = new RacunDTO(r);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	@Override
	public RacunDTO getRacunDTO(Racun r) {
		RacunDTO dto = new RacunDTO(r);
		dto.setDnevnoStanjeListFromSet(r.getDnevnoStanje());
		return dto;
	}
	
	public String createBrojRacuna(Banka banka) {
		long min = (long) Math.pow(10, 13 - 1);
		long oznakaRacuna13 = ThreadLocalRandom.current().nextLong(min, min * 10);
		String oznakaRacuna = Long.toString(oznakaRacuna13);
		
		StringBuilder brojRacuna = new StringBuilder();
		brojRacuna.append(banka.getSifra());
		brojRacuna.append(oznakaRacuna);
		
		int suma = 0;
		for(int i=0;i<brojRacuna.length();i++) {
			int cifra = Character.getNumericValue(brojRacuna.charAt(i));
			suma+=cifra;
		}
		int checksum = suma % 97;
		brojRacuna.append(checksum);
		return brojRacuna.toString();
	}

}
