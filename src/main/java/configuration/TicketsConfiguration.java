package configuration;

import handlers.TicketsHandler;
import handlers.TicketsParserImpl;
import jsons.Ticket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@ComponentScan(basePackages = {"handlers", "jsons"})
@Configuration
public class TicketsConfiguration {

    @Bean
    public File file(){
        return new File("tickets.json");
    }

    public TicketsHandler handler(){
        return new TicketsHandler();
    }

    public TicketsParserImpl parser(){
        return new TicketsParserImpl();
    }

    public Ticket ticket(){
        return new Ticket();
    }
}
