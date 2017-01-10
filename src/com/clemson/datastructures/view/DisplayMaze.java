package com.clemson.datastructures.view;


import com.clemson.datastructures.models.Maze;
import com.clemson.datastructures.models.Room;
import com.clemson.datastructures.models.square.SquareMaze;
import com.clemson.datastructures.models.square.SquareRoom;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a maze to the user as a GUI component.
 */
public class DisplayMaze extends JPanel  {
    private static final long serialVersionUID = 1L;

    private static final Stroke WALL_STROKE = new BasicStroke(1);
    private static final Color WALL = Color.BLACK;
    private static final Color SOLUTION = Color.GREEN;

    private Maze maze;

    public DisplayMaze(Maze view) {
        super();
        setMaze(view);
        repaint();
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        //super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        //double scaleX = getWidth() * 1.0 / (maze.getWidth() *( maze.getScale()+3));
        //double scaleY = getHeight() * 1.0 / (maze.getHeight() * (maze.getScale()+3));
        //g.scale(scaleX, scaleY);
        //g.setStroke(WALL_STROKE);
        for (Room room : maze) {
            System.out.println(room.getShape());
            g.setColor(Color.WHITE);
            g.fill(room.getShape());
            g.setColor(WALL);
            g.draw(room.getWalls());
            
        }
    }


    public void setMaze(final Maze view) {
        maze = view;
        Dimension size = new Dimension(900, 900);
        setMinimumSize(size);
        setPreferredSize(size);
        repaint();
    }


    public final void onSolved() {
        repaint();
    }

    public final void onStepSolved() {
        repaint();
    }
}
