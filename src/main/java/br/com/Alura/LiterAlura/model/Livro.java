package br.com.Alura.LiterAlura.model;

import br.com.Alura.LiterAlura.DTO.LivroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "Livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Long quantidadeDownload;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro() {
    }

    public Livro(LivroDTO livroDados) {
        this.titulo = livroDados.titulo();
        this.idioma = livroDados.idioma().get(0);
        this.quantidadeDownload = livroDados.quantidadeDownload();
        this.autor = livroDados.autores().stream().map(Autor::new).toList().get(0);
    }

    public void adicionarAutor(Autor autor) {
        autor.getLivros().add(this);
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getQuantidadeDownload() {
        return quantidadeDownload;
    }

    public void setQuantidadeDownload(Long quantidadeDownload) {
        this.quantidadeDownload = quantidadeDownload;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }


    @Override
    public String toString() {
        return "------ LIVRO -----\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + autor.getNome() + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de downloads: " + quantidadeDownload+ "\n"+
                "-----------------"+ "\n" + "\n";
    }
}
