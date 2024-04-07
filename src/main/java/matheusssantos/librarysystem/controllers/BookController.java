package matheusssantos.librarysystem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import matheusssantos.librarysystem.models.Book;

@RestController
@RequestMapping("/books")
public class BookController {
  ArrayList<Book> books = new ArrayList<>();

  public BookController() {
    this.books.add(new Book("Teste de livro", "Autor teste", 2002));
    this.books.add(new Book("Livro 1", "Matheus", 1999));
  }

  @GetMapping("")
  public List<Book> getBooks() {
    return this.books;
  }

  @GetMapping("/titles")
  public List<String> getTitles() {
    return this.books.stream()
      .map(book -> book.getTitle())
      .toList();
  }

  @GetMapping("/authors")
  public List<String> getAuthors() {
    return this.books.stream()
      .map(book -> book.getAuthor())
      .toList();
  }

  @GetMapping("/filterbooks")
  public List<Book> findBooksByAuthor(@RequestParam("author") String author) {
    return this.books.stream()
      .filter(book -> book.getAuthor().equals(author))
      .toList();
  }

  @GetMapping("/title/{title}")
  public ResponseEntity<Book> findBooksByTitle(@PathVariable("title") String title) {
    final Book res = this.books.stream()
      .filter(book -> book.getTitle().equals(title))
      .findFirst()
      .orElse(null);

    return ResponseEntity
      .status(HttpStatus.OK)
      .body(res);
  }

  @PostMapping("/create")
  public boolean create(@RequestBody Book body) {
    try {
      this.books.add(body);
      return true;
    } catch (Error error) {
      return false;
    }
  }

  @GetMapping("/outdated/date/{date}")
  public List<Book> getOutdatedByDate(@PathVariable("date") int date) {
    return this.books.stream()
      .filter(book -> book.getYear() < date)
      .toList();
  }

  @PatchMapping("/{id}/update")
  public Book update(@PathVariable("id") int id, @RequestBody Book body) {
    for (int i = 0; i < this.books.size(); i++) {
      Book aux = this.books.get(i);
      if (aux.getId().equals(id)) {
        this.books.set(i, body);
        return body;
      }
    }
    
    return null;
  }
}