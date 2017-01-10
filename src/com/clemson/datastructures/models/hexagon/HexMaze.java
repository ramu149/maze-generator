package com.clemson.datastructures.models.hexagon;

import com.clemson.datastructures.controller.EllersAlgorithm;
import com.clemson.datastructures.controller.KruskalsAlgorithm;
import com.clemson.datastructures.controller.PrimsAlgorithm;
import com.clemson.datastructures.models.Maze;
import com.clemson.datastructures.models.Room;
import com.clemson.datastructures.models.square.SquareRoom;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by rnerella on 10/19/16.
 */
public class HexMaze extends Maze{


    private HexRoom [][] data;
    private int width;
    private int height;
    private int scale;

    public HexMaze(int width, int height, int scale, String algorithm){
        this.width = width;
        this.height = height;
        this.scale = scale;

        data = new HexRoom[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = new HexRoom(i, j, scale);
            }
        }

        generate(algorithm);

    }

    public void generate(String algorithm) {

        if(algorithm.equals("prims"))
            new PrimsAlgorithm().generateMaze(this);
        else if(algorithm.equals("kruskal"))
            new KruskalsAlgorithm().generateMaze(this);
        else
            new EllersAlgorithm().generateMaze(this);

    }
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getScale() {
        return scale;
    }


    private java.util.List<Point> getDirections(Point p) {
        List<Point> dirs = new ArrayList<Point>();
        dirs.add(new Point(1, 0));
        dirs.add(new Point(-1, 0));
        dirs.add(new Point(0, 1));
        dirs.add(new Point(0, -1));
       if(p.getY() % 2 == 0) {
           dirs.add(new Point(-1, -1));
           dirs.add(new Point(-1,1));
       }else {
           dirs.add(new Point(1, -1));
           dirs.add(new Point(1,1));
       }
        return dirs;
    }

    @Override
    public Room get(Point p) {
        if(inBounds(p))
          return data[p.x][p.y];
        else
            return null;
    }

    private boolean inBounds(Point p) {
        return (p.x >= 0) && (p.y >= 0) && (p.x < width) && (p.y < height);
    }




    @Override
    public List<Point> getValidAdjacentRooms(Point p) {
        List<Point> rooms = new ArrayList<>();
        for(Point dir : getDirections(p)){
            Room adjRoom = this.get(new Point(dir.x + p.x,dir.y+p.y));
            if(adjRoom!=null)
                rooms.add(new Point(dir.x + p.x,dir.y+p.y));
        }
        return rooms;
    }


    @Override
    public Iterator<Room> iterator() {
        return new HexMazeIterator();
    }

    private class HexMazeIterator implements Iterator<Room> {
        private int x = 0;
        private int y = 0;

        @Override
        public boolean hasNext() {
            return y < height;
        }

        @Override
        public HexRoom next() {
            if (y >= height) {
                throw new NoSuchElementException();
            }
            HexRoom ret = data[x][y];
            x++;
            if (x >= width) {
                x = 0;
                y++;
            }
            return ret;
        }

        @Override
        public void remove() {
            String msg = "Can't remove cells from a maze.";
            throw new UnsupportedOperationException(msg);
        }
    }
}
