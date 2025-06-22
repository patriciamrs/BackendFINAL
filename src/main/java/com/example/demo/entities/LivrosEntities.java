package com.example.demo.entities;
import jakarta.persistence.*;
import java.util.UUID;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Livros")

public class LivrosEntities {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id_livro;
    private String nome;
    private String autor;
    private String descricao;

    @Size(max = 13)
    @Column(name = "codigoISBN", length = 13)
    private String codigoISBN;
    private boolean disponibilidade = true;

    public LivrosEntities() {
    }

    public LivrosEntities(String nome, String autor, String descricao, String codigoISBN, boolean disponibilidade) {
        this.nome = nome;
        this.autor = autor;
        this.descricao = descricao;
        this.codigoISBN = codigoISBN;
        this.disponibilidade = disponibilidade;
    }

    public UUID getId() {
        return id_livro;
    }

    public void setId(UUID id) {
        this.id_livro = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoISBN() {
        return codigoISBN;
    }

    public void setCodigoISBN(String codigoISBN) {
        this.codigoISBN = codigoISBN;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

}

