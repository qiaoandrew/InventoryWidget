import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * <h1 style="color: rgb(21, 52, 80); background-color: rgb(143, 188, 219); text-align:center;">Inventory Class (Inventory Widget)</h1>
 * 
 * <p>
 * The <b>Inventory</b> is a Greenfoot Actor that can store a variable amount of items.
 * <ul>
 *      <li>It is extremely customizable, with various constructors, taking in a multitude of different parameters</li>
 *      <li>It has the purpose of creating a single, or multiple inventories, depending on how the user would like to use it</li>
 *      <li>When a new item is added, the item's picture will be added into an empty slot</li>
 *      <li>If the same item is added again, it will update the number at the bottom of the slot by the appropriate number</li>
 *      <li>However, if the slot is full, the item will not be added</li>
 *      <li>Through keyboard interactions, the user can also select slots and choose to either drop 1 count of the item, or drop all of that item</li>
 *      <li>When these events take place, the inventory will be updated accordingly</li>
 *      <li>Other classes can call its methods under different circumstances, allowing for many different possible uses</li>
 * </ul>
 * </p>
 * 
 * <h4>Methods:</h4>
 * <ul>
 *      <li>update(): The first update() method draws the inventory at its current state</li>
 *      <li>update(): The second update() method can be used to change the transparency of the inventory</li>
 *      <li>update(): The third update() method tracks how many of each item there are in an inventory</li>
 *      <li>update(): The fourth update() method changes the colours of the inventory</li>
 *      <li>dropOne(): Drops one count of the current selected item in the inventory, if any</li>
 *      <li>dropAll(): Drops all of the current selected item in the inventory, if any</li>
 *      <li>checkFull(): Checks if a certain inventory slot is full</li>
 *      <li>getNumberOfItems(): Returns the number of slots the inventory is displaying</li>
 *      <li>setSelectIndex(): Sets the select index, the slot that is currently selected</li>
 *      <li>getSelectIndex(): Returns an integer for the select index, the slot that is currently selected (<b>NOTE:</b> Starts at 0)</li>
 *      <li>getIsFull(): Returns a boolean for whether or not the inventory is currently full in terms of slots</li>
 *      <li>getClasses(): Returns an ArrayList for the classes that are currently in the inventory in order</li>
 *      <li>getNumbers(): Returns an array holding the number of items in each slot</li>
 * </ul>
 * 
 * <h4>How To Use:</h4>
 * <ol>
 *      <li>Initialize an Inventory Greenfoot Actor (See constructors)</li>
 *      <li>Add the Actor to your World</li>
 *      <li>Make sure you have an Actor for the player (In this case, Trainer)</li>
 *      <li>Make sure you have an Actor(s) for items (In this case, Bow, Cake, etc.)</li>
 *      <li>Inside the player Actor, check for collision with the item Actor(s)</li>
 *      <li>If there is a collision with an item:</li>
 *      <ul>
 *          <li>Pass the class of the item into the checkFull(class ObjectClass) method, returning a boolean, to check if the inventory is full of that item. In the attached demonstration, nothing happens if it is full</li>
 *          <li>Next, check if the inventory's slots are currently full using getIsFull(), which returns a boolean</li>
 *          <ul>
 *              <li>If it is, check if the inventory currently contains the item's class, using getClasses().contains(Class objectClass) which returns a boolean</li>
 *              <ul>
 *                  <li>If true is returned, use update(Class objectClass, GreenfootImage image, int number) to add the item's class, image, and number of items into the inventory</li>
 *                  <li>Remove the item from the world</li>
 *              </ul>
 *              <li>If the inventory's slots are not full, use update(Class objectClass, GreenfootImage image, int number) to add the item's class, image, and number of items into the inventory and remove the item from the world</li>
 *          </ul>
 *          <li><b>NOTE:</b> Each of these situations can be modified, depending on the game functions</li>
 *      </ul>
 *      <li>Determine which keys will be used to select slots (In the example demonstration, 1 is the first slot, 2 is the second slot... 0 is the tenth slot)</li>
 *      <ul>
 *          <li><b>NOTE:</b> You may add as many slots as you wish, as long as keys are allocated for the selection of the slots</li>
 *      </ul>
 *      <li>Determine which key will be used to drop 1 count of the currently selected slot (In the example demonstration, it is "p")</li>
 *      <li>Determine which key will be used to drop all of the currently selected slot (In the example demonstration, it is "o")</li>
 *      <li>When the drop keys are pressed check for these 3 conditions</li>
 *      <ul>
 *          <li>Use getSelectIndex() to return the current selected slot, make sure it is not -1 (-1 means nothing is selected)</li>
 *          <li>Make sure getSelectIndex() returns a value less than getClasses().size()</li>
 *          <li>Use getClasses().isEmpty() to make sure the ArrayList containing the classes is not empty</li>
 *          <li><b>NOTE:</b> The specifics for these conditions may change depending on how your game uses its inventory</li>
 *          <li><b>NOTE:</b> These 3 conditions are checked within the drop methods, however, checking them outside of the inventory class will allow you to know if you should find which item will be dropped, in order to add it back to your world</li>
 *      </ul>
 *      <li>When an object is removed, the programmer has two choices:</li>
 *      <ul>
 *          <li>"Destroy" the object, by not re-adding it into the World</li>
 *          <li>Determine which object it was by calling getClasses().get(inventory.getSelectIndex()) to find the class of the dropped item and re-add it into the World at a certain location</li>
 *          <li><b>NOTE:</b> Re-add the item away from the player, otherwise, it will likely be instantly picked up unless extra conditions are set on the item</li>
 *      </ul>
 *      <li>update() can be used at any time to update the inventory's graphics if any graphics need to be changed</li>
 *      <li>update(int transparency) can be used to change the transparency of the inventory</li>
 *      <li>update(int backgroundR, int backgroundG, int backgroundB, int outlineR, int outlineG, int outlineB, int selectR, int selectG, int selectB, int numberColorR, int numberColorG, int numberColorB) can be used to change the colours of the inventory</li>
 *      <li>With the basics set up, the Inventory can then be further modified to suit any addition in-game functions!</li>
 * </ol>
 * 
 * <h4>Possible Uses:</h4>
 * <ul>
 *      <li>Scavenger hunt type game, where the player has to find a certain number of items</li>
 *      <li>Pokemon style game, where the player goes around a map, finding items to advance in the game</li>
 *      <li>Sandbox game, where the player can amass items and use them in certain situations</li>
 *      <ul>
 *          <li>You can call methods to determine what the user is currently selecting, allowing you to have the user's avatar hold an object, like in Minecraft</li>
 *      <li>The class can further be specified for certain games, allowing for more game-specific interactions</li>
 *      <ul>
 *          <li>Allow players to "use up" certain items</li>
 *          <li>Have the max number of items a slot can hold be different for different items (i.e. 1 sword takes up an entire slot)</li>
 *      </ul>
 * </ul>
 * 
 * <h4>Next Steps:</h4>
 * <ul>
 *      <li><b>Implemented in 1.2!</b> Add a scaling feature to scale the image if they are too big or too small</li>
 *      <li><b>Implemented in 1.4!</b> Add drop feature</li>
 *      <li><b>Implemented in 1.3!</b> Allow users to specify max number of item types to be lower than the total number of items</li>
 *      <li><b>Implemented in 1.3!</b> Add a transparency feature for other classes to change the transparency of the inventory</li>
 *      <li>Add a sound effect feature, where the programmer can input a sound file to be played every time an object is picked up</li>
 * </ul>
 * 
 * @author <b>Andrew Qiao</b>
 * @version <b>1.4</b>
 */
