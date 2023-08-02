package org.tcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Class {
    private static final String API_KEY = "b6907d289e10d714a6e88b30761fae22";
    private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    getWeather();
                    break;
                case 2:
                    getWindSpeed();
                    break;
                case 3:
                    getPressure();
                    break;
                case 0:
                    System.out.println("Exiting the program Thank you.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Get weather");
        System.out.println("2. Get Wind Speed");
        System.out.println("3. Get Pressure");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void getWeather() {
        String date = getUserInputDate();
        String weatherInfo = fetchDataFromAPI(date);
        // Extract and print the temperature information from the weatherInfo JSON
        System.out.println("Temperature on " + date + ": " + extractTemperature(weatherInfo) + " °C");
    }

    private static void getWindSpeed() {
        String date = getUserInputDate();
        String weatherInfo = fetchDataFromAPI(date);
        // Extract and print the wind.speed information from the weatherInfo JSON
        System.out.println("Wind Speed on " + date + ": " + extractWindSpeed(weatherInfo) + " m/s");
    }

    private static void getPressure() {
        String date = getUserInputDate();
        String weatherInfo = fetchDataFromAPI(date);
        // Extract and print the pressure information from the weatherInfo JSON
        System.out.println("Pressure on " + date + ": " + extractPressure(weatherInfo) + " hPa");
    }

    private static String getUserInputDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the date (yyyy-MM-dd HH:mm:ss): ");
        return scanner.nextLine();
    }

    private static String fetchDataFromAPI(String date) {
        String apiUrl = BASE_URL + "&appid=" + API_KEY;
        // You can use any HTTP client library of your choice. Here, we use HttpURLConnection.
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    private static double extractTemperature(String weatherInfo) {
        
        // Here, I'm returning a dummy value of 25.0 as an example.
        return 25.0;
    }

    private static double extractWindSpeed(String weatherInfo) {
      // Here, I'm returning a dummy value of 5.0 as an example.
        return 5.0;
    }

    private static double extractPressure(String weatherInfo) {
      
       // Here, I'm returning a dummy value of 1012.0 as an example.
        return 1012.0;
    }
}
