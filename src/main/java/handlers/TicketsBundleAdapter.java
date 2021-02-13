package handlers;

import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.util.List;

public interface TicketsBundleAdapter {

    List<Ticket> read(JsonReader jsonReader) throws IOException;

}
