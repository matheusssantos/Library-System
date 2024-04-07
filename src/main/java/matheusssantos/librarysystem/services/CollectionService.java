package matheusssantos.librarysystem.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import matheusssantos.librarysystem.interfaces.ICollectionRepository;
import matheusssantos.librarysystem.models.Book;

@Repository
public class CollectionService implements ICollectionRepository{
  ArrayList<Book> books = new ArrayList<>();

  public CollectionService() {
    this.books.add(new Book("Teste de livro", "Autor teste", 2002));
    this.books.add(new Book("Livro 1", "Matheus", 1999));
  }

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

    Book book = new Book(data.getTitle(), data.getAuthor(), data.getYear());
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
