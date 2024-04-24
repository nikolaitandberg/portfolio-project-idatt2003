package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.ChaosGameObserver;

public interface ChaosGameObservable {

      void addObserver(ChaosGameObserver observer);

      void removeObserver(ChaosGameObserver observer);

      void notifyObservers();
}
