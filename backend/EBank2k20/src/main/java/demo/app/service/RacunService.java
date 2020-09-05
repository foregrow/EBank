package demo.app.service;

import java.util.ArrayList;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
