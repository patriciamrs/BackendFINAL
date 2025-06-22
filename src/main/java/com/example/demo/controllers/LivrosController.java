package com.example.demo.controllers;

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

    @PostMapping("/api/InserirNovoLivro")
    @Operation(summary = "Insira um novo livro.", description = "Preencha os campos abaixo para cadastrar um novo livro.")
        public ResponseEntity<LivrosEntities> criar(@RequestBody InserirLivroDTO dtos) {
            LivrosEntities livro = new LivrosEntities(dtos.getNome(), dtos.getAutor());
            LivrosEntities salvo = livrosRepository.save(livro);
            return ResponseEntity.status(201).body(salvo);
        }
    }



