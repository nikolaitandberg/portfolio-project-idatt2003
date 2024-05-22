package edu.ntnu.stud.idatt2003.model.transformations;

import edu.ntnu.stud.idatt2003.model.math.Vector2D;

/**
 * An interface for 2D transformations.
 *
 * @version 1.0
 * @since 2024-02-22
 */
public interface Transform2D {

  Vector2D transform(Vector2D point);

}
