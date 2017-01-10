package com.clemson.datastructures.controller;
import com.clemson.datastructures.models.Maze;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * @author rnerella
 */
public class KruskalsAlgorithm {

    public void generateMaze(Maze maze){
        Random random = new Random();
        int wallsDown = 0;
        UnionFind uf= new UnionFind(maze.getHeight(),maze.getWidth());
        while(wallsDown < maze.getWidth()*maze.getHeight() -1){
            Point current = new Point(random.nextInt(maze.getWidth()),random.nextInt(maze.getHeight()));
            List<Point> randomNeighbors = maze.getValidAdjacentRooms(current);
            Point randomNeighbor =  randomNeighbors.get(random.nextInt(randomNeighbors.size()));
            if(!uf.connected(current,randomNeighbor)){
                uf.union(current,randomNeighbor);
                maze.get(current).breakWalls(maze.get(randomNeighbor));
                wallsDown++;
            }

        }
    }

    class UnionFind {
        private HashMap<Point, Point> parent = new HashMap<>();
        private HashMap<Point, Integer> rank = new HashMap<>();
        public UnionFind(int h , int w) {
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    Point t = new Point(i,j);
                    parent.put(t,t);
                    rank.put(t,0);
                }
            }

        }

        public boolean connected(Point p, Point q) {
            return find(p) == find(q);
        }
        public Point find(Point p) {
            while (p != parent.get(p)) {
                parent.put(p,parent.get(parent.get(p)));
                p = parent.get(p);
            }
            return p;
        }
        public void union(Point p, Point q) {
            Point rootP = find(p);
            Point rootQ = find(q);
            if (rootP.equals(rootQ)) return;
            if      (rank.get(rootP) < rank.get(rootQ)) parent.put(rootP,rootQ);
            else if (rank.get(rootP) > rank.get(rootQ)) parent.put(rootQ,rootP);
            else {
                parent.put(rootQ,rootP);
                rank.put(rootP,rank.get(rootP)+1);
            }

        }


    }

}