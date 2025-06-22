package com.example.demo.controllers;

import com.example.demo.dtos.AtualizarLivroDTO;
import com.example.demo.dtos.InserirLivroDTO;
import com.example.demo.entities.LivrosEntities;
import com.example.demo.repositories.LivrosRepository;
import com.example.demo.services.LivrosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/livros")
public class LivrosController {
    private final LivrosService livrosService;

    public LivrosController(LivrosService livrosService) {
        this.livrosService = livrosService;
    }

    @Autowired
    private LivrosRepository livrosRepository;

    @GetMapping
    @Operation(summary = "Listar todos os livros.", description = "Retorna todos os livros já cadastrados no sistema!")
    public ResponseEntity<List<LivrosEntities>> listarTodos() {
        return ResponseEntity.ok(livrosService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca o livro pelo Id.")

    public ResponseEntity<LivrosEntities> buscarPorId(@PathVariable UUID id) {
        return livrosRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/InserirNovoLivro")
    @Operation(summary = "Insira um novo livro.", description = "Preencha os campos abaixo para cadastrar um novo livro.")
    public ResponseEntity<LivrosEntities> criar(@RequestBody InserirLivroDTO dtos) {
        LivrosEntities livro = new LivrosEntities(
                dtos.getNome(),
                dtos.getAutor(),
                dtos.getDescricao(),
                dtos.getCodigoISBN(),
                dtos.isDisponibilidade());
        LivrosEntities salvo = livrosRepository.save(livro);
        return ResponseEntity.status(201).body(salvo);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edita informações do livro.", description = "Responder com null caso não queira alterar um dos campos.")
    public ResponseEntity<LivrosEntities> atualizarLivro(@PathVariable UUID id, @RequestBody AtualizarLivroDTO dto) {
        return livrosRepository.findById(id)
                .map(livro -> {
                    if (dto.getNome() != null) livro.setNome(dto.getNome());
                    if (dto.getAutor() != null) livro.setAutor(dto.getAutor());
                    if (dto.getDescricao() != null) livro.setDescricao(dto.getDescricao());
                    if (dto.getCodigoISBN() != null) livro.setCodigoISBN(dto.getCodigoISBN());
                    if (dto.getDisponibilidade() != null) livro.setDisponibilidade(dto.getDisponibilidade());

                    livrosRepository.save(livro);
                    return ResponseEntity.ok(livro);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o livro pelo Id.")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (livrosRepository.existsById(id)) {
            livrosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/buscarLivros")
    @Operation(summary = "Buscar livros", description = "Busca por nome, autor e/ou código ISBN. Todos os filtros são opcionais.")
    public ResponseEntity<List<LivrosEntities>> buscar(
            @RequestParam(defaultValue = "") String nome,
            @RequestParam(defaultValue = "") String autor,
            @RequestParam(defaultValue = "") String codigoISBN) {

        List<LivrosEntities> resultado = livrosRepository
                .findByNomeContainingIgnoreCaseAndAutorContainingIgnoreCaseAndCodigoISBNContainingIgnoreCase(
                        nome, autor, codigoISBN
                );

        return ResponseEntity.ok(resultado);
    }


}




