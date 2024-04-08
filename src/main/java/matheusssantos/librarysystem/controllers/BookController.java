package matheusssantos.librarysystem.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import matheusssantos.librarysystem.models.Book;
import matheusssantos.librarysystem.repositorys.CollectionJDBRepository;
import matheusssantos.librarysystem.services.CollectionService;

@RestController
@RequestMapping("/books")
public class BookController {
  private CollectionService collectionService;
  private CollectionJDBRepository collectionRepository;

  public BookController(CollectionJDBRepository collectionRepository) {
    this.collectionRepository = collectionRepository;
  }

  @GetMapping("")
  public List<Book> getBooks() {
    return this.collectionRepository.findAllBooks();
  }

  @GetMapping("/titles")
  public List<String> getTitles() {
    return this.collectionRepository.findTitles();
  }

  @GetMapping("/authors")
  public List<String> getAuthors() {
    return this.collectionRepository.findAuthors();
  }

  @GetMapping("/filterbooks")
  public List<Book> findBooksByAuthor(@RequestParam("author") String author) {
    return this.collectionRepository.findBooks(author);
  }

  @GetMapping("/title/{title}")
  public ResponseEntity<Book> findBookByTitle(@PathVariable("title") String title) {
    Book res = this.collectionRepository.findBook(title);

    return ResponseEntity
      .status(HttpStatus.OK)
      .body(res);
  }

  @PostMapping("/create")
  public boolean create(@RequestBody Book body) {
    return this.collectionRepository.createBook(body);
  }

  @PatchMapping("/{id}/update")
  public boolean update(@PathVariable("id") int id, @RequestBody Book body) {
    return this.collectionRepository.updateBook(id, body);
  }

  @DeleteMapping("/{id}/delete")
  public boolean remove(@PathVariable("id") int id) {
    return this.collectionRepository.removeBook(id);
  }
}