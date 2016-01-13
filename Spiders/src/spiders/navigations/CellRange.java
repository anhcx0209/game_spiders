package spiders.navigations;

/**
 * Cell ranges
 * @author anhcx
 */
public class CellRange {
   // ------------------ All inrange value  ------------------
    private  int _min = 0;
    private  int _max = 0;

    /**
     * Constructor
     * @param min - minimum coordinate value
     * @param max - maximum coordinate value
     */
    public CellRange(int min, int max){
        if(min < 0)     min = 0;
        if(max < min)   max = min;
        
        _min = min;
        _max = max;
    }
    
    /**
     * Getter for min
     * @return minimum coordinate value
     */
    public int min(){
        return _min;
    }

    /**
     * Getter for max
     * @return - maximum coordinate value
     */
    public int max(){
        return _max;
    }

    /**
     * Get length of range
     * @return length of range
     */
    public int length(){
        return _max - _min + 1;
    }
    
    /**
     * Check pair min - max is valid range or not
     * @param min - minimum coordinate value
     * @param max - maximum coordinate value
     * @return true - valid, false invalid
     */
    public static boolean isValidRange(int min, int max) {
      return min > 0 && max >= min;  
    }

    /**
     * Check if value in range or not
     * @param val - value
     * @return true - in range, false out range
     */
    public boolean contains(int val){
       return val >= _min && val <= _max;
    } 
}
