/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assessment_2.view;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 *  id：21146528
 * @author Xuanhao Wang
 */

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //create items under function menu
    JMenuItem rePlayItem=new JMenuItem("Replay");
    JMenuItem reLoginItem=new JMenuItem("ReLogin");
    JMenuItem closeItem=new JMenuItem("Close the game");
    JMenuItem aboutItem=new JMenuItem("Instruction");

    //Create a 2D array
    int [][] data=new int[4][4];
    //Set x and y to record the coordinates of the blank spaces
    int x=0;
    int y=0;
    //Create a 2D array to store win conditions
    int[][]win=new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    int step=0;


    public GameJFrame(){
        //game main frame
        initJFrame();
        //initial menu
        initJMenuBar();
        //initialized data
        initData();
        //initial picture
        initImage();

        //show
        this.setVisible(true);
    }
    //
    //Scramble data
    private void initData() {
        int[] temp=new int[16];
        Random random=new Random();
        int tempIndex;
        int randomIndex;
        for (int i = 0; i < temp.length; i++) {
            temp[i]=i;
        }
        //By setting a temporary storage variable and exchanging index data, the picture can be scrambled
        for (int i = 0; i < temp.length; i++) {
            tempIndex=temp[i];
            randomIndex=random.nextInt(temp.length);
            temp[i]=temp[randomIndex];
            temp[randomIndex]=tempIndex;
        }

        //Store shuffled data into a two-dimensional array
        for (int i = 0; i < temp.length; i++) {
            if(temp[i]==0){
                x=i/4;
                y=i%4;
                System.out.println(x+" "+y);
            }
            data[i / 4][i % 4] = temp[i];

        }
    }

    private void initImage() {
        //clear all images
        this.getContentPane().removeAll();

        //check whether to win
        if(victory()){
            JLabel winJlabel=new JLabel(new ImageIcon("./src/main/java/com/mycompany/assessment_2/images/win.png" ));
            winJlabel.setBounds(140,50,200,200);
            this.getContentPane().add(winJlabel);
        }

        //calculate step
        JLabel stepCount=new JLabel("Step : "+ step);
        stepCount.setBounds(50,320,100,20);
        this.getContentPane().add(stepCount);


        //create an imageIcon obj
        //Join all images using double loop

        for (int i = 0; i <4 ; i++) {
            System.out.println(" ");
            for (int j=0;j<4;j++){
                //create JLabel for each img
                System.out.print(data[i][j]+" ");
                ImageIcon icon=new ImageIcon("./src/main/java/com/mycompany/assessment_2/images/"+data[i][j]+".png");
                JLabel jLabel=new JLabel(icon);
                jLabel.setBounds(127*j,77*i,125,75);
                jLabel.setBorder(new BevelBorder(0));
                this.getContentPane().add(jLabel);
                this.getContentPane().repaint();//刷新图片
            }



        }

    }

    private void initJMenuBar() {
        JMenuBar jMenuBar=new JMenuBar();
        //sett two menu in JMenuBar
        JMenu functionJMenu=new JMenu("Function");
        JMenu aboutJMenu=new JMenu("About Game");

        //add items in menu
        functionJMenu.add(rePlayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(aboutItem);
        //add function for each bar when clicked
        rePlayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        aboutItem.addActionListener(this);
        //add two menu in menu bar
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //sett Game main window
        this.setSize(510,400);
        this.setTitle("Puzzle Game");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        //cancel default layout
        this.setLayout(null);
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        //Press A to view full image
        if(code==65){
            this.getContentPane().removeAll();
            JLabel check=new JLabel(new ImageIcon("./src/main/java/com/mycompany/assessment_2/images/original.png"));
            check.setBounds(0,0,500,302);
            this.getContentPane().add(check);
            this.getContentPane().repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //left 37 up:38 right:39 down: 40
        int code = e.getKeyCode();
        System.out.println(code);

        if (code == 37) {
            if(y!=0) {
                data[x][y] = data[x][y-1];
                data[x][y-1] = 0;
                y--;
                step++;
                initImage();
            }
            System.out.println("left");
        } else if (code == 38) {
            if(x!=0) {
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                step++;
                initImage();
            }
            System.out.println("up");
        } else if (code == 39) {
            if(y!=3) {
                data[x][y] = data[x][y+1];
                data[x][y+1] = 0;
                y++;
                step++;
                initImage();
            }
            System.out.println("right");
        } else if (code == 40) {

            if(x!=3) {
                data[x][y] = data[x +1][y];
                data[x +1][y] = 0;
                x++;
                step++;
                initImage();
            }
            System.out.println("down");
        } else if (code==65) {
            //if "A " released ,reload images with previous order
            initImage();

        } else if (code==87) {
            //if press "W " the puzzle will be completed

            data=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            //Reposition the coordinates of x and y
            x=3;
            y=3;
            initImage();
        }
    }
    //Check if the data in 'data' is the same as the data in 'win'
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //functions operation
        Object obj = e.getSource();
        if (obj == reLoginItem) {
            this.setVisible(false);
            new LoginJFrame();
            System.out.println("reLoginItem");
        } else if (obj == rePlayItem) {

            step = 0;
            initData();
            initImage();
            System.out.println("rePlayItem");

        } else if (obj == closeItem) {
            System.out.println("closeItem");
            System.exit(0);
        } else if (obj == aboutItem) {
            System.out.println("about the game");
            JDialog jDialog = new JDialog();
            jDialog.setTitle("Instruction");
            JTextArea instructionTextArea = new JTextArea("Use the arrow keys to manipulate the blank part to complete the game\n" +
                    "Press A to view full image\n" +
                    "Press W to go through the level quickly");
            instructionTextArea.setEditable(false);
            instructionTextArea.setLineWrap(true);
            instructionTextArea.setWrapStyleWord(true);
            jDialog.getContentPane().add(instructionTextArea, BorderLayout.CENTER);
            jDialog.setSize(300, 150);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }
    }
}