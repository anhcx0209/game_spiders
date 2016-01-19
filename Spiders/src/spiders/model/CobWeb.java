package spiders.model;

import java.util.ArrayList;
import java.util.Random;
import spiders.figure.Rain;
import spiders.figure.Spider;
import spiders.figure.SpiderFood;
import spiders.figure.Stone;
import spiders.model.CobWebObject.TypeObject;
import spiders.navigations.Position;

/**
 * Cobweb is game field with n x n position. 
 * Spider, spider food, stone, rain are placed on it.
 * @author anhcx
 */
public class CobWeb {
    
    // ------------------ size --------------------
    private int _size;              // size of cobweb
    int[][] _mark;                  // _mark[i][j] is number of objects in row i, column j.
    
    CobWeb(int s) {
        _size = s;
        
        // reset mark array
        _mark = new int[_size + 1][_size + 1];
        for (int i = 0; i < _size + 1; i++)
            for (int j = 0; j < _size + 1; j++)
                _mark[i][j] = 0;
    }
    
    /**
     * Getter of size.
     * @return size of cobweb.
     */
    public int size() {
        return _size;
    }
    
    /**
     * Capture food form a list of foods.
     * @param foods - list of food outside the web.
     */
    void captureFoods(ArrayList<SpiderFood> foods) {
        int count = 0;
        for (SpiderFood sf : foods) {
            if (sf.failIntoWeb()) {
                count++;
                Position newPos = getFreePosition();
                sf.setPosition(newPos);
                addObject(sf);
            }
        }
        
        System.err.println("Capture : " + count);
    }
    
    // ------------------- get free position --------
    private ArrayList<Position> freePosition(int i) {
        ArrayList<Position> ret = new ArrayList<>();
        for (int j = 1; j < _size + 1; j++) {
            if (_mark[i][j] == 0)
                ret.add(new Position(i, j));
        }
        
        return ret;
    }
    
    private ArrayList<Position> freePosition() {
        ArrayList<Position> ret = new ArrayList<>();
        for (int i = 1; i < _size + 1; i++) {
            ArrayList<Position> inrow = freePosition(i);
            ret.addAll(inrow);
        }
        
        return ret;
    }
    
    /**
     * Get a free position on web.
     * @return free position or null if cobweb doesn't have any free position.
     */
    public Position getFreePosition() {
        ArrayList<Position> freePos = freePosition();
        
        if (freePos.size() > 0) {
            Random rand = new Random();
            int n = rand.nextInt(freePos.size());
            return freePos.get(n);
        }
        
        return null;
    }
    
    public Position getFreePosition(int i) {
        ArrayList<Position> freePos = freePosition(i);
        
        if (freePos.size() > 0) {
            Random rand = new Random();
            int n = rand.nextInt(freePos.size());
            return freePos.get(n);
        }
        
        return null;
    }
    
      
    // ------------------- objects on cobweb --------
    ArrayList<CobWebObject> _objects = new ArrayList<>();
    
    /**
     * Add more object to cobweb.
     * @param obj object will be added.
     * @return true if successful or false if not.
     */
    public boolean addObject(CobWebObject obj) {
        if (obj.position() == null)
            return false;
        
        _objects.add(obj);
        return true;
    }
    
    /**
     * Remove object from cobweb.
     * @param obj object will be removed.
     */
    public void removeObject(CobWebObject obj) {
        if (_objects.contains(obj)) {
            _objects.remove(obj);
            _mark[obj.position().row()][obj.position().column()]--;
        }
    }
    
    /**
     * @return rate food per spider
     */
    public double foodPerSpider() {
        double nFood = 0;
        double nSpider = 0;
        for (CobWebObject e : _objects) {
            if (e.type() == TypeObject.FOOD) {
                nFood = nFood + 1;
            }
            if (e.type() == TypeObject.COMPUTER) {
                nSpider = nSpider + 1;
            }
        }
        // add 1 spider is player
        nSpider = nSpider + 1;   
        // print log
        System.err.println("Food: " + nFood + " Spider: " + nSpider);
        return nFood / nSpider;
    }
    
    /**
     * @return all objects on cobweb.
     */
    public ArrayList<CobWebObject> objects() {
        return _objects;
    }
    
    /**
     * Return exactly list of objects have a same type.
     * @param type type of list.
     * @return list of objects, which have same type.
     */
    public ArrayList<CobWebObject> objects(TypeObject type) {
        ArrayList<CobWebObject> ret = new ArrayList<>();
        for (CobWebObject e : _objects) {
            if (e.type() == type) {
                ret.add(e);
            }
        }
        return ret;
    }
    
    // ----------------- check what have in a position -------------
    
    /**
     * Check existing of object in position.
     * @param type - type of object
     * @param pos - position
     * @return true if exist object have type in position, false if not.
     */
    public boolean have(TypeObject type, Position pos) {
        if (pos.isValid()) {
            for (CobWebObject obj : _objects) {
                if (obj.position().equals(pos) && obj.type() == type) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Get food from a position.
     * @param pos position
     * @return food in position
     */
    public SpiderFood getFood(Position pos) {
        for (CobWebObject obj : _objects) {
            if (obj.position().equals(pos) && obj.type() == TypeObject.FOOD) {
                return (SpiderFood)obj;
            }
        }
        
        return null;
    }
    
    /**
     * Get stone from a position.
     * @param pos position
     * @return stone in position
     */
    public Stone getStone(Position pos) {
        for (CobWebObject obj : _objects) {
            if (obj.position().equals(pos) && obj.type() == TypeObject.STONE) {
                return (Stone)obj;
            }
        }
        
        return null;
    }
    
    /**
     * Get spider from a position.
     * @param pos position
     * @return spider in position
     */
    public Spider getSpider(Position pos) {
        for (CobWebObject obj : _objects) {
            if (obj.position().equals(pos) 
                    && (obj.type() == TypeObject.PLAYER || obj.type() == TypeObject.COMPUTER )
                        ) {
                return (Spider)obj;
            }
        }
        
        return null;
    }
    
    /**
     * Make bug can escape from cobweb.
     */
    public void letBugGoOut() {
        ArrayList<CobWebObject> bugs = objects(TypeObject.FOOD);
        for (CobWebObject obj : bugs) {
            SpiderFood sf = (SpiderFood)obj;
            if (sf.escape()) {
                System.err.println("Bug in " + sf.position().toString() + " is gone.");
                removeObject(sf);
            }
        }
    }

    void letRainOut() {
        ArrayList<CobWebObject> rains = objects(TypeObject.RAIN);
        for (CobWebObject obj : rains) {
            Rain r = (Rain)obj;
            if (r.position().row() == size() && !r.isHoldingFood() && !r.isHoldingSpider()) {
                removeObject(r);
            }
        }
    }
    
}