public class Inventory extends Actor
{
    //Instance variables and objects
    private GreenfootImage inventory; //Image where everthing will be drawn onto
    
    private Color background; //Color of the background
    private Color outline; //Color of the outline of the images
    private Color select; //Color of the outline of the images when its slot is selected
    private Color numberColor; //Color of the numbers
    
    private Font numberFont; //Font of the numbers
    
    private ArrayList<Class> classes; //ArrayList containing the classes of items that are in the inventory in order
    private ArrayList<GreenfootImage> images; //ArrayList containing the images of the items that are in the inventory in order
    
    private int[] numbers; //Array containing the number of items that are in the inventory in order
    
    private int height; //Height of the inventory
    private int width; //Width of the inventory
    private int numberOfItems; //Number of items the user wants the inventory to display
    private int currentIndex; //Next slot that will be populated by items
    private int margin; //Margin between the edges of the inventory and the picture
    private int imageWidth; //Width of the inventory image
    private int imageHeight; //Height of the inventory image
    private int maxCount; //Max number of items inventory can hold
    private int selectIndex; //Index of current slot that is selected
    private double scale; //Scale of the pictures
    private int transparency; //Transparency of the inventory
    private boolean isFull; //If the inventory is maxed out in terms of slots
    
    /**
     * Creates a default inventory. Not customizable, also requires the image size to be 30 pixels, therefore not very practical as well.
     */
    public Inventory()
    {
        this(30, 30, 10, 9, 99, 0, 0, 0, 100, 100, 100, 255, 255, 255, 255, 255, 255, "Serif", 5, 1, 255);
    }
    
