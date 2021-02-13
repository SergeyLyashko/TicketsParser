package jsons;

import handlers.Ticket;
import handlers.TicketsBundle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ticketsBundle")
public class TicketsBundleImpl implements TicketsBundle {

    private List<Ticket> ticketsBundle;

    @Override
    public List<Ticket> getAllTickets(){
        return ticketsBundle;
    }

    @Override
    public void add(List<Ticket> allTickets) {
        this.ticketsBundle = allTickets;
    }
}
