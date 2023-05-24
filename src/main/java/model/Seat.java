package model;
import model.enums.SeatType ;
import model.enums.SeatClass ;
public class Seat {
    private int number ;
    private SeatType seatType ;
    private SeatClass seatClass ;
    private boolean isFree ;

    public Seat() {}

    public Seat(int number) {
        this.number = number;
        if (number < 80) {
            this.seatClass = SeatClass.ECONOMY;
            if (number % 3 == 0) {
                this.seatType = SeatType.WINDOW;
            } else if (number % 3 == 1) {
                this.seatType = SeatType.AISLE;
            } else {
                this.seatType = SeatType.MIDDLE;
            }
        } else if (number >= 80 && number < 125) {
            this.seatClass = SeatClass.BUSINESS;
            if (number % 2 == 0) {
                this.seatType = SeatType.WINDOW;
            } else if (number % 2 == 1) {
                this.seatType = SeatType.AISLE;
            }
        } else {
            this.seatType = SeatType.WINDOW;
            this.seatClass = SeatClass.FIRSTCLASS;
        }
        this.isFree = true;

    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "number=" + number +
                ", seatType=" + seatType +
                ", seatClass=" + seatClass +
                '}';
    }
}
