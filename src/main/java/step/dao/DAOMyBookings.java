package step.dao;

import step.entity.City;
import step.entity.TimetableLine;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAOMyBookings implements DAO<TimetableLine> {
    private final List<TimetableLine> data = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/step/data/myFlights.txt")));
    TimetableLine ttl;
    String line;
    String[] splitedLine = new String[6];
    String[] result ;

    public DAOMyBookings() throws FileNotFoundException, IOException {
    }


    @Override
    public TimetableLine get(String input) throws IOException {
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
    public void delete(TimetableLine timetableLine) {

    }

}
