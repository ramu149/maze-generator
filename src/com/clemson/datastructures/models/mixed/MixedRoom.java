package com.clemson.datastructures.models.mixed;



import com.clemson.datastructures.models.Room;

import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.awt.*;


public class MixedRoom extends Room{

    private int x ;
    private int y ;
    private int size;
    private Shape shape;

    public boolean southEast = true;
    public boolean southWest = true;
    public boolean northEast = true;
    public boolean northWest = true;
    public boolean north = true;
    public boolean south = true;

    public int [] xpoints;
    public int [] ypoints;


    public MixedRoom(int x, int y, int scale){
        this.x = x;
        this.y = y;
        this.size = scale;
        int numSides;
        if(y % 3 == 0) {
            numSides = 4;
            xpoints = new int[]{x*3*scale + 3*scale/2+200 ,x*3*scale + 3*scale +200, x*3*scale+3*scale/2+200, x*3*scale+200};
            ypoints = new int[]{y*scale+500 , y*scale + 3*scale/2+500 , y*scale  + 3*scale+500, y*scale + 3*scale/2+500};
            shape = new Polygon(xpoints,ypoints,numSides);
        }else if(y %3 == 1){
            numSides = 3;
            xpoints = new int[]{x*3*scale + 3*scale+200 , x*3*scale + 9*scale/2+200 , x*3*scale+3*scale/2+200};
            ypoints = new int[]{y*scale + scale/2+500, y*scale + 2*scale+500 , y*scale + 2*scale+500};
            shape = new Polygon(xpoints,ypoints,numSides);
        }else{
            numSides =3;
            xpoints = new int[]{x*3*scale + 3*scale/2+200 , x*3*scale + 3*scale+200 , x*3*scale + 9*scale/2+200};
            ypoints = new int[]{y*scale +scale+500, y*scale + 5*scale/2+500 , y*scale + scale+500};
            shape = new Polygon(xpoints,ypoints,numSides);
        }

        //shape = new Polygon(xpoints,ypoints,numSides);
        this.neighbors = new ArrayList<>();
    }


    @Override
    public Shape getShape() {
        return shape;
    }


    public final Shape getWalls() {
        Path2D.Double walls = new Path2D.Double();
        if(y%3 == 0) {
            if(northWest) walls.append( new Line2D.Double(xpoints[2], ypoints[2],
                    xpoints[3], ypoints[3]),false);
            if(northEast) walls.append( new Line2D.Double(xpoints[1], ypoints[1],
                    xpoints[2], ypoints[2]),false);
            if(southEast) walls.append(new Line2D.Double(xpoints[1], ypoints[1],
                    xpoints[0], ypoints[0]),false);
            if(southWest) walls.append(new Line2D.Double(xpoints[0], ypoints[0],
                    xpoints[3], ypoints[3]),false);
        } else if (y%3 == 1) {
            if(north) walls.append(new Line2D.Double(xpoints[1], ypoints[1],
                    xpoints[2], ypoints[2]),false);
            if(southEast) walls.append(new Line2D.Double(xpoints[0], ypoints[0],
                    xpoints[1], ypoints[1]),false);
            if(southWest) walls.append(new Line2D.Double(xpoints[2], ypoints[2],
                    xpoints[0], ypoints[0]),false);
        }else{
            if(south) walls.append(new Line2D.Double(xpoints[2], ypoints[2],
                    xpoints[0], ypoints[0]),false);
            if(northEast) walls.append(new Line2D.Double(xpoints[1], ypoints[1],
                    xpoints[2], ypoints[2]),false);
            if(northWest) walls.append(new Line2D.Double(xpoints[0], ypoints[0],
                    xpoints[1], ypoints[1]),false);
        }
        return walls;
    }


    @SuppressWarnings("Duplicates")
    @Override
    public void breakWalls( Room room) {
        MixedRoom mixed =  (MixedRoom) room;
        if(y % 3 == 0){
            if ( mixed.y > y) {
                if(mixed.x ==  x) {
                    northEast = false;
                    mixed.southWest = false;
                }
                else if(mixed.x < x ) {
                    northWest = false;
                    mixed.southEast = false;
                }
            } else if(mixed.y < y) {
                if(mixed.x == x) {
                    southEast = false;
                    mixed.northWest = false;
                }
                else if(mixed.x < x ) {
                    southWest = false;
                    mixed.northEast = false;
                }
            }

        }else if(y%3 == 1){
            if(mixed.y > y){
                this.north = false;
                mixed.south = false;
            } else if(mixed.y < y){
                if(mixed.x > x) {
                    southEast = false;
                    mixed.northWest = false;
                }
                else if(mixed.x == x ) {
                    southWest = false;
                    mixed.northEast = false;
                }
            }

        }else {
            if(mixed.y < y){
                this.south = false;
                mixed.north = false;
            } else if ( mixed.y > y) {
                    if(mixed.x > x) {
                        northEast = false;
                        mixed.southWest = false;
                    }
                    else if(mixed.x == x ) {
                        northWest = false;
                        mixed.southEast = false;
                    }
                }
            }

        neighbors.add(room);
    }


}
