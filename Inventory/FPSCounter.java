import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>
 * This is the implementation of the FPSCounter modular widget actor.
 * </p>
 * 
 * <p>
 * The FPSCounter is a Greenfoot Actor that displays the FPS of a program, to a max of 60fps.
 * It has customizable height, width, font size, font family, text color, background color,
 * and whether or not it is transparent or not.
 * </p>
 * 
 * @author <p>Andrew Qiao</p>
 * @version 1.1
 */
public class FPSCounter extends Actor
{
    //Instance variables for FPSCounter class
    private int height; //The height of the FPSCounter actor
    private int width; //The width of the FPSCounter actor
    
    private Color textColor; //The color of the text
    private Color backgroundColor; //The color of the background
    private Font textFont; //The font of the text
    
    private GreenfootImage fpsCounter; //The image where everything is displayed
    
    private String text; //The text that is written onto the display
    
    private int actCount; //Variable that counts the number of acts that have passed in a second

    private long nextSecond; //Variable for exactly 1000 milliseconds in the future
    
    private int lastSecondFrames; //Frames that have passed in the last second
    private int currentSecondFrames; //Frames that have passed in the current second
    
    private String prefix; //Prefix of the displayed String
    
    final int maxFrames = 60;
    
    /**
     * Main constructor - The basic constructor that sets default values. Simple, but not flexible.
     */
    public FPSCounter()
    {
        this(160, 40, 30, "Serif", 255, 255, 255, 0, 0, 0, true, "FPS: ");
    }
    
    /**
     * isShowing constructor - Is passed a boolean, determining whether the FPSCounter will be hidden or not.
     * 
     * @param isShowing     A boolean variable. Determines if the FPSCounter will be hidden or not
     */
    public FPSCounter(boolean isShowing)
    {
        this(160, 40, 30, "Serif", 255, 255, 255, 0, 0, 0, isShowing, "FPS: ");
    }
    
    /**
     * Simple constructor - User specifies the width, height, and fontSize of the FPSCounter.
     * 
     * @param width         Integer variable for the width of the FPSCounter.
     * @param height        Integer variable for the height of the FPSCounter.
     * @param fontSize      Integer variable for the font size of the text.
     */
    public FPSCounter(int width, int height, int fontSize)
    {
        this(width, height, fontSize, "Serif", 255, 255, 255, 0, 0, 0, true, "FPS: ");
    }
    
    /**
     * The king FPSCounter constructor - Is passed multiple parameters, making it the most flexible.
     * 
     * @param width         Integer variable for the width of the FPSCounter.
     * @param height        Integer variable for the height of the FPSCounter.
     * @param fontSize      Integer variable for the font size of the text.
     * @param fontName      String variable for the name of the font for the text.
     * @param backgroundR   Integer variable for the Red value of the font color for the background.
     * @param backgroundG   Integer variable for the Green value of the font color for the background.
     * @param backgroundB   Integer variable for the Blue value of the font color for the background.
     * @param textR         Integer variable for the Red value of the font color for the text.
     * @param textG         Integer variable for the Green value of the font color for the text.
     * @param textB         Integer variable for the Blue value of the font color for the text.
     * @param isShowing     Boolean variable for whether or not the FPSCounter will be transparent or not.
     * @param prefix        The text that will be displayed before the FPS number (FPS: )
     */
    public FPSCounter(int width, int height, int fontSize, String fontName, int backgroundR, int backgroundG, int backgroundB, int textR, int textG, int textB, boolean isShowing, String prefix)
    {
        fpsCounter = new GreenfootImage(width, height);
        
        backgroundColor = new Color(backgroundR, backgroundG, backgroundB);
        textColor = new Color(textR, textG, textB);
        
        textFont = new Font(fontName, fontSize);
        
        fpsCounter.setColor(backgroundColor);
        fpsCounter.fill();
        
        this.setImage(fpsCounter);
        
        fpsCounter.setFont(textFont);
        
        this.width = width;
        this.height = height;
        
        actCount = 0;
        
        nextSecond = System.currentTimeMillis() + 1000;
        lastSecondFrames = 0;
        currentSecondFrames = 0;
        
        if (!isShowing) {
            fpsCounter.setTransparency(0);
        }
        
        this.prefix = prefix;
    }
    
    /**
     * update Method:
     * 
     * No parameters
     * 
     * Calculates the FPS of the past second, and draws to the display accordingly.
     */
    public void update()
    {
        actCount++;
        
        long currentTime = System.currentTimeMillis();
        if (currentTime > nextSecond) {
            nextSecond += 1000;
            lastSecondFrames = currentSecondFrames;
            currentSecondFrames = 0;
        }
        
        currentSecondFrames++;
        
        if (lastSecondFrames > maxFrames) {
            text = prefix + maxFrames;
        } else {
            text = prefix + lastSecondFrames;
        }
        
        fpsCounter.setColor(backgroundColor);
        fpsCounter.fill();
        
        fpsCounter.setColor(textColor);
        fpsCounter.drawString(text, 10, 30);
    }
    
    /**
     * update Method (overloaded x2):
     * 
     * @param output    String variable determining what will be output onto the display
     */
    public void update(String output)
    {
        fpsCounter.setColor(backgroundColor);
        fpsCounter.fill();
        
        fpsCounter.setColor(textColor);
        fpsCounter.drawString(output, 10, 30);
    }
}
