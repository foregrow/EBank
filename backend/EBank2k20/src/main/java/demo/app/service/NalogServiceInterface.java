package demo.app.service;


import java.util.Date;
import java.util.List;

import demo.app.entity.Drzava;
import demo.app.entity.Nalog;
import demo.app.entity.Racun;
import demo.app.entity.Valuta;
import demo.app.web.dto.NalogDTO;

public interface NalogServiceInterface {

	List<Nalog> findAll();
	
	Nalog findOne(long id);
	
	Nalog save(Nalog nalog);
	
	void remove(long id);

	List<Nalog> naloziDnevnogStanjaZaRacunPoDatumu(Date odDatum, Date doDatum, long rid);
	
	void saveTransakcija(NalogDTO dto,Racun racunDuznika,Racun racunPrimaoca, Drzava drzava, Valuta valuta, int param);
}
