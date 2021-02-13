package handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service("ticketsParser")
public class TicketsJsonParser implements TicketsParser {
    private final String jsonFilePath;
    private TicketsBundle ticketsBundle;
    private JsonDeserializer<TicketsBundle> jsonDeserializer;
    private TicketsBundleAdapter bundleAdapter;

    @Autowired
    public TicketsJsonParser(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @Autowired
    public void setJsonDeserializer(JsonDeserializer<TicketsBundle> jsonDeserializer){
        this.jsonDeserializer = jsonDeserializer;
    }

    @Autowired
    public void setBundleAdapter(TicketsBundleAdapter bundleAdapter){
        this.bundleAdapter = bundleAdapter;
    }

    @Override
    public void parse() {
        Type ticketListType = new TypeToken<List<Ticket>>() {}.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TicketsBundle.class, jsonDeserializer)
                .registerTypeAdapter(ticketListType, bundleAdapter)
                .create();
        InputStreamReader inputStreamReader = encodeReader(jsonFilePath);
        if(inputStreamReader != null) {
            this.ticketsBundle = gson.fromJson(inputStreamReader, TicketsBundle.class);
        }
    }

    @Override
    public TicketsBundle getTicketsBundle(){
        return ticketsBundle;
    }

    private InputStreamReader encodeReader(String filePath) {
        try {
            return new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
