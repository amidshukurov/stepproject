package step.dao;

import step.Console;
import step.SystemConsole;
import step.entity.City;
import step.entity.TimetableLine;
import step.service.TimeTableService;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOMyBookings implements DAO<TimetableLine> {
    private final List<TimetableLine> data = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/step/data/myFlights.txt")));
    TimetableLine ttl;
    String line;
    String[] splitedLine = new String[6];
    String[] result;
    Console console = new SystemConsole();

    public DAOMyBookings() throws IOException {
    }


    @Override
    public TimetableLine get(String input) {
        return null;
    }

    @Override
    public List<TimetableLine> getAll() throws IOException, ParseException {
        while ((line = br.readLine()) != null) {
            result = line.split(" ");
            int j = 0;
            for (int i = 0; i < result.length; i++) {
                if (!result[i].equals("")) {
                    splitedLine[j++] = result[i];
                }
            }
            ttl = new TimetableLine(splitedLine[0],
                    new SimpleDateFormat("dd/MM/yyyy").parse(splitedLine[1]),
                    new SimpleDateFormat("HHmm").parse(splitedLine[2]),
                    new City(1, splitedLine[3]),
                    new City(2, splitedLine[4]),
                    Integer.parseInt(splitedLine[5]));
            data.add(ttl);
            if (line == null) break;
        }
        return data;
    }

    @Override
    public void put(TimetableLine timetableLine) throws IOException, ParseException {
        List<TimetableLine> myFlight = getAll();
        myFlight.add(timetableLine);
        addingToFile(myFlight);
    }


    @Override
    public void delete(String input) throws IOException, ParseException {

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

    public void addingToFile (List<TimetableLine> origin) {

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
