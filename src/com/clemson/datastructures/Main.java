package com.clemson.datastructures;

import com.clemson.datastructures.models.Maze;
import com.clemson.datastructures.models.MazeFactory;
import com.clemson.datastructures.models.square.SquareMaze;
import com.clemson.datastructures.view.DisplayMaze;
import com.clemson.datastructures.view.Formview;

import javax.swing.*;


/**
 * Created by rnerella on 10/19/16.
 */

public final class Main {
    public static void main(final String[] args) {
    	 System.setProperty("sun.java2d.pmoffscreen", "false");
    	new Formview();
       
    }
}
