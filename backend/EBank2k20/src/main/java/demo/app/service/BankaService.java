package demo.app.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Banka;
import demo.app.repository.BankaRepository;


@Service
public class BankaService  implements BankaServiceInterface {

	@Autowired
	BankaRepository br;
	
	@Override
	public List<Banka> findAll() {
		return br.findAll();
	}
	@Override
	public Banka findOne(long id) {
		return br.findById(id).orElse(null);
	}
	@Override
	public Banka save(Banka kor) {
		return br.save(kor);
	}
	@Override
	public void remove(long id) {
		br.deleteById(id);
	}

}
