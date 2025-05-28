import java.util.HashMap;
import ecs100.*;

/**
 * This class handles the collection of cards that the user adds and displays card from.
 * This class uses the collection datatype of a HashMap to manage and pass through info to the Card class, and back again to the GUI class.
 * It uses the total amount of cards to keep track of the collection. 
 * Sends info to the GUI class such as findCard.
 * Pulls info from the Card class such as getValue.
 * 
 * @author (HFJ)
 * @version (20/5/25)
 */
public class Cards
{
    // instance variables
    private HashMap<Integer, Card> collection; // declare the hashmap
    private int currCardId; // store the most recent unique card ID
    private Card currCard; // store the last card searched by the GUI

    /**
     * Constructor for objects of class Cards
     */
    public Cards()
    {
        // initialise instance variables
        collection = new HashMap<Integer, Card>(); // initialise the hashmap
        
        // set up a few initial cards
        Card c1 = new Card(1, "DRAGONITE", 4.18, "dragonite.jpg");
        Card c2 = new Card(2, "HYDREIGON", 15.59, "hydreigon.jpg");
        Card c3 = new Card(3, "ROSERADE", 2.0, "roserade.jpg");
        Card c4 = new Card(4, "LEAFEON", 104.85, "leafeon.jpg");
        Card c5 = new Card(5, "VOLCARONA", 2.0, "volcarona.jpg");
        Card c6 = new Card(6, "SALAMENCE", 2.56, "salamence.png");
        Card c7 = new Card(7, "CLEFAIRY", 153.12, "clefairy.jpg");
        Card c8 = new Card(8, "MAGNEZONE", 2.43, "magnezone.jpg");
        
        collection.put(1, c1);
        collection.put(2, c2);
        collection.put(3, c3);
        collection.put(4, c4);
        collection.put(5, c5);
        collection.put(6, c6);
        collection.put(7, c7);
        collection.put(8, c8);
        
        // set the current amount of cards to 8
        this.currCardId = 8;
    }

    /**
     * Increase the current amount of cards in the collection by 1.
     */
    private void setCardId()
    {
        this.currCardId += 1;
    }
    
    /**
     * Check if a card currently exists in the collection using its name.
     * Take in String (name) parameter. Iterate over the collection's keys to 
     * find a possible match in any of the Pokemon cards' names.
     * @param: String NAME, double VALUE
     * @return: boolean TRUE if a match is found
     *          boolean FALSE if no match is found
     */
    public boolean findCard(String name) {
        // search through each key in the hashmap
        for (int cardId: collection.keySet()) {
            if (collection.get(cardId).getName().equals(name)) {
                // save the card match found in the currCard variable
                this.currCard = this.collection.get(cardId);
                return true; // card was found
            }
        }
        return false; // card was not found
    }
    
    /**
     * Add a new card to the collection with its own unique ID. No image included.
     * @param: String NAME, double VALUE
     */
    public void addCard(String name, double value) {
        this.setCardId();
        collection.put(currCardId, new Card(currCardId, name, value));
    }
    
    /**
     * Add a new card to the collection with its own unique ID. Includes image.
     * @param: String NAME, double VALUE, String IMG
     */
    public void addCard(String name, double value, String img) {
        this.setCardId();
        collection.put(currCardId, new Card(currCardId, name, value, img));
    }
    
    /**
     * Getter for the current card object.
     * @return: Card CURRCARD
     */
    public Card getCard() {
        return this.currCard;
    }
    
    /**
     * Print all the cards in the collection in rows.
     */
    public void printAllCards() {
        // variables, constants
        double imgSpace = 30;
        final double WIDTH = 150;
        final double HEIGHT = 210;
        int cardInRow = 1;
        double locY;
        double locX;
        int row = 1;
        
        for (int cardId: collection.keySet()) {
            // increase X positioning to the right for each new card displayed
            locX = (imgSpace * cardInRow) + (WIDTH * (cardInRow - 1));
                        
            // change Y postioning depending on the row
            if (row == 1) {
                locY = imgSpace;
            } else {
                // not row 1
                locY = (2 * imgSpace * row) + (HEIGHT * (row - 1));
            }
            
            // draw the image using the dimensions
            Card card = collection.get(cardId);
            UI.drawImage(card.getImage(), locX, locY, WIDTH, HEIGHT);
            
            // display details under image
            UI.drawString("Card " + cardId + " Details: ", locX, locY + HEIGHT + 20);
            UI.drawString(card.getName() + ", $" + card.getValue(), locX, locY + HEIGHT + 40);
            
            cardInRow += 1; // increase no. of cards in the row
            
            // check if new row needs to be started
            if (cardInRow == 6) {
                row += 1; // start a new row
                cardInRow = 1; // reset X positioning for next row
            }
        }
    }
}
