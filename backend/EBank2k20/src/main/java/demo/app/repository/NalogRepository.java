package demo.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.entity.Nalog;

public interface NalogRepository extends JpaRepository<Nalog, Long> {

	/*@Query("select n from Nalog n, DnevnoStanje d where d.id = n.dnevnoStanje.id and (d.datumPrometa BETWEEN :odDatum AND :doDatum) and d.racun.id = :racunId")
	List<Nalog> naloziDnevnogStanja(Date odDatum, Date doDatum, long racunId);*/
}
