package com.clemson.datastructures.view;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import Maze.MazeView;
import com.clemson.datastructures.models.Maze;
import com.clemson.datastructures.models.MazeFactory;
import java.awt.*;
/**
 * Created by rnerella on 10/21/16.
 */
public class Formview   {
JFrame shapeFrame = new JFrame();
JPanel mazeS =new JPanel();
JButton generateShape, generateMaze;
JComboBox<String> mazeWidth, mazeHeight;
JComboBox<String> defaultShapes;
int height,width; 
String shape;
public Formview() {
    super();
    shapeFrame.setSize(900, 900);
    shapeFrame.setLayout(null);
    shapeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    shapeFrame.setTitle("Welcome to Maze Generator !");
   // shapeFrame.pack();  
    onInit();

}
    private void onInit()
    {    
    	JLabel labelForMazeHeight= new JLabel("Height:");
    	labelForMazeHeight.setBounds(230,45,60,20);
    	shapeFrame.add(labelForMazeHeight);
    	
    	String roomDimension[]={"5","6","8","10"};
    	mazeHeight = new JComboBox<String>(roomDimension);
    	mazeHeight.setBounds(290,45,50,20);
    	shapeFrame.add(mazeHeight);
    	
    	JLabel labelForMazeWidth = new JLabel("Width:");
    	labelForMazeWidth.setBounds(380, 45, 90, 20);
    	shapeFrame.add(labelForMazeWidth);
    	
    	mazeWidth = new JComboBox<String>(roomDimension);
    	mazeWidth.setBounds(435, 45, 50, 20);
    	shapeFrame.add(mazeWidth);
    	
    	//generateShape = new JButton("Generate Shape");
    	//shapeFrame.add(generateShape);
    	//generateShape.setBounds(75,80,150,20);
    	//generateShape.addActionListener(this);
    	
    	generateMaze = new JButton("Generate Maze");
    	shapeFrame.add(generateMaze);
    	generateMaze.setBounds(600,80,150,20);   
    
    	
    	String mazeTypes[] = {"select","square","hexagon","mixed"};
    	JLabel labelForMaze = new JLabel("Shape:");
    	labelForMaze.setBounds(20,45,90,20);
    	shapeFrame.add(labelForMaze);
    	
    	defaultShapes = new JComboBox<String>(mazeTypes);
    	defaultShapes.setBounds(75,45,90,20);
    	defaultShapes.setSize(100,20);
    	shapeFrame.add(defaultShapes);
    	
    	String algorithm[] = {"select","prims","kruskal", "eller"};
    	JLabel labelForAlgorithm = new JLabel("Algorithm:");
    	labelForAlgorithm.setBounds(520,45,90,20);
    	shapeFrame.add(labelForAlgorithm);
    	
    	JComboBox<String> algo = new JComboBox<String>(algorithm);
    	algo.setBounds(600, 45,90,20);
    	algo.setSize(90,20);
    	shapeFrame.add(algo);
    	shapeFrame.setVisible(true);
    	shapeFrame.getContentPane().setLayout(new BorderLayout());
    	shapeFrame.setLocationRelativeTo(null);
    	
    	generateMaze.addActionListener(new ActionListener(){
   		 public void actionPerformed(ActionEvent ae) {
   			 	String shape, algorithm;
   				shape= defaultShapes.getSelectedItem().toString();
   				algorithm=algo.getSelectedItem().toString();
   				int height=Integer.parseInt(mazeHeight.getSelectedItem().toString()); 
   				int width=Integer.parseInt(mazeWidth.getSelectedItem().toString());
   				Maze maze = MazeFactory.getMaze(shape,height,width,algorithm);
   				shapeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   			    DisplayMaze display = new DisplayMaze(maze);
   			    shapeFrame.getContentPane().add(display, BorderLayout.WEST);
   			    shapeFrame.add(display);
   			    shapeFrame.setResizable(false);
   			    shapeFrame.setVisible(true); 
   			    shapeFrame.setLocationRelativeTo(null);
   			    shapeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		 }
   	});
    }

   /* @Override
	public void actionPerformed(ActionEvent e) {
    	String shape;
		shape= defaultShapes.getSelectedItem().toString();
    	if (generateShape.isEnabled()){
			int height=Integer.parseInt(mazeHeight.getSelectedItem().toString()); 
			int width=Integer.parseInt(mazeWidth.getSelectedItem().toString());
			MazeShape draw=new MazeShape();
			draw.drawShape(height, width, shape);
			shapeFrame.getContentPane().add(draw, BorderLayout.WEST);
			shapeFrame.add(draw);
			shapeFrame.setVisible(true);
    	}
    }*/
    
    public static void main(String[] args) {
        new Formview();
    }



}
