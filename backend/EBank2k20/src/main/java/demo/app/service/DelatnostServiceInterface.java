package demo.app.service;

import java.util.List;


import demo.app.entity.Delatnost;

public interface DelatnostServiceInterface {

	List<Delatnost> findAll();
	
	Delatnost findOne(long id);
	
	Delatnost save(Delatnost del);
	
	void remove(long id);

}
