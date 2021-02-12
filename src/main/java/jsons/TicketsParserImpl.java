package jsons;

import com.google.gson.Gson;
import handlers.TicketParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component("parser")
public class TicketsParserImpl implements TicketParser {

    private List<Ticket> allTickets;

    @Autowired
    public void setFile(File file){
        try {
            TicketsBundle ticketsBundle = new Gson().fromJson(new FileReader(file), TicketsBundle.class);
            this.allTickets = ticketsBundle.getAllTickets();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getTicketList(){
        return allTickets;
    }
}