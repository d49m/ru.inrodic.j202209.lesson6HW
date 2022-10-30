package ru.inrodic.j202209.lesson6HW;

import java.util.ArrayList;

public class AutomatSpaceNormalizer implements SpaceNormalizer {
    @Override
    public String normalizeSpace(String str) {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder wordBuffer = new StringBuilder();
        //
        final int EXPECT_WORD = 0;
        final int WORD_RECOGNIZING = 1;
        final int WORD_RECOGNIZING_AFTER_COMM = 2;
        final int ERROR = -1;

        int state = EXPECT_WORD;
        int nextState;
        char[] arr = str.toCharArray();
        int i;
        for (i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (state == EXPECT_WORD) {
                if (ch == ' ') {
                    nextState = EXPECT_WORD;
                } else if (Character.isAlphabetic(ch)) {
                    wordBuffer.append(ch);
                    nextState = WORD_RECOGNIZING;
                } else if (ch == ',') {
                    nextState = EXPECT_WORD;
                } else {
                    nextState = ERROR;
                }

            } else if (state == WORD_RECOGNIZING) {
                if (ch == ' ') {
                    words.add(wordBuffer.toString());
                    wordBuffer.setLength(0);
                    nextState = EXPECT_WORD;
                } else if (Character.isAlphabetic(ch)) {
                    nextState = WORD_RECOGNIZING;
                    wordBuffer.append(ch);
                } else if (ch == ',') {
                    wordBuffer.append(ch + " ");
                    nextState = WORD_RECOGNIZING_AFTER_COMM;
                } else {
                    nextState = ERROR;
                }


            } else if (state == WORD_RECOGNIZING_AFTER_COMM) {
                if (ch == ' ') {
                    nextState = EXPECT_WORD;
                } else if (Character.isAlphabetic(ch)) {
                    nextState = WORD_RECOGNIZING;
                    wordBuffer.append(ch);
                } else if (ch == ',') {
                    nextState = EXPECT_WORD;
                } else {
                    nextState = ERROR;
                }

            } else {
                throw new IllegalStateException();
            }

            state = nextState;
            if (state == ERROR) {
                break;
            }
        }
        if (state == ERROR) {
            System.out.println("Error in position " + i + ", invalid character '" + arr[i] + "'");
        } else {

            if (wordBuffer.length() > 0) {
                words.add(wordBuffer.toString());
                wordBuffer.setLength(0);

            }
            System.out.println("String traversal completed successfully");

        }

        return String.join(" ", words);
    }
}
