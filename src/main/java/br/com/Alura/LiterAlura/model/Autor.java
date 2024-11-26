package br.com.Alura.LiterAlura.model;

import br.com.Alura.LiterAlura.DTO.AutorDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    private Integer anoDeNascimento;

    private Integer anoDeMorte;


    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {
    }

    public Autor(AutorDTO autorDados) {
        this.nome = autorDados.nome();
        this.anoDeNascimento = autorDados.anoDeNascimento();
        this.anoDeMorte = autorDados.anoDeMorte();
    }

    @Override
    public String toString() {
        return "------ AUTOR -----\n" +
                "Nome: " + nome + "\n" +
                "Ano de nascimento: " + anoDeNascimento + "\n" +
                "Ano de Falecimento: " + anoDeMorte +  "\n" +
                "Livros: [" + livros.get(0).getTitulo() + "] \n"+
                 "-----------------"+ "\n" + "\n";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(Integer anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public Integer getAnoDeMorte() {
        return anoDeMorte;
    }

    public void setAnoDeMorte(Integer anoDeMorte) {
        this.anoDeMorte = anoDeMorte;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
