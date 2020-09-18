package demo.app.service;

import java.util.ArrayList;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.MedjubankarskiPrenos;
import demo.app.entity.Nalog;
import demo.app.entity.Racun;
import demo.app.entity.Valuta;
import demo.app.repository.MedjubankarskiPrenosRepository;
import demo.app.repository.ValutaRepository;
import demo.app.web.dto.MedjubankarskiPrenosDTO;
import demo.app.web.dto.NalogDTO;
import demo.app.web.dto.ValutaDTO;




@Service
@Transactional(readOnly = true)
public class MedjubankarskiPrenosService  implements MedjubankarskiPrenosServiceInterface, MedjubankarskiPrenosDTOServiceInterface {

	@Autowired
	MedjubankarskiPrenosRepository mpr;
	@Override
	public List<MedjubankarskiPrenosDTO> getAllDTOs(List<MedjubankarskiPrenos> md) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedjubankarskiPrenosDTO getMedjubankarskiPrenosDTO(MedjubankarskiPrenos m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedjubankarskiPrenos> findAll() {
		return mpr.findAll();
	}

	@Override
	public MedjubankarskiPrenos findOne(long id) {
		return mpr.findById(id).orElse(null);
	}

	@Override
	public MedjubankarskiPrenos save(MedjubankarskiPrenos medj) {
		return mpr.save(medj);
	}

	@Override
	public void remove(long id) {
		mpr.deleteById(id);
		
	}

	@Override
	public void saveMedjubankarskiPrenos(NalogDTO dto, Racun racunDuznika, Racun racunPrimaoca, Valuta valuta) {
		// TODO Auto-generated method stub
		
	}

	

}
