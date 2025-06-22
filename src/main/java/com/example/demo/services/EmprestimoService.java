package com.example.demo.services;

import com.example.demo.dtos.InserirEmprestimoDTO;
import com.example.demo.entities.EmprestimoEntities;
import com.example.demo.entities.LeitorEntities;
import com.example.demo.entities.LivrosEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.EmprestimoRepository;
import com.example.demo.repositories.LeitorRepository;
import com.example.demo.repositories.LivrosRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivrosRepository livrosRepository;

    @Autowired
    private LeitorRepository leitorRepository;

    public List<EmprestimoEntities> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Optional<EmprestimoEntities> buscarPorId(UUID id) {
        return emprestimoRepository.findById(id);
    }

    public Object criarEmprestimo(InserirEmprestimoDTO dto) {
        LocalDateTime agora = LocalDateTime.now();
        if (agora.getDayOfWeek() == DayOfWeek.SATURDAY || agora.getDayOfWeek() == DayOfWeek.SUNDAY ||
                agora.getHour() < 8 || agora.getHour() >= 22) {
            return "Só criamos empréstimos de segunda à sexta, das 8h às 22h";
        }

        Optional<LivrosEntities> livroOpt = livrosRepository.findById(dto.getIdLivro());
        if (livroOpt.isEmpty()) return "Livro não encontrado";

        Optional<LeitorEntities> leitorOpt = leitorRepository.findById(dto.getIdLeitor());
        if (leitorOpt.isEmpty()) return "Leitor não encontrado";

        LivrosEntities livro = livroOpt.get();
        if (!livro.isDisponibilidade()) return "Livro já está emprestado";

        livro.setDisponibilidade(false);
        livrosRepository.save(livro);

        EmprestimoEntities novoEmprestimo = new EmprestimoEntities();
        novoEmprestimo.setLivro(livro);
        novoEmprestimo.setLeitor(leitorOpt.get());
        novoEmprestimo.setDataEmprestimo(dto.getDataEmprestimo());
        novoEmprestimo.setDataDevolucao(dto.getDataDevolucao());
        novoEmprestimo.setStatusEmprestimo("Emprestado");

        return emprestimoRepository.save(novoEmprestimo);
    }

    public boolean deletar(UUID id) {
        Optional<EmprestimoEntities> emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isPresent()) {
            LivrosEntities livro = emprestimo.get().getLivro();
            livro.setDisponibilidade(true);
            livrosRepository.save(livro);
            emprestimoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean devolver(UUID id) {
        Optional<EmprestimoEntities> emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isPresent()) {
            EmprestimoEntities e = emprestimo.get();
            e.setStatusEmprestimo("Devolvido");
            e.setDataDevolucao(LocalDate.now());
            emprestimoRepository.save(e);

            LivrosEntities livro = e.getLivro();
            livro.setDisponibilidade(true);
            livrosRepository.save(livro);
            return true;
        }
        return false;
    }
}
