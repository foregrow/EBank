package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.KursnaLista;

public interface KursnaListaRepository extends JpaRepository<KursnaLista, Long> {

}
