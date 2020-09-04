package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.Klijent;

public interface KlijentRepository extends JpaRepository<Klijent, Long> {

	Klijent getByKorisnikId(long id);
}
