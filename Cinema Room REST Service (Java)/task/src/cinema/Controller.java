package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final CinemaRoom cinemaRoom;

    Controller(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    @GetMapping("/seats")
    public CinemaRoom seats() {
        return cinemaRoom;
    }

    @GetMapping("/stats")
    public Stats stats(@RequestParam(required = false) String password) throws BadPasswordException {
        if (!"super_secret".equals(password)) throw new BadPasswordException();
        int purchased = cinemaRoom.purchased.size();
        int available = cinemaRoom.seats.size() - purchased;
        int income = cinemaRoom.purchased.values().stream().mapToInt(Seat::getPrice).sum();
        return new Stats(income, available, purchased);
    }

    @PostMapping("/purchase")
    public Ticket purchase(@RequestBody PurchaseRequest purchase) throws AlreadyPurchasedException, OutOfBoundsException {
        return cinemaRoom.purchase(purchase.row(), purchase.column());
    }

    @PostMapping("/return")
    public Ticket returnTicket(@RequestBody ReturnRequest returnRequest) throws WrongTokenException {
        return cinemaRoom.returnTicket(returnRequest.token());
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handle(BadRequestException bre) {
        return new Error(bre.getMessage());
    }

    @ExceptionHandler({BadPasswordException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Error handle(BadPasswordException bpwe) {
        return new Error(bpwe.getMessage());
    }

    static record Error(String error) {
    }
}
