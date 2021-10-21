package Tic_Tok_Toe.Game_Logic;

public class Menu {

    Menu menu = this;
    private Game game;

    public Menu() {
        game = new Game(menu);
    }

    public void printGreeting() {
        System.out.println("\nWelcome in the console game Tic Tac Toe. Select a menu item\n" +
                "1. Start game \n" +
                "2. Look ranking of champions ");
        game.readItemMenu();
    }

    public void printEnterName() {
        System.out.print("Enter your name: ");
        game.readNamePlayer();
    }

    public void printWiner(boolean winer) {
        if (winer) {
            System.out.println("Winer!!!. Congratulations on your victory. You are included in the table of champions.");
        } else {
            System.out.println("Lose!!!.");
        }
        System.out.println("Do you want to continue the game?");
        game.readContunieGame();
    }

    public void printDraw() {
        System.out.println("Draw");
    }

    public void printError(String error) {
        System.out.println("!!! " + error + " !!!");
    }

    public void printBoard(String[] board) {
        System.out.println("\n ----------- \n"
                + "| " + board[0] + " | " + board[1] + " | " + board[2] + " |\n ----------- \n"
                + "| " + board[3] + " | " + board[4] + " | " + board[5] + " |\n ----------- \n"
                + "| " + board[6] + " | " + board[7] + " | " + board[8] + " |\n ----------- \n");
    }

    public void printRecord(String[][] recordPlayers) {
        System.out.println("----------- Record -----------");
        for (int i = 0; i < recordPlayers.length; i++) {
            System.out.println(i + 1 + ". " + recordPlayers[i][0] + " : " + recordPlayers[i][1] + " shots  " + recordPlayers[i][2] + " minutes ");
        }

    }

    public void printTurn(String turn) {
        System.out.println(turn + "'s turn; enter a slot num " + turn + " in:");
    }
}
