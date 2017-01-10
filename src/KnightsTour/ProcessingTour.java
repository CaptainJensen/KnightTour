package KnightsTour;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jensen on 1/8/17.
 */
public class ProcessingTour extends PApplet {

    private int boardSize = 8;
    private int iterationAwnser = 0;

    Square startingSquare;
    Knight knight;

    private boolean lineMode = false;

    private PFont font = createFont("Arial",16,true);

    private ArrayList<Square> awnsers;

    public void setup() {

        if(boardSize < 5) {
            throw new IllegalArgumentException("Board Size Not aviable less than 5");
        }

        size(boardSize*100,boardSize*100);

        startingSquare = new Square((int)random(0,boardSize-1),(int)random(0,boardSize-1),0);
        knight = new Knight(startingSquare , boardSize, boardSize);

        awnsers = knight.solve();

        System.out.println(awnsers.toString());



        System.out.println(Arrays.deepToString(knight.getBoard()));
    }


    public void draw(){


        DrawBoard();

        textFont(font,40);


        for (int i = 0; i < iterationAwnser; i++) {
            if(lineMode) {
                if(i<awnsers.size()-1) {
                    strokeWeight(2);
                    line(awnsers.get(i).getRow()*(height/boardSize)+ (height/boardSize)/2, awnsers.get(i).getColumn()*(width/boardSize) + (width/boardSize)/2, awnsers.get(i+1).getRow()*(height/boardSize) + (height/boardSize)/2, awnsers.get(i+1).getColumn()*(width/boardSize) + (width/boardSize)/2);
                }

            } else {
                if(i==0) {
                    fill(0,255,0);
                }
                else if(i==awnsers.size()-1) {
                    fill(255,0,0);
                }
                else {
                    fill(0,0,0);
                }
                text( i+1 , (awnsers.get(i).getRow()*(height/boardSize)+ (height/boardSize)/2)-10,(awnsers.get(i).getColumn()*(width/boardSize) + (width/boardSize)/2)+10);

            }

        }






        //noLoop();
    }



    public void DrawBoard() {

        background (255,255,255);
        stroke(0,0,0);
        strokeWeight(5);

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
            if(iterationAwnser < awnsers.size()) {
                iterationAwnser++;
                draw();
                System.out.println("Right Key Pressed & iteration ++");
            }

        }
        if (keyCode == UP) {
            iterationAwnser = awnsers.size();
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
        if(keyCode == CONTROL) {
            startingSquare = new Square((int)random(0,boardSize-1),(int)random(0,boardSize-1),0);
            knight = new Knight(startingSquare , boardSize, boardSize);
            iterationAwnser = 0;
            setup();
            System.out.println("Controll Key Pressed & Creating new solution");
        }


    }
}
