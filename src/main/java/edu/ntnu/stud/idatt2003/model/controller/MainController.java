package edu.ntnu.stud.idatt2003.model.controller;

import edu.ntnu.stud.idatt2003.model.model.ChaosGame;
import edu.ntnu.stud.idatt2003.model.model.ChaosGameDescription;
import edu.ntnu.stud.idatt2003.model.model.ChaosGameDescriptionFactory;
import edu.ntnu.stud.idatt2003.model.view.MainView;

public class MainController {

  MainView view;
  ChaosGame chaosGame;


  MainController(MainView view) {
    this.view = view;
  }

  public void addObservers() {

  }
}
