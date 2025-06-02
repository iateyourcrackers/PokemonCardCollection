import ecs100.*;

/**
 * Support class for the Cards and GUI classes to manage individual card data.
 * Store the id, name, monetary value, image path and drawing positioning, and current visibility.
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
    private boolean currVisibility = false; // True if the card is being displayed
    static final String DEFAULT_IMAGE = "pokemon.png"; // set a default/fallback card image
    
    // coordinates for top left corner of image
    private double locX = 50;
    private double locY = 50;  
    private final double WIDTH = 350; // image dimensions
    private final double HEIGHT = 490;

    /**
     * Constructor for objects of class Card with a given id, name, value.
     * Uses a default image if the path is null.
     */
    public Card(int key, String nm, double price, String img)
    {
        // initialise instance variables
        this.id = key;
        this.name = nm;
        this.value = price;
        
        // check image path
        if (img == null) {
            this.image = DEFAULT_IMAGE; // no path provided
        } else {
            this.image = img;
        }
    }

    /**
     * Constructor for objects of class Card using the default image.
     */
    public Card(int key, String nm, double price)
    {
        this(key, nm, price, DEFAULT_IMAGE);
    }
    
    /**
     * Check if the mouse's click (mx, my) is within the image bounds.
     * Card must be currently displaying to return true.
     * @return true if (mx, my) is within the card area. Returns false if 
     * (mx, my) is NOT within the card area
     */
    public boolean onCard(double mx, double my) {
        return ((mx >= locX && mx <= locX + WIDTH) && (my >= locY && my <= locY + HEIGHT)) 
        && currVisibility;
    }
    
    /**
     * Draw the card's image on the GUI and marks it as visible.
     */
    public void displayImage() {
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
        this.currVisibility = true;
    }
    
    /**
     * Hides the card by clearing the pane if it is currently visible.
     */
    public void hideCard() {
        if (this.currVisibility) {
            this.currVisibility = false; // prevent user from redisplaying it accidentally
            UI.clearGraphics();
        }
    }
    
    /**
     * @return the card's id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * @return the card's name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @return the card's image path
     */
    public String getImage() {
      return this.image;
    }
    
    /**
     * @return the card's monetary value
     */
    public double getValue() {
        return this.value;
    }
    
    /**
     * @return whether the card is currently visible
     */
    public boolean getCurrVisibility() {
        return this.currVisibility;
    }
}
