package spiders.views;

import java.awt.Graphics;
import java.awt.Point;
import spiders.figure.Holder;
import spiders.figure.Rain;
import spiders.figure.Stone;

/**
 * Appearance of holder
 * @author anhcx
 */
public class HolderView extends ObjectView{
    
    private static final String STONE_IMG = ROOT.concat("\\image\\rock.png");
    private static final String RAIN_IMG = ROOT.concat("\\image\\drop.png");

    /**
     * Constructor with a holder.
     * @param obj holder.
     */
    public HolderView(Holder obj) {
        super(obj);
        
        if (obj instanceof Stone)
            getImageFrom(STONE_IMG);
        else 
            getImageFrom(RAIN_IMG);
    }

    @Override
    public void drawOn(Graphics g, GamePanel p) {
        drawImage(g, p);
    }
    
}
