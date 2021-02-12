package main;

import configuration.TicketsConfiguration;
import handlers.TicketsHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(TicketsConfiguration.class);
        TicketsHandler handler = context.getBean("handler", TicketsHandler.class);

        String averageFlightTime = handler.averageFlightTime("Владивосток", "Тель-Авив");
        System.out.println("average: "+averageFlightTime);
        String percentileFlightTime = handler.percentileFlightTime(90);
        System.out.println("percentile: "+percentileFlightTime);
    }
}
