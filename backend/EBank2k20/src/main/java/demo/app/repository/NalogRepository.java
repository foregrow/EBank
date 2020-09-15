package demo.app.repository;

import java.util.Date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.entity.Nalog;

public interface NalogRepository extends JpaRepository<Nalog, Long> {

	@Query("SELECT n FROM Nalog n, DnevnoStanje d WHERE (n.racunDuznika.id = :rid OR n.racunPrimaoca.id = :rid) AND "
			+ "(date(d.datumPrometa) BETWEEN :odDatum AND :doDatum AND date(n.datumPrijema) BETWEEN :odDatum AND :doDatum) AND n.dnevnoStanje.id = d.id AND d.racun.id = :rid ORDER BY n.id DESC")
	List<Nalog> naloziDnevnogStanjaZaRacunPoDatumu(Date odDatum, Date doDatum, long rid);
}
