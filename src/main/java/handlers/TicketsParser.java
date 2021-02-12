package handlers;

import com.google.gson.Gson;
import jsons.Ticket;
import jsons.TicketsBundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TicketsParser {

    private final File file;

    public TicketsParser(File file) {
        this.file = file;
    }

    public List<Ticket> createTicketsList() {
        try {
            TicketsBundle ticketsBundle = new Gson().fromJson(new FileReader(file), TicketsBundle.class);
            return ticketsBundle.getTicketsBundle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}