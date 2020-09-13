package demo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.Racun;

public interface RacunRepository  extends JpaRepository<Racun, Long>{

	List<Racun> getByBankaIdAndOdobrenAndIzbrisan(long id, boolean odobren, boolean izbrisan);
	
	List<Racun> getByKlijentIdAndOdobrenAndIzbrisan(long id, boolean odobren, boolean izbrisan);
	
	List<Racun> getByBankaIdAndKlijentId(long bankaId, long kid);
	}
