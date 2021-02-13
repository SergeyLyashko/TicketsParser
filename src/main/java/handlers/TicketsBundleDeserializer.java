package handlers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service("jsonDeserializer")
public class TicketsBundleDeserializer implements JsonDeserializer<TicketsBundle> {

    private TicketsBundle ticketsBundle;

    @Override
    public TicketsBundle deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Type ticketListType = new TypeToken<List<Ticket>>() {}.getType();
        List<Ticket> allTickets = context.deserialize(jsonObject.getAsJsonArray("tickets"), ticketListType);
        ticketsBundle.add(allTickets);
        return ticketsBundle;
    }

    @Autowired
    public void setTicketsBundle(TicketsBundle ticketsBundle){
        this.ticketsBundle = ticketsBundle;
    }
}