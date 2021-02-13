package jsons;

import handlers.Ticket;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("ticket")
@Scope("prototype")
public class TicketImpl implements Ticket {

    private String originName;
    private String destinationName;
    private String departureTime;
    private String arrivalTime;

    @Override
    public void setOriginName(String originName){
        this.originName = originName;
    }

    @Override
    public String getOriginName(){
        return originName;
    }

    @Override
    public void setDestinationName(String destinationName){
        this.destinationName = destinationName;
    }

    @Override
    public String getDestinationName(){
        return destinationName;
    }

    @Override
    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }

    @Override
    public String getDepartureTime(){
        return departureTime;
    }

    @Override
    public void setArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String getArrivalTime(){
        return arrivalTime;
    }
}
