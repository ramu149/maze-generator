package com.clemson.datastructures.models.square;

import com.clemson.datastructures.models.Room;


import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.awt.*;


public class SquareRoom  extends Room{

    private int x ;
    private int y ;
    private int size;
    private Shape shape;

    public boolean left = true;
    public boolean top = true;
    public boolean bottom = true;
    public boolean right = true;


    public SquareRoom(int x, int y, int scale){
        this.x = x;
        this.y = y;
        this.size = scale;
        shape = new Rectangle(x*scale+50,y*scale+120,this.size,this.size);
        this.neighbors = new ArrayList<>();
    }


    @Override
    public Shape getShape() {
        return shape;
    }


    public final Shape getWalls() {
        Path2D.Double walls = new Path2D.Double();
        if (left) {
            Shape leftWall = new Line2D.Double(x * size+50, y * size+120,
                    x * size+50, y * size + size+120);
            walls.append(leftWall, false);
        }
        if (top) {
            Shape topWall = new Line2D.Double(x * size+50, y * size+120,
                    x * size + size+50, y * size+120);
            walls.append(topWall, false);
        }

        if(bottom) {
            Shape bottomWall = new Line2D.Double(x * size+50, y * size+size+120,
                    x * size+size+50, y * size+size+120);
            walls.append(bottomWall, false);
        }
        if(right) {
            Shape rightWall = new Line2D.Double(x * size+size+50, y * size+120,
                    x * size+size+50, y * size+size+120);
            walls.append(rightWall,false);
        }
        return walls;
    }


    @Override
    public void breakWalls( Room room) {
        SquareRoom squared =  (SquareRoom)room;
        if (squared.x < x ) {
            left = false;
            squared.right = false;
        } else if (squared.y < y ) {
            top = false;
            squared.bottom = false;
        } else if(squared.x > x){
            right = false;
            squared.left =false;
        } else if(squared.y > y){
            bottom = false;
            squared.top = false;
        }
        neighbors.add(room);
    }


}
