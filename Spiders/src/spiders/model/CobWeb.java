package spiders.model;

import java.util.ArrayList;
import java.util.Random;
import spiders.figure.SpiderFood;
import spiders.model.CobWebObject.TypeObject;
import spiders.navigations.Position;

/**
 * Cobweb - where spider and bug move
 * size of cobweb = n x n
 * @author anhcx
 */
public class CobWeb {
    // ------------------ size --------------------
    int _size;
    
    CobWeb(int s) {
        _size = s;
        _mark = new int[_size + 1][_size + 1];
        for (int i = 0; i < _size + 1; i++)
            for (int j = 0; j < _size + 1; j++)
                _mark[i][j] = 0;
    }
    
    /**
     * @return size of cobweb.
     */
    public int size() {
        return _size;
    }
    
    // ------------------- try to capture some food ----------------------
    /**
     * Capture food form a mount of food.
     * @param outFood - food outside the web.
     */
    public void captureMoreFood(ArrayList<SpiderFood> outFood) {
        int count = 0;
        for (SpiderFood sf : outFood) {
            if (sf.failIntoWeb()) {
                count++;
                addObject(sf);
            }
        }
        
        System.err.println("Capture : " + count);
    }
    
    // ------------------- get free position --------
    int[][] _mark;
    
    private ArrayList<Position> freePosition() {
        ArrayList<Position> ret = new ArrayList<>();
        for (int i = 1; i < _size + 1; i++) {
            for (int j = 1; j < _size + 1; j++) {
                if (_mark[i][j] == 0)
                    ret.add(new Position(i, j));
            }
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
        _mark[obj.position().row()][obj.position().column()]++;
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
                boolean trueType = false;
                if (type == TypeObject.FOOD)
                    trueType = (obj.type() == TypeObject.FLY || obj.type() == TypeObject.GRASSHOPPER
                            || obj.type() == TypeObject.MOSQUITO || obj.type() == TypeObject.WASP);
                else
                    trueType = obj.type() == type;
                if (obj.position().equals(pos) && trueType) {
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
}
