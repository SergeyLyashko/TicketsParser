package handlers;

import java.util.List;

public interface TicketsBundle {

    List<Ticket> getAllTickets();

    void add(List<Ticket> allTickets);
}
