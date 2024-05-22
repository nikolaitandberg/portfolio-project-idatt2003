package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.exceptions.UnknownTransformationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChaosGameTest {

  @Test
  void testConstructor() throws UnknownTransformationException {

    ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Julia set");

    ChaosGame chaosGame = new ChaosGame(chaosGameDescription, 100, 100);

    assertEquals(ChaosGame.class, chaosGame.getClass());
  }

  @Test
  @DisplayName("Test getCanvas")
  void testGetCanvas() throws UnknownTransformationException {

    ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Julia set");

    ChaosGame chaosGame = new ChaosGame(chaosGameDescription, 100, 100);

    assertEquals(ChaosCanvas.class, chaosGame.getCanvas().getClass());
  }

  @Test
  @DisplayName("Test runSteps")
  void testRunSteps() throws UnknownTransformationException {

    ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Julia set");

    ChaosGame chaosGame = new ChaosGame(chaosGameDescription, 100, 100);

    chaosGame.runSteps(10000);

    int whitePixels = 0;
    for (int i = 0; i < chaosGame.getCanvas().getCanvas().length; i++) {
      for (int j = 0; j < chaosGame.getCanvas().getCanvas()[0].length; j++) {
        if (chaosGame.getCanvas().getCanvas()[i][j] == 0) {
          whitePixels++;
        }
      }
    }

    assertTrue(whitePixels > 0);
  }

}