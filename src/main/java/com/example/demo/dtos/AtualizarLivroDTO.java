package com.example.demo.dtos;


public class AtualizarLivroDTO {
    private String nome;
    private String autor;
    private String descricao;
    private String codigoISBN;
    private Boolean disponibilidade; // com B maiúsculo para permitir null

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

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }
    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}

