package com.clemson.datastructures.models.hexagon;

import com.clemson.datastructures.models.Room;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * Created by rnerella on 10/19/16.
 */
public class HexRoom extends Room {

    private int x ;
    private int y ;
    private int size;
    private Shape shape;

    public boolean northWest = true;
    public boolean northEast = true;
    public boolean east = true;
    public boolean west = true;
    public boolean southWest = true;
    public boolean southEast = true;

    public int[] xpoints;
    public int[] ypoints;

    public HexRoom (int x, int y, int scale){
        this.x = x;
        this.y = y;
        this.size = scale;

        if(y%2==0) {
            xpoints = new int[]{x * scale+500, x * scale+500, x * scale + scale / 2+500, x * scale + scale+500, x * scale + scale+500, x * scale + scale / 2+500};
            ypoints = new int[]{y * scale + scale / 2+120, y * scale + scale+120, y * scale + 3*scale/2+120, y * scale + scale+120, y * scale + scale / 2+120, y * scale+120};
            shape = new Polygon(xpoints, ypoints, 6);
        }else {
            xpoints = new int[]{x * scale + scale/2+500, x * scale + scale/2+500, x * scale + scale / 2 + scale/2+500, x * scale + scale + scale/2+500, x * scale + scale + scale/2+500, x * scale + scale / 2 + scale/2+500};
            ypoints = new int[]{y * scale + scale / 2+120, y * scale + scale+120, y * scale + 3*scale/2+120, y * scale + scale+120, y * scale + scale / 2+120, y * scale+120};
            shape = new Polygon(xpoints, ypoints, 6);
        }

        this.neighbors = new ArrayList<>();
    }

    @Override
    public  Shape getWalls() {
        Path2D.Double walls = new Path2D.Double();
        if (northWest) {
            Shape nwWall = new Line2D.Double(xpoints[1], ypoints[1],
                    xpoints[2], ypoints[2]);
            walls.append(nwWall, false);
        }
        if (northEast) {
            Shape neWall = new Line2D.Double(xpoints[2], ypoints[2],
                    xpoints[3], ypoints[3]);
            walls.append(neWall, false);
        }

        if(east) {
            Shape eWall = new Line2D.Double(xpoints[3], ypoints[3],
                    xpoints[4], ypoints[4]);
            walls.append(eWall, false);
        }
        if (southEast) {
            Shape seWall = new Line2D.Double(xpoints[4], ypoints[4],
                    xpoints[5], ypoints[5]);
            walls.append(seWall, false);
        }
        if (southWest) {
            Shape swWall = new Line2D.Double(xpoints[5], ypoints[5],
                    xpoints[0], ypoints[0]);
            walls.append(swWall, false);
        }

        if(west) {
            Shape wWall = new Line2D.Double(xpoints[0], ypoints[0],
                    xpoints[1], ypoints[1]);
            walls.append(wWall, false);
        }

        return walls;
    }



    @Override
    public Shape getShape() {
        return shape;
    }



    @Override
    public void breakWalls(Room room) {
        HexRoom hexRoom =  (HexRoom) room;
        if(y%2 == 0) {
            if(x == hexRoom.x ) {
                if(y > hexRoom.y){
                    southEast = false;
                    hexRoom.northWest = false;
                }if(y<hexRoom.y){
                    northEast = false;
                    hexRoom.southWest = false;
                }
            }
            if(x < hexRoom.x) {
                east = false;
                hexRoom.west = false;
            }
            if(x > hexRoom.x) {
                if(y > hexRoom.y){
                    southWest = false;
                    hexRoom.northEast = false;
                }if(y<hexRoom.y){
                    northWest = false;
                    hexRoom.southEast = false;
                }if(y == hexRoom.y){
                    west = false;
                    hexRoom.east = false;
                }
            }



        }
        else {
            if(x == hexRoom.x ) {
                if(y > hexRoom.y){
                    southWest = false;
                    hexRoom.northEast = false;

                }if(y<hexRoom.y){
                    northWest = false;
                    hexRoom.southEast = false;
                }
            }
            if(x > hexRoom.x) {
                west = false;
                hexRoom.east = false;
            }
            if(x < hexRoom.x) {
                if(y > hexRoom.y){
                    southEast = false;
                    hexRoom.northWest = false;
                }if(y<hexRoom.y){
                    northEast = false;
                    hexRoom.southWest = false;
                }if(y == hexRoom.y){
                    east = false;
                    hexRoom.west = false;
                }
            }

        }


        neighbors.add(room);

    }
}
