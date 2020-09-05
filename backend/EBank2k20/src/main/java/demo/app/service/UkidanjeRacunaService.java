package demo.app.service;

import java.util.ArrayList;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Racun;
import demo.app.entity.UkidanjeRacuna;
import demo.app.repository.UkidanjeRacunaRepository;
import demo.app.web.dto.UkidanjeRacunaDTO;


@Service
@Transactional(readOnly = true)
public class UkidanjeRacunaService  implements UkidanjeRacunaServiceInterface, UkidanjeRacunaDTOServiceInterface {

	@Autowired
	UkidanjeRacunaRepository urr;
	
	@Autowired
	RacunService rs;
	
	@Override
	public List<UkidanjeRacuna> findAll() {
		return urr.findAll();
	}
	@Override
	public UkidanjeRacuna findOne(long id) {
		return urr.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public UkidanjeRacuna save(UkidanjeRacuna ur) {
		return urr.save(ur);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		urr.deleteById(id);
	}
	@Override
	public List<UkidanjeRacuna> findAllByBankaIdUToku(long id) {
		return urr.findAllByBankaIdUToku(id);
	}
	@Override
	public List<UkidanjeRacuna> findAllByKlijentIdWhereZavrsenoFalse(long id) {
		return urr.findAllByKlijentIdWhereZavrsenoFalse(id);
	}
	@Override
	public UkidanjeRacuna findByRacunZaUkidanjeId(long id) {
		return urr.findByRacunZaUkidanjeId(id);
	}
	
	@Override
	public List<UkidanjeRacunaDTO> getAllDTOs(List<UkidanjeRacuna> url) {
		List<UkidanjeRacunaDTO> dtos = new ArrayList<>();
		for (UkidanjeRacuna ur : url) {
			UkidanjeRacunaDTO dto = new UkidanjeRacunaDTO(ur);
			dtos.add(dto);
		}
		return dtos;
	}
	@Override
	public UkidanjeRacunaDTO getUkidanjeRacunaDTO(UkidanjeRacuna ur) {
		UkidanjeRacunaDTO dto = new UkidanjeRacunaDTO(ur);
		return dto;
	}
	@Override
	@Transactional(readOnly = false)
	public void ukidanjeRacunaAccepted(UkidanjeRacuna ur) {
		ur.setZavrseno(true);
		Racun rZaUkidanje = ur.getRacunZaUkidanje();
		Racun rZaPrenosNovca = ur.getRacunZaPrenosNovca();
		rZaUkidanje.setIzbrisan(true);
		if(rZaPrenosNovca == null) {
			rZaUkidanje.setStanje(0);
			rs.save(rZaUkidanje);
		}else {
			rZaPrenosNovca.setStanje(rZaPrenosNovca.getStanje() + rZaUkidanje.getStanje());
			rZaUkidanje.setStanje(0);
			rs.save(rZaUkidanje);
			rs.save(rZaPrenosNovca);
		}
		urr.save(ur);
		
	}
	@Override
	public List<UkidanjeRacuna> findAllByBankaIdUkinuti(long id) {
		return urr.findAllByBankaIdUkinuti(id);
	}
	
	
	

}
