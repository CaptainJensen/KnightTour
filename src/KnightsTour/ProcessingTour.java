package KnightsTour;

import processing.core.PApplet;

import java.awt.*;

/**
 * Created by Jensen on 1/8/17.
 */
public class ProcessingTour extends PApplet {

    private int boardSize = 8;
    private Square startingSquare = new Square(0,0,0);
    private Knight knight = new Knight(startingSquare ,boardSize, boardSize);


    public void setup() {

        size(700,700);
        System.out.println(knight.solve().toString());
    }


    public void draw(){


        DrawBoard();


    }



    public void DrawBoard() {


        background (0,0,0);
        stroke(255,255,255);

        float h = (float) height/boardSize;
        float v = (float) width/boardSize;

        for(int i = 0; i < boardSize; i++)
        {
            line(0, i*h, width, i*h);
            line (i*v,0,i*v, height);
        }




    }
}
