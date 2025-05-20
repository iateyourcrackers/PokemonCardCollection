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
        UI.addButton("Hide current Pokemon card's details", this::hideCard); // to do and figure out tbh
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
        
        String name;
        
        // do i need to find the longest pokemon name??
        name = UI.askString("Enter the Pokemon's name: ").trim().toLowerCase();
        
        if (name.equals(null)) {
            
        }
    }

    /**
     * Search for a card by the Pokemon's name.
     * 
     */
    public void findCard() {
        // ok
    }
    
    /**
     * add a new card to the collection
     */
    public void addCard() {
        // get the user details
        String name = checkString();
        double value = checkValue();
        
        // check if the card already exists, then add to collection
        
        
    }
    
    /**
     * Main routine
     */
    public static void main(String[] args) {
        new GUI();
    }
}
