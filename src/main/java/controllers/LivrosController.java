package controllers;


import entities.LeitorEntities;
import entities.LivrosEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.LivrosRepository;
import services.LivrosService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/livros")
public class LivrosController {

    private final LivrosRepository livrosRepository;

    public LivrosController(LivrosRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }

    @PostMapping("/inserirNovo")
    public ResponseEntity<String> inserir(@RequestBody LivrosEntities livrosEntities) {
        try {
            livrosEntities.setDisponibilidade(true); // valor inicial
            livrosRepository.save(livrosEntities);
            return ResponseEntity.ok("Livro inserido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/excluirLivro/{id}")
    public ResponseEntity<String> excluir(@PathVariable UUID id_livro) {
        try {
            livrosRepository.deleteById(id_livro);
            return ResponseEntity.ok("Livro excluído com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscarLivro")
    public ResponseEntity<?> buscar(
            @RequestParam(required = false) UUID id_livro,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) String codigoISBN) {

        try {
            List<LivrosEntities> livros = livrosRepository.findAll().stream()
                    .filter(l -> (id_livro == null || l.getId().equals(id_livro)) &&
                            (nome == null || l.getNome().equalsIgnoreCase(nome)) &&
                            (autor == null || l.getAutor().equalsIgnoreCase(autor)) &&
                            (codigoISBN == null || l.getCodigoISBN().equalsIgnoreCase(codigoISBN)))
                    .toList();

            if (livros.isEmpty()) return ResponseEntity.notFound().build();

            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLivro(@PathVariable UUID id_livro) {
        var livro = livrosRepository.findById(id_livro);

        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        } else {
            return ResponseEntity.status(404).body("Livro não encontrado");
        }
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable UUID id, @RequestBody LivrosEntities livroInput) {
        try {
            LivrosEntities livro = livrosRepository.findById(id).orElse(null);
            if (livro == null) return ResponseEntity.notFound().build();

            livro.setDisponibilidade(livroInput.isDisponibilidade());
            livrosRepository.save(livro);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

