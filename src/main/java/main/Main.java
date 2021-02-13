package main;

import configuration.TicketsConfiguration;
import handlers.TicketsHandler;
import handlers.TicketsParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(TicketsConfiguration.class);
        TicketsParser ticketsParser = context.getBean("ticketsParser", TicketsParser.class);
        ticketsParser.parse();

        TicketsHandler handler = context.getBean("handler", TicketsHandler.class);
        String averageFlightTime = handler.averageFlightTime();
        System.out.println("average: "+averageFlightTime);

        String percentileFlightTime = handler.percentileFlightTime();
        System.out.println("percentile: "+percentileFlightTime);
    }
}
