package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.ChaosGameObserver;

import java.util.ArrayList;
import java.util.List;

abstract class ChaosGameObservable {

      private final List<ChaosGameObserver> observers;

      protected ChaosGameObservable() {
            observers = new ArrayList<>();
      }

      public void addObserver(ChaosGameObserver observer) {
            observers.add(observer);
      }

      public void removeObserver(ChaosGameObserver observer) {
            observers.remove(observer);
      }

      public void notifyObservers(int[][] canvas) {
            observers.forEach(o -> o.update(canvas));
      }
}
