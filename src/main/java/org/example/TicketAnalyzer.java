package org.example;




import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author batal
 * @Date 12.08.2024
 */
public class TicketAnalyzer {
    private final String path;
    private final ObjectMapper mapper;
    public TicketAnalyzer(String path, ObjectMapper mapper) {
        this.path = path;
        this.mapper = mapper;
    }

    public void getSolution() throws IOException {
        List<Ticket> tickets = mapper.readValue(new File(path), new TypeReference<List<Ticket>>() {
        });
        //Фильтруем только рейсы между Владивостоком и Тель - Авивом
        List<Ticket> filteredTickets = new ArrayList<>();
        for (Ticket ticket1 : tickets) {
            if (ticket1.departure.equals("Владивосток") && ticket1.arrival.equals("Тель-Авив")) {
                filteredTickets.add(ticket1);
            }
        }
        // Минимальное время полета для каждого авиаперевозчика
        Map<String, Integer> minFlightTimes = new HashMap<>();
        for (Ticket ticket : filteredTickets) {
            minFlightTimes.put(ticket.carrier, Math.min(minFlightTimes.getOrDefault(ticket.carrier, Integer.MAX_VALUE), ticket.flight_time));
        }
        System.out.println("Минимальное время полета между Владивостоком и Тель-Авивом для каждого авиаперевозчика:");
        for (Map.Entry<String, Integer> entry : minFlightTimes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " минут");
        }
        // Разница между средней ценой и медианой
        List<Integer> prices = filteredTickets.stream().map(ticket -> ticket.price).collect(Collectors.toList());
        double averagePrice = prices.stream().mapToInt(Integer::intValue).average().orElse(0);
        Collections.sort(prices);
        double medianPrice;
        if (prices.size() % 2 == 0) {
            medianPrice = (prices.get(prices.size() / 2 - 1) + prices.get(prices.size() / 2)) / 2.0;
        } else {
            medianPrice = prices.get(prices.size() / 2);
        }

        double priceDifference = averagePrice - medianPrice;

        System.out.println("Разница между средней ценой и медианой: " + priceDifference);
    }
}
