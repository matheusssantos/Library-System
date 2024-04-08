package matheusssantos.librarysystem.models;

public class Book {
  private Integer id;
  private String title;
  private String author;
  private Integer year;
  
  public Book(Integer id, String title, String author, Integer year) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.year = year;
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

  public Integer getYear() {
    return this.year;
  }
}
