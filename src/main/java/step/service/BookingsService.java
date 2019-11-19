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
    String[] result ;
    Console console = new SystemConsole();

    public BookingsService() throws IOException {
    }

    public List<TimetableLine> search(String request) throws IOException, ParseException {

        List<TimetableLine> data = show.getAll();
        List<TimetableLine> result = new ArrayList<>();
        int count = 0;
        for (int i = 0; i <data.size() ; i++) {
            if(request.toLowerCase().equalsIgnoreCase(data.get(i).getDst().getName())){
                count++;
                result.add(data.get(i));
            }
        }
        if (count==data.size()) System.out.println("No flight found");
        return result;
    }
    public void addService() throws IOException, ParseException {
        List<TimetableLine> myFlight = showBooking.getAll();;
        List<TimetableLine> searchResult;
        List<TimetableLine> availableFlightsAfterBooking = show.getAll();
        while (true){
            System.out.println("Enter Destination City:");
            String dest = console.readLn();
            searchResult = search(dest);
            if (searchResult.size()<1) {
                continue;
            }
            break;
        }

        for (int i = 0; i <searchResult.size() ; i++) {
            System.out.println( searchResult.get(i).toString());
        }
        boolean cont=true;
        while (cont) {
            System.out.println("Select flight number for booking");
            String flightid = console.readLn();
            for (int i = 0; i < searchResult.size(); i++) {
                if (flightid.toLowerCase().equalsIgnoreCase(searchResult.get(i).getFlightId())) {
                    System.out.println("How many seat do you want?");
                    int seat = Integer.parseInt(console.readLn());
                    if (seat < searchResult.get(i).getFreeSeat()) {
                        searchResult.get(i).setFreeSeat(seat);
                        myFlight.add(searchResult.get(i));
//                        for (int j = 0; j <availableFlightsAfterBooking.size() ; j++) {
//                            if (availableFlightsAfterBooking.get(j).equals(searchResult.get(i))){
//                                System.out.println(availableFlightsAfterBooking.get(j).toString());
//                                availableFlightsAfterBooking.get(j).setFreeSeat(availableFlightsAfterBooking.get(j).getFreeSeat()-seat);
//                            }
//                        }
                        new TimeTableService().addingToFile(availableFlightsAfterBooking);
                        cont=false;
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
        addingToFile(myFlight);


    }

    public void removeService() throws IOException, ParseException {
        showMyFlightService();
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
        addingToFile(data);

    }

    public void showMyFlightService() throws IOException, ParseException {
        for (int i = 0; i <showBooking.getAll().size() ; i++) {
            System.out.println( showBooking.getAll().get(i).toString());
        }

    }
    public void addingToFile (List<TimetableLine> origin) throws IOException, ParseException {

        for (int i = 0; i <origin.size() ; i++) {
            try (
                    BufferedWriter bw =
                            new BufferedWriter(new FileWriter(new File("src/main/java/step/data/myFlights.txt")));
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
