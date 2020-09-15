package demo.app.repository;

import java.util.Date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.entity.DnevnoStanje;

public interface DnevnoStanjeRepository extends JpaRepository<DnevnoStanje, Long> {

	@Query("select d from DnevnoStanje d where d.racun.id = :rid and d.datumPrometa = date(:datum)")
	List<DnevnoStanje> dnevnoStanjeZaDatum(Date datum, long rid);
	
	@Query("select count(d.id) from DnevnoStanje d")
	int countDnevnoStanje();
}
