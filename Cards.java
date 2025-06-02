import java.util.HashMap;

/**
 * The Cards class manages a collection of Pokemon Card objects.
 * It is designed to be used by the GUI class to display a user's collection and retrieve Card data.
 * A unique ID is assigned for each new Card object added to the collection.
 * Provided methods include: 
 * - Add new Pokemon cards to the collection with and without an image path; 
 * - Search for a Card object in the collection by using the Pokemon's name;
 * - Retrieve Card objects from within the collection by its ID.
 *
 * @author (HFJ)
 * @version (20/5/25)
 */
public class Cards {
  private HashMap<Integer, Card> collection;
  private int cardIdTracker;     // track most recent ID for unique id assignment
  private Card currCard;         // track most recent card found for easy retrieval

  /**
   * Constructor for objects of class Cards.
   */
  public Cards() {
    // initialise instance variables
    collection = new HashMap<Integer, Card>(); // initialise the hashmap

    // add a few initial cards to the collection
    collection.put(1, new Card(1, "DRAGONITE", 4.18, "dragonite.jpg"));
    collection.put(2, new Card(2, "HYDREIGON", 15.59, "hydreigon.jpg"));
    collection.put(3, new Card(3, "ROSERADE", 2.0, "roserade.jpg"));
    collection.put(4, new Card(4, "LEAFEON", 104.85, "leafeon.jpg"));
    collection.put(5, new Card(5, "VOLCARONA", 2.0, "volcarona.jpg"));
    collection.put(6, new Card(6, "SALAMENCE", 2.56, "salamence.png"));
    collection.put(7, new Card(7, "CLEFAIRY", 153.12, "clefairy.jpg"));
    collection.put(8, new Card(8, "MAGNEZONE", 2.43, "magnezone.jpg"));

    // start tracking no. of cards in collection
    this.cardIdTracker = 8;
  }

  /**
   * Increase the ID tracker (by 1) to ensure a unique ID is assigned for each new card.
   */
  private void setCardIdTracker() {
    this.cardIdTracker += 1;
  }

  /**
   * Add a new Pokemon card (without an image path) to the collection.
   *
   * @param name the card's name
   * @param value the card's monetary value
   */
  public void addCard(String name, double value) {
    this.setCardIdTracker(); // set unique ID
    collection.put(cardIdTracker, new Card(cardIdTracker, name, value));
  }

  /**
   * Add a new Pokemon card (with an image path) to the collection.
   *
   * @param name the card's name
   * @param value the card's monetary value
   * @param img the path to the card's image file
   */
  public void addCard(String name, double value, String img) {
    this.setCardIdTracker(); // set unique ID
    collection.put(cardIdTracker, new Card(cardIdTracker, name, value, img));
  }
  
  /**
   * Search for a Pokemon card in the collection by using its name.
   *
   * @param name the name of the card
   * @return true if a match is found, or false if there's no match
   */
  public boolean findCard(String name) {
    // search through each key in the hashmap
    for (int cardId : collection.keySet()) {
      // if card name matches (was found)
      if (collection.get(cardId).getName().equals(name)) {
        // save the card match found in currCard variable
        this.currCard = this.collection.get(cardId);
        return true;
      }
    }
    return false; // no match was found
  }
  
  /**
   * Retrieve a Card object in the collection by its unique ID.
   *
   * @return Card
   */
  public Card getCardById(int id) {
    return this.collection.get(id);
  }

  /**
   * Retrieve the most recent Card object found.
   *
   * @return Card
   */
  public Card getCurrCard() {
    return this.currCard;
  }
  
  /**
   * Retrieve the highest/most recently assigned ID (e.g. number of cards in the collection).
   *
   * @return cardIdTracker
   */
  public int getCardIdTracker() {
    return this.cardIdTracker;
  }
}
