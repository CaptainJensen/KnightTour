package KnightsTour;

import javafx.scene.input.KeyEvent;
import processing.core.PApplet;
import processing.core.PFont;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jensen on 1/8/17.
 */
public class ProcessingTour extends PApplet {

    private int boardSize = 8;
    private int iterationAwnser = 0;

    private boolean lineMode = false;

    private PFont font = createFont("Arial",16,true);

    private ArrayList<Square> awnsers;

    public void setup() {
        size(boardSize*100,boardSize*100);

        Square startingSquare = new Square((int)random(0,boardSize-1),(int)random(0,boardSize-1),0);//TODO: Make it random
        Knight knight = new Knight(startingSquare , boardSize, boardSize);
        awnsers = knight.solve();

        System.out.println(awnsers.toString());



        System.out.println(Arrays.deepToString(knight.getBoard()));
    }


    public void draw(){


        DrawBoard();

        textFont(font,23);
        fill(0,0,0);





       // text(String.valueOf(iterationAwnser), centerOfSquareX, centerOfSquareY);
       // text(String.valueOf(iterationAwnser), height/(boardSize*2), width/(boardSize*2)*14);

        for (int i = 0; i < iterationAwnser; i++) {
            if(lineMode) {
                line(awnsers.get(i).getRow()*(height/boardSize)+ (height/boardSize)/2, awnsers.get(i).getColumn()*(width/boardSize) + (width/boardSize)/2, awnsers.get(i+1).getRow()*(height/boardSize) + (height/boardSize)/2, awnsers.get(i+1).getColumn()*(width/boardSize) + (width/boardSize)/2);

            } else {
                text( i+1 , awnsers.get(i).getRow()*(height/boardSize)+ (height/boardSize)/2,awnsers.get(i).getColumn()*(width/boardSize) + (width/boardSize)/2);

            }

        }






        //noLoop();
    }



    public void DrawBoard() {


        //TODO:CHANGE
        background (255,255,255);
        stroke(0,0,0);

        float h = (float) height/boardSize;
        float v = (float) width/boardSize;

        for(int i = 0; i < boardSize; i++)
        {
            line(0, i*h, width, i*h);
            line (i*v,0,i*v, height);
        }

    }


    public void keyPressed() {
        if (keyCode == LEFT) {
            if(iterationAwnser >= 0) {
                 iterationAwnser--;
                 draw();
                 System.out.println("Left Key Pressed & iteration --");
            }

        }
        if (keyCode == RIGHT) {
            if(iterationAwnser < awnsers.size()-1) {
                iterationAwnser++;
                draw();
                System.out.println("Right Key Pressed & iteration ++");
            }

        }
        if (keyCode == UP) {
            iterationAwnser = awnsers.size()-1;
            draw();
            System.out.println("Up Key Pressed & iteration awnsers.size()-1");

        }
        if (keyCode == DOWN) {
            iterationAwnser = 0;
            draw();
            //System.out.println(Arrays.deepToString(knight.getBoard()));
            System.out.println("Up Key Pressed & iteration 0");

        }
        if (keyCode == SHIFT && !lineMode) {
            lineMode = true;
            draw();
            System.out.println("Shift Key Pressed & Line mode True");

        }
        else if (keyCode == SHIFT && lineMode) {
            lineMode = false;
            draw();
            System.out.println("Shift Key Pressed & Line mode True");

        }


    }
}
