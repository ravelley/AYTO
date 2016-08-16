import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AYTO {
    private static final List<String> girls = Arrays.asList("Alyssa", "Camille", "Emma", "Francesca", "Julia", "Kaylen", "Mikala", "Nicole", "Tori", "Victoria");
    private static final List<String> boys = Arrays.asList("Asaf", "Cam", "Cameron", "Gio", "John", "Morgan", "Prosper", "Sam", "Stephen", "Tyler");

    private static List<List<String>> getPermutations(List<String> items) {
        if (items.size() < 3) {
            return Arrays.asList(items, new ArrayList<>(Arrays.asList(items.get(1), items.get(0))));
        } else {
            List<List<String>> matches = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                List<String> subList = new ArrayList<>(items);
                subList.remove(i);
                List<List<String>> permutations = getPermutations(subList);
                for (int j = 0; j < permutations.size(); j++) {
                    permutations.get(j).add(0, items.get(i));
                    matches.add(permutations.get(j));
                }
            }
            return matches;
        }
    }

    private static List<List<String>> truthBooth(List<List<String>> matches, String girl, String boy, boolean isMatch) {
        List<List<String>> validMatches = new ArrayList<>();
        int index = girls.indexOf(girl);

        System.out.println(girl + " & " + boy + " " + (isMatch ? "\u001B[32m\u2713" : "\u001B[31m\u2717") +  "\u001B[0m");
        for (int i = 0; i < matches.size(); i++) {
            List<String> match = matches.get(i);
            if (isMatch && match.get(index).equals(boy) || !isMatch && !match.get(index).equals(boy)) validMatches.add(match);
        }

        System.out.println(validMatches.size());
        return validMatches;
    }

    private static List<List<String>> ceremony(List<List<String>> matches, List<String> seats, int lights) {
        List<List<String>> validMatches = new ArrayList<>();

        System.out.println("\nLights [\u001B[33m" + lights + "\u001B[0m]");
        for (int i = 0; i < matches.size(); i++) {
            int correct = 0;
            List<String> match = matches.get(i);
            for (int j = 0; j < match.size(); j++) {
                if (match.get(j).equals(seats.get(j))) correct++;
            }
            if (correct == lights) validMatches.add(match);
        }
        System.out.println(validMatches.size());
        return validMatches;
    }

    private static void printMatches(List<List<String>> matches) {
        for (int i = 0; i < matches.size(); i++) {
            printHeader("Match " + (i + 1));
            List<String> match = matches.get(i);
            for (int j = 0; j < girls.size(); j++) {
                System.out.println(girls.get(j) + " - " + match.get(j));
            }
        }
    }

    private static void printHeader(String header) {
        System.out.println("\n====== " + header + " ======");
    }

    public static void main(String[] args) {
        List<List<String>> matches = getPermutations(boys);
        System.out.println("Total matches: " + matches.size());

        int week = 1;
        printHeader("Week " + week++);
        matches = truthBooth(matches, "Tori", "Prosper", false);
        matches = ceremony(matches, Arrays.asList("Sam", "Prosper", "John", "Asaf", "Morgan", "Gio", "Cameron", "Stephen", "Tyler", "Cam"), 3);

        printHeader("Week " + week++);
        matches = truthBooth(matches, "Julia", "John", false);
        matches = ceremony(matches, Arrays.asList("Morgan", "Asaf", "Prosper", "Sam", "Cam", "Gio", "Cameron", "John", "Stephen", "Tyler"), 3);

        printHeader("Week " + week++);
        matches = truthBooth(matches, "Mikala", "Cameron", true);
        matches = ceremony(matches, Arrays.asList("Sam", "Asaf", "Prosper", "Morgan", "Tyler", "Gio", "Cameron", "Cam", "Stephen", "John"), 4);

        printHeader("Week " + week++);
        matches = truthBooth(matches, "Tori", "Asaf", false);
        matches = ceremony(matches, Arrays.asList("Sam", "Asaf", "Cam", "Tyler", "Stephen", "Gio", "Cameron", "Prosper", "Morgan", "John"), 4);

        printHeader("Week " + week++);
        matches = truthBooth(matches, "Kaylen", "Gio", false);
        matches = ceremony(matches, Arrays.asList("Sam", "Asaf", "Cam", "Gio", "Morgan", "Tyler", "Cameron", "Stephen", "John", "Prosper"), 4);

        printHeader("Week " + week++);
        matches = truthBooth(matches, "Alyssa", "Sam", true);
        matches = ceremony(matches, Arrays.asList("Sam", "Asaf", "John", "Gio", "Stephen", "Prosper", "Cameron", "Tyler", "Morgan", "Cam"), 4);

        printHeader("Week " + week++);
        matches = truthBooth(matches, "Victoria", "Cam", false);
        matches = ceremony(matches, Arrays.asList("Sam", "Tyler", "Gio", "Asaf", "Stephen", "John", "Cameron", "Cam", "Morgan", "Prosper"), 4);

        printMatches(matches);
    }
}
