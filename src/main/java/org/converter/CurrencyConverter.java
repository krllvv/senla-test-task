package org.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private final static Map<String, Double> EXCHANGE_RATES = new HashMap<>();
    static {
        EXCHANGE_RATES.put("USD", 1.0);
        EXCHANGE_RATES.put("EUR", 0.92);
        EXCHANGE_RATES.put("RUB", 97.44);
        EXCHANGE_RATES.put("BYN", 3.27);
        EXCHANGE_RATES.put("PLN", 4.03);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Доступные валюты: " + EXCHANGE_RATES.keySet());
        boolean converted = false;
        while (!converted) {
            System.out.println("Введите сумму и валюту, которую хотите конвертировать (в формате ЧИСЛО ВАЛЮТА): ");
            String amount = scanner.next();
            String from = scanner.next().toUpperCase();
            if (!isNumeric(amount)) {
                System.out.println("Ошибка при вводе данных!");
                continue;
            }
            if (!EXCHANGE_RATES.containsKey(from)) {
                System.out.println("Данная валюта недоступна. Доступные валюты: " + EXCHANGE_RATES.keySet());
                continue;
            }
            System.out.println("Введите валюту, в которую вы хотите конвертировать: ");
            String to = scanner.next().toUpperCase();

            if (!EXCHANGE_RATES.containsKey(to)) {
                System.out.println("Данная валюта недоступна. Доступные валюты: " + EXCHANGE_RATES.keySet());
                continue;
            }
            double amountValue = Double.parseDouble(amount);
            double result = convert(amountValue, from, to);
            System.out.printf("%.2f %s = %.2f", amountValue, from, result);
            converted = true;
        }

    }

    private double convert(Double amount, String from, String to) {
        double fromRate = EXCHANGE_RATES.get(from);
        double toRate = EXCHANGE_RATES.get(to);
        return (toRate / fromRate) * amount;
    }

    private boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
