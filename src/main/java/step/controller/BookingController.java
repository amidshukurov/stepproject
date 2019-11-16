package step.controller;
import step.Console;
import step.SystemConsole;
import step.dao.DAOMyBookings;
import step.dao.DAOTimeTableLine;
import step.entity.City;
import step.entity.TimetableLine;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookingController {
  DAOTimeTableLine show = new DAOTimeTableLine();
  DAOMyBookings showBooking = new DAOMyBookings();
  BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/step/data/myFlights.txt")));
  TimetableLine ttl;
  String line;
  String[] splitedLine = new String[6];
  String[] result ;
  Console console = new SystemConsole();

  public BookingController() throws IOException {
  }

  public List<TimetableLine> search(String request) throws IOException, ParseException {

    List<TimetableLine> data = show.getAll();
    List<TimetableLine> result = new ArrayList<>();
    int i = 0;
    for (; i <data.size() ; i++) {
      if(request.toLowerCase().equalsIgnoreCase(data.get(i).getDst().getName())){
        result.add(data.get(i));
      }
    }
    if (i==data.size()) System.out.println("No flight found");
    return result;
  }
  public void add() throws IOException, ParseException {
    /*The user is prompted to enter the following information:
    destination, date, number of people (how many tickets to buy).
    After that, the program must search for flights that meet the specified conditions
    (there must be enough seats for all passengers). All found flights should be displayed on the screen.
    After that, the user can choose one of the found flights by specifying its serial number or
    return to the main menu (selecting item 0). If the user decides to book a flight,
    he will need to enter the names and surnames of all passengers.
*/
    List<TimetableLine> myFlight = new ArrayList<>();
    List<TimetableLine> avaiableFlights;
     while (true){
       System.out.println("Enter Destination City:");
       String dest = console.readLn();
       avaiableFlights = search(dest);
       if (avaiableFlights.size()<1) {
         continue;
       }
       break;
     }

    for (int i = 0; i <avaiableFlights.size() ; i++) {
      System.out.println( avaiableFlights.get(i).toString());
    }
    boolean cont=true;
    while (cont) {
      System.out.println("Select flight number for booking");
      String flightid = console.readLn();
      for (int i = 0; i < avaiableFlights.size(); i++) {
        if (flightid.toLowerCase().equalsIgnoreCase(avaiableFlights.get(i).getFlightId())) {
          System.out.println("How many seat do you want?");
          int seat = Integer.parseInt(console.readLn());
          if (seat < avaiableFlights.get(i).getFreeSeat()) {
            myFlight.add(avaiableFlights.get(i));
            cont=false;
            break;
          } else {
            System.out.printf("No seat available. The number of available seats in this flight are %d", avaiableFlights.get(i).getFreeSeat());
          }
        }
        if (i == avaiableFlights.size() - 1) {
          System.out.println("No such flight number");
        }
      }
    }
    addingToFile(myFlight);


  }

  public void remove() throws IOException, ParseException {
    showMyFlight();
    System.out.println("Select Flightid to cancel");
    String input = console.readLn();
    List<TimetableLine> data = new ArrayList<>();
    String[] result;

    while ((line = br.readLine()) != null) {
      result =line.split(" ");
      int j=0;
      for (int i = 0; i < result.length ; i++) {
        if (!result[i].equals("")) {
          splitedLine[j++] = result[i];
        }
      }
      ttl = new TimetableLine(splitedLine[0],
              new SimpleDateFormat("dd/MM/yyyy").parse(splitedLine[1]),
              new SimpleDateFormat("HHmm").parse(splitedLine[2]),
              new City(1,splitedLine[3]),
              new City(1,splitedLine[4]),
              Integer.parseInt(splitedLine[5]));

      if (!input.equalsIgnoreCase(ttl.getFlightId())){

        data.add(ttl);
      }
      if (line==null) break;
    }
    br.close();

  }

  public void showMyFlight() throws IOException, ParseException {
    DAOMyBookings show = new DAOMyBookings();
    List<TimetableLine> data = show.getAll();
    for (int i = 0; i <data.size() ; i++) {
      System.out.println( data.get(i).toString());
    }

  }
  public void addingToFile (List<TimetableLine> origin) throws IOException, ParseException {
    List<TimetableLine> data =showBooking.getAll();
    for (int i = 0; i <origin.size() ; i++) {
      data.add(origin.get(i));
    }
    for (int i = 0; i <origin.size() ; i++) {
      System.out.println( origin.get(i).toString());
      try (
              BufferedWriter bw =
                      new BufferedWriter(new FileWriter(new File("src/main/java/step/data/myFlights.txt")));
      ) {
        data.forEach(c -> {
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
