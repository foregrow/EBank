package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.Nalog;

public interface NalogRepository extends JpaRepository<Nalog, Long> {

}
