package com.clemson.datastructures.models;

import com.clemson.datastructures.models.hexagon.HexMaze;
import com.clemson.datastructures.models.mixed.MixedMaze;
import com.clemson.datastructures.models.square.SquareMaze;

/**
 * Created by rnerella on 10/19/16.
 */
public class MazeFactory {

    public static  Maze getMaze(String mazeType,int height,int width, String algorithm){
        if(mazeType.equals("mixed")){
            return new MixedMaze(width,height,18, algorithm);

        }
        else if (mazeType.equals("hexagon")){
            return new HexMaze(width,height,30, algorithm);
        }
        else if(mazeType.equals("square")) {
            return new SquareMaze(width,height,30,algorithm);

        }else{
            throw new IllegalArgumentException(String.format("Maze type %s Unknown",mazeType));
        }
    }

}
