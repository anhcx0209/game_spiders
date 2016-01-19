package spiders.figure;

import java.util.ArrayList;
import spiders.events.PlayerActionListener;
import spiders.model.CobWeb;
import spiders.model.CobWebObject;
import spiders.navigations.Direction;
import spiders.navigations.Position;

/**
 * Computer can think and move to food, which is nearest from him.
 * @author anhcx
 */
public class Computer extends Spider implements PlayerActionListener {

    /**
     *
     * @param cw
     */
    public Computer(CobWeb cw) {
        super(cw);
        _type = TypeObject.COMPUTER;
    }
    
    /**
     * Find the nearest food from him.
     * @return direction to nearest food.
     */
    public ArrayList<Direction> think() {
        
        ArrayList<Direction> ret = new ArrayList<>();
        Position myPos = position();
        Position bestPos = null;
        int min = 999999;
        // get all object is food
        ArrayList<CobWebObject> listFood = cobweb().objects(TypeObject.FOOD);
        
        // find the min lenght
        for (CobWebObject e : listFood) {
            int cur_length = Position.lengthPath(myPos, e.position());
            if (cur_length < min) 
                bestPos = e.position();
        }
        
        // in case best pos is found, return a list of moveable to target
        if (bestPos != null) {
            System.err.println(name() + " dertemined target: " + bestPos.toString());
            return wayTo(myPos, bestPos);
        } else {
            System.err.println(name() + " moved random!");
            return randomDirection();
        }
    }
    
    private ArrayList<Direction> randomDirection() {
        ArrayList<Direction> ret = new ArrayList<>();
        
        Direction west = Direction.west();
        ret.add(west);
        Direction east = Direction.east();
        ret.add(east);
        Direction north = Direction.north();
        ret.add(north);
        Direction south = Direction.south();
        ret.add(south);
        
        return ret;
    }
    
    private ArrayList<Direction> wayTo(Position a, Position b) {
        ArrayList<Direction> ret = new ArrayList<>();
        
        int delta_row = a.row() - b.row();
        int delta_col = a.column() - b.column();
        
        if (delta_col > 0)
            ret.add(Direction.west());

        if (delta_col < 0)
            ret.add(Direction.east());

        if (delta_row > 0)
            ret.add(Direction.north());

        if (delta_row < 0)
            ret.add(Direction.south());
        
        return ret;
    }

    @Override
    public void playerMoved() {
        boolean moved = false;
        ArrayList<Direction> dir = think();
        for (Direction d : dir) {
            if (moveTo(d)) {
                System.err.println(name() + " move " + dir.toString());
                moved = true;
                break;
            }
        }

        if (!moved)
            System.err.println(name() + " can not move anyway");
    }
    
}