    /**
     * Creates a slightly more customizable inventory, with image dimensions, margins, number of slots and the maximum of each item it can hold.
     * 
     * @param imageWidth        The width of the images of items that will be displayed
     * @param imageHeight       The height of the images of items that will be displayed
     * @param margin            The margin between the edge of the inventory and the image of the item
     * @param numberOfItems     The number of slots or items the inventory will hold
     * @param maxCount          The maximum number of each item the inventory can hold
     */
    public Inventory(int imageWidth, int imageHeight, int margin, int numberOfItems, int maxCount)
    {
        this(imageWidth, imageHeight, margin, numberOfItems, maxCount, 0, 0, 0, 100, 100, 100, 255, 255, 255, 255, 255, 255, "Serif", 5, 1, 255);
    }
    
    /**
     * Creates an even more customizable inventory, adds the scale and transparency.
     * 
     * @param imageWidth        The width of the images of items that will be displayed
     * @param imageHeight       The height of the images of items that will be displayed
     * @param margin            The margin between the edge of the inventory and the image of the item
     * @param numberOfItems     The number of slots or items the inventory will hold
     * @param maxCount          The maximum number of each item the inventory can hold
     * @param scale             The scale of images (0.5 times, 2 times, etc.)
     * @param transparency      The transparency of the inventory
     */
    public Inventory(int imageWidth, int imageHeight, int margin, int numberOfItems, int maxCount, double scale, int transparency)
    {
        this(imageWidth, imageHeight, margin, numberOfItems, maxCount, 0, 0, 0, 100, 100, 100, 255, 255, 255, 255, 255, 255, "Serif", 5, scale, transparency);
    }
    
    /**
     * Creates an inventory that can be customized with additional colors.
     * 
     * @param imageWidth        The width of the images of items that will be displayed
     * @param imageHeight       The height of the images of items that will be displayed
     * @param margin            The margin between the edge of the inventory and the image of the item
     * @param numberOfItems     The number of slots or items the inventory will hold
     * @param maxCount          The maximum number of each item the inventory can hold
     * @param backgroundR       The R value of the inventory's background color
     * @param backgroundG       The G value of the inventory's background color
     * @param backgroundB       The B value of the inventory's background color
     * @param outlineR          The R value of the inventory's image outline color
     * @param outlineG          The G value of the inventory's image outline color
     * @param outlineB          The B value of the inventory's image outline color
     * @param selectR           The R value of the inventory's select box color
     * @param selectG           The G value of the inventory's select box color
     * @param selectB           The B value of the inventory's select box color
     * @param numberColorR      The R value of the inventory's inventory number color
     * @param numberColorG      The G value of the inventory's inventory number color
     * @param numberColorB      The B value of the inventory's inventory number color
     */
    public Inventory(int imageWidth, int imageHeight, int margin, int numberOfItems, int maxCount, int backgroundR, int backgroundG, int backgroundB,
    int outlineR, int outlineG, int outlineB, int selectR, int selectG, int selectB, int numberColorR, int numberColorG, int numberColorB)
    {
        this(imageWidth, imageHeight, margin, numberOfItems, maxCount, backgroundR, backgroundG, backgroundB, outlineR, outlineG, outlineB, selectR, selectG, selectB, numberColorR, numberColorG, numberColorB, "Serif", 5, 1, 255);
    }
    
