package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import demo.app.entity.Delatnost;

public interface DelatnostRepository extends JpaRepository<Delatnost, Long> {

}
