package matheusssantos.librarysystem.models;

public class Book {
  private Integer id;
  private String title;
  private String author;
  
  public Book(String title, String author) {
    this.id = 1;
    this.title = title;
    this.author = author;
  }
  
  public String getAuthor() {
    return this.author;
  }

  public String getTitle() {
    return this.title;
  }

  public Integer getId() {
    return this.id;
  }
}