    /**
     * King of the inventory constructors, includes the selection of font name and size, making the inventory more visually appealing if needed.
     * 
     * @param imageWidth        The width of the images of items that will be displayed
     * @param imageHeight       The height of the images of items that will be displayed
     * @param margin            The margin between the edge of the inventory and the image of the item
     * @param numberOfItems     The number of slots or items the inventory will hold
     * @param maxCount          The maximum number of each item the inventory can hold
     * @param backgroundR       The R value of the inventory's background color
     * @param backgroundG       The G value of the inventory's background color
     * @param backgroundB       The B value of the inventory's background color
     * @param outlineR          The R value of the inventory's image outline color
     * @param outlineG          The G value of the inventory's image outline color
     * @param outlineB          The B value of the inventory's image outline color
     * @param selectR           The R value of the inventory's select box color
     * @param selectG           The G value of the inventory's select box color
     * @param selectB           The B value of the inventory's select box color
     * @param numberColorR      The R value of the inventory's inventory number color
     * @param numberColorG      The G value of the inventory's inventory number color
     * @param numberColorB      The B value of the inventory's inventory number color
     * @param numberFontName    The name of the font for the inventory numbers
     * @param numberFontSize    The size of the font for the inventory numbers
     * @param scale             The scale of images (0.5 times, 2 times, etc.)
     * @param transparency      The transparency of the inventory
     */
    public Inventory(int imageWidth, int imageHeight, int margin, int numberOfItems, int maxCount, int backgroundR, int backgroundG, int backgroundB,
    int outlineR, int outlineG, int outlineB, int selectR, int selectG, int selectB, int numberColorR, int numberColorG, int numberColorB,
    String numberFontName, int numberFontSize, double scale, int transparency)
    {
        //Set the values of instance variables
        this.numberOfItems = numberOfItems;
        this.margin = margin;
        this.imageWidth = (int)(imageWidth * scale); //Scale up the image's width
        this.imageHeight = (int)(imageHeight * scale); //Scale up the image's height
        this.maxCount = maxCount;
        this.scale = scale;
        this.isFull = false;
        
        //If the transparency is within allowed range of 0 to 255 (inclusive) set it to the parameter value, otherwise, set it to 255 (opaque)
        if (transparency < 0 || transparency > 255) {
            this.transparency = 255;
        } else {
            this.transparency = transparency;
        }    
        
        //Initalize the objects
        classes = new ArrayList<Class>();
        images = new ArrayList<GreenfootImage>();
        numbers = new int[numberOfItems];
        
        //Set the colors
        background = new Color(backgroundR, backgroundG, backgroundB);
        outline = new Color(outlineR, outlineG, outlineB);
        select = new Color(selectR, selectG, selectB);
        numberColor = new Color(numberColorR, numberColorG, numberColorB);
        
        //Set the font
        numberFont = new Font(numberFontName, numberFontSize);
        
        //Set the currentIndex to 0
        currentIndex = 0;
        
        //Set selectIndex to -1 (nothing is selected right now)
        selectIndex = -1;
        
        //Calculate the entire height and width of the inventory
        height = this.imageHeight + margin * 2 + 20;
        width = this.imageWidth * numberOfItems + margin * (numberOfItems + 1);
        
        //Initalize the inventory and call update() to draw all the boxes and numbers
        inventory = new GreenfootImage(width, height);
        update();
    }
    
