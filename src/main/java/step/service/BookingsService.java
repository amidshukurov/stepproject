package step.service;

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

public class BookingsService {
    DAOTimeTableLine show = new DAOTimeTableLine();
    DAOMyBookings showBooking = new DAOMyBookings();
    BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/step/data/myFlights.txt")));
    TimetableLine ttl;
    String line;
    String[] splitedLine = new String[6];
    String[] result;
    Console console = new SystemConsole();

    public BookingsService() throws IOException {
    }

    public List<TimetableLine> search(String request) throws IOException, ParseException {

        List<TimetableLine> data = show.getAll();
        List<TimetableLine> result = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (request.toLowerCase().equalsIgnoreCase(data.get(i).getDst().getName())) {
                count++;
                result.add(data.get(i));
            }
        }
        if (count == data.size()) System.out.println("No flight found");
        return result;
    }

    public void addService() throws IOException, ParseException {
        List<TimetableLine> searchResult;
        List<TimetableLine> availableFlightsAfterBooking = new DAOTimeTableLine().getAll();
        while (true) {
            System.out.println("Enter Destination City:");
            String dest = console.readLn();
            searchResult = search(dest);
            if (searchResult.size() < 1) {
                continue;
            }
            break;
        }

        for (int i = 0; i < searchResult.size(); i++) {
            System.out.println(searchResult.get(i).toString());
        }
        boolean cont = true;
        while (cont) {
            System.out.println("Select flight number for booking");
            String flightid = console.readLn();
            for (int i = 0; i < searchResult.size(); i++) {
                if (flightid.toLowerCase().equalsIgnoreCase(searchResult.get(i).getFlightId())) {
                    System.out.println("How many seat do you want?");
                    int seat = Integer.parseInt(console.readLn());
                    if (seat <= searchResult.get(i).getFreeSeat()) {
                        System.out.println("AMID");
                        System.out.println();
                        for (int j = 0; j < availableFlightsAfterBooking.size(); j++) {
                            if (availableFlightsAfterBooking.get(j).equals(searchResult.get(i))) {
                                availableFlightsAfterBooking.get(j).setFreeSeat(availableFlightsAfterBooking.get(j).getFreeSeat() - seat);
                            }
                        }
                        searchResult.get(i).setFreeSeat(seat);
                        showBooking.put(searchResult.get(i));
                        new TimeTableService().addingToFile(availableFlightsAfterBooking);
                        cont = false;
                        break;
                    } else {
                        System.out.printf("No seat available. The number of available seats in this flight are %d", searchResult.get(i).getFreeSeat());
                    }
                }
                if (i == searchResult.size() - 1) {
                    System.out.println("No such flight number");
                }
            }
        }


    }

    public void removeService() throws IOException, ParseException {
        showMyFlightService();
        System.out.println("Select Flightid to cancel");
        String input = console.readLn();
        showBooking.delete(input);

    }

    public void showMyFlightService() throws IOException, ParseException {
        for (int i = 0; i < showBooking.getAll().size(); i++) {
            System.out.println(showBooking.getAll().get(i).toString());
        }

    }

}
