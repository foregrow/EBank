package demo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.entity.Klijent;

public interface KlijentRepository extends JpaRepository<Klijent, Long> {

	Klijent getByKorisnikId(long id);
	
	@Query("select distinct k from Klijent k, Racun r where r.banka.id = :id and k.odobren = true")
	List<Klijent> getOdobreniByBankaId(long id);
	
	@Query("select k from Klijent k, Racun r, Banka b where b.id = :id and r.klijent.id = k.id and k.odobren = false and b.id = r.banka.id")
	List<Klijent> getNeodobreniByBankaId(long id);
}
