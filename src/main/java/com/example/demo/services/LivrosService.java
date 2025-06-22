package com.example.demo.services;

import com.example.demo.entities.LivrosEntities;
import com.example.demo.repositories.LivrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.example.demo.dtos.InserirLivroDTO;


@Service
public class LivrosService {
    private final LivrosRepository livrosRepository;

    public LivrosService(LivrosRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }

    public List<LivrosEntities> listarTodos() {
        return livrosRepository.findAll();
    }

    public LivrosEntities criarLivro(InserirLivroDTO dto) {
        LivrosEntities livro = new LivrosEntities(
                dto.getNome(),
                dto.getAutor(),
                dto.getDescricao(),
                dto.getCodigoISBN(),
                dto.isDisponibilidade()
        );
        return livrosRepository.save(livro);
    }

    public LivrosEntities buscarPorId(UUID id) {
        return livrosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }
}
