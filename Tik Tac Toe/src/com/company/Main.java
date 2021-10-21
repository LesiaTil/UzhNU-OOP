package com.company;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static String[] board;
    static String turn;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        board = new String[9];
        turn = "X";
        String winner = null;

        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }

        System.out.println("Welcome");
        printBoard();

        System.out.println("X play first");
        int checker =0;
        while (winner == null) {
            int numInput;
            if(checker%2==0) {
                numInput = in.nextInt();
                turn = "X";
                checker++;
            }else{
                    numInput = botSelect();
                    turn = "O";
                    checker++;
                }

            if (!(numInput > 0 && numInput <= 9)) {
                System.out.println("Reselect cell");
            }
            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = turn;

                printBoard();
                winner = checkWinner();
            } else {
                checker--;
                System.out.println("Slot is full");
                System.out.println(turn + "'s turn; enter a slot num " + turn + " in:");
            }
        }

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("DRAW");
        } else System.out.println("Winner is " + winner);
    }

    public static String checkWinner() {
        for (int i = 0; i < 8; i++) {
            String line = null;

            switch (i) {
                case 0 -> line = board[0] + board[1] + board[2];
                case 1 -> line = board[3] + board[4] + board[5];
                case 2 -> line = board[6] + board[7] + board[8];
                case 3 -> line = board[0] + board[3] + board[6];
                case 4 -> line = board[1] + board[4] + board[7];
                case 5 -> line = board[2] + board[5] + board[8];
                case 6 -> line = board[0] + board[4] + board[8];
                case 7 -> line = board[2] + board[4] + board[6];
            }

            if (line.equals("OOO")) return "O";
            else if (line.equals("XXX")) return "X";
        }

        for (int j = 0; j < 9; j++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(j + 1))) break;
            else if (j == 8) return "draw";
        }

        System.out.println(turn + "'s turn; enter a slot num " + turn + " in:");
        return null;
    }

    public static void printBoard() {
        System.out.println("\n\n----------------------\n"
                + "| " + board[0] + " | " + board[1] + " | " + board[2] + " |\n----------------------\n"
                + "| " + board[3] + " | " + board[4] + " | " + board[5] + " |\n----------------------\n"
                + "| " + board[6] + " | " + board[7] + " | " + board[8] + " |\n----------------------\n\n");
    }

    public static int botSelect(){
        Random rand = new Random();
        int select = rand.nextInt(8)+2;
        if((board[select - 1].equals(String.valueOf(select)))){
            return select;}
        return botSelect();
    }
}