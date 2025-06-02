import ecs100.*;

/**
 * The GUI class is responsible for interacting with the user.
 * This class acts as the main function for the user's collection.
 * It passes info to the Cards class to manage the collection, and displays card info.
 * Functions include:
 * - Adding a new Pokemon card to the collection;
 * - Displaying all Pokemon cards currently in the collection;
 * - Searching for and displaying a specific card;
 * - Hiding all displayed cards.
 *
 * @author (HFJ)
 * @version (20/5/25)
 */
public class GUI {
    // instance variables
    private Card currCard; // stores the current card being displayed
    private Cards collection; // user's collection of cards
    
    // collection image display
    private static final int MAX_ROWS_ON_GUI = 3; // max full rows able to be displayed
    private static final int CARDS_PER_ROW = 5;
    private static final int TOTAL_CARDS_ON_GUI = MAX_ROWS_ON_GUI * CARDS_PER_ROW;
    
    // multiple image spacing and dimensions
    private static final double IMG_SPACING = 60;
    private static final double TEXT_VERT_SPACING = IMG_SPACING/3;
    private static final double MULTIPLE_WIDTH = 129;
    private static final double MULTIPLE_HEIGHT = 180;
    
    // single image dimensions
    private static final double SINGLE_X = 50; // top left corner x positioning
    private static final double SINGLE_Y = 50; // top left corner y positioning
    private static final double SINGLE_WIDTH = 350;
    private static final double SINGLE_HEIGHT = 490;

    /**
     * Constructor for objects of class GUI.
     */
    public GUI() {
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
     * Handles mouse interaction.
     * Hides a card by mouse click if it is currently being displayed.
     * 
     * @param action which tracks the user mouse action 
     * @param x      corresponding to the mouse's X position
     * @param y      corresponding to the mouse's Y position
     */
    public void doMouse(String action, double x, double y) {
      if (action.equals("clicked") && currCard != null 
           && currCard.onCard(x, y, SINGLE_X, SINGLE_Y, SINGLE_WIDTH, SINGLE_HEIGHT)) {
          this.currCard = null;
          UI.clearPanes();
      }
    }
    
    /**
     * Add a new card to the collection and checks for duplicates.
     */
    private void addCard() {
      // warn the user if all GUI space is full
      if (this.collection.getCardIdTracker() == TOTAL_CARDS_ON_GUI) {
        UI.println("Any extra cards added will not have their info displayed on the GUI.");
        UI.println("When displaying all info, please check the left text panel for the text info. Thank you!");
        UI.println();
      }
        
      String name = InputValidator.checkString();
        
      // check for duplicates
      if (this.collection.findCard(name)) {
        // if already in collection, print error message
        UI.println("This card is already in your collection.");
        UI.println();
      } else {
        // if not in collection, get rest of card info
        double value = InputValidator.checkValue();
        String imgFileName = InputValidator.checkImage();
            
        // add to collection
        this.collection.addCard(name, value, imgFileName);
        UI.println("Card added.");
        UI.println();
      }
    }
    
    /**
     * Searches for a card by the Pokemon's name and display it if found.
     * Prints an error message if there is no match.
     */
    private void displayOne() {
      clearAll(); // clear the UI graphics first
        
      // get Pokemon card name from user
      String name = InputValidator.checkString();
        
      // check if the card exists
      if (this.collection.findCard(name)) {
        // set to current card for easy retrieval
        UI.println("Displaying the card's info...");
        this.currCard = this.collection.getCurrCard();
            
        // display the card info
        this.currCard.display(SINGLE_X, SINGLE_Y, SINGLE_WIDTH, 
        SINGLE_HEIGHT, TEXT_VERT_SPACING);
      } else {
        UI.println("You don't have this card in your collection.");
      }
    }
    
    /**
     * Display all the cards' images and info in the collection on the GUI in rows.
     * If the user has more cards in their collection than enough space on the GUI, remaining cards' info are printed as text.
     */
    public void displayAll() {
      // variables
      int cardInRow = 0;
      int row = 1;
        
      // prepare for card overflow
      UI.println("Any GUI card overflow printed here!");
      UI.println();
        
      for (int cardId = 1; cardId <= this.collection.getCardIdTracker(); cardId++) {
        Card card = this.collection.getCardById(cardId);
            
        if (cardId <= TOTAL_CARDS_ON_GUI) {
          cardInRow ++; // increase no. of cards in the row
            
          // change X/Y positioning for each new card displayed
          double locX = (IMG_SPACING * cardInRow) + (MULTIPLE_WIDTH * (cardInRow - 1));
          double locY = (IMG_SPACING/2 * row) + ((MULTIPLE_HEIGHT + IMG_SPACING - TEXT_VERT_SPACING)* (row - 1));
        
          // draw the card's image and display details
          card.display(locX, locY, MULTIPLE_WIDTH, MULTIPLE_HEIGHT, TEXT_VERT_SPACING);
            
          // check if new row needs to be started
          if (cardInRow == CARDS_PER_ROW) {
            row ++; // start a new row
            cardInRow = 0; // reset X positioning for next row
          }
        } else {
          // overflow - info is text only
          UI.println("Card " + cardId + " Details: ");
          UI.println(card.getName() + ", $" + String.format("%.2f", card.getValue()));
          UI.println();
        }
      }
    }
    
    /**
     * Hide all card info currently being displayed in the GUI.
     */
    private void clearAll() {
      UI.clearPanes();
    }
    
    /**
     * Main routine.
     */
    public static void main(String[] args) {
      new GUI();
    }
}
