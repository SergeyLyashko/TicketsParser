package handlers;

public interface Ticket {

    void setOriginName(String originName);
    String getOriginName();

    void setDestinationName(String destinationName);
    String getDestinationName();

    void setDepartureTime(String departureTime);
    String getDepartureTime();

    void setArrivalTime(String arrivalTime);
    String getArrivalTime();
}
