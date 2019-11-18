package step.controller;

import step.Console;
import step.SystemConsole;
import step.dao.DAOTimeTableLine;
import step.entity.City;
import step.entity.TimetableLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimetableController {
  DAOTimeTableLine show = new DAOTimeTableLine();
  Console console = new SystemConsole();

  public TimetableController() throws IOException, ParseException {
  }

  public void show() throws IOException, ParseException {
    List<TimetableLine> data = show.getAll();
    for (int i = 0; i <data.size() ; i++) {
      System.out.println( data.get(i).toString());
    }
  }

  public void showDetail() throws IOException, ParseException {
    show();
    System.out.println("Select flight id");
    String input = console.readLn();
    if (show.get(input)!= null){

      System.out.println(show.get(input).toString());
    }
    else {
      System.out.println("No flight found");}
  }
}
