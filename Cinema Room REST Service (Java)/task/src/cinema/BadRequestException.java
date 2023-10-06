package cinema;

class BadRequestException extends Exception {
    BadRequestException(String message) {
        super(message);
    }
}
class AlreadyPurchasedException extends BadRequestException {
    AlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }
}

class OutOfBoundsException extends BadRequestException {
    OutOfBoundsException() {
        super("The number of a row or a column is out of bounds!");
    }
}

class WrongTokenException extends BadRequestException{
    WrongTokenException() {
        super("Wrong token!");
    }
}
