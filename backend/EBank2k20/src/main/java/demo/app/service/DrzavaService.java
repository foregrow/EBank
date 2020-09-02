package demo.app.service;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Drzava;
import demo.app.repository.DrzavaRepository;
import demo.app.web.dto.DrzavaDTO;




@Service
@Transactional(readOnly = true)
public class DrzavaService  implements DrzavaServiceInterface, DrzavaDTOServiceInterface {

	@Autowired
	DrzavaRepository dr;
	
	@Override
	public List<Drzava> findAll() {
		return dr.findAll();
	}
	@Override
	public Drzava findOne(long id) {
		return dr.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public Drzava save(Drzava d) {
		return dr.save(d);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		dr.deleteById(id);
	}
	@Override
	public List<DrzavaDTO> getAllDTOs(List<Drzava> drzave) {
		List<DrzavaDTO> dtos = new ArrayList<>();
		for (Drzava d : drzave) {
			DrzavaDTO dto = new DrzavaDTO(d);
			dto.setNaloziListFromSet(d.getNalozi());
			dto.setValuteListFromSet(d.getValute());
			dtos.add(dto);
		}
		return dtos;
	}
	@Override
	public DrzavaDTO getDrzavaDTO(Drzava d) {
		DrzavaDTO dto = new DrzavaDTO(d);
		dto.setNaloziListFromSet(d.getNalozi());
		dto.setValuteListFromSet(d.getValute());
		return dto;
	}

}
