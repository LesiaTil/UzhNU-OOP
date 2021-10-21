package Tic_Tok_Toe.DB;

import Tic_Tok_Toe.Players.Player;

import java.io.*;

public class DB {
    private BufferedReader in;
    private BufferedWriter out;
    private String[][] recordArray;



    public DB(){
        setRecordArray(parsingLineRecord(getRecordArray()));
    }
    private String readRecord() {
        String line = "";
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream("res\\record.txt"), "UTF-8"));
            while (in.ready()) {
                line += in.readLine();
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Problem Read File");
        }
        return line;
    }

    public void addRecord(Player player) {
        String lineRecord = player.getNamePlayer() + ":" + player.getShotsGame() + ":" + +player.getMinutesGame() + "." + player.getSecondsGame() + ";";
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("res\\record.txt", true), "UTF-8"));
            out.write(lineRecord);
            out.newLine();
            out.close();

        } catch (IOException e) {
            System.out.println("Problem Write File");
        }
    }

    private String[][] sortRecord(String[][] recordArray) {
        for(int i = 0; i < recordArray.length; i++){
            for (int j = 0; j<recordArray.length; j++){
                String[] element = recordArray[j];
                String[] nextElemnt = (j+1 == recordArray.length) ? recordArray[recordArray.length - 1] : recordArray[j + 1];
                if(Integer.parseInt(element[1]) > Integer.parseInt(nextElemnt [1])){
                    recordArray[j] = nextElemnt;
                    recordArray[j+1] = element;
                }else if(Integer.parseInt(element[1]) == Integer.parseInt(nextElemnt [1])){
                    if(Double.parseDouble(element[2]) < Double.parseDouble(nextElemnt[2])){
                        recordArray[j] = nextElemnt;
                        recordArray[j+1] = element;
                    }
                }
            }
        }
        return recordArray;
    }

    public boolean checkNameInRecord(String[][] recordArray, String nameUser) {
        parsingLineRecord(recordArray);

        for (int i = 0; i < recordArray.length; i++) {
            if (recordArray[i][0].equals(nameUser)) {
                return true;
            }
        }
        return false;
    }


    private String[][] parsingLineRecord(String[][] recordArray) {
        String recordLine = readRecord();
        String[] recordLines = recordLine.split(";");
        recordArray = new String[recordLines.length][3];

        for (int i = 0; i < recordLines.length; i++) {
            recordArray[i] = recordLines[i].split(":");
        }

        setRecordArray(sortRecord(recordArray));
        return recordArray;
    }

    public String[][] getRecordArray() {
        return recordArray;
    }

    public void setRecordArray(String[][] recordArray) {
        this.recordArray = recordArray;
    }

    public String[][] getArrayRecord() {
        setRecordArray(parsingLineRecord(getRecordArray()));
        return  getRecordArray();
    }

}
