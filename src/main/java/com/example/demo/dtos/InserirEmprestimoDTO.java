package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class InserirEmprestimoDTO {

    private UUID idLivro;
    private UUID idLeitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public InserirEmprestimoDTO() {
    }

    public InserirEmprestimoDTO(UUID idLivro, UUID idLeitor, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.idLivro = idLivro;
        this.idLeitor = idLeitor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public UUID getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(UUID idLivro) {
        this.idLivro = idLivro;
    }

    public UUID getIdLeitor() {
        return idLeitor;
    }

    public void setIdLeitor(UUID idLeitor) {
        this.idLeitor = idLeitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
