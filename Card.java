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
   *
   * @param mx      x position of the mouse click
   * @param my      y position of the mouse click
   * @param x       x boundary (left side) of the card
   * @param y       y boundary (top) of the card
   * @param width   width of the card
   * @param height  height of the card
   * @return true if (mx, my) is within the card area; false otherwise
   */
  public boolean onCard(double mx, double my, double x, double y, double width, double height) {
    return ((mx >= x && mx <= x + width) && (my >= y && my <= y + height));
  }
    
  /**
   * Draw the card's image and info on the GUI.
   *
   * @param x           x positioning of the card (top left corner)
   * @param y           y positioning of the card (top left corner)
   * @param width       width of the card
   * @param height      height of the card
   * @param textSpacing vertical spacing of the info text
   */
  public void display(double x, double y, double width, double height, double textSpacing) {
    UI.drawImage(this.image, x, y, width, height);
    // draw text info below the image
    UI.drawString("Card " + this.id + " Details: ", x, y + height + textSpacing);
    UI.drawString(this.name + ", $" + String.format("%.2f", this.value), 
                       x, y + height + 2 * textSpacing);
  }
    
  /**
   * Retrieve the Pokemon card's id number.
   *
   * @return id
   */
  public int getId() {
    return this.id;
  }
    
  /**
   * Retrieve the Pokemon card's name.
   *
   * @return name
   */
  public String getName() {
    return this.name;
  }
    
  /**
   * Retrieve the Pokemon card's image path.
   *
   * @return image.
   */
  public String getImage() {
    return this.image;
  }
    
  /**
   * Retrieve the Pokemon card's monetary value.
   *
   * @return value
   */
  public double getValue() {
    return this.value;
  }
}
