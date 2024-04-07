package matheusssantos.librarysystem.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import matheusssantos.librarysystem.services.StatisticsService;

@RestController
@RequestMapping("/library")
public class LibraryController {
  private StatisticsService statisticsService;

  public LibraryController() {
    this.statisticsService = new StatisticsService();
  }

  @GetMapping("")
  public String root() {
    return "Bem vindo ao sistema de biblioteca!";
  }
  
  @GetMapping("/statistics/author/{author}/total-works")
  public int getAuthorStatistics1(@PathVariable("author") String author) {
    return this.statisticsService.getTotalWorks(author);
  }

  @GetMapping("/statistics/author/{author}/recent-works/limit-date/{date}")
  public int calculateAuthorStatistics(
    @PathVariable("author") String author, 
    @PathVariable("date") int date
  ) {
    return this.statisticsService.countRecentWorks(author, date);
  }
  
}
