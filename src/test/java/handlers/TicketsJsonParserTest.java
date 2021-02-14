package handlers;

import jsons.TicketImpl;
import jsons.TicketsBundleImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TicketsJsonParserTest {

    private final String testOriginName = "testOrigin";
    private final String testDestinationName = "testDestination";
    private List<Ticket> testAllTickets;

    @Before
    public void setUp() throws Exception {
        this.testAllTickets = new ArrayList<>();

        Ticket testTicket1 = new TicketImpl();
        testTicket1.setDepartureTime("01:00");
        testTicket1.setOriginName(testOriginName);
        testTicket1.setDestinationName(testDestinationName);
        testTicket1.setArrivalTime("03:00");
        testTicket1.setOriginName("");
        testAllTickets.add(testTicket1);

        Ticket testTicket2 = new TicketImpl();
        testTicket2.setDepartureTime("02:00");
        testTicket2.setArrivalTime("04:00");
        testTicket2.setOriginName(testOriginName);
        testTicket2.setDestinationName(testDestinationName);
        testAllTickets.add(testTicket2);
    }

    @Test
    public void parse() {
        TicketsBundle testTicketsBundle = new TicketsBundleImpl();
        testTicketsBundle.add(testAllTickets);
        List<Ticket> expected = testTicketsBundle.getAllTickets();

        List<Ticket> actual = new ArrayList<>();
        actual.addAll(testAllTickets);
        Assert.assertEquals(expected, actual);
    }
}