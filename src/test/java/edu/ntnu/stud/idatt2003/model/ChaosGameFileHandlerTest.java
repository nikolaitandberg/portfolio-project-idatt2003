package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.math.Complex;
import edu.ntnu.stud.idatt2003.exceptions.UnknownTransformationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChaosGameFileHandlerTest {

  private final String testFilePath = "C:\\Users\\jerry\\OneDrive\\Dokumenter\\Programmering\\Prog2\\chaos-game-group34\\textfileTest.txt";
  private ChaosGameFileHandler fileHandler;


  private static void clearFile(String path) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
    }
  }
  @BeforeEach
  void setUp() {
    fileHandler = new ChaosGameFileHandler();

  }
  @Test
  void testWriteToFile() throws IOException {
    ChaosGameDescription description = ChaosGameDescriptionFactory.createJuliaSet(new Complex(-0.74543, 0.11301));
    Path path = Path.of(testFilePath);
    clearFile(testFilePath);

    List<String> checkFileIsEmpty = Files.readAllLines(path);
    assertTrue(checkFileIsEmpty.isEmpty());

    fileHandler.writeToFile(description, testFilePath);
    List<String> writtenLines = Files.readAllLines(path);
    assertFalse(writtenLines.isEmpty());

    assertTrue(writtenLines.getFirst().contains("Julia"));
    assertTrue(writtenLines.get(1).contains("-1.6,-1.0"));
    assertTrue(writtenLines.get(2).contains("1.6,1.0"));
    assertTrue(writtenLines.get(3).contains("-0.74543,0.11301,1"));
  }


  @Test
  void testReadFromFileDoesNotExist() {
    assertThrows(IllegalArgumentException.class, () -> fileHandler.readFromFile("nonexistent.txt"));
  }

  @Test
  @DisplayName("Test read from file with right format")
  void testReadFromFileRightFormat() throws IOException, UnknownTransformationException {
    ChaosGameDescription description = ChaosGameDescriptionFactory.createJuliaSet(new Complex(-0.74543, 0.11301));
    clearFile(testFilePath);

    fileHandler.writeToFile(description, testFilePath);
    assertEquals(String.valueOf(description), String.valueOf(fileHandler.readFromFile(testFilePath)));
  }
}