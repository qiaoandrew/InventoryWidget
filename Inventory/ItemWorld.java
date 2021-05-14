import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>ItemWorld is the setting of the Inventory demonstration.</p>
 * 
 * <p>
 * This World has the goal of demonstrating the usage of the Inventory actor.
 * It is a simple game, where the user moves around the World, picking up and dropping items based on keyboard input.
 * Simply moving over an item will pick it up.
 * Selecting an inventory slot using keyboard numbers 1-9 and pressing "p" will drop one count of that item.
 * Pressing "o" will drop all of the items in that slot.
 * These default keyboard interactions can be reconfigured to your liking!
 * All item changes will be displayed in the inventory accordingly.
 * </p>
 * 
 * <p>
 * Creates an Inventory Actor, which will act as the inventory.
 * It also creates a Trainer Actor, which will be controlled by the user. Objects that the Trainer touches will be displayed in the inventory.
 * </p>
 * 
 * <h4>Methods:</h4>
 * <ul>
 *      <li>act(): Called every act, and checks for keyboard input accordingly</li>
 *      <li>spawnItems(): Randomly spawns the various items in the World</li>
 * </ul>
 * 
 * <h4>Credits:</h4>
 * <ul>
 *      <li>Nintendo Co., Ltd. for the image of Trainer</li>
 *      <li>Mojang Studios for the images of the various items</li>
 * </ul>
 * 
 * @author <b>Andrew Qiao</b>
 * @version <b>1.1</b>
 */
public class ItemWorld extends World
{
    //Decalre variables and objects
    private GreenfootImage image; //Background image
    
    private Trainer trainer; //Trainer that the user controls
    private FPSCounter fpsCounter; //FPSCounter that displays the FPS
    private Inventory inventory; //Inventory that displays the user's inventory
    
    private int randomize; //Random number generated to determine which item to spawn, if any
    
    final int spawnRate = 600; //Spawn rate for items in the World
    
    /**
     * Constructor for MyWorld. Creates all starting Actors, sets the background image, and sets the paint order.
     */
    public ItemWorld()
    {    
        // Create a new world with 960x600 cells with a cell size of 1x1 pixels
        super(960, 600, 1); 
        
        //Set the background image
        image = new GreenfootImage("background.png");
        setBackground(image);
        
        //Create and add the inventory to ItemWorld
        inventory = new Inventory(30, 30, 10, 9, 64, 41, 64, 82, 143, 188, 219, 248, 228, 204, 248, 228, 204, "Helvetica", 5, 1.25, 200);
        addObject(inventory, this.getBackground().getWidth() / 2, 555);
        
        //Create and add the trainer to ItemWorld
        trainer = new Trainer(2, inventory);
        addObject(trainer, 480, 300);
        
        //Create and add the fpsCounter to ItemWorld
        fpsCounter = new FPSCounter(140, 40, 30, "Garamond", 216, 223, 203, 73, 166, 166, true, "FPS: ");
        addObject(fpsCounter, 70, 20);
        fpsCounter.update("FPS: -");
        
        //Set the paint order of the classes (which shows on top of which)
        setPaintOrder(FPSCounter.class, Inventory.class, Trainer.class, Item.class);
    }
    