    /**
     * Updates the inventory by resetting the image, drawing the image boxes that will contain the individual item images, the item images, the selection box (if applicable), and number of items in each slot.
     * It will also set the transparency of the image, and set it for display.
     */
    public void update()
    {
        //Reset the image to a blank, one-coloured rectangle
        inventory.setColor(background);
        inventory.fill();
        
        inventory.setColor(outline);
        
        //Draws the individual image boxes that will contain the individual object images
        int currentX = margin / 2;
        for (int i = 0; i < numberOfItems; i++) {
            inventory.drawRect(currentX, margin / 2, imageWidth + margin, imageHeight + margin);
            currentX += margin + imageWidth;
        }
        
        //Draws the images
        currentX = margin;
        for (Class objectClass : classes) {
            GreenfootImage image = images.get(classes.indexOf(objectClass));
            image.scale(imageWidth, imageHeight);
            inventory.drawImage(image, currentX, margin);
            currentX += imageWidth + margin;
        }
        
        //Draws the numbers corresonding to how many of a certain object there are
        inventory.setColor(numberColor);
        currentX = margin + imageWidth / 2;
        for (int number : numbers) {
            inventory.drawString(String.valueOf(number), currentX, this.height - 5);
            currentX += margin + imageWidth;
        }
        
        //Draws the selection box if application
        if (selectIndex != -1) {
            inventory.setColor(select);
            inventory.drawRect(imageWidth * selectIndex + margin * selectIndex + margin / 2, margin / 2, imageWidth + margin, imageHeight + margin);
        }
            
        //Sets the transparency
        inventory.setTransparency(transparency);
        
        //Sets the image for display
        this.setImage(inventory);
    }
    
    /**
     * Used by other classes to change the transparency of the inventory only if it falls into an acceptable range (0-255 inclusive).
     * Then it calls update() to redraw everything.
     * 
     * @param transparency       The transparency of the inventory
     */
    public void update(int transparency) {
        if (transparency >= 0 && transparency <= 255) {
            this.transparency = transparency;
            update();
        }   
    }
    
    /**
     * Updates the inventory classes ArrayList, images ArrayList, and number of items in each slot array, based on input from other classes.
     * Then calls update() to display the new changes.
     * 
     * @param objectClass       The class the object that was picked up belongs to
     * @param image             The image of the object that was picked up
     * @param number            The number of objects that were picked up
     */
    public void update(Class objectClass, GreenfootImage image, int number)
    {
        //If the inventory is not full
        if (currentIndex < numberOfItems) {
            if (classes.contains(objectClass)) { //If the object is already in the inventory
                numbers[classes.indexOf(objectClass)] += number; //Increment its number by the number
            } else { //If the object is not in the inventory
                //Add its class and image to their respective ArrayLists
                classes.add(objectClass);
                images.add(image);
                //Update the number of elements array
                numbers[currentIndex] = number;
                //Increment the currentIndex
                currentIndex++;
            }
            // System.out.println(Arrays.toString(numbers)); //For testing
            
            //Call update() to draw everything out
            update();
        } else { //If the inventory is full
            if (classes.contains(objectClass)) { //If the object is already in the inventory, increment its number by the number parameter
                numbers[classes.indexOf(objectClass)] += number;
            }
            // System.out.println(Arrays.toString(numbers)); //For testing
            
            //Call update() to draw everything out
            update();
            
            //The inventory is now full
            isFull = true;
        }
    }
    
    /**
     * Allows other classes to change the colours of the background, outline, selectBox, and numbers. 
     * Then calls update() to redraw everything.
     * 
     * @param backgroundR       The R value of the inventory's background color
     * @param backgroundG       The G value of the inventory's background color
     * @param backgroundB       The B value of the inventory's background color
     * @param outlineR          The R value of the inventory's image outline color
     * @param outlineG          The G value of the inventory's image outline color
     * @param outlineB          The B value of the inventory's image outline color
     * @param selectR           The R value of the inventory's select box color
     * @param selectG           The G value of the inventory's select box color
     * @param selectB           The B value of the inventory's select box color
     * @param numberColorR      The R value of the inventory's inventory number color
     * @param numberColorG      The G value of the inventory's inventory number color
     * @param numberColorB      The B value of the inventory's inventory number color
     */
    public void update(int backgroundR, int backgroundG, int backgroundB, int outlineR, int outlineG, int outlineB,
    int selectR, int selectG, int selectB, int numberColorR, int numberColorG, int numberColorB) {
        //Change the colors
        background = new Color(backgroundR, backgroundG, backgroundB);
        outline = new Color(outlineR, outlineG, outlineB);
        select = new Color(selectR, selectG, selectB);
        numberColor = new Color(numberColorR, numberColorG, numberColorB);
        
        update(); //Redraw the inventory
    }
    
