package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.LeitorEntities;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeitorRepository extends JpaRepository<LeitorEntities, UUID> {
}