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
  List<TimetableLine> data = show.getAll();
  Console console = new SystemConsole();
  BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/step/data/available_flights.txt")));
  TimetableLine ttl;
  String line;
  String[] splitedLine;

  public TimetableController() throws IOException, ParseException {
  }

  public void show() throws IOException, ParseException {

    for (int i = 0; i <data.size() ; i++) {
      System.out.println( data.get(i).toString());
    }
  }


  public void showDetail() throws IOException, ParseException {
    show();
    System.out.println("Select flight id");
    String input = console.readLn();
    while ((line = br.readLine()) != null) {
      splitedLine =line.split(" ");
      ttl = new TimetableLine(splitedLine[0],
              new SimpleDateFormat("dd/MM/yyyy").parse(splitedLine[1]),
              new SimpleDateFormat("HHmm").parse(splitedLine[2]),
              new City(1,splitedLine[3]),
              new City(2,splitedLine[4]),
              Integer.parseInt(splitedLine[5]));
      data.add(ttl);
      if (input.equalsIgnoreCase(ttl.getFlightId())){

        System.out.println(ttl.toString()); break;
      }
      if (line==null) break;
    }
    br.close();
  }
}
