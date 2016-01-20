/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiders.navigations;

import java.util.HashMap;

/**
 * Position
 * @author anhcx
 */
public class Position {
    // -- Диапазоны возможных значений по горизонтали и вертикали для всех позиций --
    private static CellRange _horizontalRange = new CellRange(0, 0);
    private static CellRange _verticalRange = new CellRange(0, 0);
    
    public static void setHorizontalRange(int min, int max) {
        if(CellRange.isValidRange(min, max))
        { _horizontalRange = new CellRange(min, max); }
    }
    
    public static CellRange horizontalRange() {
      return _horizontalRange;
    }
    
    public static void setVerticalRange(int min, int max) {
        if(CellRange.isValidRange(min, max))
        { _verticalRange = new CellRange(min, max); }
    }
    
    public static int lengthPath(Position a, Position b) {
        int dx, dy;
        if (a.row() > b.row())
            dx = a.row() - b.row();
        else 
            dx = b.row() - a.row();
        
        if (a.column()> b.column())
            dy = a.column()- b.column();
        else 
            dy = b.column() - a.column();
        
        
        return dx + dy;
    }
    
    public static CellRange verticalRange() {
        return _verticalRange;
    }
    
    // ------------------ Позиция внутри диапазона ---------------------
    private int _row;
    private int _column;
    
    public Position(int row, int col) {
        if(!isValid(row, col)) {  
            // throw exception here
        }

        _row = row;
        _column = col;
    }
    
    public int row() {        
        return _row;
    }
    
    public int column() {
        return _column;
    }
    
    // Позиция может стать невалидной, если изменились диапазоны допустимых значений
    public boolean isValid(){
        return isValid(_row, _column);
    }
    
    public static boolean isValid(int row, int col) {
        return _horizontalRange.contains(col) && _verticalRange.contains(row);
    }
    
    @Override
    public Position clone(){
        return new Position(_row, _column);
    }
    
    // ------------------ Порождение и проверка смежных позиций ---------------------
    public Position next(Direction direct){
        int[] newPos = calcNewPosition(_row, _column, direct);
        return new Position(newPos[0], newPos[1]);
    }
    
    // Вовзвращает массив из двух элементов: индекс строки, индекс столбца
    private int[] calcNewPosition(int row, int col, Direction direct){
        
        // Таблица смещения для различных направлений: (горизонталь,вертикаль)
        HashMap<Direction, int [] > offset=  new  HashMap<Direction, int [] >();
        
        offset.put(Direction.north(),   new int []{ 0, -1} );
        offset.put(Direction.south(),   new int []{ 0,  +1} );
        offset.put(Direction.east(),    new int []{ +1,  0} );
        offset.put(Direction.west(),    new int []{-1,  0} );
        
        int[] newPos = new int[2];
        
        newPos[0] = _row + offset.get(direct)[1];
        newPos[1] = _column + offset.get(direct)[0];
        
        return newPos;
    }
    
    // ------------------ Сравнение позиций ---------------------
    @Override
    public boolean equals(Object other){
        
        if(!isValid())
        {  //  TODO породить исключение 
        }

        if(other instanceof Position) {
            // Типы совместимы, можно провести преобразование
            Position otherPosition = (Position)other;
            // Возвращаем результат сравнения углов
            return _row == otherPosition._row && _column == otherPosition._column;
        }
        
        return false;
    }
    
    // --------------------- TO STRING --------------------------
    @Override 
    public String toString() {
        return "(" + _row + ", " + _column + ")";
    }
}