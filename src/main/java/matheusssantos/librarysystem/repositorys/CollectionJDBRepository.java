package matheusssantos.librarysystem.repositorys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import matheusssantos.librarysystem.interfaces.ICollectionRepository;
import matheusssantos.librarysystem.models.Book;

@Repository
@Primary
public class CollectionJDBRepository implements ICollectionRepository {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public CollectionJDBRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

  public List<Book> findAllBooks() {
    try {
      List<Book> res = this.jdbcTemplate.query(
        "SELECT * FROM books", 
        (rs, rowNum) -> new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publication_year"))
      );
      return res;
    } catch (Error error) {
      return null;
    }
  }

  public List<String> findTitles() {
    try {
      List<String> res = this.jdbcTemplate.query(
        "SELECT * FROM books", 
        (rs, rowNum) -> new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publication_year")).getTitle()
      );
      return res;
    } catch (Error error) {
      return null;
    }
  }

  public List<String> findAuthors() {
    try {
      List<String> res = this.jdbcTemplate.query(
        "SELECT * FROM books", 
        (rs, rowNum) -> new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publication_year")).getAuthor()
      );
      return res;
    } catch (Error error) {
      return null;
    }
  }

  public List<Book> findBooks(String author) {
    try {
      List<Book> res = this.findAllBooks().stream()
        .filter(book -> book.getAuthor().equals(author))
        .toList();
      return res;
    } catch (Error error) {
      return null;
    }
  }

  public Book findBook(String title) {
    try {
      List<Book> res = this.findAllBooks().stream()
        .filter(book -> book.getTitle().equals(title))
        .toList();

      if (res.size() > 0) {
        return res.get(0);
      } else {
        return null;
      }
    } catch (Error error) {
      return null;
    }
  }

  public boolean createBook(Book data) {
    try {
      this.jdbcTemplate.update(
        "INSERT INTO books(title, author, publication_year) VALUES (?, ?, ?)", 
        data.getTitle(), data.getAuthor(), data.getYear()
      );
      return true;
    } catch (Error error) {
      return false;
    }
  }

  public boolean updateBook(int id, Book data) {
    try {
      this.jdbcTemplate.update(
        "UPDATE books SET title = ?, author = ?, publication_year = ? WHERE id = ?",
        data.getTitle(), data.getAuthor(), data.getYear(), id
      );
      return true;
    } catch (Error error) {
      return false;
    }
  }

  public boolean removeBook(int id) {
    try {
      String sql = "DELETE FROM books WHERE id = " + id;
      this.jdbcTemplate.execute(sql); 
      return true;
    } catch (Error error) {
      return false;
    }
  }
}
