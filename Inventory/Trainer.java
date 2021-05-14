import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util package

/**
 * <h1 style="color: rgb(21, 52, 80); background-color: rgb(206, 235, 251); text-align:center;">Trainer Class</h1>
 * 
 * <p>
 * The Trainer class is an Actor that can move left, right, up, or down based on keyboard input.
 * It also constantly checks for collision with items to pick them up and put them into the inventory.
 * </p>
 * 
 * <h4>Methods:</h4>
 * <ul>
 *      <li>act(): Called every act, checks for keyboard input and calls checkCollision()</li>
 *      <li>checkCollision(): Called every act, checks if the Trainer comes into contact with any item, and if so, adds it to the inventory</li>
 * </ul>
 * 
 * @author <b>Andrew Qiao</b>
 * @version 1.1
 */
public class Trainer extends Actor
{
    //Instance variables
    private GreenfootImage image; //Image for the trainer
    private int speed; //How fast the Trainer moves
    private int offset; //Offset for calculating collisions with items
    private Inventory inventory;
    
    public Trainer(int speed, Inventory inventory)
    {
        image = new GreenfootImage("trainerdown.png");
        setImage(image); 
        this.inventory = inventory;
        
        this.speed = speed;
    }
    
    /**
     * Called every act. Checks for keyboard input for directions, and moves accordingly. Also calls checkCollision().
     */
    public void act() 
    {
        if (Greenfoot.isKeyDown("w")) {
            setImage("trainerup.png");
            setLocation(getX(), getY() - speed);
        } else if (Greenfoot.isKeyDown("s")) {
            setImage("trainerdown.png");
            setLocation(getX(), getY() + speed);
        } else if (Greenfoot.isKeyDown("a")) {
            setImage("trainerleft.png");
            setLocation(getX() - speed, getY());
        } else if (Greenfoot.isKeyDown("d")) {
            setImage("trainerright.png");
            setLocation(getX() + speed, getY());
        }
        
        checkCollision();
    }
    
    /**
     * Checks if the Trainer comes into contact with any item on all 4 sides, and acts accordingly.
     */
    private void checkCollision()
    {
        //Check the right of the trainer
        for (int y = -this.getImage().getHeight() / 2; y < this.getImage().getHeight() / 2; y++) {
            Item i = (Item)getOneObjectAtOffset(this.getImage().getWidth() / 2, y, Item.class);
            
            if (i != null && !inventory.checkFull(i.getClass())) {
                if (inventory.getIsFull()) {
                    if (inventory.getClasses().contains(i.getClass())) {
                        inventory.update(i.getClass(), i.getImage(), 1);
                        getWorld().removeObject(i);
                    }
                } else {
                    inventory.update(i.getClass(), i.getImage(), 1);
                    getWorld().removeObject(i);
                }
            }
        }
        
        //Check the left of the trainer
        for (int y = -this.getImage().getHeight() / 2; y < this.getImage().getHeight() / 2; y++) {
            Item i2 = (Item)getOneObjectAtOffset(-this.getImage().getWidth() / 2, y, Item.class);
            
            if (i2 != null && !inventory.checkFull(i2.getClass())) {
                if (inventory.getIsFull()) {
                    if (inventory.getClasses().contains(i2.getClass())) {
                        inventory.update(i2.getClass(), i2.getImage(), 1);
                        getWorld().removeObject(i2);
                    }
                } else {
                    inventory.update(i2.getClass(), i2.getImage(), 1);
                    getWorld().removeObject(i2);
                }
            }
        }
        
        //Check the bottom of the trainer
        for (int x = -this.getImage().getWidth() / 2; x < this.getImage().getWidth() / 2; x++) {
            Item i3 = (Item)getOneObjectAtOffset(x, -this.getImage().getHeight() / 2, Item.class);
            
            if (i3 != null && !inventory.checkFull(i3.getClass())) {
                if (inventory.getIsFull()) {
                    if (inventory.getClasses().contains(i3.getClass())) {
                        inventory.update(i3.getClass(), i3.getImage(), 1);
                        getWorld().removeObject(i3);
                    }
                } else {
                    inventory.update(i3.getClass(), i3.getImage(), 1);
                    getWorld().removeObject(i3);
                }
            }
        }
            
        //Check the top of the Trainer
        for (int x = -this.getImage().getWidth() / 2; x < this.getImage().getWidth() / 2; x++) {
            Item i4 = (Item)getOneObjectAtOffset(x, this.getImage().getHeight() / 2, Item.class);
            
            if (i4 != null && !inventory.checkFull(i4.getClass()) && !inventory.getIsFull()) {
                if (inventory.getIsFull()) {
                    if (inventory.getClasses().contains(i4.getClass())) {
                        inventory.update(i4.getClass(), i4.getImage(), 1);
                        getWorld().removeObject(i4);
                    }
                } else {
                    inventory.update(i4.getClass(), i4.getImage(), 1);
                    getWorld().removeObject(i4);
                }
            }
        }
    }
}
