package step.dao;

import step.entity.City;
import step.entity.TimetableLine;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOTimeTableLine implements DAO<TimetableLine> {
    private final List<TimetableLine> data = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/step/data/available_flights.txt")));
    TimetableLine ttl;
    String line;
    String[] splitedLine;

    public DAOTimeTableLine() throws FileNotFoundException, IOException {
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

        int id =1;
        while ((line = br.readLine()) != null) {
            splitedLine =line.split(" ");
            ttl = new TimetableLine(splitedLine[0],
                    new SimpleDateFormat("dd/MM/yyyy").parse(splitedLine[1]),
                    new SimpleDateFormat("HHmm").parse(splitedLine[2]),
                    new City(id,splitedLine[3]),
                    new City(id,splitedLine[4]),
                    Integer.parseInt(splitedLine[5]));
            data.add(ttl);
            id++;
            if (line==null) break;
        }
        return data;
    }

    @Override
    public void put(TimetableLine timetableLine) {
        throw new IllegalArgumentException("DAOTimeTableLine:put:not yet");

    }

    @Override
    public void delete(TimetableLine timetableLine) {

    }

}
