package matheusssantos.librarysystem.services;

import java.util.List;

import matheusssantos.librarysystem.models.Book;

public class StatisticsService {
  private CollectionService collection;
  
  public StatisticsService() {
    this.collection = new CollectionService();
  }

  public int getTotalWorks(String author) {
    return this.collection.findBooks(author).size();
  }

  public int countRecentWorks(String author, int limitDate) {
    List<Book> aux = this.collection.findBooks(author);
    return aux.stream()
      .filter(book -> book.getYear() > limitDate)
      .toList()
      .size();
  }
} 
