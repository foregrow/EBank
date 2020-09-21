package demo.app.service;

import java.util.List;

import demo.app.entity.Drzava;
import demo.app.entity.MedjubankarskiPrenos;
import demo.app.entity.Racun;
import demo.app.entity.Valuta;
import demo.app.web.dto.NalogDTO;



public interface MedjubankarskiPrenosServiceInterface {

	List<MedjubankarskiPrenos> findAll();
	
	MedjubankarskiPrenos findOne(long id);
	
	MedjubankarskiPrenos save(MedjubankarskiPrenos medj);
	
	void remove(long id);
	
	void saveMedjubankarskiPrenos(NalogDTO dto,Racun racunDuznika,Racun racunPrimaoca,Drzava drzava,Valuta valuta);

	
}
