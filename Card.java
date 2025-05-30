import ecs100.*;

/**
 * The Card class is the support class for the Cards and GUI classes.
 * This class is responsible for managing the individual card objects.
 * Attributes to a Card contains the id, name, monetary value, and image.
 *
 * @author (HJF)
 * @version (20/5/25)
 */
public class Card {
    // instance variables
    private int id;
    private String name;
    private double value;
    private String image;
    private boolean isVisible = false; // selected card is initially not visible
    static final String DEFAULT_IMAGE = "pokemon.png"; // set a default card image
    
    // coordinates for top left corner of image
    private double locX = 50;
    private double locY = 50;  
    private final double WIDTH = 350; // image dimensions
    private final double HEIGHT = 490;

    /**
     * Constructor for objects of class Card.
     */
    public Card(int key, String nm, double price, String img)
    {
        // initialise instance variables
        this.id = key;
        this.name = nm;
        this.value = price;
        
        // check if image is null
        if (img == null) {
            this.image = DEFAULT_IMAGE; // set the image to a default one
        } else {
            this.image = img;
        }
    }

    /**
     * Constructor for objects of class Card.
     */
    public Card(int key, String nm, double price)
    {
        this(key, nm, price, DEFAULT_IMAGE);
    }
    
    /**
     * Check whether the mouse's click (mx, my) is within the bounds of the image.
     * Only returns TRUE if the card is currently being displayed.
     * @return TRUE if the mouse click is within the card area
     * @return FALSE if the mouse click is NOT within the card area
     */
    public boolean onCard(double mx, double my) {
        return ((mx >= locX && mx <= locX + WIDTH) 
        && (my >= locY && my <= locY + HEIGHT)) && isVisible;
    }
    
    /**
     * Draw the stored current card's image on the GUI.
     */
    public void displayImage() {
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
        this.isVisible = true; // change the visibility of the card
    }
    
    /**
     * Hide the current card displayed.
     * Only hides if the card is currently visible (showing), so the user cannot
     * redisplay it accidentally.
     */
    public void hideCard() {
        if (this.isVisible) {
            this.isVisible = false;
            UI.clearGraphics();
        }
    }
    
    /**
     * @return int ID for current card object
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * @return: String NAME for current card object
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @return String IMAGE for current card object
     */
    public String getImage() {
      return this.image;
    }
    
    /**
     * @return double VALUE for current card object
     */
    public double getValue() {
        return this.value;
    }
    
    /**
     * @return boolean ISVISIBLE for current card object
     */
    public boolean getIsVisible() {
        return this.isVisible;
    }
}
