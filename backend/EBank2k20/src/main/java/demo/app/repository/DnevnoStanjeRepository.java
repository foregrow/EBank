package demo.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.entity.DnevnoStanje;

public interface DnevnoStanjeRepository extends JpaRepository<DnevnoStanje, Long> {

	@Query("select d from DnevnoStanje d where d.racun.id = :rid and (d.datumPrometa BETWEEN :odDatum AND :doDatum)")
	List<DnevnoStanje> dnevnoStanjeZaRacunPoDatumu(Date odDatum, Date doDatum, long rid);
}
