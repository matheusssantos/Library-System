package matheusssantos.librarysystem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import matheusssantos.librarysystem.services.CollectionService;

@RestController
@RequestMapping("/books")
public class BookController {
  private CollectionService collectionService;

  public BookController() {
    this.collectionService = new CollectionService();
  }

  @GetMapping("")
  public List<Book> getBooks() {
    return this.collectionService.findAllBooks();
  }

  @GetMapping("/titles")
  public List<String> getTitles() {
    return this.collectionService.findTitles();
  }

  @GetMapping("/authors")
  public List<String> getAuthors() {
    return this.collectionService.findAuthors();
  }

  @GetMapping("/filterbooks")
  public List<Book> findBooksByAuthor(@RequestParam("author") String author) {
    return this.collectionService.findBooks(author);
  }

  @GetMapping("/title/{title}")
  public ResponseEntity<Book> findBookByTitle(@PathVariable("title") String title) {
    Book res = this.collectionService.findBook(title);

    return ResponseEntity
      .status(HttpStatus.OK)
      .body(res);
  }

  @PostMapping("/create")
  public boolean create(@RequestBody Book body) {
    try {
      return this.collectionService.createBook(body);
    } catch (Error error) {
      return false;
    }
  }

  @GetMapping("/outdated/date/{date}")
  public List<Book> getOutdatedByDate(@PathVariable("date") int date) {
    return this.collectionService.findOutdatedBooks(date);
  }

  @PatchMapping("/{id}/update")
  public boolean update(@PathVariable("id") int id, @RequestBody Book body) {
    return this.collectionService.updateBook(id, body);
  }
}