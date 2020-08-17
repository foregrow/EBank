package demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entity.Banka;

public interface BankaRepository extends JpaRepository<Banka, Long> {

}
