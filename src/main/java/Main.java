import java.io.*;
import java.util.*;

public class Main {
    private final String filePath;
    private final String newFilePath;
    List<String> firstListOfWords;
    List<String> secondListOfWords;

    public Main(String filePath, String newFilePath) {
        this.filePath = filePath;
        this.newFilePath = newFilePath;
        firstListOfWords = new ArrayList<>();
        secondListOfWords = new ArrayList<>();
    }

    public static void main(String[] args) {
        Main main = new Main("src/test/resources/input3.txt", "src/test/resources/output3.txt");
        main.readFileIntoLists();
        Map<String, String> map = main.getResult();
        main.createResultFile(map);
    }

    public void readFileIntoLists() {
        List<String> listOfWords = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (!((line = fileReader.readLine()) == null)) {
                listOfWords.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        firstListOfWords = listOfWords.subList(1, Integer.parseInt(listOfWords.get(0)) + 1);
        secondListOfWords = listOfWords.subList(Integer.parseInt(listOfWords.get(0)) + 2, listOfWords.size());
    }

    public Map<String, String> getResult() {
        Map<String, String> map = new LinkedHashMap<>();

        if (firstListOfWords.size() == 1 && secondListOfWords.size() == 1) {
            map.put(firstListOfWords.get(0), secondListOfWords.get(0));
        }

        if (firstListOfWords.size() > secondListOfWords.size()) {
            for (String word : firstListOfWords) {
                map.putIfAbsent(word, "?");
            }
        }

        for (String firstWord : firstListOfWords) {
            String[] temp1 = firstWord.split(" ");
            for (String secondWord : secondListOfWords) {
                String[] temp2 = secondWord.split(" ");
                for (String word1 : temp1) {
                    for (String word2 : temp2) {
                        if (word1.equalsIgnoreCase(word2) || word1.equalsIgnoreCase(word2.substring(0, word2.length() - 1))) {
                            map.put(firstWord, secondWord);
                        }
                    }
                }
            }
        }

        if (firstListOfWords.size() < secondListOfWords.size()) {
            Set<Map.Entry<String, String>> entrySet = map.entrySet();
            for (String word : secondListOfWords) {
                for (Map.Entry<String, String> pair : entrySet) {
                    if (!word.equalsIgnoreCase(pair.getValue())) {
                        map.put(word, "?");
                    }
                }
            }
        }
        return map;
    }

    public void createResultFile(Map<String, String> map) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(newFilePath))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                fileWriter.write(entry.getKey() + ":" + entry.getValue());
                fileWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
