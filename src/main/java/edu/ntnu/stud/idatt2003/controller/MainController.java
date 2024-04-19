package edu.ntnu.stud.idatt2003.controller;

import edu.ntnu.stud.idatt2003.model.ChaosGame;
import edu.ntnu.stud.idatt2003.view.MainView;

public class MainController {

  private final MainView view;
  private final ChaosGame model;


  MainController(MainView view, ChaosGame model) {
    this.view = view;
    this.model = model;
    this.model.addObserver(view);
  }
}
