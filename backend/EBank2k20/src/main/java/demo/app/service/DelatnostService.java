package demo.app.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Delatnost;
import demo.app.repository.DelatnostRepository;
import demo.app.web.dto.DelatnostDTO;



@Service
@Transactional(readOnly = true)
public class DelatnostService  implements DelatnostServiceInterface {

	@Autowired
	DelatnostRepository dr;
	
	@Override
	public List<Delatnost> findAll() {
		return dr.findAll();
	}
	@Override
	public Delatnost findOne(long id) {
		return dr.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public Delatnost save(Delatnost ban) {
		return dr.save(ban);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		dr.deleteById(id);
	}
	@Override
	public List<DelatnostDTO> getAllDTOs(List<Delatnost> delatnosti) {

		List<DelatnostDTO> dtos = new ArrayList<>();
		for (Delatnost d : delatnosti) {
			DelatnostDTO dto = new DelatnostDTO(d);
			dto.setKlijentiListFromSet(d.getKlijenti());
			dtos.add(dto);
		}
		return dtos;
	}


}
