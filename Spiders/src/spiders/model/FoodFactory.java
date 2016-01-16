/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            ret.add(new SpiderFood(_gameModel.field()));
        }
        return ret;
    }
    
}
