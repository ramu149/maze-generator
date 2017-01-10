package com.clemson.datastructures.controller;

import com.clemson.datastructures.models.Maze;
import com.clemson.datastructures.models.Room;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by rnerella on 10/26/16.
 */
public class EllersAlgorithm {

    private String IN = "in";
    private String FRONTIER = "frontier";

    public void generateMaze(Maze maze){
         List<Point> frontiers = new ArrayList<>();
         Random random = new Random();
         int x = random.nextInt(maze.getWidth());
         int y = random.nextInt(maze.getHeight());

        Point start = new Point(x,y);
        frontiers.add(start);
        maze.get(start).mark(FRONTIER);

        while(!frontiers.isEmpty()){
             int randomIndex = random.nextInt(frontiers.size());
             Point removed = frontiers.remove(randomIndex);
             Room  removedRoom = maze.get(removed);


             List<Point> adjRooms = maze.getValidAdjacentRooms(removed);

             Point randomAdj = getRandomInNeighbor(adjRooms,maze);
             if(randomAdj != null) {
                 Room randomAdjRoom = maze.get(randomAdj);
                 removedRoom.breakWalls(randomAdjRoom);
             }
             removedRoom.mark(IN);
             for(Point p: adjRooms){
                 if(!maze.get(p).hasMark(IN) && !maze.get(p).hasMark(FRONTIER) ){
                     frontiers.add(p);
                      maze.get(p).mark(FRONTIER);

                 }
             }
         }

    }


   private Point getRandomInNeighbor(List<Point> adjRooms, Maze maze){
       List<Point> inAdjacentPoints = new ArrayList<>();
       for(Point p : adjRooms){
           if(maze.get(p).hasMark(IN))
               inAdjacentPoints.add(p);
       }
       if(inAdjacentPoints.size() > 0)
        return inAdjacentPoints.get(new Random().nextInt(inAdjacentPoints.size())) ;
       return null;

   }






}
