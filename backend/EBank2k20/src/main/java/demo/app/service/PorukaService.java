package demo.app.service;




import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Poruka;
import demo.app.repository.PorukaRepository;
import demo.app.web.dto.PorukaDTO;




@Service
@Transactional(readOnly = true)
public class PorukaService  implements PorukaServiceInterface, PorukaDTOServiceInterface {

	@Autowired
	PorukaRepository pr;
	
	@Override
	public List<Poruka> findAll() {
		return pr.findAll();
	}
	@Override
	public Poruka findOne(long id) {
		return pr.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public Poruka save(Poruka por) {
		return pr.save(por);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		pr.deleteById(id);
	}
	@Override
	public List<PorukaDTO> getAllDTOs(List<Poruka> poruke) {
		return null;
	}
	@Override
	public PorukaDTO getPorukaDTO(Poruka poruka) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
