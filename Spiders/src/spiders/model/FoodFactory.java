/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.model;
import java.util.ArrayList;
import java.util.Random;
import spiders.figure.Fly;
import spiders.figure.Grasshopper;
import spiders.figure.Mosquito;
import spiders.figure.SpiderFood;
import spiders.figure.Wasp;

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
     * @param number - number food want to create
     */
    public ArrayList<SpiderFood> createFood(int number) {
        ArrayList<SpiderFood> ret = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Random rnd = new Random();
            
            int f = rnd.nextInt(15) + 1;
            
            ret.add(getFood(f));
        }
        return ret;
    }
    
    protected SpiderFood getFood(int n) {
        if (n >= 1 && n <= 10) {
            Random random = new Random();
            if (random.nextBoolean())
                return new Fly(_gameModel.field());
            else 
                return new Mosquito(_gameModel.field());
        }
        
        if (n >= 11 && n <= 13)
            return new Wasp(_gameModel.field());
        
        return new Grasshopper(_gameModel.field());
    }
    
}
