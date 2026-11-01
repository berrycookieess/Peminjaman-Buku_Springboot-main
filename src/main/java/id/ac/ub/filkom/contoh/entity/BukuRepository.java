package id.ac.ub.filkom.contoh.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BukuRepository extends JpaRepository<Buku, String> {

    List<Buku> findByJudulContainingIgnoreCase(String kata);
}
