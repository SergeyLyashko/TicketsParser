package handlers;

import jsons.Ticket;

import java.util.List;

public interface TicketParser {

    List<Ticket> getTicketList();
}