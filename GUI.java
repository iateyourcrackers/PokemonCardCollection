import ecs100.*;

/**
 * This class is responsible for interacting with the user in a graphics interface format. idk
 * It will pass on information to the Cards class, and take info from both the Card and Cards class to successfully display the intended function for the user.
 * Such functions include the adding of a new Pokemon card, the displaying of 
 * all Pokemon cards currently held in the collection (which is obtained from 
 * the Cards class), searching for a specific card, and hiding all displayed cards.
 * The program checks and validates any input to provide a seamless user experience.
 * i should go into marketing omg
 *
 * @author (HFJ)
 * @version (20/5/25)
 */
public class GUI
{
    // instance variables
    private double startX, startY; // remember mouse input ('pressed' position)
    private Card currCard;
    private Cards collection;

    /**
     * Constructor for objects of class GUI.
     */
    public GUI()
    {
        // initialise instance variables
        collection = new Cards();
        
        // initalise the GUI and the mouse
        UI.initialise();
        UI.setMouseListener(this::doMouse);
        
        // add UI buttons
        UI.addButton("Add a new Pokemon card" , this::addCard);
        UI.addButton("Search for a Pokemon card" , this::displayOne);
        UI.addButton("Display all Pokemon cards", this::displayAll);
        UI.addButton("Clear all Pokemon cards", this::clearAll);
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Hide the image displayed by checking the set conditions.
     * All conditions must equal TRUE to hide the card successfully.
     * 
     * @param: String ACTION (user mouse action) 
     *         double X (mouse X position), double Y (mouse Y position)
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("clicked") && currCard != null 
            && currCard.getIsVisible() && currCard.onCard(x, y)) {
            // if true, clear the graphics
            currCard.hideCard();
        }
    }
    
    /**
     * Check the validity of a double input (value) from the user.
     * A valid double input means it must be an int, and must be within range.
     * Keep asking the user for a price until a valid input is entered.
     * Range: $0.0 - $260,000.0
     * See README.TXT file if you want the reason for the boundary max price!
     * @return: double VALUE (if valid)
     */
    private double checkValue() {
        final double MAX_VALUE = 260000.0; // see README.TXT file
        double value;
        
        // check the boundaries for the card's price
        do {
            value = UI.askDouble("Enter the card's monetary value: ");
            
            if ((value >= 0) && (value <= MAX_VALUE)) {
                UI.println("Value accepted."); // test purposes
            } else if (value > MAX_VALUE) {
                UI.println("That's almost the price of a house these days woah");
            } else if (value < 0) {
                UI.println("The value must be equal to/greater than 0.");
            } else {
                UI.println("Please enter a number.");
            }
        } while (0 > value || value > MAX_VALUE);
        
        return value;
    }
    
    /**
     * Check the String input from the user.
     * See README.TXT file if you want the reason for the boundary max length!
     * @return: String NAME (if valid)
     */
    private String checkString() {
        // variables, constants
        final int MIN_LENGTH = 3; // see README.TXT file
        final int MAX_LENGTH = 12;
        String name;
        
        // keep asking until a valid name is entered
        do {
            name = UI.askString("Enter the Pokemon's name: ").trim().toUpperCase();
            
            if (name == null || name.isEmpty()) {
                UI.println("Hey enter something here please");
            } else if (name.length() > MAX_LENGTH) {
                UI.println("This Pokemon name is too long (does this Pokemon actually exist?)");
            } else if (name.length() < MIN_LENGTH) {
                UI.println("This Pokemon name is too short (does this Pokemon actually exist?)");
            } 
        } while (name == null || name.isEmpty()
        || name.length() > MAX_LENGTH || name.length() < MIN_LENGTH);
        
        UI.println("String (name) accepted.");
        UI.println();
        return name;
    }

    /**
     * Search for a card by the Pokemon's name.
     * Display Pokemon card if found, print error message if not found.
     */
    private void displayOne() {
        clearAll(); // clear the graphics first
        
        // get the card name from user
        String name = checkString();
        
        // check if the card exists
        if (this.collection.findCard(name)) {
            // if found, set to current card
            UI.println("Found the pokemon card");
            this.currCard = collection.getCard();
            
            // display the card info
            this.currCard.displayImage();
            UI.println("Pokemon name: " + this.currCard.getName());
            UI.println("Monetary value: " + this.currCard.getValue());
        } else {
            // if not found, display error message
            UI.println("You don't have this card in your collection.");
        }
    }
    
    /**
     * Add a new card to the collection.
     * Only allow new Pokemon to be added to the collection (no double ups).
     */
    private void addCard() {
        // get card name
        String name = checkString();
        
        // check if card already exists
        if (this.collection.findCard(name)) {
            // if already in collection, print error message
            UI.println("This card already exists in your collection.");
            UI.println();
        } else {
            // if not already in collection, get rest of card info
            double value = checkValue();
            String imgFileName = UIFileChooser.open("Choose Image File: ");
            
            // add new card to collection
            this.collection.addCard(name, value, imgFileName);
        }
    }
    
    /**
     * Hide every card currently being displayed in the GUI.
     * Does not clear the text panel of the GUI.
     */
    private void clearAll() {
        UI.clearPanes();
    }
    
    /**
     * Show the info for all cards.
     * Info displayed includes the card's name, price, and assigned image.
     */
    private void displayAll() {
        this.collection.printAllCards();
    }
    
    /**
     * Main routine.
     */
    public static void main(String[] args) {
        new GUI();
    }
}
