package step.controller;

import step.service.BookingsService;

import java.io.*;
import java.text.ParseException;

public class BookingController {

    public BookingController() {
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
