package matheusssantos.librarysystem.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import matheusssantos.librarysystem.interfaces.ICollectionRepository;
import matheusssantos.librarysystem.models.Book;

@Repository
public class CollectionService implements ICollectionRepository {
  ArrayList<Book> books = new ArrayList<>();

  public CollectionService() {}

  public List<Book> findAllBooks() {
    return this.books;
  } 

  public List<String> findTitles() {
    return this.books.stream()
      .map(book -> book.getTitle())
      .toList();
  }

  public List<String> findAuthors() {
    return this.books.stream()
      .map(book -> book.getAuthor())
      .toList();
  }

  public List<Book> findBooks(String author) {
    return this.books.stream()
      .filter(book -> book.getAuthor().equals(author))
      .toList();
  }

  public Book findBook(String title) {
    final Book aux = this.books.stream()
      .filter(book -> book.getTitle().equals(title))
      .findFirst()
      .orElse(null);

    return aux;
  }

  public boolean createBook(Book data) {
    if (data.getYear() > 2024) return false;

    Book book = new Book(0, data.getTitle(), data.getAuthor(), data.getYear());
    this.books.add(book);

    return true;
  }

  public List<Book> findOutdatedBooks(int date) {
    return this.books.stream()
      .filter(book -> book.getYear() < date)
      .toList();
  }

  public boolean updateBook(int id, Book data) {
    for (int i = 0; i < this.books.size(); i++) {
      Book aux = this.books.get(i);
      if (aux.getId().equals(id)) {
        this.books.set(i, data);
        return true;
      }
    }

    return false;
  }

  public boolean removeBook(int id) {
    List<Book> aux = this.books.stream()
      .filter(book -> book.getId() == id)
      .toList();  
    
    return aux.removeAll(aux);
  }
}
