package main;

import handlers.TicketsHandler;
import handlers.TicketsParser;
import jsons.Ticket;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File file = new File("tickets.json");
        TicketsParser ticketsParser = new TicketsParser(file);
        List<Ticket> allTickets = ticketsParser.createTicketsList();


        TicketsHandler ticketsHandler = new TicketsHandler(allTickets);
        String averageFlightTime = ticketsHandler.averageFlightTime("Владивосток", "Тель-Авив");
        System.out.println("average: "+averageFlightTime);
        String percentileFlightTime = ticketsHandler.percentileFlightTime(90);
        System.out.println("percentile: "+percentileFlightTime);

    }
}
