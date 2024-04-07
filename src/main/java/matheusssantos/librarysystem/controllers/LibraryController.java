package matheusssantos.librarysystem.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/library")
public class LibraryController {

  @GetMapping("")
  public String root() {
    return "Bem vindo ao sistema de biblioteca!";
  }
  
}
