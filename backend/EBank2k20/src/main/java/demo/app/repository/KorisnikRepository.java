package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

}
