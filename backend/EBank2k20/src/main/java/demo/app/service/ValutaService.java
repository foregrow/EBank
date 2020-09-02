package demo.app.service;

import java.util.ArrayList;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Valuta;
import demo.app.repository.ValutaRepository;
import demo.app.web.dto.ValutaDTO;




@Service
@Transactional(readOnly = true)
public class ValutaService  implements ValutaServiceInterface {

	@Autowired
	ValutaRepository vr;
	
	@Override
	public List<Valuta> findAll() {
		return vr.findAll();
	}
	@Override
	public Valuta findOne(long id) {
		return vr.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public Valuta save(Valuta va) {
		return vr.save(va);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		vr.deleteById(id);
	}
	@Override
	public List<ValutaDTO> getAllDTOs(List<Valuta> valute) {
		List<ValutaDTO> dtos = new ArrayList<>();
		for (Valuta v : valute) {
			ValutaDTO dto = new ValutaDTO(v);
			dto.setRacuniListFromSet(v.getRacuni());
			dto.setKursKaoOsnovnaListFromSet(v.getKursKaoOsnovnaValuta());
			dto.setKursKaoSporednaListFromSet(v.getKursKaoSporednaValuta());
			dto.setNaloziListFromSet(v.getNalozi());
			dtos.add(dto);
		}
		return dtos;
	}
	@Override
	public ValutaDTO getValutaDTO(Valuta v) {
		ValutaDTO dto = new ValutaDTO(v);
		dto.setRacuniListFromSet(v.getRacuni());
		dto.setKursKaoOsnovnaListFromSet(v.getKursKaoOsnovnaValuta());
		dto.setKursKaoSporednaListFromSet(v.getKursKaoSporednaValuta());
		dto.setNaloziListFromSet(v.getNalozi());
		return dto;
	}

}
