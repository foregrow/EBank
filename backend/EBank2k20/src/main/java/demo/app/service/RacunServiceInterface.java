package demo.app.service;

import java.util.List;


import demo.app.entity.Racun;



public interface RacunServiceInterface {

	List<Racun> findAll();
	
	Racun findOne(long id);
	
	Racun save(Racun r);
	
	void remove(Racun racun);
	
	List<Racun> getByBankaIdAndOdobrenAndIzbrisan(long id, boolean odobren, boolean izbrisan);
	
	List<Racun> getByKlijentIdAndOdobrenAndIzbrisan(long id, boolean odobren, boolean izbrisan);
	
	List<Racun> getByBankaIdAndKlijentId(long bankaId, long kid);

	Racun getByBrojRacuna(String brojRacuna);
}
