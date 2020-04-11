/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import editor.display.CharacterDisplay;

import java.util.LinkedList;


/**
 * This class represents the document being edited. Using a 2d array to hold the
 * document content is probably not a very good choice. Fixing this class is the
 * main focus of the exercise. In addition to designing a better data model, you
 * must add methods to do at least basic editing: write and delete text, and
 * moving the cursor
 *
 * @author TuvaCe,
 */
public class Document {

    private CharacterDisplay display;
    private int cursorRow;
    private int cursorCol;
    private char[][] data;
    LinkedList<Character> LinkedRow = new LinkedList<>();
    LinkedList<Character> LinkedColum = new LinkedList<>();

    public Document(CharacterDisplay display) {
        this.display = display;
        data = new char[CharacterDisplay.HEIGHT][CharacterDisplay.WIDTH];
        cursorCol = cursorRow = 0;
        display.displayCursor(' ', cursorRow, cursorCol);
    }

    // Inserts char in list.
    public void insertChar(char c) {
        // data[cursorRow][cursorCol] = c;
        LinkedRow.add(cursorRow, c);
        LinkedColum.add(cursorCol, c);
        display.displayChar(c, cursorRow, cursorCol);
        cursorCol++;
        if (cursorCol >= CharacterDisplay.WIDTH) {
            cursorCol = 0;
            cursorRow++;
        }
        display.displayCursor(data[cursorRow][cursorCol],
                cursorRow, cursorCol);
    }

    // Deletes char from list.
    public void deleteChar(char c) {
        // data[cursorRow][cursorCol] = c;
        if (LinkedColum.contains(c) && LinkedRow.contains(c)) {
            LinkedColum.remove(cursorCol);
            LinkedRow.remove(cursorRow);
        }
        display.displayChar(c, cursorRow, cursorCol);
        if (cursorCol == 0 && cursorRow == 0) {
        } else if (cursorCol == 0 && cursorRow >= 0) {
            cursorCol = 39;
            cursorRow--;
        } else {
            cursorCol--;
        }
        display.displayCursor(' ', cursorRow, cursorCol);

    }

    // Enter, lineshift
    public void lineShift(char c) {
        //data[cursorRow][cursorCol] = c;
        LinkedColum.add(cursorCol, c);
        LinkedRow.add(cursorRow, c);
        display.displayChar(c, cursorRow, cursorCol);
        if (cursorRow == 19) {
        } else {
            cursorCol = 0;
            cursorRow++;

        }
        display.displayCursor(' ', cursorRow, cursorCol);

    }

    // For navigasjon option + pil

    // Navigasjon opp
    public void up() {

        if (cursorRow == 0) {
        } else {
            cursorRow--;
        }
        display.displayCursor(' ', cursorRow, cursorCol);
    }

    //Navigasjon ned
    public void down() {

        if (cursorRow == 19) {
        } else {
            cursorRow++;
        }
        display.displayCursor(' ', cursorRow, cursorCol);
    }

    // Navigasjon høyre
    public void right() {
        if (cursorCol == 39 && cursorRow == 19) {

        } else {
            cursorCol++;
            if (cursorCol >= CharacterDisplay.WIDTH) {
                cursorCol = 0;
                cursorRow++;
            }
        }
        display.displayCursor(' ', cursorRow, cursorCol);
    }

    // Navigasjon venstre
    public void left() {
        if (cursorCol == 0 && cursorRow == 0) {

        } else if (cursorCol == 0 && cursorRow >= 0) {
          cursorCol = 39;
          cursorRow--;
        } else {
            cursorCol--;
        }
        display.displayCursor(' ', cursorRow, cursorCol);
    }

    // Overwriting char in list.
    public void replace(char c) {
    }


}
