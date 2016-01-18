package spiders.model;

import java.util.Random;

/**
 * Difficulty level of the game, its depends on some factor: size of field, 
 * number of computers will play with player, how frequently generate food 
 * after each step, number of stones, number of rains will threat spiders.
 * There are 3 different level: HARD, MEDIUM and EASY. 
 * @author anhcx
 */
public class Level {
    
    // ----------------- FREQUENCY GENERTATE FOODS---------------------
    private static final int N_FF_HARD = 10;    // FOR HARD = 10%
    private static final int N_FF_MEDIUM = 15;  // FOR MEDIUM = 15%
    private static final int N_FF_EASY = 20;    // FOR EASY = 20%
    
    // ----------------- FACTOR DERTEMINE DIFFICULTY OF GAME ---------
    private int _baseSize;              // size of field.
    private int _numberOfComputers;     // number of computers will play.
    private int _numberOfFoods;         // number of foods will appear after each step.
    private int _numberOfStones;        // number of stones in the field.
    private int _numberOfRains;         // number of rains in the field.
    private int[] _factorGenFood;       // frequency generate food after each step.
    
    /**
     * This is getter, return number of rains on the field.
     * @return number of rains on the field.
     */
    public int numberOfRain() {
        return _numberOfRains;
    }
    
    /**
     * This is getter, return number of stones on the field.
     * @return number of stones.
     */
    public int numberOfStone() {
        return _numberOfStones;
    }
    
    /**
     * This is getter, return number of foods will be generated.
     * @return number of bug must be generate per time.
     */
    public int numberOfFoods() {
        return _numberOfFoods;
    }
    
    /**
     * This is getter, return number of spiders will be controlled by computers.
     * @return number of computer in start of game.
     */
    public int numberOfComputers() {
        return _numberOfComputers;
    }
    
    /**
     * This is getter, return size of field will be generated.
     * @return size of game field.
     */
    public int baseSize() {
        return _baseSize;
    }
    
    /**
     * This method will determine generating more food after each step or not.
     * @return boolean generate or not.
     */
    public boolean spin() {
        Random rand = new Random();
        int x = rand.nextInt(100);
        for (int i = 0; i < _factorGenFood.length; i++)
            if (_factorGenFood[i] == x)
                return true;
        
        return false;
    }
    
    // --------------------- STATIC FUNCTIONS ----------------------------
    
    /**
     * Create a level with difficulty - hard.
     * @return hard level.
     */
    public static Level hard() {
        Level l = new Level();
        l._baseSize = 14;
        l._numberOfComputers = 4;
        l._numberOfFoods = 20;
        l._numberOfRains = 10;
        l._numberOfStones = 7;
        l.makeFactor(N_FF_HARD);
        return l;
    }
    
    /**
     * Create a level with difficulty - medium.
     * @return medium level.
     */
    public static Level medium() {
        Level l = new Level();
        l._baseSize = 12;
        l._numberOfComputers = 3;
        l._numberOfFoods = 10;
        l._numberOfRains = 7;
        l._numberOfStones = 5;
        l.makeFactor(N_FF_MEDIUM);
        return l;
    }
    
    /**
     * Create a level with difficulty - easy.
     * @return easy level.
     */
    public static Level easy() {
        Level l = new Level();
        l._baseSize = 9;
        l._numberOfComputers = 2;
        l._numberOfFoods = 6;
        l._numberOfRains = 4;
        l._numberOfStones = 3;
        l.makeFactor(N_FF_EASY);
        return l;
    }
    
    // ------------------ MAKE FACTOR ----------------------------
    private void makeFactor(int n) {
        _factorGenFood = new int[n]; 
        
        boolean[] flag = new boolean[100];
        Random rn = new Random();
        for (int i = 0; i < n; i++) {
            boolean ok = true;
            while (ok) {
                int k = rn.nextInt(100);
                if (!flag[k]) {
                    flag[k] = true;
                    _factorGenFood[i] = k;
                    ok = false;
                }
            }
        }
    }
}
