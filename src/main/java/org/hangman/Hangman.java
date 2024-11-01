package org.hangman;

import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private static final String[] WORDS = {"переводчик", "программист", "автомобиль", "расписание", "супермаркет"};
    private int lives = 5;
    private char[] guessedLetters;
    private String wordToGuess;
    private StringBuilder playerGuess;

    public Hangman() {
        Random random = new Random();
        guessedLetters = new char[33];
        wordToGuess = WORDS[random.nextInt(WORDS.length)];
        playerGuess = new StringBuilder("_".repeat(wordToGuess.length()));
    }

    public void startGame() {
        System.out.println("Игра 'Виселица'");
        Scanner scanner = new Scanner(System.in);

        while (lives > 0 && playerGuess.indexOf("_") >= 0) {
            System.out.println("Количество жизней: " + lives);
            System.out.println("Текущее слово: " + playerGuess);
            System.out.println("Введите букву: ");

            String in = scanner.nextLine().toLowerCase();
            if ((in.length() != 1) || !Character.isLetter(in.charAt(0))) {
                System.out.println("Введите одну букву!");
                continue;
            }

            char letter = in.charAt(0);
            int letterIndex = letter - 'а';

            if (letterIndex < 0) {
                System.out.println("Введите букву на русском языке!");
                continue;
            }

            if (guessedLetters[letterIndex] > 0) {
                System.out.println("Вы уже вводили данную букву!");
                continue;
            }
            guessedLetters[letterIndex] = letter;

            if (wordToGuess.indexOf(letter) >= 0) {
                updatePlayerGuess(wordToGuess, playerGuess, letter);
                System.out.println("Такая буква есть!");
            } else {
                System.out.println("Такой буквы в слове нет! Вы потеряли 1 жизнь.");
                lives--;
            }

        }

        if (lives > 0) {
            System.out.println("Поздравляем! Ты угадал загаданное слово: " + wordToGuess);
        } else {
            System.out.println("Жизни закончились! Загаданное слово было: " + wordToGuess);
        }

        scanner.close();
    }

    private void updatePlayerGuess(String wordToGuess, StringBuilder playerGuess, char letter) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter) {
                playerGuess.setCharAt(i, letter);
            }
        }
    }
}