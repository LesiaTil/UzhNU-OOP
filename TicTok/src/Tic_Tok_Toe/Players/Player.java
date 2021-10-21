package Tic_Tok_Toe.Players;

public class Player {
    private String namePlayer;
    private long minutesGame;
    private long secondsGame;
    private byte shotsGame;


    public byte getShotsGame() {
        return shotsGame;
    }

    public long getMinutesGame() {
        return minutesGame;
    }

    public long getSecondsGame() {
        return secondsGame;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setShotsGame(byte shotsGame) {
        this.shotsGame = shotsGame;
    }

    public void setMinutesGame(long minutesGame) {
        this.minutesGame = minutesGame;
    }

    public void setSecondsGame(long secondsGame) {
        this.secondsGame = secondsGame;
    }



    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }
}
