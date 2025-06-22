package entities;
import  jakarta.persistence.*;
import java.util.UUID;

//id nome contato

@Entity
@Table(name = "Leitores")
public class LeitorEntities {
    @Id
    @GeneratedValue (strategy =GenerationType.UUID)
    private UUID id_leitor;
    private String nome;
    private String contato;

    public LeitorEntities() {

    }

    public LeitorEntities(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public UUID getId_leitor() {
        return id_leitor;
    }

    public void setId_leitor(UUID id_leitor) {
        this.id_leitor = id_leitor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
