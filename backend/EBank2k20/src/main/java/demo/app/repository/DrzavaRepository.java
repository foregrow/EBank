package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.Drzava;

public interface DrzavaRepository extends JpaRepository<Drzava, Long>{

}
