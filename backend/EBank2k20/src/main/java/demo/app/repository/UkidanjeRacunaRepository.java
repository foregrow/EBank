package demo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.entity.UkidanjeRacuna;

public interface UkidanjeRacunaRepository extends JpaRepository<UkidanjeRacuna, Long> {

	@Query("select ur from UkidanjeRacuna ur, Racun r, Banka b where ur.racunZaUkidanje.id = r.id and r.banka.id = b.id and b.id = :id and ur.zavrseno = false")
	List<UkidanjeRacuna> findAllByBankaIdUToku(long id);
	
	@Query("select ur from UkidanjeRacuna ur, Racun r, Banka b where ur.racunZaUkidanje.id = r.id and r.banka.id = b.id and b.id = :id and ur.zavrseno = true")
	List<UkidanjeRacuna> findAllByBankaIdUkinuti(long id);
	
	@Query("select ur from UkidanjeRacuna ur, Racun r where ur.racunZaUkidanje.id = r.id and r.klijent.id = :id and ur.zavrseno = false")
	List<UkidanjeRacuna> findAllByKlijentIdWhereZavrsenoFalse(long id);
	
	UkidanjeRacuna findByRacunZaUkidanjeId(long id);
}