    /**
     * Drops 1 count of the item in the currently selected slot, and updates the inventory accordingly.
     * If there is is only 1 item, it calls dropAll() instead.
     */
    public void dropOne()
    {
        //Make sure a slot is selected, the selected slot has something in it, and the inventory is not empty
        if (selectIndex != -1 && selectIndex < classes.size() && !classes.isEmpty()) {
            //If the number of items in that slot is greater than 1, subtract and update, otherwise, call dropAll()
            if (numbers[selectIndex] > 1) {
                numbers[selectIndex] -= 1;
                update();
            } else {
                dropAll();
            }
        }
    }
    
    /**
     * Drops all of the items in the currently selected slot.
     * This includes removing the class from its ArrayList, image from its ArrayList, and moving values in the numbers array into their new positions.
     * Finishes by updating the inventory.
     */
    public void dropAll()
    {
        //Make sure a slot is selected, the selected slot has something in it, and the inventory is not empty
        if (selectIndex != -1 && selectIndex < classes.size() && !classes.isEmpty()) {
            classes.remove(selectIndex); //Remove the class from its ArrayList
            images.remove(selectIndex); //Remove the image from its ArrayList
            currentIndex -= 1; //Decrease the next slot to be filled by 1, since 1 slot just got emptied
            
            for (int i = selectIndex; i < numbers.length - 1; i++) {
                numbers[i] = numbers[i + 1];
            }
            
            //Inventory is not full
            isFull = false;
            
            //Last slot is now empty
            numbers[numberOfItems - 1] = 0;
    
            update(); //Update the inventory to show new changes
        }
    }
    
    /**
     * Checks if the inventory is full of a certain item, returning true or false accordingly.
     * 
     * @param objectClass       The class the object that was picked up belongs to
     * @return boolean          True if the inventory is full of that item, false if not.
     */
    public boolean checkFull(Class objectClass)
    {
        //If the inventory contains the class, check if the number of that item is equal to the max number of items, return true or false accordingly
        if (classes.indexOf(objectClass) != -1) {
            
            if (numbers[classes.indexOf(objectClass)] >= maxCount) {
                return true;
            } else {
                return false;
            }
            
        } else { //If the inventory does not contain the class, return false
            return false;
        }
    }
    
    /**
     * Returns the number of items the inventory can hold, as specified by the user.
     * 
     * @return int        The number of items the inventory can hold
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }
    
    /**
     * Sets the currently selected slot within the inventory, within the possible range.
     * 
     * @param selectIndex       The index of the slot to be selected
     */
    public void setSelectIndex(int selectIndex) {
        if (selectIndex >= 0 && selectIndex < numberOfItems) {
            this.selectIndex = selectIndex;
            update();
        }
    }
    
    /**
     * Returns the currently selected slot index.
     * 
     * @return int      Index of the slot that is currently selected
     */
    public int getSelectIndex() {
        return selectIndex;
    }
    
    /**
     * Returns whether or not the inventory is full in terms of slots.
     * 
     * @return boolean      True if the inventory is full, false if not
     */
    public boolean getIsFull() {
        return isFull;
    }
    
    /**
     * Returns an ArrayList of the classes currently in the inventory in order.
     * 
     * @return ArrayList<Class>     ArrayList of classes currently in the inventory in order
     */
    public ArrayList<Class> getClasses() {
        return classes;
    }
    
    /**
     * Returns an array of the number of items currently in each slot of the inventory.
     * 
     * @return int[]        Array of the number of items currently in the inventory in order
     */
    public int[] getNumbers() {
        return numbers;
    }
}
