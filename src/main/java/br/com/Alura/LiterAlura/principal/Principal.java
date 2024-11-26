package br.com.Alura.LiterAlura.principal;

import br.com.Alura.LiterAlura.DTO.ConsultaDTO;
import br.com.Alura.LiterAlura.DTO.LivroDTO;
import br.com.Alura.LiterAlura.model.Autor;
import br.com.Alura.LiterAlura.model.Livro;
import br.com.Alura.LiterAlura.repository.IAutorRepository;
import br.com.Alura.LiterAlura.repository.ILivroRepository;
import br.com.Alura.LiterAlura.service.ConsumoAPI;
import br.com.Alura.LiterAlura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Service
public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private final String BASE_URL = "https://gutendex.com/books/?search=";
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private IAutorRepository autorRepository;
    private ILivroRepository livroRepository;

    private List<Livro> listaDeLivros = new ArrayList<Livro>();
    private List<Autor> listaDeAutores = new ArrayList<Autor>();

    @Autowired
    public Principal(ILivroRepository livroRepository, IAutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }


    public void exibeMenu() {


        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    *** LiterAlura ***                    
                    
                    1- Buscar livro pelo título.
                    2- Listar livros registrados.
                    3- Listar autores registrados.
                    4- Listar autores vivos em um determinado ano.
                    5- Listar livros em um determinado idioma.
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }


    private LivroDTO getDadosLivro() {
        System.out.println("Insira o nome do livro que voce deseja procurar: ");
        var nomeLivro = leitura.nextLine();
        String json = consumo.obterDados(BASE_URL + nomeLivro.replace(" ", "+"));
        ConsultaDTO resultadoConsulta = conversor.obterDados(json, ConsultaDTO.class);

        if(resultadoConsulta.livros().isEmpty()){
            return null;
        }else
            return resultadoConsulta.livros().get(0);
    }

    private void buscarLivroPorTitulo() {
        LivroDTO livroBuscado = getDadosLivro();

        try {
            assert livroBuscado != null;
            Livro livro = new Livro(livroBuscado);
            livroRepository.save(livro);
            System.out.println("Livro salvo com sucesso!");
            System.out.println(livro);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Este livro já foi registrado !");
        } catch (NullPointerException e) {
            System.out.println("O livro informado não foi encontrado\n");
        }

    }


    private void listarLivros() {
        listaDeLivros = livroRepository.findAll();

        listaDeLivros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }


    private void listarAutores() {
        listaDeAutores = autorRepository.findAll();

        listaDeAutores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }


    private void listarAutoresVivos() {
        System.out.println("Digite o ano para a busca: ");
        var anoBusca = leitura.nextInt();  // Lê o ano informado pelo usuário
        try {
            List<Autor> autorBuscado = autorRepository.buscaPorAno(anoBusca);
            if (autorBuscado.isEmpty())
                System.out.println("Não há autores registrados.");
            else
                autorBuscado.forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("\nLista vazia!\n");
        }
    }

    private void listarLivrosPorIdioma() {
        var opcoesIdiomas = """
                Insira o idioma para realizar a busca: 
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                
                
                Digite o idioma para a busca:
                """;
        System.out.println(opcoesIdiomas);
        var idioma = leitura.nextLine();

        List<Livro> livros = livroRepository.findByIdiomaIgnoreCase(idioma);
        if (livros.isEmpty()) {
            System.out.println("Não há livros registrados para o idioma indicado.");
        } else {
            livros.forEach(System.out::println);
        }
    }


}
