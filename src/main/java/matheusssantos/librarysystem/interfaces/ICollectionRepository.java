package matheusssantos.librarysystem.interfaces;

import java.util.List;

import matheusssantos.librarysystem.models.Book;

public interface ICollectionRepository {
  List<Book> findAllBooks();
  List<String> findTitles();
  List<String> findAuthors();
  List<Book> findBooks(String author);
  Book findBook(String title);
  boolean createBook(Book data);
  boolean updateBook(int id, Book data);
  boolean removeBook(int id);
}
