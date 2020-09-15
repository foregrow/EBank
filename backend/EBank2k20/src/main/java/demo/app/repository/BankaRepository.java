package demo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.entity.Banka;

public interface BankaRepository extends JpaRepository<Banka, Long> {

	@Query("select distinct b from Banka b, Racun r where r.banka.id = b.id AND r.klijent.id = :kid")
	List<Banka> bankeUKojimaKlijentImaRacun(long kid);
}
