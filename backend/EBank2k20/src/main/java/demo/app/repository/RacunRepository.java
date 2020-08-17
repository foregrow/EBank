package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.Racun;

public interface RacunRepository  extends JpaRepository<Racun, Long>{

}
