package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Operations {

    public static Date generateRandomDate() {
        int year = ThreadLocalRandom.current().nextInt(2024, 2031);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
        int day = ThreadLocalRandom.current().nextInt(1, maxDay + 1);
        int hour = ThreadLocalRandom.current().nextInt(0, 24);
        int minute = ThreadLocalRandom.current().nextInt(0, 60);
        int second = ThreadLocalRandom.current().nextInt(0, 60);
        LocalDate localDate = LocalDate.of(year, month, day);
        LocalTime localTime = LocalTime.of(hour, minute, second);

        return Date.from(localDate.atTime(localTime).atZone(ZoneId.systemDefault()).toInstant());
    }

    private static final ArrayList<String> ciudades = new ArrayList<>(Arrays.asList(
            "Santa Rosa",
            "Marinilla",
            "Itagüí",
            "San Andrés",
            "Cartagena",
            "Bogotá",
            "Medellín",
            "Cali",
            "Santa Marta",
            "Barranquilla",
            "Villavicencio",
            "San Gil",
            "Bucaramanga"
    ));

    public static ArrayList<String> generateRandomCities(int numberOfCities) {
        if (numberOfCities <= 0 || numberOfCities > ciudades.size()) {
            throw new IllegalArgumentException("no valido");
        }

        ArrayList<String> ciudadesAleatorias = new ArrayList<>(ciudades);
        Random random = new Random();

        for (int i = ciudadesAleatorias.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            String temp = ciudadesAleatorias.get(i);
            ciudadesAleatorias.set(i, ciudadesAleatorias.get(index));
            ciudadesAleatorias.set(index, temp);
        }
        return new ArrayList<>(ciudadesAleatorias.subList(0, numberOfCities));
    }

}
