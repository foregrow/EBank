package demo.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.DnevnoStanje;
import demo.app.repository.DnevnoStanjeRepository;


@Service
@Transactional(readOnly = true)
public class DnevnoStanjeService  implements DnevnoStanjeServiceInterface, DnevnoStanjeDTOServiceInterface {

	@Autowired
	DnevnoStanjeRepository dr;
	
	@Override
	public List<DnevnoStanje> findAll() {
		return dr.findAll();
	}
	@Override
	public DnevnoStanje findOne(long id) {
		return dr.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public DnevnoStanje save(DnevnoStanje ds) {
		return dr.save(ds);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		dr.deleteById(id);
	}
	@Override
	public List<DnevnoStanje> dnevnoStanjeZaRacunPoDatumu(Date odDatum, Date doDatum, long rid) {
		return dr.dnevnoStanjeZaRacunPoDatumu(odDatum, doDatum, rid);
	}
	

}
