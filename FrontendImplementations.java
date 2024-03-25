import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Class for all the method implementations for the FrontendInterface
 */
public class FrontendImplementations implements FrontendInterface {
  private Scanner scanner;
  private BackendInterface backend;

  // Initialize the backend and scanner
  public FrontendImplementations(BackendInterface backend, Scanner scanner) {
    this.backend = backend;
    this.scanner = scanner;
  }

  /**
   * Repeated gives the user an opportunity to issue new commands until they select Q to quit.
   */
  @Override
  public void runCommandLoop() {
    boolean running = true;
    while (running) {
      displayMainMenu();
      String command = scanner.nextLine().trim().toUpperCase();
      // filter through the commands that are valid to use
      switch (command) {
        case "R":
          readFile();
          break;
        case "G":
          getValues();
          break;
        case "F":
          setFilter();
          break;
        case "D":
          topFive();
          break;
        case "Q":
          System.out.println("Exiting iSongify."); // iSongify is not running anymore
          running = false;
          break;
        default:
          System.out.println("Enter your command."); // wait for user to enter a command
      }
    }

  }

  /**
   * Displays the menu of command options to the user.
   */
  @Override
  public void displayMainMenu() {
    System.out.println("====== Main Menu ======");
    System.out.println("[R]ead Data");
    System.out.println("[G]et Songs by Speed BPM");
    System.out.println("[F]ilter Old Songs");
    System.out.println("[D]isplay Five Most Danceable Songs");
    System.out.println("[Q]uit");
    System.out.print("Enter command: ");
  }

  /**
   * Provides text-based user interface and error handling for the [R]ead Data command.
   */
  @Override
  public void readFile() {
    // readData throws an exception when there is trouble finding/reading file
    try {
      System.out.println("Enter filename: ");
      String filename = scanner.nextLine().trim();
      backend.readData(filename);
      System.out.println("File read successfully."); // File was successfully read
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }
  }

  /**
   * Provides text-based user interface and error handling for the [G]et Songs by Speed BPM command.
   */
  @Override
  public void getValues() {
    System.out.println("Enter low BPM: ");
    int low = scanner.nextInt();
    System.out.println("Enter high BPM: ");
    int high = scanner.nextInt();
    scanner.nextLine();
    List<String> songs = backend.getRange(low, high);
    // enhanced for loop to print out all the songs in the specified range
    System.out.println("Songs by Specified Speed BPM: ");
    for (String song : songs) {
      System.out.println(song);
    }
  }

  /**
   * Provides text-based user interface and error handling for the [F]ilter Old Songs (by Max Year)
   * command.
   */
  @Override
  public void setFilter() {
    System.out.println("Enter maximum year for song filter:");
    int maxYear = scanner.nextInt();
    scanner.nextLine();
    List<String> filteredSongs = backend.filterOldSongs(maxYear);
    System.out.println("Filtered Songs: ");
    // enhanced for loop to print out all the songs in the specified filter
    for (String song : filteredSongs) {
      System.out.println(song);
    }
  }

  /**
   * Provides text-based user interface and error handling for the [D]isplay Five Most Danceable
   * command.
   */
  @Override
  public void topFive() {
    // fiveMostDanceable() throws exception when getRange() was not previously called
    try {
      List<String> danceableSongs = backend.fiveMostDanceable();
      System.out.println("Top 5 Danceable Songs: ");
      for (String song : danceableSongs) {
        System.out.println(song);
      }
    } catch (IllegalStateException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

}
