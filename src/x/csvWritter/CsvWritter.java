package x.csvWritter;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
// класс для записи статистики в таблицу
public class CsvWritter {

    public CsvWritter() {
        try {
            clearTheFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // записывает в таблицу 1 строку
    public void writeLine(String[] args) {
        File csvOutputFile = new File("test.csv");
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(csvOutputFile, true))) {
            String line = convertToCSV(args);
            pw.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // перевод строки в правильную кодировку
    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    // очиистка файла
    public void clearTheFile() throws IOException {
        FileWriter fwOb = new FileWriter("test.csv", false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
}
