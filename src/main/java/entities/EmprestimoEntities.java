package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "emprestimos")
public class EmprestimoEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private LivrosEntities livro;


    @ManyToOne
    @JoinColumn(name = "leitor_id", nullable = false)
    private LeitorEntities leitor;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String statusEmprestimo;


    public EmprestimoEntities() {
    }


    public EmprestimoEntities(LivrosEntities livro, LeitorEntities leitor,
                              LocalDate dataEmprestimo, LocalDate dataDevolucao,
                              String statusEmprestimo) {
        this.livro = livro;
        this.leitor = leitor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.statusEmprestimo = statusEmprestimo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LivrosEntities getLivro() {
        return livro;
    }

    public void setLivro(LivrosEntities livro) {
        this.livro = livro;
    }

    public LeitorEntities getLeitor() {
        return leitor;
    }

    public void setLeitor(LeitorEntities leitor) {
        this.leitor = leitor;
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

    public String getStatusEmprestimo() {
        return statusEmprestimo;
    }

    public void setStatusEmprestimo(String statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }
}