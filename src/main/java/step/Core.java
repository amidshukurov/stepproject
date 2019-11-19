package step;

import step.controller.BookingController;
import step.controller.TimetableController;
import step.io.Command;
import step.io.Parser;

import java.io.IOException;
import java.text.ParseException;

public class Core {

  private final Console console;
  private final Menu menu;
  private final Parser parser;
  private final BookingController bookingController;
  private final TimetableController timetableController;

  public Core(Console console) throws IOException, ParseException {
    this.console = console;
    this.menu = new Menu();
    this.parser = new Parser();
    this.timetableController = new TimetableController();
    this.bookingController = new BookingController();
  }

  public void run() throws IOException, ParseException {
    boolean cont = true;
    while (cont) {
      console.printLn(menu.show());
      String line = console.readLn();
      Command user_input = parser.parse(line);
      switch (user_input) {
        case TIMETABLE_SHOW:
          timetableController.show();
          break;
        case TIMETABLE_SHOWBYFLIGHT:
          timetableController.showDetail();
          break;
        case BOOKING_ADD:
          bookingController.add();
          break;
        case BOOKING_REMOVE:
          bookingController.remove();
          break;
        case BOOKING_MYFLIGHT:
          bookingController.showMyFlight();
          break;
        case EXIT:
          System.out.println("Exited");
          cont = false;
          break;
        default:
          console.printLn(menu.show());
          break;
      }
    }
  }
}
