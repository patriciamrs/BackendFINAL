package controllers;

import entities.LeitorEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repositories.LeitorRepository;
import services.LeitorService;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/leitores")
public class LeitorController {
    private final LeitorService leitorService;

    public LeitorController(LeitorService leitorService){
        this.leitorService = leitorService;
    }

    @Autowired
    private LeitorRepository leitorRepository;

    @GetMapping
    public ResponseEntity<List<LeitorEntities>> listarTodos() {
        return ResponseEntity.ok(leitorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeitorEntities> buscarPorId(@PathVariable UUID id) {
        return leitorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LeitorEntities> criar(@RequestBody LeitorEntities leitor) {
        LeitorEntities salvo = leitorRepository.save(leitor);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeitorEntities> atualizar(@PathVariable UUID id, @RequestBody LeitorEntities dados) {
        return leitorRepository.findById(id)
                .map(leitorExistente -> {
                    leitorExistente.setNome(dados.getNome());
                    leitorExistente.setContato(dados.getContato());
                    leitorRepository.save(leitorExistente);
                    return ResponseEntity.ok(leitorExistente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (leitorRepository.existsById(id)) {
            leitorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

