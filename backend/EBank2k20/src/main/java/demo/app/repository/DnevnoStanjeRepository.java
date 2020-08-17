package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.DnevnoStanje;

public interface DnevnoStanjeRepository extends JpaRepository<DnevnoStanje, Long> {

}
