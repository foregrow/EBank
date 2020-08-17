package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.Valuta;

public interface ValutaRepository extends JpaRepository<Valuta, Long> {

}
