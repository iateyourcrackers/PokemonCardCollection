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
        Card c1 = new Card(1, "DRAGONITE", 4.18, "dragonite.jpg");
        Card c2 = new Card(2, "HYDREIGON", 15.59, "hydreigon.jpg");
        Card c3 = new Card(3, "ROSERADE", 2.0, "roserade.jpg");
        Card c4 = new Card(4, "LEAFEON", 104.85, "leafeon.jpg");
        Card c5 = new Card(5, "VOLCARONA", 2.0, "volcarona.jpg");
        Card c6 = new Card(6, "SALAMENCE", 2.56, "salamence.png");
        Card c7 = new Card(7, "CLEFAIRY", 153.12, "clefairy.jpg");
        Card c8 = new Card(8, "MAGNEZONE", 2.43, "magnezone.jpg");
        
        // when testing, you can add slowpoke, sylveon, nidoqueen, and metagross
        // price guides for the actual cards are below:
        // nidoqueen: no set price (as its not a real card)
        // slowpoke: $19.55
        // sylveon: $121.25
        // metagross: $2.99
        
        // put them into the collection
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
     * Print all the cards in the collection in rows.
     */
    public void printAllCards() {
        // variables
        double imgSpace = 30;
        final double WIDTH = 150;
        final double HEIGHT = 210;
        int cardCount = 1;
        double locY;
        double locX;
        int row = 1;
        
        for (int cardId: collection.keySet()) {
            // increase X positioning to the left for each card
            if (cardCount == 1) {
                locX = imgSpace;
            } else {
                locX = (imgSpace * cardCount) + (WIDTH * (cardCount - 1));
            }
            
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
            
            cardCount += 1; // increase no. of cards in the row
            
            // check if new row needs to be started
            if (cardCount == 6) {
                row += 1; // start a new row
                cardCount = 1; // reset X positioning
            }
        }
    }
}
