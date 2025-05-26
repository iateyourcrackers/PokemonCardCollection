import ecs100.*;

/**
 * Write a description of class GUI here.
 *
 * @author (HFJ)
 * @version (20/5/25)
 */
public class GUI
{
    // instance variables
    private double startX, startY; // fields to remember mouse input ('pressed' position)
    private Card currCard;
    private Cards collection;

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        collection = new Cards();
        
        // initalise the GUI
        UI.initialise();
        UI.setMouseListener(this::doMouse);
        
        // add UI buttons
        UI.addButton("Add a new Pokemon card" , this::addCard);
        UI.addButton("Search for a Pokemon card" , this::displayOne);
        UI.addButton("Hide all Pokemon card details", this::hideAll);
        UI.addButton("Display all Pokemon cards", this::displayAll);
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Flip the image by checking mouse action.
     */
    public void doMouse(String action, double x, double y) {
        clearImage(action, x, y);
    }
    
    /**
     * hehehe
     */
    public void clearImage(String action, double x, double y) {
        if (currCard == null) {
            UI.println("No card currently selected.");
            UI.println();
        } else {
            if (action.equals("pressed")) {
                this.startX = x;
                this.startY = y;
            } else if (action.equals("released")) {
                // fix this later
                if (currCard.onCard(x, y)) {
                    currCard.hideCard();
                }
            }
        }
    }
    
    /**
     * Check if the double input from the user is within range.
     * Range: $0 - $260,000
     * See README.TXT file if you want the reason for the max price :)
     * @return: double VALUE (if valid)
     */
    public double checkValue() {
        final double MAX_VALUE = 260000; // see README.TXT file
        double value;
        
        // check the boundaries for the card's price
        do {
            value = UI.askDouble("Enter the card's monetary value: ");
            
            if ((value >= 0) && (value <= MAX_VALUE)) {
                UI.println("Accepted.");
            } else if (value > MAX_VALUE) {
                UI.println("YOU PAID HOW MUCH FOR THIS CARD???");
            } else if (value < 0) {
                UI.println("Must be equal to/greater than 0.");
            } else {
                UI.println("That's not a number??");
            }
        } while (0 > value || value > MAX_VALUE);
        
        return value;
    }
    
    /**
     * Check the String input from the user.
     * See README.TXT file if you want the reason for the max length :)
     * @return: String NAME (if valid)
     */
    public String checkString() {
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
                UI.println("This pokemon name is too long (it also doesn't exist?)");
            } else if (name.length() < MIN_LENGTH) {
                UI.println("This Pokemon name is too short (it also doesn't exist?)");
            } 
        } while (name == null || name.isEmpty()
        || name.length() > MAX_LENGTH || name.length() < MIN_LENGTH);
        
        UI.println("Accepted.");
        return name;
    }

    /**
     * Search for a card by the Pokemon's name.
     * Display Pokemon card if found, print error message if not found.
     */
    public void displayOne() {
        hideAll(); // clear the graphics first
        
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
    public void addCard() {
        // get the card name from user
        String name = checkString();
        
        // check if card already exists
        if (this.collection.findCard(name)) {
            // if already in collection, print error message
            UI.println("This card already exists in your collection.");
            
        } else {
            // if not in collection, ask for rest of card info
            double value = checkValue();
            String imgFileName = UIFileChooser.open("Choose Image File: ");
            
            // add card to collection
            this.collection.addCard(name, value, imgFileName);
        }
    }
    
    /**
     * Hide every card's details.
     */
    public void hideAll() {
        UI.clearGraphics();
    }
    
    /**
     * Show the info for all cards.
     */
    public void displayAll() {
        this.collection.printAllCards();
    }
    
    /**
     * Main routine.
     */
    public static void main(String[] args) {
        new GUI();
    }
}
