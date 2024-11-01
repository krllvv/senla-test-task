package org.generator;

import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_SYMBOLS = "!@#$%^&*()_+-={}[]:;<>,.?/";
    private static final String SYMBOLS = UPPERCASE_LETTERS + LOWERCASE_LETTERS + DIGITS + SPECIAL_SYMBOLS;

    private final SecureRandom random = new SecureRandom();

    public void generatePasswordAndShow() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите длину пароля (от 8 до 12 символов): ");
            if (scanner.hasNextInt()) {
                int passwordLength = scanner.nextInt();
                if (passwordLength >= 8 && passwordLength <= 12) {
                    String password = generatePassword(passwordLength);
                    System.out.print("Ваш сгенерированный пароль: " + password);
                    break;
                } else {
                    System.out.println("Длина пароля должна быть от 8 до 12 символов!");
                }
            } else {
                System.out.println("Введите целое число!");
                scanner.next();
            }

        }
    }

    private String generatePassword(int length) {
        StringBuilder password = new StringBuilder();

        password.append(getRandomSymbol(UPPERCASE_LETTERS));
        password.append(getRandomSymbol(LOWERCASE_LETTERS));
        password.append(getRandomSymbol(DIGITS));
        password.append(getRandomSymbol(SPECIAL_SYMBOLS));

        for (int i = 4; i < length; i++ ) {
            password.append(getRandomSymbol(SYMBOLS));
        }
        return shuffleString(password.toString());
    }

    private char getRandomSymbol(String from) {
        return from.charAt(random.nextInt(from.length()));
    }

    private String shuffleString(String string) {
        char[] chars = string.toCharArray();
        for (int i = string.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }

}
