import java.util.HashMap;
import ecs100.*;

/**
 * The Cards class is responsible for directly handling the user's collection of cards.
 * It uses the HashMap datatype to manage the cards, and the total amount of cards to keep track of the collection.
 * Info is passed to the Card and GUI classes:
 * Sends info to the GUI class through HashMap iterations.
 * Pulls individual card info from the Card class through getters.
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
        UI.println("Current card ID increased to: " + currCardId);
    }
    
    /**
     * Check if a card currently exists in the collection using its name.
     * Take in String (name) parameter. Iterate over the collection's keys to 
     * find a possible match in any of the Pokemon cards' names.
     * @param String NAME, double VALUE
     * @return boolean TRUE if a match is found
     * @return boolean FALSE if no match is found
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
     * @param String NAME, double VALUE
     */
    public void addCard(String name, double value) {
        this.setCardId();
        collection.put(currCardId, new Card(currCardId, name, value));
        UI.println("card added successfully. (CARDS CLASS)");
    }
    
    /**
     * Add a new card to the collection with its own unique ID. Includes image.
     * @param String NAME, double VALUE, String IMG
     */
    public void addCard(String name, double value, String img) {
        this.setCardId();
        collection.put(currCardId, new Card(currCardId, name, value, img));
        UI.println("card added successfully. (CARDS CLASS)");
    }
    
    /**
     * Getter for the current card object.
     * @return Card CURRCARD
     */
    public Card getCurrCard() {
        return this.currCard;
    }
    
    /**
     * Getter for the current card object by its ID.
     * @return Card card
     */
    public Card getCardById(int id) {
        return this.collection.get(id);
    }
    
    /**
     * Getter for the current max cards in collection.
     * @return int CURRCARDID
     */
    public int getCurrCardId() {
        return this.currCardId;
    }
}
