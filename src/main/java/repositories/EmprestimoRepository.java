package repositories;

import entities.EmprestimoEntities;
import entities.LeitorEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoEntities, UUID> {
}
