package com.clemson.datastructures.models.mixed;

import com.clemson.datastructures.controller.KruskalsAlgorithm;
import com.clemson.datastructures.controller.PrimsAlgorithm;
import com.clemson.datastructures.models.Maze;
import com.clemson.datastructures.models.Room;
import com.clemson.datastructures.models.square.SquareRoom;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by dneralla on 10/19/16.
 */
public class MixedMaze extends Maze{

    private MixedRoom[][] data;
    private int width;
    private int height;
    private int scale;


    public MixedMaze(int width, int height, int scale, String algorithm){
        this.width = width;
        this.height = height;
        this.scale = scale;

        data = new MixedRoom[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = new MixedRoom(i, j, scale);
            }
        }

        generate(algorithm);

    }

    @SuppressWarnings("Duplicates")
    public void generate(String algorithm) {
        if(algorithm.equals("prims"))
            new PrimsAlgorithm().generateMaze(this);
        else if(algorithm.equals("kruskal"))
            new KruskalsAlgorithm().generateMaze(this);
        else
            new PrimsAlgorithm().generateMaze(this);

    }

    private java.util.List<Point> getDirections(Point p) {
        List<Point> dirs = new ArrayList<Point>();
        if(p.getY()%3 == 0){
            dirs.add(new Point(-1, 1));
            dirs.add(new Point(-1, -1));
            dirs.add(new Point(0, 1));
            dirs.add(new Point(0, -1));
        }else if(p.getY()%3 == 1){
            dirs.add(new Point(0, 1));
            dirs.add(new Point(0, -1));
            dirs.add(new Point(1, -1));

        }else{
            dirs.add(new Point(0, -1));
            dirs.add(new Point(0, 1));
            dirs.add(new Point(1, 1));
        }

        return dirs;
    }

    public List<Point> getValidAdjacentRooms(Point p) {

        List<Point> rooms = new ArrayList<>();
        for(Point dir : getDirections(p)){
            Room adjRoom = this.get(new Point(dir.x + p.x,dir.y+p.y));
            if(adjRoom!=null)
                rooms.add(new Point(dir.x + p.x,dir.y+p.y));
        }
        return rooms;
    }




    public MixedRoom get( Point p) {
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
        return scale*4;
    }

    public final Iterator<Room> iterator() {
        return new MixedMazeIterator();
    }

    private class MixedMazeIterator implements Iterator<Room> {
        private int x = 0;
        private int y = 0;

        @Override
        public boolean hasNext() {
            return y < height;
        }

        @Override
        public MixedRoom next() {
            if (y >= height) {
                throw new NoSuchElementException();
            }
            MixedRoom ret = data[x][y];
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