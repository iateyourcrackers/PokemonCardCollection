import ecs100.*;

/**
 * Write a description of class Card here.
 *
 * @author (HJF)
 * @version (20/5/25)
 */
public class Card
{
    // instance variables
    private int id;
    private String name;
    private double value;
    private String image;
    static final String DEFAULT_IMAGE = "pokemon.jpg"; // set a default card image

    /**
     * Constructor for objects of class Card
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
     * Constructor for objects of class Card
     */
    public Card(int key, String nm, double price)
    {
        // put your code here
        this(key, nm, price, DEFAULT_IMAGE);
    }
    
    /**
     * Display a singular card's image on the GUI.
     */
    public void displayCard() {
        // draw the card cover
        int locX = 50; // image x start position
        int locY = 50; // image y start position
        
        final double WIDTH = 250;
        final double HEIGHT = 350;
        
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
    }
    
    /**
     * Get the int ID for the current card object.
     * @return: int ID
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Get the String NAME for the current card object.
     * @return: String NAME
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Get the String IMAGE for the current card object
     * @return: String IMAGE
     */
    public String getImage() {
        return this.image;
    }
    
    /**
     * Get the double VALUE for the current card object.
     * @return: double VALUE
     */
    public double getValue() {
        return this.value;
    }
}
