package com.example.demo.services;

import com.example.demo.entities.LeitorEntities;
import com.example.demo.entities.LivrosEntities;
import com.example.demo.repositories.LivrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LivrosService {
    private final LivrosRepository livrosRepository;

    public LivrosService(LivrosRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }

    public LivrosEntities buscarPorId(UUID id) {
        return livrosRepository.findById(id).orElseThrow(()-> new RuntimeException("Livro não encontrado!"));
    }

    public List<LivrosEntities> listarTodos() {
        return livrosRepository.findAll();
    }

}
