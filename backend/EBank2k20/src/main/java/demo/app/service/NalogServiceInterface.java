package demo.app.service;

import java.util.Date;
import java.util.List;



import demo.app.entity.Nalog;

public interface NalogServiceInterface {

	List<Nalog> findAll();
	
	Nalog findOne(long id);
	
	Nalog save(Nalog nalog);
	
	void remove(long id);

	List<Nalog> naloziDnevnogStanja(Date odDatum, Date doDatum, long racunId);

}