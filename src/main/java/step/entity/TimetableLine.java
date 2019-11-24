package step.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TimetableLine {
  private final String flightId;
  private final Date date;
  private final Date time;
  private final City src;
  private final City dst;
  private int freeSeat;

  public TimetableLine(String flightId, Date date, Date time, City src, City dst, int freeSeat) {
    this.flightId = flightId;
    this.date = date;
    this.time = time;
    this.src = src;
    this.dst = dst;
    this.freeSeat = freeSeat;
  }


  @Override
  public String toString() {
    return String.format("%-20s%-20s%-20s%-20s%-20s%d",flightId,
            new SimpleDateFormat("dd/MM/yyyy").format(date),
            new SimpleDateFormat("HHmm").format(time),
            src.getName(),
            dst.getName(),
            freeSeat);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TimetableLine that = (TimetableLine) o;
    return freeSeat == that.freeSeat &&
            flightId.equals(that.flightId) &&
            date.equals(that.date) &&
            time.equals(that.time) &&
            src.equals(that.src) &&
            dst.equals(that.dst);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flightId, date, time, src, dst, freeSeat);
  }

  public String getFlightId() {
    return flightId;
  }

  public Date getDate() {
    return date;
  }

  public Date getTime() {
    return time;
  }

  public City getSrc() {
    return src;
  }

  public City getDst() {
    return dst;
  }

  public int getFreeSeat() {
    return freeSeat;
  }

  public void setFreeSeat(int freeSeat) {
    this.freeSeat = freeSeat;
  }

}
