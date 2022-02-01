package ru.anik.word;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GuessWord {
    public static final String ANSI_GREEN = "u001B[34m";
    public static final String ANSI_PURPLE = "u001B[35m";
    public static final String ANSI_WHITE = "u001B[37m";
    public static final String ANSI_RESET = "u001B[0m";

    public GuessWord() {
        //setKeyWords();
        keyWords = new ArrayList<String>();
        keyWords.add("кошка");
        keyWords.add("порка");
        keyWords.add("слеза");
        keyWords.add("вепрь");
        keyWords.add("хворь");
        keyWords.add("пшено");
        keyWords.add("ворох");
        keyWords.add("пурга");
        keyWords.add("волна");
        keyWords.add("атака");
        int keeLen = keyWords.size();

        Random random = new Random();
        int numberElement = random.nextInt(keeLen);
        keyWord = keyWords.get(numberElement%keeLen);
    }

    List<String> keyWords;

    String keyWord;

    private int getInfo(final String guessWord) {
        if (!keyWords.contains(guessWord)) {
            System.out.println(ANSI_WHITE + "this word is not exist!" + ANSI_RESET);
            return 0;
        }
        if (guessWord.equals(keyWord)) {
            System.out.println("you are right!");
            return guessWord.length();
        }
        int [] result = getOKposition(guessWord, keyWord);
        StringBuilder sb = new StringBuilder();
        int amount = 0;
        for(int i = 0; i < result.length; i++) {
            if (result[i] == 1) {
                sb.append(keyWord.toCharArray()[i]);
                amount++;
            } else {
                sb.append("*");
            }
        }
        System.out.println(sb.toString());
        return amount;
    }

    int[] getOKposition (String guessWord, String keyWord) {
        int [] result = new int [5];
        char [] g = guessWord.toCharArray();
        char [] k = keyWord.toCharArray();
        for (int i = 0; i < g.length; i++) {
            if (g[i] == k[i]) {
                result[i] = 1;
                continue;
            }
            if (keyWord.indexOf(g[i])> 1) {
                result[i] = -1;
            }
        }
        return result;
    }

    private void setKeyWords () {
        keyWords.add("кошка");
        keyWords.add("порка");
        keyWords.add("слеза");
        keyWords.add("вепрь");
        keyWords.add("хворь");
        keyWords.add("волна");
        keyWords.add("атака");
    }

    private String setKeyWord() {
        int keeLen = keyWords.size();

        Random random = new Random(keeLen);
        int numberElement = random.nextInt();
        return keyWords.get(numberElement);
    }

    public static void main(String[] args) {
        GuessWord guessWord = new GuessWord();
        guessWord.readAndGuess();
    }

    public void readAndGuess(){
        int result = 0;
        String str;
        Scanner sc = new Scanner(System.in);
        while (result < keyWord.length()) {
            str = sc.nextLine();
            if (str.length() != keyWord.length()) {
                System.out.println("length is not correct!");
                continue;
            }
            result = getInfo(str);
        }
        System.out.println("you are win!");
    }

}
