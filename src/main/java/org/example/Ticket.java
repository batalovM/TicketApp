package org.example;

/**
 * @author batal
 * @Date 12.08.2024
 */
public class Ticket {
    public String carrier;//какой-то перевозчик
    public String departure;//отправление
    public String arrival;//прибытие
    public int flight_time;//время полета
    public int price;//цена

    @Override
    public String toString() {
        return "Ticket{" +
                "Перевозчик='" + carrier + '\'' +
                ", отправление='" + departure + '\'' +
                ", прибытие='" + arrival + '\'' +
                ", время полета=" + flight_time +
                ", цена=" + price +
                '}';
    }
}
