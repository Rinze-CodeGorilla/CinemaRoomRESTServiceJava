package cinema;

import java.util.*;
import java.util.function.Predicate;

public class CinemaRoom {
    int rows;
    int columns;
    Set<Seat> seats;
    Map<UUID, Seat> purchased = new HashMap<>();

    public CinemaRoom() {
    }

    public CinemaRoom(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        seats = new HashSet<>(rows * columns);
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                seats.add(new Seat(row, column));
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    Seat getSeat(int row, int column) throws OutOfBoundsException {
        var seat = new Seat(row, column);
        if (!seats.contains(seat)) throw new OutOfBoundsException();
        return seat;
    }

    Ticket purchase(int row, int column) throws AlreadyPurchasedException, OutOfBoundsException {
        var seat = getSeat(row, column);
        if (purchased.containsValue(seat)) throw new AlreadyPurchasedException();
        var token = UUID.randomUUID();
        purchased.put(token, seat);
        return new Ticket(token, seat);
    }

    Ticket returnTicket(UUID token) throws WrongTokenException {
        var seat = purchased.remove(token);
        if (seat == null) throw new WrongTokenException();
        return new Ticket(null, seat);
    }
}
