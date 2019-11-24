package step.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface DAO<T> {
    T get(String t) throws IOException, ParseException;

    List<T> getAll() throws IOException, ParseException;

    void put(T t) throws IOException, ParseException;

    void delete(String t) throws IOException, ParseException;
}
