package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.exceptions.UnknownTransformationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ChaosGameDescriptionFactory
 */
class ChaosGameDescriptionFactoryTest {

  @Test
  @DisplayName("Test get method with Julia set")
  void testGetJuliaSet() throws UnknownTransformationException {
    ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Julia set");
    assertNotNull(chaosGameDescription);
  }

  @Test
  @DisplayName("Test get method with unknown transformation")
  void testGetUnknownTransformation() {
    assertThrows(UnknownTransformationException.class, () -> ChaosGameDescriptionFactory.get("Unknown transformation"));
  }
}