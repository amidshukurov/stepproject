package step.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
/*

            "TimetableLine = {" +
            "FlightId= "+flightId +
            ", Date= " + new SimpleDateFormat("dd/MM/yyyy").format(date)+
            ", Time= "+ new SimpleDateFormat("dd/MM/yyyy").format(time) +
            ", src=" + src.getName() +
            ", dst=" + dst.getName() +
        '}'*/
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TimetableLine)) return false;
    TimetableLine that = (TimetableLine) o;

    if (flightId!=that.flightId) return false;
    if (date != that.date& time != that.time) return false;

    if (src != null ? !src.equals(that.src) : that.src != null) return false;

    return dst != null ? dst.equals(that.dst) : that.dst == null;
  }

  @Override
  public int hashCode() {
    int result = src != null ? src.hashCode() : 0;
    result = 31 * result + (dst != null ? dst.hashCode() : 0);
    result = 31 * result + freeSeat;
    return result;
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
