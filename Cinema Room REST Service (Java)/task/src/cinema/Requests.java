package cinema;

import java.util.UUID;

record PurchaseRequest(int row, int column) {
}
record ReturnRequest(UUID token) {
}
