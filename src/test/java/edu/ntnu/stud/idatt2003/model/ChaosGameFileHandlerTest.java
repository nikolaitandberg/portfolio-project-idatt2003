package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.exceptions.UnknownTransformationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChaosGameFileHandlerTest {

  private final String testFilePath = "textfileTest.txt";
  private final ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();


  @Test
  void testWriteToFile() throws IOException, UnknownTransformationException {
    ChaosGameDescription description = ChaosGameDescriptionFactory.get("Julia set");
    Path path = Path.of(testFilePath);

    fileHandler.writeToFile(description, testFilePath);
    List<String> writtenLines = Files.readAllLines(path);
    assertFalse(writtenLines.isEmpty());

    assertTrue(writtenLines.getFirst().contains("Julia"));
    assertTrue(writtenLines.get(1).contains("-1.6,-1.0"));
    assertTrue(writtenLines.get(2).contains("1.6,1.0"));
    assertTrue(writtenLines.get(3).contains("-0.74543,0.11301,-1"));
  }


  @Test
  void testReadFromFileDoesNotExist() {
    assertThrows(IllegalArgumentException.class, () -> fileHandler.readFromFile("nonexistent.txt"));
  }

  @Test
  @DisplayName("Test read from file with right format")
  void testReadFromFileRightFormat() throws IOException, UnknownTransformationException {
    ChaosGameDescription description = ChaosGameDescriptionFactory.get("Julia set");

    fileHandler.writeToFile(description, testFilePath);
    assertEquals(String.valueOf(description), String.valueOf(fileHandler.readFromFile(testFilePath)));
  }

  @AfterAll
  static void cleanUp() throws IOException {
    Files.deleteIfExists(Path.of("textfileTest.txt"));
  }
}