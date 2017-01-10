package com.clemson.datastructures.models;


import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rnerella
 */
public abstract class Room{


    protected Map<String,Boolean> marks = new HashMap<>();
    protected List<Room> neighbors;
    public abstract Shape getShape();
    public abstract Shape getWalls();
    public abstract void breakWalls(Room room);


    public void mark(String mark){
        marks.put(mark,true);
    }


    public boolean hasMark(String marker){
        if(marks.containsKey(marker))
            return marks.get(marker);
        else
            return false;
    }



}
