package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ){
        TicketAnalyzer analyzer = new TicketAnalyzer("tickets.json", new ObjectMapper());
        try {
            analyzer.getSolution();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
