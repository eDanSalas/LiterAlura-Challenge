package com.edansama.Literatura_Alura.main;

import com.edansama.Literatura_Alura.dto.AuthorDTO;
import com.edansama.Literatura_Alura.dto.BookDTO;
import com.edansama.Literatura_Alura.dto.ResponseBooksDTO;
import com.edansama.Literatura_Alura.model.Author;
import com.edansama.Literatura_Alura.model.Book;
import com.edansama.Literatura_Alura.service.AuthorService;
import com.edansama.Literatura_Alura.service.BookService;
import com.edansama.Literatura_Alura.service.ConsumeAPI;
import com.edansama.Literatura_Alura.service.Deserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private ConsumeAPI consumeAPI;

    @Autowired
    private Deserialize deserialize;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    private final String BASE_URL = "https://gutendex.com/books/";

    public void showMenu() {
        int opt = 0;

        String menu = """
                ********** Spring Literatura **********
                
                1) Buscar un libro por titulo
                2) Listar libros registrados
                3) Listar autores registrados
                4) Listar autores vivos en un determinado año
                5) Listar libros por idioma
                6) Salir
                Digite la opcion...
                """;

        while (opt != 6) {
            System.out.print(menu);
            try {
                opt = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida!!!");
                System.out.println("Vuelve a intentarlo por favor");
                opt = 0;
                scanner.nextLine();
                continue;
            }

            switch (opt) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    bookService.showBooks().forEach(b -> {
                        System.out.println("***** Libro ******");
                        System.out.println("Titulo: " + b.getTitle());
                        System.out.println("Autor: " + (b.getAuthor() != null ? b.getAuthor().getName() : "Anonimo"));
                        System.out.println("Idioma: " + b.getLanguages());
                        System.out.println("Numero de descargas: " + b.getDownloadCount());
                    });
                    break;
                case 3:
                    authorService.showAuthors().forEach(a -> {
                        System.out.println("***** Autor ******");
                        System.out.println("Nombre: " + a.getName());
                        System.out.println("Fecha de nacimiento: " + a.getBirthYear());
                        System.out.println("Fecha de fallecimiento: " + (a.getDeathYear() != null ? a.getDeathYear() : "Desconocido"));
                    });
                    break;
                case 4:
                    System.out.print("Ingrese la fecha a consultar: ");
                    int date = scanner.nextInt();
                    scanner.nextLine();
                    List<Author> authorsLeft = authorService.searchAliveAuthors(date);

                    if (authorsLeft.isEmpty()) {
                        System.out.println("No se encontraron autores vivos en el año: " + date);
                        break;
                    }

                    authorsLeft.forEach(a -> {
                        System.out.println("Nombre: " + a.getName());
                        System.out.println("Fecha de nacimiento: " + a.getBirthYear());
                        System.out.println("Fecha de fallecimiento: " + (a.getDeathYear() != null ? a.getDeathYear() : "Desconocido"));
                        System.out.println("Cantidad de Libros listados: " + a.getBooks().size());
                    });
                    break;
                case 5:
                    System.out.println("Ingrese el idioma:");
                    System.out.println("es");
                    System.out.println("en");
                    System.out.println("fr");
                    System.out.println("pt");
                    String language = scanner.nextLine();
                    if ("es".equalsIgnoreCase(language) || "en".equalsIgnoreCase(language) || "fr".equalsIgnoreCase(language) || "pt".equalsIgnoreCase(language)) {
                        bookService.listByLangauge(language).forEach(b -> {
                            System.out.println("------LIBRO--------");
                            System.out.println("Título: " + b.getTitle());
                            System.out.println("Autor: " + (b.getAuthor() != null ? b.getAuthor().getName() : "Desconocido"));
                            System.out.println("Idioma: " + b.getLanguages());
                            System.out.println("Número de descargas: " + b.getDownloadCount());
                        });
                    } else {
                        System.out.println("Idioma no válido. Regresando al menú principal, intentalo otra vez!!!.");
                    }
                    break;
                case 6:
                    System.out.println("Hasta luego!!!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no disponible");
                    break;
            }

        }

    }

    private void searchByTitle() {
        System.out.print("Nombre del libro: ");
        String title = scanner.nextLine();
        try {
            String json = consumeAPI.getInfo(BASE_URL + "?search=" + title.replace(" ", "+"));
            ResponseBooksDTO responseBooksDTO = deserialize.getInformation(json, ResponseBooksDTO.class);
            List<BookDTO> bookDTOS = responseBooksDTO.getBooks();

            if (bookDTOS.isEmpty()) {
                System.out.println("Libro no encontrado");
                return;
            }

            BookDTO bookDTO = bookDTOS.stream()
                    .filter(b -> b.getTitle().toUpperCase().contains(title.toUpperCase()))
                    .map(b -> new BookDTO(b.getId(), b.getTitle(), b.getAuthors(), b.getLanguages(), b.getDownloadCount()))
                    .findFirst()
                    .orElse(null);

            if (bookDTO == null) {
                System.out.println("Libro no encontrado");
                return;
            }

            if (bookService.findBookByTitle(bookDTO.getTitle()).isPresent()) {
                System.out.println("***** Libro ya añadido *****");
                System.out.println("Busca otro");
                return;
            }

            Book book = new Book(bookDTO);

            Author author = new Author(bookDTO.getAuthors().getFirst());

            authorService.createAuthor(author);

            book.setAuthor(author);

            bookService.createBook(book);
            System.out.println("***** Libro Registrado ******");
            System.out.println(book);
        } catch(Exception e) {
            System.out.println("Error al obtener datos de la api, pila: " + e);
        }
    }
}