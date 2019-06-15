package model;

public class Airplane implements Comparable<Airplane>
{
    private int human_capacity;
    private int load_capacity;
    private double route_length;
    private double fuel_consuming;

    public Airplane(int human_capacity, int load_capacity,
                    double route_length, double fuel_consuming)
    {
        if(human_capacity > 0 && load_capacity > 0 && route_length > 0 && fuel_consuming > 0)
        {
            this.human_capacity = human_capacity;
            this.load_capacity = load_capacity;
            this.route_length = route_length;
            this.fuel_consuming = fuel_consuming;
        } else
            throw  new IllegalArgumentException("Arguments can't be less than zero!");
    }

    @Override
    public String toString()
    {
        return new StringBuffer("Airplane: {\n")
                .append("human capacity = ").append(human_capacity)
                .append(";\nload capacity = ").append(load_capacity)
                .append(";\nroute length = ").append(route_length)
                .append(";\nfuel consuming = ").append(fuel_consuming)
                .append("\n}").toString();
    }

    @Override
    public int compareTo(Airplane airplane)
    {
        if(this.human_capacity == airplane.human_capacity &&
           this.load_capacity == airplane.load_capacity &&
           this.route_length - airplane.route_length < 0.00001 &&
           this.fuel_consuming - airplane.fuel_consuming < 0.00001)
        {
            return 0;
        } else if(this.human_capacity < airplane.human_capacity &&
                this.load_capacity < airplane.load_capacity &&
                this.route_length < airplane.route_length &&
                this.fuel_consuming < airplane.fuel_consuming)
        {
            return -1;
        } else
        {
            return 1;
        }
    }

    public int getHuman_capacity()
    {
        return human_capacity;
    }

    public int getLoad_capacity()
    {
        return load_capacity;
    }

    public double getRoute_length()
    {
        return route_length;
    }

    public double getFuel_consuming()
    {
        return fuel_consuming;
    }
}
