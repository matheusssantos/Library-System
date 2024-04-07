package matheusssantos.librarysystem.controllers;

import org.springframework.web.bind.annotation.RestController;

import matheusssantos.librarysystem.models.Book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("library")
public class LibraryController {
  ArrayList<Book> books = new ArrayList<>();

  public LibraryController() {
    this.books.add(new Book("Teste de livro", "Autor teste"));
    this.books.add(new Book("Livro 1", "Matheus"));
  }

  @GetMapping("books")
  public List<Book> getBooks() {
    return this.books;
  }

  @GetMapping("authors")
  public List<String> getAuthors() {
    return this.books
      .stream()
      .map(book -> book.getAuthor())
      .toList();
  }
  
  @GetMapping("titles")
  public List<String> getTitles() {
    return this.books
      .stream()
      .map(book -> book.getTitle())
      .toList();
  }
}
