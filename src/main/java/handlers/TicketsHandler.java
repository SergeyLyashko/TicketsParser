package handlers;

import jsons.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TicketsHandler {

    private static final int TIME_FORMAT_FACTOR = 60;
    private final List<Ticket> allTickets;
    private final Comparator<Ticket> ticketComparator;

    public TicketsHandler(List<Ticket> tickets) {
        this.allTickets = tickets;
        ticketComparator = Comparator.comparing(this::getFlightTime);
    }

    /**
     * Average time value search among all tickets
     * based on flight path
     * @param originName origin point name
     * @param destinationName destination point name
     * @return time value
     */
    public String averageFlightTime(String originName, String destinationName) {
        Double time = allTickets.stream()
                .filter(ticket -> ticket.getOriginName().equalsIgnoreCase(originName)
                        && ticket.getDestinationName().equalsIgnoreCase(destinationName))
                .collect(Collectors.averagingLong(this::getFlightTime));
        return millisecondsToTime(time.longValue());
    }

    /**
     * Percentile search based on percentile value
     * @param percentile 0<=value<=100
     * @return percentile time value
     */
    public String percentileFlightTime(int percentile){
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
        if(!allTickets.isEmpty()) {
            return allTickets.stream()
                    .sorted(ticketComparator)
                    .skip(allTickets.size() - position)
                    .findFirst().get();
        }
        return null;
    }

    private int getCountBefore(int position){
        return (int) allTickets.stream()
                .sorted(ticketComparator)
                .skip(allTickets.size() - position)
                .count();
    }

    private double getPosition(int percentile){
        return (allTickets.size() * (double)percentile/100);
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
