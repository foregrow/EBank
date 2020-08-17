package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.Poruka;

public interface PorukaRepository extends JpaRepository<Poruka, Long> {

}
