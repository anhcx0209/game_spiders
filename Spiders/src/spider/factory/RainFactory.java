package spider.factory;

import java.util.ArrayList;
import spiders.figure.Computer;
import spiders.figure.Rain;
import spiders.model.GameModel;

/**
 * Factory to generate rain.
 * @author anhcx
 */
public class RainFactory extends ObjectFactory {

    /**
     * Constructor with a model.
     * @param gm game model.
     */
    public RainFactory(GameModel gm) {
        super(gm);
    }
    
    
    /**
     * Create n drop rain on the first line of cobweb.
     * Position is randomly selected.
     * @param n number of rain will be created.
     * @return list of rains.
     */
    public ArrayList<Rain> createRains(int n) {
        ArrayList<Rain> ret = new ArrayList<>();
        
        
        for (int i = 1; i <= n; i++) {
            // create rain
            Rain r = new Rain(model().field());
            r.setPosition(model().field().getFreePosition(1));
            
            // add spider listener
            model().player().addSAL(r);
            for (Computer com : model().computers()) {
                com.addSAL(r);
            }
            
            model().field().addObject(r);
            
            ret.add(r);
            model().addGML(r);
        }
        
        return ret;
    }
}