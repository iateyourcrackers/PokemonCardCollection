import ecs100.*;

/**
 * Support class for the Cards and GUI classes to manage individual card data.
 * Stores the id, name, monetary value, image path and drawing positioning, and current visibility.
 *
 * @author (HJF)
 * @version (20/5/25)
 */
public class Card {
    // card attributes
    private final int id;
    private final String name;
    private final double value;
    private final String image;
    
    // card display
    static final String DEFAULT_IMAGE = "pokemon.png";  // set fallback card image

    /**
     * Constructor for objects of class Card with a given id, name, value.
     * Uses a default image if the path is null.
     */
    public Card(int key, String nm, double price, String img) {
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
    public Card(int key, String nm, double price) {
      this(key, nm, price, DEFAULT_IMAGE);
    }
    
    /**
     * Check if the mouse's click (mx, my) is within the image bounds.
     * @return true if (mx, my) is within the card area. Returns false if 
     * (mx, my) is NOT within the card area.
     */
    public boolean onCard(double mx, double my, double x, double y, double width, double height) {
      return ((mx >= x && mx <= x + width) && (my >= y && my <= y + height));
    }
    
    /**
     * Draw the card's image on the GUI.
     */
    public void display(double x, double y, double width, double height, double textSpacing) {
      UI.drawImage(this.image, x, y, width, height);
      UI.drawString("Card " + this.id + " Details: ", x, y + height + textSpacing);
      UI.drawString(this.name + ", $" + String.format("%.2f", this.value), 
                        x, y + height + 2 * textSpacing);
    }
    
    /**
     * @return the card's id.
     */
    public int getId() {
      return this.id;
    }
    
    /**
     * @return the card's name.
     */
    public String getName() {
      return this.name;
    }
    
    /**
     * @return the card's image path.
     */
    public String getImage() {
      return this.image;
    }
    
    /**
     * @return the card's monetary value.
     */
    public double getValue() {
      return this.value;
    }
}
