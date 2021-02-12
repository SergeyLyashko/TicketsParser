package jsons;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketsBundle {

    @SerializedName("tickets")
    private List<Ticket> ticketsBundle;

    public void setTicketsBundle(List<Ticket> ticketsBundle){
        this.ticketsBundle = ticketsBundle;
    }

    public List<Ticket> getTicketsBundle(){
        return ticketsBundle;
    }
}
