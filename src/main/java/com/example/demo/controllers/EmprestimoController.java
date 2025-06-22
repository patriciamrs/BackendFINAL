package com.example.demo.controllers;

import com.example.demo.dtos.InserirEmprestimoDTO;
import com.example.demo.entities.EmprestimoEntities;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.EmprestimoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    @Operation(summary = "Listar todos os Emprestimos.", description = "Retorna todos os Emprestimos já cadastrados no sistema!")
    public List<EmprestimoEntities> getTodos() {
        return emprestimoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista os emprestimos por ID.")
    public ResponseEntity<Object> getPorId(@PathVariable UUID id) {
        Optional<EmprestimoEntities> emprestimo = emprestimoService.buscarPorId(id);

        if (emprestimo.isPresent()) {
            return ResponseEntity.ok(emprestimo.get());
        } else {
            return ResponseEntity.status(404).body("Empréstimo não encontrado");
        }
    }

    @PostMapping
    @Operation(summary = "Insere um novo emprestimo.")
    public ResponseEntity<?> criar(@RequestBody InserirEmprestimoDTO dto) {
        Object resultado = emprestimoService.criarEmprestimo(dto);
        if (resultado instanceof String) {
            return ResponseEntity.badRequest().body(resultado);
        } else {
            return ResponseEntity.status(201).body(resultado);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um emprestimo.")
    public ResponseEntity<?> deletar(@PathVariable UUID id) {
        boolean deletado = emprestimoService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body("Empréstimo não encontrado");
        }
    }

    @PatchMapping("/{id}/devolver")
    @Operation(summary = "Devolve o livro emprestado.")
    public ResponseEntity<?> devolver(@PathVariable UUID id) {
        boolean devolvido = emprestimoService.devolver(id);
        if (devolvido) {
            return ResponseEntity.ok("Livro devolvido");
        } else {
            return ResponseEntity.status(404).body("Empréstimo não encontrado");
        }
    }
}
