package spider.factory;
import java.util.ArrayList;
import spiders.figure.SpiderFood;
import spiders.model.GameModel;

/**
 * Factory to generate food.
 * @author anhcx
 */
public class FoodFactory extends ObjectFactory{
    
    /**
     * Constructor with a model.
     * @param gm - game model.
     */
    public FoodFactory(GameModel gm) {
        super(gm);
    }
    
    /**
     * Generate more food, and let them fall in the field.
     * @param n maximum number of food can be created.
     * @return set of spiders food fall into cobweb.
     */
    public ArrayList<SpiderFood> createFood(int n) {
        ArrayList<SpiderFood> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            SpiderFood sf = new SpiderFood(model().field());
            
            if (sf.failIntoWeb()) {
                ret.add(sf);
            }
        }
        return ret;
    }
    
}
