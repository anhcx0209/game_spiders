package spiders.model;
import java.util.ArrayList;
import spiders.figure.SpiderFood;

/**
 * Factory to generate food.
 * @author anhcx
 */
public class FoodFactory {
    private GameModel _gameModel;
    
    /**
     * Create a food factory.
     * @param gm
     */
    public FoodFactory(GameModel gm) {
        _gameModel = gm;
    }
    
    /**
     * Generate more food.
     */
    public ArrayList<SpiderFood> createFood(int number) {
        ArrayList<SpiderFood> ret = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            SpiderFood sf = new SpiderFood(_gameModel.field());
            
            if (sf.failIntoWeb()) {
                sf.setName("Bug " + i);
                ret.add(sf);
            }
        }
        return ret;
    }
    
}
