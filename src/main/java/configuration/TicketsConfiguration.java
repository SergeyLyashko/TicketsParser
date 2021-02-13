package configuration;

import com.google.gson.JsonDeserializer;
import handlers.*;
import jsons.TicketImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:ticket-handler-context.xml", "classpath:json-parser-context.xml" })
@ComponentScan(basePackages = {"handlers", "jsons"})
@Configuration
public class TicketsConfiguration {

    public JsonDeserializer<TicketsBundle> jsonDeserializer(){
        return new TicketsBundleDeserializer();
    }

    public TicketsBundleAdapter bundleAdapter(){
        return new TicketsBundleTypeAdapter();
    }

    public Ticket ticket(){
        return new TicketImpl();
    }
}
