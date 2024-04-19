package edu.ntnu.stud.idatt2003.controller;

import edu.ntnu.stud.idatt2003.math.Complex;
import edu.ntnu.stud.idatt2003.math.Matrix2x2;
import edu.ntnu.stud.idatt2003.math.Vector2D;
import edu.ntnu.stud.idatt2003.model.ChaosGameDescription;
import edu.ntnu.stud.idatt2003.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.transformations.JuliaTransform;
import edu.ntnu.stud.idatt2003.transformations.Transform2D;
import edu.ntnu.stud.idatt2003.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainController {

  private final MainView view;


  public MainController(MainView view) {
    this.view = view;
  }

  public ChaosGameDescription createJuliaDescription() {

    List<String[]> juliaBoxValues = view.getJuliaBoxValues();
    String[] minMaxValues = view.getMinMaxCoords();

    List<Transform2D> transforms = new ArrayList<>();

    for (String[] juliaBoxValue : juliaBoxValues) {
      String[] values = juliaBoxValue;
      Complex c = new Complex(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
      int sign = Integer.parseInt(values[2]);
      transforms.add(new JuliaTransform(c, sign));
    }


    Vector2D minCords = new Vector2D(Double.parseDouble(minMaxValues[0]), Double.parseDouble(minMaxValues[1]));
    Vector2D maxCords = new Vector2D(Double.parseDouble(minMaxValues[2]), Double.parseDouble(minMaxValues[3]));

    return new ChaosGameDescription(minCords, maxCords, transforms);
  }

  public ChaosGameDescription createAffineDescription() {
    List<String[]> affineBoxValues = view.getAffineBoxValues();
    String[] minMaxValues = view.getMinMaxCoords();

    List<Transform2D> transforms = new ArrayList<>();

    for (String[] affineBoxValue : affineBoxValues) {
      String[] values = affineBoxValue;
      double a = Double.parseDouble(values[0]);
      double b = Double.parseDouble(values[1]);
      double c = Double.parseDouble(values[2]);
      double d = Double.parseDouble(values[3]);
      double e = Double.parseDouble(values[4]);
      double f = Double.parseDouble(values[5]);
      transforms.add(new AffineTransform2D(new Matrix2x2(a, b, c, d), new Vector2D(e, f)));
    }

    Vector2D minCords = new Vector2D(Double.parseDouble(minMaxValues[0]), Double.parseDouble(minMaxValues[1]));
    Vector2D maxCords = new Vector2D(Double.parseDouble(minMaxValues[2]), Double.parseDouble(minMaxValues[3]));

    return new ChaosGameDescription(minCords, maxCords, transforms);
  }

  public void printDescription(ChaosGameDescription description) {
    System.out.println("Min coordinates: " + description.getMinCords());
    System.out.println("Max coordinates: " + description.getMaxCords());
    System.out.println("Transformations: ");
    for (Transform2D transform : description.getTransforms()) {
      System.out.println(transform);
    }
  }

  public void handleButtonClick() {
    System.out.println("Button clicked");
  }




}
