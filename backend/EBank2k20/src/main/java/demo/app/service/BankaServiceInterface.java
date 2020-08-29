package demo.app.service;

import java.util.List;

import demo.app.entity.Banka;

public interface BankaServiceInterface {

	List<Banka> findAll();
	
	Banka findOne(long id);
	
	Banka save(Banka bank);
	
	void remove(long id);
	
}
