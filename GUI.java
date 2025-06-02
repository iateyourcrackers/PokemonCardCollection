import ecs100.*;

/**
 * Responsible for interacting with the user through a graphical format.
 * Passes on info to the Cards class, and takes info from both the Card and Cards class to successfully display the intended function for the user.
 * Functions include the adding of a new Pokemon card, the displaying of 
 * all Pokemon cards currently held in the collection (which is obtained from 
 * the Cards class), searching for a specific card, and hiding all displayed cards.
 *
 * @author (HFJ)
 * @version (20/5/25)
 */
public class GUI
{
    // instance variables
    private Card currCard;
    private Cards collection;
    
    private static final int MAX_ROWS_ON_GUI = 3; // max full rows able to be displayed
    private static final int CARDS_PER_ROW = 5;
    private static final int TOTAL_CARDS_ON_GUI = MAX_ROWS_ON_GUI * CARDS_PER_ROW;

    /**
     * Constructor for objects of class GUI.
     */
    public GUI()
    {
        // initialise instance variables
        collection = new Cards();
        
        // initalise GUI and mouse
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
     * Conditions: mouse click on a card currently being displayed (visible)
     * 
     * @param action which tracks the user mouse action 
     * @param x      corresponding to the mouse's X position
     * @param y      corresponding to the mouse's Y position
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("clicked") && currCard != null 
            && currCard.getCurrVisibility() && currCard.onCard(x, y)) {
            // if true, clear the graphics
            this.currCard.hideCard();
        }
    }
    
    /**
     * Check the validity of a double input (value) from the user.
     * A valid double input means it must be an int, and must be within range.
     * Keep asking the user for a price until a valid input is entered.
     * Range: $0.0 - $260,000.0
     * See README.TXT file if you want the reason for the boundary max price!
     * @return value of the card
     */
    private double checkValue() {
        final double MAX_VALUE = 260000.0; // see README.TXT file
        double value;
        
        // check the boundaries for the card's price
        do {
            value = UI.askDouble("Enter the card's monetary value: ");
            
            if ((value >= 0) && (value <= MAX_VALUE)) {
                UI.println("Value accepted.");
            } else if (value > MAX_VALUE) {
                UI.println("That's almost the price of a house these days woah");
                UI.println("Try again - the value must be equal to/less than $260,000.");
                UI.println();
            } else if (value < 0) {
                UI.println("The value must be equal to/greater than 0.");
                UI.println();
            } else {
                UI.println("Please enter a number.");
                UI.println();
            }
        } while (0 > value || value > MAX_VALUE);
        
        // round the entered value to 2dp
        double roundedValue = Math.round(value * 100.0) / 100.0;
        UI.println();
        return roundedValue;
    }
    
    /**
     * Check the String input from the user.
     * See README.TXT file if you want the reason for the boundary max length!
     * @return name if valid
     */
    private String checkString() {
        // variables, constants
        final int MIN_LENGTH = 3; // see README.TXT file
        final int MAX_LENGTH = 12;
        String name;
        
        // keep asking until a valid name is entered
        do {
            name = UI.askString("Enter the Pokemon's name: ").trim().toUpperCase();  // for case-insensitive comparison
            
            if (name == null || name.isEmpty()) {
                UI.println("Hey enter something here please");
            } else if (name.length() > MAX_LENGTH) {
                UI.println("This Pokemon name is too long (does this Pokemon actually exist?)");
            } else if (name.length() < MIN_LENGTH) {
                UI.println("This Pokemon name is too short (does this Pokemon actually exist?)");
            } 
        } while (name == null || name.isEmpty()
        || name.length() > MAX_LENGTH || name.length() < MIN_LENGTH);
        
        UI.println();
        return name;
    }
    
    /**
     * Check the validity of a String IMAGEFILE from the user.
     * A valid file means it must be an image (jpeg, jpg, png). Webp are not 
     * accepted as BlueJ doesn't support their file type.
     * Keep asking the user for a image until a valid one is entered.
     * @return the image path if valid
     */
    private String checkImage() {
        boolean loop = true;
        
        // keep asking until a valid image is entered
        while (loop) {
            String imgFile = UIFileChooser.open("Choose Image File (.jpg/.jpeg/.png): ");
            
            if (imgFile == null) {
                // if no image was clicked, resort to default
                UI.println("Using the default image...");
                return null;
            } 
            
            String lower = imgFile.toLowerCase(); // for case-insensitive comparison
            if (lower.endsWith(".jpg") || lower.endsWith(".jpeg") 
            || lower.endsWith(".png")) {
                // return valid image
                return imgFile;
            } else {
                UI.println("That's not a valid image file. Please choose a .jpg or .jpeg or .png.");
            } 
        }
        
        return null; // default image
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
            UI.println("Displaying the card's info...");
            this.currCard = this.collection.getCurrCard();
            
            // display the card info
            this.currCard.displayImage();
            UI.println("Pokemon name: " + this.currCard.getName());
            UI.println("Monetary value: " + String.format("%.2f", this.currCard.getValue()));
        } else {
            // if not found, display error message
            UI.println("You don't have this card in your collection.");
        }
    }
    
    /**
     * Print all the cards in the collection in rows.
     * Show the info for all cards.
     * Info displayed includes the card's name, price, and assigned image.
     */
    public void displayAll() {
        // variables, constants
        final double IMG_SPACE = 60;
        final double TEXT_SPACE = IMG_SPACE/3;
        final double WIDTH = 129;
        final double HEIGHT = 180;
        int cardInRow = 0;
        double locY;
        int row = 1;
        
        // prepare for card overflow
        UI.println("Any GUI card overflow printed here!");
        UI.println();
        
        for (int cardId = 1; cardId <= this.collection.getCardIdTracker(); cardId++) {
            Card card = this.collection.getCardById(cardId);
            
            if (cardId <= TOTAL_CARDS_ON_GUI) {
                cardInRow ++; // increase no. of cards in the row
                
                // increase X positioning to the right for each new card displayed
                double locX = (IMG_SPACE * cardInRow) + (WIDTH * (cardInRow - 1));
                
                // change Y postioning depending on the row
                locY = (IMG_SPACE/2 * row) + ((HEIGHT + IMG_SPACE - TEXT_SPACE)* (row - 1));
                
                // draw the image using the dimensions
                UI.drawImage(card.getImage(), locX, locY, WIDTH, HEIGHT);
                
                // display details under image
                UI.drawString("Card " + cardId + " Details: ", locX, locY + HEIGHT + TEXT_SPACE);
                UI.drawString(card.getName() + ", $" + String.format("%.2f", card.getValue()), 
                                locX, locY + HEIGHT + 2 * TEXT_SPACE);
                
                // check if new row needs to be started
                if (cardInRow == CARDS_PER_ROW) {
                    row ++; // start a new row
                    cardInRow = 0; // reset X positioning for next row
                }
                
            } else {
                UI.println("Card " + cardId + " Details: ");
                UI.println(card.getName() + ", $" + String.format("%.2f", card.getValue()));
                UI.println();
            }
        }
    }
    
    /**
     * Add a new card to the collection.
     * Only allow new Pokemon to be added to the collection (no double ups).
     */
    private void addCard() {
        if (this.collection.getCardIdTracker() == TOTAL_CARDS_ON_GUI) {
            UI.println("Any extra cards added will not have all info displayed on the GUI.");
            UI.println("When displaying all info, check the left text panel for the text info.");
        }
        
        // get card name
        String name = checkString();
        
        // check if card already exists
        if (this.collection.findCard(name)) {
            // if already in collection, print error message
            UI.println("This card is already in your collection.");
            UI.println();
        } else {
            // if not already in collection, get rest of card info
            double value = checkValue();
            String imgFileName = checkImage();
            
            // add new card to collection
            this.collection.addCard(name, value, imgFileName);
            UI.println("Card added.");
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
     * Main routine
     */
    public static void main(String[] args) {
        new GUI();
    }
}
