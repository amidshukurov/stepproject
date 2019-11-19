package step;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

public class Application {
  public static void main(String[] args) throws IOException, ParseException {
    Console console = new SystemConsole();
    Core app = new Core(console);
/*
    Cities city =  new Cities();
    Iterator<String> it = city.iterator();
    while (it.hasNext()) {
      System.out.printf(">> %s <<\n", it.next());
    }*/
   app.run();
  }
}
