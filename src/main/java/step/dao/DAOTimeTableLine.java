package step.dao;

import step.entity.City;
import step.entity.TimetableLine;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOTimeTableLine implements DAO<TimetableLine> {
    private final List<TimetableLine> data = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/step/data/available_flights.txt")));
    TimetableLine ttl;
    String line;
    String[] splitedLine = new String[6];
    String[] result ;


    public DAOTimeTableLine() throws IOException {
    }


    @Override
    public TimetableLine get(String input) throws IOException, ParseException {
        getAll();
        for (int i = 0; i <data.size() ; i++) {
            if (input.equalsIgnoreCase(data.get(i).getFlightId())){
                return data.get(i);
            }
        }
        return null;
    }

    @Override
    public List<TimetableLine> getAll() throws IOException, ParseException {
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
                    new City(2,splitedLine[4]),
                    Integer.parseInt(splitedLine[5]));
            data.add(ttl);
            if (line==null) break;
        }
        return data;
    }

    @Override
    public void put(TimetableLine timetableLine) {
        throw new IllegalArgumentException("DAOTimeTableLine:put:not yet");

    }

    @Override
    public void delete(String t) {

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
