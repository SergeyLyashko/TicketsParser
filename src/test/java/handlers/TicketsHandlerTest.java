package handlers;

import jsons.TicketImpl;
import jsons.TicketsBundleImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TicketsHandlerTest {

    private final String testOriginName = "testOrigin";
    private final String testDestinationName = "testDestination";
    private TicketsParser testTicketsParser;

    @Before
    public void setUp() throws Exception {
        this.testTicketsParser = new TicketsParser() {
            private TicketsBundle testTicketsBundle;

            @Override
            public TicketsBundle getTicketsBundle() {
                return testTicketsBundle;
            }

            @Override
            public void parse() {
                List<Ticket> allTickets = new ArrayList<>();

                TicketImpl ticket1 = new TicketImpl();
                ticket1.setDepartureTime("01:00");
                ticket1.setOriginName(testOriginName);
                ticket1.setDestinationName(testDestinationName);
                ticket1.setArrivalTime("03:00");
                ticket1.setOriginName("");
                allTickets.add(ticket1);

                TicketImpl ticket2 = new TicketImpl();
                ticket2.setDepartureTime("02:00");
                ticket2.setArrivalTime("04:00");
                ticket2.setOriginName(testOriginName);
                ticket2.setDestinationName(testDestinationName);
                allTickets.add(ticket2);
                this.testTicketsBundle = new TicketsBundleImpl();
                testTicketsBundle.add(allTickets);
            }
        };
        testTicketsParser.parse();
    }

    @Test
    public void averageFlightTime() {
        int testPercentile = 1;
        TicketsHandler testTicketsHandler = new TicketsHandler(testOriginName, testDestinationName, testPercentile);
        testTicketsHandler.setTicketsParser(testTicketsParser);
        String expected = testTicketsHandler.averageFlightTime();
        Assert.assertEquals(expected, "02:00");
    }

    @Test
    public void percentileFlightTime() {
        int testPercentile = 50;
        TicketsHandler testTicketsHandler = new TicketsHandler(testOriginName, testDestinationName, testPercentile);
        testTicketsHandler.setTicketsParser(testTicketsParser);
        String expected = testTicketsHandler.percentileFlightTime();
        Assert.assertEquals(expected, "02:00");
    }
}