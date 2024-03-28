package edu.ntnu.stud.idatt2003;

import edu.ntnu.stud.idatt2003.models.*;
import edu.ntnu.stud.idatt2003.models.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.models.transformations.JuliaTransform;
import edu.ntnu.stud.idatt2003.models.transformations.Transform2D;

import java.util.ArrayList;
import java.util.List;

public class ChaosGameApp {

  public ChaosGameDescription initializeChaosGameDescription() {
    Vector2D minCords = new Vector2D(-1.0, -1.0); // Replace with your actual values
    Vector2D maxCords = new Vector2D(1.0, 1.0); // Replace with your actual values

    // Define the transformations
    List<Transform2D> transforms = new ArrayList<>();
    transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(1, 0)));
    transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0.5)));
    // Add more transformations as needed

    // Optionally, if you have Julia transforms as well
    Complex juliaPoint = new Complex(-0.7, 0.27015); // Replace with your actual complex number
    int juliaSign = 1; // or -1, depending on your transformation
    transforms.add(new JuliaTransform(juliaPoint, juliaSign));

    // Create the description object
    return new ChaosGameDescription(minCords, maxCords, transforms);

  }
}
