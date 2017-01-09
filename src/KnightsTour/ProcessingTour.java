package KnightsTour;

import javafx.scene.input.KeyEvent;
import processing.core.PApplet;
import processing.core.PFont;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jensen on 1/8/17.
 */
public class ProcessingTour extends PApplet {

    private int boardSize = 8;
    private int iterationAwnser;
    private Square startingSquare = new Square(0,0,0);//TODO: Make it random
    private Knight knight = new Knight(startingSquare , boardSize, boardSize);

    private PFont font = createFont("Arial",16,true);

    private ArrayList<Square> awnsers = knight.solve();

    public void setup() {
        size(boardSize*100,boardSize*100);
        iterationAwnser = 0;

            System.out.println(awnsers.toString());




    }


    public void draw(){


        DrawBoard();

        textFont(font,23);
        fill(0,0,0);





       // text(String.valueOf(iterationAwnser), centerOfSquareX, centerOfSquareY);
       // text(String.valueOf(iterationAwnser), height/(boardSize*2), width/(boardSize*2)*14);

        for (int i = 0; i < awnsers.size()-1; i++) {
            text( i , awnsers.get(i).getRow()*(height/boardSize)+ (height/boardSize)/2,awnsers.get(i).getColumn()*(width/boardSize) + (width/boardSize)/2);

        }


       // line(awnsers.get(0).getRow()*(height/boardSize)+ (height/boardSize)/2, awnsers.get(0).getColumn()*(width/boardSize) + (width/boardSize)/2, awnsers.get(1).getRow()*(height/boardSize) + (height/boardSize)/2, awnsers.get(1).getColumn()*(width/boardSize) + (width/boardSize)/2);



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
        if (keyCode == RIGHT) {


            if(iterationAwnser <= awnsers.size()-1) {
                iterationAwnser++;
                draw();
                System.out.println("Right Key Pressed & iteration ++");
            }




        }

    }
}
