import ecs100.*;

/**
 * Write a description of class GUI here.
 *
 * @author (HFJ)
 * @version (20/5/25)
 */
public class GUI
{
    // instance variables - replace the example below with your own
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
        UI.addButton("Add a new Pokemon card" , this::addCard); // to do
        UI.addButton("Search for a Pokemon card" , this::findCard); // to do
        UI.addButton("Hide all Pokemon card details", this::hideAll); // to do and figure out tbh
        UI.addButton("Display all Pokemon cards", this::displayAll); //to do AND FIGURE OUT HOW AM I GONNA DO THIS
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Check if the double input from the user is within range.
     * Range: $0 - $8,901,676.55
     * Converted to NZD, the most expensive Pokemon card was bought by Logan Paul in 2022.
     * 
     * @return: double VALUE (if valid)
     */
    public double checkValue() {
        final double MAX_VALUE = 8901676.55; // this is flipping crazy
        double value;
        
        // check the boundaries for the card's price
        do {
            value = UI.askDouble("Enter the card's monetary value: ");
            if ((value >= 0) && (value <= MAX_VALUE)) {
                UI.println("Accepted.");
            } else if (value > MAX_VALUE) {
                UI.println("YOU PAID HOW MUCH FOR THIS CARD???");
            } else if (value < 0) {
                UI.println("Must be equalto/greater than 0.");
            } else {
                UI.println("That's not a number??");
            }
        } while (0 > value || value > MAX_VALUE);
        
        return value;
    }
    
    /**
     * Check the String input from the user.
     */
    public String checkString() {
        // do i need to find the longest pokemon name??
        String name = UI.askString("Enter the Pokemon's name: ").trim().toUpperCase();
        
        do {
            if (name.equals(null)) {
                UI.println("Hey enter something here please");
            }
        } while (name.equals(null));
    
        return name;
    }

    /**
     * Search for a card by the Pokemon's name.
     * 
     */
    public void findCard() {
        // get the user details
        String name = checkString();
        
        // check if the card already exists, then add to collection
        if (this.collection.findCard(name)) {
            // if found, display its info
            UI.println("Found the pokemon card");
            this.currCard = collection.getCard();
            
            // display the card info
            this.currCard.displayCard();
            UI.println("Pokemon name: " + this.currCard.getName());
            UI.println("Monetary value: " + this.currCard.getValue());
        } else {
            // if not found, display an error message
            UI.println("You don't have this card in your collection.");
        }
    }
    
    /**
     * Add a new card to the collection.
     * Only allow new Pokemon to be added to the collection (no double ups).
     */
    public void addCard() {
        // get the user details
        String name = checkString();
        
        // check if the card already exists, then add to collection
        if (this.collection.findCard(name)) {
            // if already in collection, print error message
            UI.println("This card already exists in your collection.");
            
        } else {
            // if not found, run through checkers then add to collection
            double value = checkValue();
            String imgFileName = UIFileChooser.open("Choose Image File: ");
            
            // add card with image
            this.collection.addCard(name, value, imgFileName);
        }
        
    }
    
    /**
     * Hide the current card's details
     */
    public void hideAll() {
        // just clear the gui??
        UI.clearGraphics();
    }
    
    /**
     * Show the info for all cards
     */
    public void displayAll() {
        // hihi
        this.collection.printAllCards();
    }
    
    /**
     * Main routine
     */
    public static void main(String[] args) {
        new GUI();
    }
}
