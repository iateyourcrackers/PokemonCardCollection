import java.util.HashMap;
import ecs100.*;

/**
 * Write a description of class Cards here.
 *
 * @author (HFJ)
 * @version (20/5/25)
 */
public class Cards
{
    // instance variables
    private HashMap<Integer, Card> collection; // declare the hashmap
    private int currCardId; // store the id number of the current card
    private Card currCard; // store the instance of the current car

    /**
     * Constructor for objects of class Cards
     */
    public Cards()
    {
        // initialise instance variables
        collection = new HashMap<Integer, Card>(); // initialise the hashmap
        
        // putting some of my cards in there >:)
        Card c1 = new Card(1, "SLOWPOKE", 20.0, "slowpoke.png");
        Card c2 = new Card(2, "HYDREIGON", 30.0, "hydreigon.jpg");
        Card c3 = new Card(3, "DRAGONITE", 40.0, "dragonite.jpg");
        Card c4 = new Card(4, "NIDOQUEEN", 40.0, "nidoqueen.png");
        Card c5 = new Card(5, "VOLCARONA", 40.0, "volcarona.jpeg");
        Card c6 = new Card(6, "METAGROSS", 40.0, "metagross.png");
        
        // put them into the collection
        collection.put(1, c1);
        collection.put(2, c2);
        collection.put(3, c3);
        collection.put(4, c4);
        collection.put(5, c5);
        collection.put(6, c6);
        
        // set the current amount of cards to 6
        this.currCardId = 6;
    }

    /**
     * Increase the current count of cards in the collection by 1.
     */
    public void setCardId()
    {
        this.currCardId += 1;
    }
    
    /**
     * Check if a card currently exists in the collection.
     * @param: String NAME, double VALUE
     * @return: boolean TRUE if found, boolean FALSE if not found
     */
    public boolean findCard(String name) {
        // search through the hashmap
        for (int cardId: collection.keySet()) {
            if (collection.get(cardId).getName().equals(name)) {
                // set the current card to the one found
                this.currCard = this.collection.get(cardId);
                return true;
            }
        }
        // if not found, return false
        return false;
    }
    
    /**
     * Add a new card to the collection.
     * @param: String NAME, double VALUE, String IMG
     */
    public void addCard(String name, double value) {
        this.setCardId();
        collection.put(currCardId, new Card(currCardId, name, value));
    }
    
    /**
     * Add a new card to the collection.
     * @param: String NAME, double VALUE, String IMG
     */
    public void addCard(String name, double value, String img) {
        this.setCardId();
        collection.put(currCardId, new Card(currCardId, name, value, img));
    }
    
    /**
     * Getter for the current card
     * @return: current CARD (object)
     */
    public Card getCard() {
        return this.currCard;
    }
    
    /**
     * Print all the cards in the collection.
     */
    public void printAllCards() {
        double imgSpace = 40;
        final double WIDTH = 200;
        final double HEIGHT = 280;
        int cardCount = 0;
        double locY = imgSpace;
        
        for (int cardId: collection.keySet()) {
            // this took me an embarrasingly amount of time to figure out
            double locX = (imgSpace * cardId) + (WIDTH * (cardId - 1));
            
            UI.drawString(cardId + " Details: ", locX, locY + HEIGHT + 20); //change this to draw string later
            UI.drawString(collection.get(cardId).getName() + " "
            + collection.get(cardId).getValue(), locX, locY + HEIGHT + 40);
            
            // add a draw image thing here too
            UI.drawImage(collection.get(cardId).getImage(), locX, locY, WIDTH, HEIGHT);
            
            cardCount += 1;
            // change the Y postiioning if three cards are displayed on one row
            if (cardCount == 3) {
                locY = (imgSpace * cardId) + (HEIGHT * (cardId - 1));
            }
        }
    }
}
