package step.controller;

import step.Console;
import step.SystemConsole;
import step.entity.TimetableLine;
import step.service.TimeTableService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class TimetableController {

    public void show() throws IOException, ParseException {
        List<TimetableLine> data = new TimeTableService().serviceGetAll();
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).toString());
        }
    }

    public void showDetail() throws IOException, ParseException {
        show();
        TimetableLine result = new TimeTableService().serviceShowDetail();
        if (result != null) {
            System.out.println(result.toString());
        } else {
            System.out.println("No such flight exist.");
        }
    }
}
