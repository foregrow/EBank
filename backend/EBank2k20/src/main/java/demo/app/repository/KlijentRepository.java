package demo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.entity.Klijent;

public interface KlijentRepository extends JpaRepository<Klijent, Long> {

	Klijent getByKorisnikId(long id);
	
	@Query("select distinct k from Klijent k, Racun r where r.banka.id = :id")
	List<Klijent> getByBankaId(long id);
}
