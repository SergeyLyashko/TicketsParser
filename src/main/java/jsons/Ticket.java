package jsons;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

@Component("ticket")
public class Ticket {

    @SerializedName("origin_name")
    private String originName;

    @SerializedName("destination_name")
    private String destinationName;

    @SerializedName("departure_time")
    private String departureTime;

    @SerializedName("arrival_time")
    private String arrivalTime;

    public Ticket(){
    }

    public void setOriginName(String originName){
        this.originName = originName;
    }

    public String getOriginName(){
        return originName;
    }

    public void setDestinationName(String destinationName){
        this.destinationName = destinationName;
    }

    public String getDestinationName(){
        return destinationName;
    }

    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }

    public String getDepartureTime(){
        return departureTime;
    }

    public void setArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTime(){
        return arrivalTime;
    }
}
