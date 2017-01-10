package com.clemson.datastructures.models;




import java.awt.*;
import java.util.List;

public abstract class Maze implements Iterable<Room> {
     public abstract int getWidth();
     public abstract int getHeight();
     public abstract int getScale();
     public abstract Room get(Point p);
     public abstract List<Point> getValidAdjacentRooms(Point p);

}
