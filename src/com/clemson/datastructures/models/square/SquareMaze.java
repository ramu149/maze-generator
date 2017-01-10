package com.clemson.datastructures.models.square;

import com.clemson.datastructures.controller.PrimsAlgorithm;
import com.clemson.datastructures.models.Maze;
import com.clemson.datastructures.models.Room;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by rnerella on 10/19/16.
 */
public class SquareMaze extends Maze {



    private SquareRoom [][] data;
    private int width;
    private int height;
    private int scale;


    public SquareMaze(int width, int height, int scale, String algorithm){
        this.width = width;
        this.height = height;
        this.scale = scale;

        data = new SquareRoom[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = new SquareRoom(i, j, scale);
            }
        }

        generate(algorithm);

    }

    public void generate(String algorithm) {

        if(algorithm.equals("prims"))
            new PrimsAlgorithm().generateMaze(this);
        else if(algorithm.equals("kruskal"))
            new PrimsAlgorithm().generateMaze(this);
        else
            new PrimsAlgorithm().generateMaze(this);

    }

    public List<Point> getValidAdjacentRooms(Point p) {
           List<Point> rooms = new ArrayList<>();
           for(Point dir : getDirections()){
               SquareRoom adjRoom = this.get(new Point(dir.x + p.x,dir.y+p.y));
               if(adjRoom!=null)
                 rooms.add(new Point(dir.x + p.x,dir.y+p.y));
           }
        return rooms;
    }




    private List<Point> getDirections() {
        List<Point> dirs = new ArrayList<Point>();
        dirs.add(new Point(1, 0));
        dirs.add(new Point(-1, 0));
        dirs.add(new Point(0, 1));
        dirs.add(new Point(0, -1));
        return dirs;

    }

    public SquareRoom get( Point p) {
        if (inBounds(p)) {
            return data[p.x][p.y];
        } else {
            return null;
        }
    }


    private boolean inBounds(Point p) {
        return (p.x >= 0) && (p.y >= 0) && (p.x < width) && (p.y < height);
    }






    public final int getWidth() {
        return width ;
    }

    public final int getHeight() {
        return height;
    }

    public final int getScale() {
        return scale;
    }

    public final Iterator<Room> iterator() {
        return new SquareMazeIterator();
    }

    private class SquareMazeIterator implements Iterator<Room> {
        private int x = 0;
        private int y = 0;

        @Override
        public boolean hasNext() {
            return y < height;
        }

        @Override
        public SquareRoom next() {
            if (y >= height) {
                throw new NoSuchElementException();
            }
            SquareRoom ret = data[x][y];
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
