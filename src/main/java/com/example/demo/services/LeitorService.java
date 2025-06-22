package com.example.demo.services;

import com.example.demo.entities.LeitorEntities;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.LeitorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class LeitorService {
    private final LeitorRepository leitorRepository;

    public LeitorService(LeitorRepository leitorRepository) {
        this.leitorRepository = leitorRepository;
    }

    public LeitorEntities buscarPorId(UUID id) {
        return leitorRepository.findById(id).orElseThrow(()-> new RuntimeException("Leitor não encontrado!"));
    }

    public List<LeitorEntities> listarTodos() {
        return leitorRepository.findAll();
    }

}
