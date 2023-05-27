package application.csv;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static application.utils.Constants.CSV_PATH_WRITE;

public class CsvWriter {

    private static CsvWriter INSTANCE;


    private CsvWriter() {
    }

    public static CsvWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CsvWriter();
        }

        return INSTANCE;
    }

    public String writeLineByLine(List<String[]> lines, Path path) throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path.toFile()))) {
            for (String[] line : lines) {
                writer.writeNext(line);
            }
        }
        // Read the file and return its contents
        return Files.readString(path);
    }


    public void appendToCSV(String[] line, Path path) throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path.toFile(), true))) {
            writer.writeNext(line);
        }
    }

}