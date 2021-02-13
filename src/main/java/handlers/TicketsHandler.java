package handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component("handler")
public class TicketsHandler {

    private static final int TIME_FORMAT_FACTOR = 60;
    private final Comparator<Ticket> ticketComparator = Comparator.comparing(this::getFlightTime);
    private final String originName;
    private final String destinationName;
    private final int percentile;
    private TicketsParser ticketsParser;

    @Autowired
    public void setTicketsParser(TicketsParser ticketsParser){
        this.ticketsParser = ticketsParser;
    }

    @Autowired
    public TicketsHandler(String originName, String destinationName, int percentile){
        this.originName = originName;
        this.destinationName = destinationName;
        this.percentile = percentile;
    }

    /**
     * Average time value search among all tickets
     * based on flight path
     * @return time value
     */
    public String averageFlightTime() {
        Double time = ticketsParser.getTicketsBundle().getAllTickets().stream()
                .filter(ticket -> ticket.getOriginName().equalsIgnoreCase(originName)
                        && ticket.getDestinationName().equalsIgnoreCase(destinationName))
                .collect(Collectors.averagingLong(this::getFlightTime));
        return millisecondsToTime(time.longValue());
    }

    /**
     * Percentile search based on percentile value
     * @return percentile time value
     */
    public String percentileFlightTime(){
        double position = getPosition(percentile);
        int countBefore = getCountBefore((int)position);
        Ticket low = getTicketOnPosition((int) position);
        Ticket high = getTicketOnPosition((int) position + 1);
        if(low != null && high != null){
            double time = getFlightTime(high)+(position - countBefore)*(getFlightTime(high) - getFlightTime(low));
            return millisecondsToTime((long) time);
        }
        return null;
    }

    private Ticket getTicketOnPosition(int position){
        if(!ticketsParser.getTicketsBundle().getAllTickets().isEmpty()) {
            return ticketsParser.getTicketsBundle().getAllTickets().stream()
                    .sorted(ticketComparator)
                    .skip(ticketsParser.getTicketsBundle().getAllTickets().size() - position)
                    .findFirst().get();
        }
        return null;
    }

    private int getCountBefore(int position){
        return (int) ticketsParser.getTicketsBundle().getAllTickets().stream()
                .sorted(ticketComparator)
                .skip(ticketsParser.getTicketsBundle().getAllTickets().size() - position)
                .count();
    }

    private double getPosition(int percentile){
        return (ticketsParser.getTicketsBundle().getAllTickets().size() * (double)percentile/100);
    }

    private long getFlightTime(Ticket ticket){
        long departure = timeToMilliseconds(ticket.getDepartureTime());
        long arrival = timeToMilliseconds(ticket.getArrivalTime());
        return (arrival - departure);
    }

    private long timeToMilliseconds(String time){
        try {
            return new SimpleDateFormat("HH:mm").parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String millisecondsToTime(long milliseconds){
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % TIME_FORMAT_FACTOR;
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds) % TIME_FORMAT_FACTOR;
        return String.format("%02d:%02d", hours, minutes);
    }
}
