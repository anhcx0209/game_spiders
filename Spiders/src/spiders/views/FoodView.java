package spiders.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import spiders.figure.SpiderFood;

/**
 * Appearance of spider foods.
 * @author anhcx
 */
public class FoodView extends ObjectView {

    private static final String FOOD_IMG_1 = ROOT.concat("\\image\\fly.png");
    private static final String FOOD_IMG_2 = ROOT.concat("\\image\\grass.png");
    
    /**
     * Constructor with spider food.
     * @param obj spider food.
     */
    public FoodView(SpiderFood obj) {
        super(obj);
        
        if (obj.size() < 7)
            getImageFrom(FOOD_IMG_1);
        else 
            getImageFrom(FOOD_IMG_2);
    }

    @Override
    public void drawOn(Graphics g, GamePanel p) {
        drawImage(g, p);
        SpiderFood s = (SpiderFood)object();
        Point pos = p.coordPoint(object().position());
        String str = "" + s.size();
        g.setColor(Color.RED);
        g.drawString(str, pos.x + (5 * p.cellSize())/8, pos.y + (2 * p.cellSize())/4 + p.fontHeight());
    }
    
}
