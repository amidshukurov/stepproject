package step.controller;
import step.Console;
import step.SystemConsole;
import step.dao.DAOMyBookings;
import step.dao.DAOTimeTableLine;
import step.entity.City;
import step.entity.TimetableLine;
import step.service.BookingsService;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookingController {

  public BookingController() throws IOException {
  }

  public void add() throws IOException, ParseException {
    new BookingsService().addService();

  }

  public void remove() throws IOException, ParseException {
    new BookingsService().removeService();
  }

  public void showMyFlight() throws IOException, ParseException {
    new BookingsService().showMyFlightService();
  }
}
