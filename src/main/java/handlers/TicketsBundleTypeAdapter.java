package handlers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("bundleAdapter")
public class TicketsBundleTypeAdapter extends TypeAdapter<List<Ticket>>
        implements TicketsBundleAdapter, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void write(JsonWriter jsonWriter, List<Ticket> tickets) { }

    @Override
    public List<Ticket> read(JsonReader jsonReader) throws IOException {
        List<Ticket> allTickets = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()){
            if(jsonReader.peek().equals(JsonToken.BEGIN_OBJECT)){
                allTickets.add(createTicket(jsonReader));
            }else{
                jsonReader.skipValue();
            }
        }
        jsonReader.endArray();
        return allTickets;
    }

    private Ticket createTicket(JsonReader jsonReader) throws IOException {
        Ticket ticket = context.getBean("ticket", Ticket.class);
        jsonReader.beginObject();
        while (jsonReader.hasNext()){
            switch (jsonReader.nextName()){
                case "origin_name":
                    ticket.setOriginName(jsonReader.nextString());
                    break;
                case "destination_name":
                    ticket.setDestinationName(jsonReader.nextString());
                    break;
                case "departure_time":
                    ticket.setDepartureTime(jsonReader.nextString());
                    break;
                case "arrival_time":
                    ticket.setArrivalTime(jsonReader.nextString());
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return ticket;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}