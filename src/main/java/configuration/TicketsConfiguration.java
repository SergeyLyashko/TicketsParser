package configuration;

import jsons.TicketsParserImpl;
import jsons.Ticket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.io.File;

@ImportResource(locations = {"classpath:ticket-handler-context.xml"})
@ComponentScan(basePackages = {"handlers", "jsons"})
@Configuration
public class TicketsConfiguration {

    @Bean
    public File file(){
        return new File("tickets.json");
    }

    public TicketsParserImpl parser(){
        return new TicketsParserImpl();
    }

    public Ticket ticket(){
        return new Ticket();
    }
}