    /**
     * Called every act, spawns items, updates the FPSCounter and checks for keyboard input and acts accordingly.
     */
    public void act()
    {
        spawnItems(); //Call the method to spawn items
        
        fpsCounter.update(); //Update the FPSCounter
        
        //Prevents a method to be called multiple times when a key is pressed once
        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("p")) { //The "p" key will be used to drop 1 of a selected item
                
                //Dropping an item only works if a slot is selected, the selected slot is not empty and the ArrayList of classes is not empty
                if (inventory.getSelectIndex() != -1 && inventory.getSelectIndex() < inventory.getClasses().size() && !inventory.getClasses().isEmpty()) {
                    
                    //Create the object that is dropped and add it to the World
                    Class objectClass = inventory.getClasses().get(inventory.getSelectIndex());
                    if (objectClass.equals(Bow.class)) {
                        addObject(new Bow(), trainer.getX(), trainer.getY() + 50);
                    } else if (objectClass.equals(Bucket.class)) {
                        addObject(new Bucket(), trainer.getX(), trainer.getY() + 50);
                    } else if (objectClass.equals(Cake.class)) {
                        addObject(new Cake(), trainer.getX(), trainer.getY() + 50);
                    } else if (objectClass.equals(Diamond.class)) {
                        addObject(new Diamond(), trainer.getX(), trainer.getY() + 50);
                    } else if (objectClass.equals(EnderPearl.class)) {
                        addObject(new EnderPearl(), trainer.getX(), trainer.getY() + 50);
                    } else if (objectClass.equals(Pickaxe.class)) {
                        addObject(new Pickaxe(), trainer.getX(), trainer.getY() + 50);
                    } else if (objectClass.equals(Redstone.class)) {
                        addObject(new Redstone(), trainer.getX(), trainer.getY() + 50);
                    } else if (objectClass.equals(Steak.class)) {
                        addObject(new Steak(), trainer.getX(), trainer.getY() + 50);
                    } else if (objectClass.equals(Sword.class)) {
                        addObject(new Sword(), trainer.getX(), trainer.getY() + 50);
                    }
                    
                    inventory.dropOne(); //Call the remove to drop one of the item
                }
                
            } else if (key.equals("o")) {
                
                //Dropping an item only works if a slot is selected, the selected slot is not empty and the ArrayList of classes is not empty
                if (inventory.getSelectIndex() != -1 && inventory.getSelectIndex() < inventory.getClasses().size() && !inventory.getClasses().isEmpty()) {
                    
                    Class objectClass = inventory.getClasses().get(inventory.getSelectIndex()); //Get the class to be dropped
                    int number = inventory.getNumbers()[inventory.getSelectIndex()]; //Get the number of items that will be dropped
                    
                    //Determine which item to create and use a for loop to create the items
                    if (objectClass.equals(Bow.class)) {
                        for (int i = 0; i < number; i++) {
                            addObject(new Bow(), trainer.getX(), trainer.getY() + 50);
                        }
                    } else if (objectClass.equals(Bucket.class)) {
                        for (int i = 0; i < number; i++) {
                            addObject(new Bucket(), trainer.getX(), trainer.getY() + 50);
                        }
                    } else if (objectClass.equals(Cake.class)) {
                        for (int i = 0; i < number; i++) {
                            addObject(new Cake(), trainer.getX(), trainer.getY() + 50);
                        }
                    } else if (objectClass.equals(Diamond.class)) {
                        for (int i = 0; i < number; i++) {
                            addObject(new Diamond(), trainer.getX(), trainer.getY() + 50);
                        }
                    } else if (objectClass.equals(EnderPearl.class)) {
                        for (int i = 0; i < number; i++) {
                            addObject(new EnderPearl(), trainer.getX(), trainer.getY() + 50);
                        }
                    } else if (objectClass.equals(Pickaxe.class)) {
                        for (int i = 0; i < number; i++) {
                            addObject(new Pickaxe(), trainer.getX(), trainer.getY() + 50);
                        }
                    } else if (objectClass.equals(Redstone.class)) {
                        for (int i = 0; i < number; i++) {
                            addObject(new Redstone(), trainer.getX(), trainer.getY() + 50);
                        }
                    } else if (objectClass.equals(Steak.class)) {
                        for (int i = 0; i < number; i++) {
                            addObject(new Steak(), trainer.getX(), trainer.getY() + 50);
                        }
                    } else if (objectClass.equals(Sword.class)) {
                        for (int i = 0; i < number; i++) {
                            addObject(new Sword(), trainer.getX(), trainer.getY() + 50);
                        }
                    }
                    
                    inventory.dropAll(); //Call the remove to drop all of the item
                }
            }
            
            //Checks the selection key for slots 1 through 9
            for (int i = 0; i < inventory.getNumberOfItems(); i++) {
                if (key.equals(String.valueOf(i + 1))) {
                    inventory.setSelectIndex(i);
                }
            }
            
            //Checks the selection key for slot 10
            if (inventory.getNumberOfItems() == 10) {
                if (key.equals("0")) {
                    inventory.setSelectIndex(9);
                }
            }
        }
    }
    
    /**
     * Determines which item to spawn and where, if any.
     */
    private void spawnItems()
    {
        //Generate the random number
        randomize = Greenfoot.getRandomNumber(spawnRate);
        
        //Randomly generate its x and y coordinates
        int xCoordinate = Greenfoot.getRandomNumber(961);
        int yCoordinate = Greenfoot.getRandomNumber(601);
        
        //Determine which object to add, if any, and add them at the determined coordinates
        if (randomize == 0) {
            addObject(new Bow(), xCoordinate, yCoordinate);
        } else if (randomize == 1) {
            addObject(new Bucket(), xCoordinate, yCoordinate);
        } else if (randomize == 2) {
            addObject(new Cake(), xCoordinate, yCoordinate);
        } else if (randomize == 3) {
            addObject(new Diamond(), xCoordinate, yCoordinate);
        } else if (randomize == 4) {
            addObject(new EnderPearl(), xCoordinate, yCoordinate);
        } else if (randomize == 5) {
            addObject(new Pickaxe(), xCoordinate, yCoordinate);
        } else if (randomize == 6) {
            addObject(new Redstone(), xCoordinate, yCoordinate);
        } else if (randomize == 7) {
            addObject(new Steak(), xCoordinate, yCoordinate);
        } else if (randomize == 8) {
            addObject(new Sword(), xCoordinate, yCoordinate);
        }
    }
}
