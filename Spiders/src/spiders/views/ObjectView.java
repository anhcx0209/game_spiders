package spiders.views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import spiders.model.CobWebObject;


/**
 * Appearance of an objects.
 * @author anhcx
 */
public abstract class ObjectView {
    // ------------------ Root folder -----------------------
    protected static final String ROOT = System.getProperty("user.dir");
    
    // ------------------ appearance of object --------------
    private final CobWebObject _object;
    protected BufferedImage _image;
    
    /**
     * Get image of view.
     * @return image of view.
     */
    public BufferedImage image() {
        return _image;
    }
    
    /**
     * Constructor with an object.
     * @param obj object.
     */
    protected ObjectView(CobWebObject obj) {
        _object = obj;
    }
    
    /**
     * Getter for object variable.
     * @return object.
     */
    protected CobWebObject object() {
        return _object;
    }
    
    /**
     * Load image form a file given by path.
     * @param path - file name of image.
     */
    protected void getImageFrom(String path) {
        try {
            _image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Draw view on game panel.
     * @param g - graphics use to draw.
     * @param p - game panel.
     */
    public abstract void drawOn(Graphics g, GamePanel p);
}
