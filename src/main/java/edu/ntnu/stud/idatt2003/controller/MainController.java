package edu.ntnu.stud.idatt2003.controller;

import edu.ntnu.stud.idatt2003.model.ChaosGame;
import edu.ntnu.stud.idatt2003.view.MainView;

public class MainController {

  MainView view;
  ChaosGame chaosGame;


  MainController(MainView view) {
    this.view = view;
  }

  public void addObservers() {

  }
}
