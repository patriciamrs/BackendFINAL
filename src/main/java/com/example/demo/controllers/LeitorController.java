package com.example.demo.controllers;

import com.example.demo.dtos.AtualizarLeitorDTO;
import com.example.demo.dtos.InserirLeitorDTO;
import com.example.demo.entities.LeitorEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositories.LeitorRepository;
import com.example.demo.services.LeitorService;
import org.springframework.http.ResponseEntity;



import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/leitores")
public class LeitorController {
    private final LeitorService leitorService;

    public LeitorController(LeitorService leitorService){
        this.leitorService = leitorService;
    }

    @Autowired
    private LeitorRepository leitorRepository;

    @GetMapping
    @Operation(summary = "Listar todos os leitores.", description = "Retorna todos os leitores já cadastrados no sistema!")
    public ResponseEntity<List<LeitorEntities>> listarTodos() {
        return ResponseEntity.ok(leitorService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca o leitor pelo Id.")

    public ResponseEntity<LeitorEntities> buscarPorId(@PathVariable UUID id) {
        return leitorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/inserirNovo")
    @Operation(summary = "Inserir novo leitor.",  description = "Insira os campos abaixo:")
    public ResponseEntity<LeitorEntities> criar(@RequestBody InserirLeitorDTO dto) {
        LeitorEntities leitor = new LeitorEntities(dto.getNome(), dto.getContato());
        LeitorEntities salvo = leitorRepository.save(leitor);
        return ResponseEntity.status(201).body(salvo);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edita informações do leitor.", description = "Responder com null caso não queira alterar um dos campos.")
    public ResponseEntity<LeitorEntities> atualizar(@PathVariable UUID id, @RequestBody AtualizarLeitorDTO dto) {
        return leitorRepository.findById(id)
                .map(leitor -> {
                    if (dto.getNome() != null) {
                        leitor.setNome(dto.getNome());
                    }
                    if (dto.getContato() != null) {
                        leitor.setContato(dto.getContato());
                    }
                    leitorRepository.save(leitor);
                    return ResponseEntity.ok(leitor);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o leitor pelo Id.")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (leitorRepository.existsById(id)) {
            leitorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

