package step.service;

import step.Console;
import step.SystemConsole;
import step.dao.DAOTimeTableLine;
import step.entity.TimetableLine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class TimeTableService {
    Console console = new SystemConsole();

    public List<TimetableLine> serviceGetAll() throws IOException, ParseException {
        return new DAOTimeTableLine().getAll();
    }

    public TimetableLine serviceShowDetail() throws IOException, ParseException {
        System.out.println("Select flight id");
        String input = console.readLn();
        return new DAOTimeTableLine().get(input);
    }


    public void addingToFile(List<TimetableLine> origin) {

        for (int i = 0; i < origin.size(); i++) {
            try (
                    BufferedWriter bw =
                            new BufferedWriter(new FileWriter(new File("src/main/java/step/data/available_flights.txt")));
            ) {
                origin.forEach(c -> {
                    try {
                        bw.write(c.toString());
                        bw.newLine();
                    } catch (IOException e) {
                        System.out.println("smth went wrong during cities file filling");
                    }
                });
            } catch (IOException e) {
                System.out.println("smth went wrong during cities file creation");
            }

        }


    }
}