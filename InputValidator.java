import ecs100.*;

/**
 * The InputValidator class checks any input to provide a seamless user experience.
 * All methods are static, so the GUI class doesn't need to make an instance of this class.
 * For this same reason, the constructor for InputValidator has also been removed 
 * (as there wasn't anything in it anyways).
 * Both reasons for the price/string range are provided in the README.TXT file.
 * Functions include: checking a double value, a String name, and a String image path.
 *
 * @author (HJF)
 * @version (2/6/25)
 */
public class InputValidator {
  // instance variables
  private static final double MAX_VALUE = 260000.0; // see README.TXT file
  private static final double MIN_VALUE = 0;
  private static final int MIN_STRING_LENGTH = 3;   // see README.TXT file
  private static final int MAX_STRING_LENGTH = 12;
    
  /**
   * Check the validitiy of a String input (Pokemon's name) from the user.
   * - Input must be within the 3 - 12 character range (inclusive).
   * - Loops until a valid name is entered.
   *
   * @return capitalised and trimmed name of the card.
   */
  public static String checkString() {
    // variables
    String name;
        
    // keep asking until a valid name is entered
    do {
      // whitespace/case-insensitive comparison
      name = UI.askString("Enter the Pokemon's name: ").trim().toUpperCase();
        
      if (name == null || name.isEmpty()) {
        UI.println("Hey enter something here please");
        UI.println();
      } else if (name.length() > MAX_STRING_LENGTH) {
        UI.println("This Pokemon name is too long (does this Pokemon actually exist?)");
        UI.println();
      } else if (name.length() < MIN_STRING_LENGTH) {
        UI.println("This Pokemon name is too short (does this Pokemon actually exist?)");
        UI.println();
      } 
    } while (name == null || name.isEmpty()
        || name.length() > MAX_STRING_LENGTH || name.length() < MIN_STRING_LENGTH);
        
    UI.println();
    return name;
  }
  
  /**
   * Checks the validity of a double input (monetary value) from the user.
   * - Must be an number.
   * - Must be within $0.0 - $260,000.0 range (inclusive).
   * - Loops until a valid value is entered.
   *
   * @return the validated (and rounded to 2dp) value of the card.
   */
  public static double checkValue() {
    //variables
    double value;
    
    // check the boundaries for the card's price
    do {
      value = UI.askDouble("Enter the card's monetary value: ");
    
      if ((value >= MIN_VALUE) && (value <= MAX_VALUE)) {
        UI.println();
      } else if (value > MAX_VALUE) {
        UI.println("That's almost the price of a house these days woah");
        UI.println("Try again - the value must be equal to/less than $260,000.");
        UI.println();
      } else if (value < MIN_VALUE) {
        UI.println("The value must be equal to/greater than 0.");
        UI.println();
      } else {
        UI.println("Please enter a number.");
        UI.println();
      }
    } while (MIN_VALUE > value || value > MAX_VALUE);
    
    // round the entered value to 2dp
    double roundedValue = Math.round(value * 100.0) / 100.0;
    return roundedValue;
  }
    
  /**
   * Check the validity of a String input from the user.
   * - Must be an image with the filetype of jpeg, jpg, png. Webp are not 
   * accepted as BlueJ doesn't support their file type.
   * - Loops until a valid image path is entered.
   *
   * @return the path to the card's image.
   */
  public static String checkImage() {
    // variables
    boolean loop = true;
        
    // loop until a valid image path is chosen
    while (loop) {
      String imgFile = UIFileChooser.open("Choose Image File (.jpg/.jpeg/.png): ");
        
      // resort to default image if user clicks 'cancel'
      if (imgFile == null) {
        UI.println("Using the default image...");
        UI.println();
        return null;
      } 
    
      // case-insensitive comparison
      String lower = imgFile.toLowerCase();
        
      if (lower.endsWith(".jpg") || lower.endsWith(".jpeg") 
          || lower.endsWith(".png")) {
        // return valid image path
        UI.println();
        return imgFile;
      } else {
        UI.println("That's not a valid image file. Please choose a .jpg or .jpeg or .png.");
      } 
    }
    return null; // default image
  }
}
