package Tic_Tok_Toe.Players;

import java.util.Random;

public class Bot {
    public int botSelect(String [] board){
        Random rand = new Random();
        int select = rand.nextInt(8)+2;
        if((board[select - 1].equals(String.valueOf(select)))){
            return select;
        }
        return botSelect(board);
    }
}
