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


}