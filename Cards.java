import java.util.HashMap;

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
        Card c1 = new Card(1, "Slowpoke", 20.0, "slowpoke.jpg");
        Card c2 = new Card(2, "Roserade", 30.0, "roserade.jpg");
        Card c3 = new Card(3, "Nidoqueen", 40.0, "nidoqueen.jpg");
        
        // put them into the collection
        collection.put(1, c1);
        collection.put(2, c2);
        collection.put(3, c3);
        
        // set the current amount of cards to 3
        this.currCardId = 3;
    }

    /**
     * Increase the current count of cards in the collection by 1.
     */
    public void setCardId()
    {
        this.currCardId += 1;
    }
    
    
}
