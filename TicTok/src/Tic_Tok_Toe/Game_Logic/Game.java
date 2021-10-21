package Tic_Tok_Toe.Game_Logic;

import Tic_Tok_Toe.DB.DB;
import Tic_Tok_Toe.Players.Player;
import Tic_Tok_Toe.Players.Bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Game {

    private Menu menuGame;
    private DB db;
    private Player player;
    private Bot bot;
    private BufferedReader in;
    private String[] board;
    private String turn;
    private byte countShourts = 0;
    private long timeGame;


    public Game(Menu menu) {
        this.menuGame = menu;
        db = new DB();
        player = new Player();
        bot = new Bot();
        in = in = new BufferedReader(new InputStreamReader(System.in));
        board = new String[9];
        turn = "X";
    }

    public void readItemMenu() {
        switch (checkValidInput()) {
            case 1:
                menuGame.printEnterName();
                break;
            case 2:
                menuGame.printRecord(db.getArrayRecord());
                menuGame.printGreeting();
                break;
            default:
                menuGame.printError(" Reselect item ");
                menuGame.printGreeting();
        }

    }

    public void readNamePlayer() {
        String name = "";
        try {
            name = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setNamePlayer(name);

        if (db.checkNameInRecord(db.getRecordArray(), player.getNamePlayer())) {
            menuGame.printError(" This name is reserved ");
            menuGame.printEnterName();
        } else {
            startGame();
        }
    }

    public void readContunieGame() {
        String rezult = "";
        try {
            rezult = in.readLine();
            if (checkContunieGame(rezult)) {
                menuGame.printGreeting();
            } else {
                System.exit(0);
            }
        } catch (IOException e) {
            System.exit(0);
        }

    }

    private boolean checkContunieGame(String checkString) {
        return checkString.equals("+") ? true : false;
    }

    private int checkValidInput() {
        int inInteger = 0;
        boolean valid = true;

        do {
            try {
                inInteger = Integer.parseInt(in.readLine());
                valid = false;
            } catch (Exception e) {
                menuGame.printError("Enter an integer. Pleas try again. ");
            }
        }while (valid);

        return inInteger;
    }

    private void startGame() {
        fiilBoard(board);
        startTimeGame();

        String winner = null;
        int checker = 0;

        while (winner == null) {
            int numInput;

            if (checker % 2 == 0) {
                menuGame.printTurn("X");
                numInput = checkValidInput();
                turn = "X";
                countShourts++;
                checker++;
            } else {
                menuGame.printTurn("O");
                numInput = bot.botSelect(board);
                turn = "O";
                checker++;
            }

            if (checkError(numInput, board)) {
                checker--;
                continue;
            } else {
                board[numInput - 1] = turn;
                menuGame.printBoard(board);
                winner = checkWinner();
            }
        }

        if (winner.equalsIgnoreCase("draw")) {
            menuGame.printDraw();
        } else {
            if (winner.equals("X")) {
                stopTimeGame();
                db.addRecord(player);
                menuGame.printWiner(true);
            } else {
                menuGame.printWiner(false);
            }
        }
    }

    private void startTimeGame() {
        timeGame = System.currentTimeMillis();
    }

    private void stopTimeGame() {
        timeGame = System.currentTimeMillis() - timeGame;

        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeGame);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeGame);
        if (seconds >= 60) {
            seconds %= 60;
        }

        player.setSecondsGame(seconds);
        player.setMinutesGame(minutes);
        player.setShotsGame(countShourts);
    }

    private boolean checkError(int inputNumber, String[] board) {
        if (!(inputNumber > 0 && inputNumber <= 9)) {
            menuGame.printError("Reselect cell");
            return true;
        }

        if (!board[inputNumber - 1].equals(String.valueOf(inputNumber))) {
            menuGame.printError("Slot is full");
            return true;
        }
        return false;
    }

    private void fiilBoard(String[] board) {
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }
        menuGame.printBoard(board);
    }

    private String checkWinner() {
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
        return null;
    }
}
