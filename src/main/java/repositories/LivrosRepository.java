package repositories;

import entities.LivrosEntities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivrosRepository extends JpaRepository<LivrosEntities, UUID> {

    // Buscar por nome (case insensitive)
    List<LivrosEntities> findByNomeContainingIgnoreCase(String nome);

    // Buscar por autor
    List<LivrosEntities> findByAutorContainingIgnoreCase(String autor);

    // Buscar por disponibilidade
    List<LivrosEntities> findByDisponibilidade(boolean disponibilidade);

    // Buscar por ISBN exato
    List<LivrosEntities> findByCodigoISBN(String codigoISBN);
}
