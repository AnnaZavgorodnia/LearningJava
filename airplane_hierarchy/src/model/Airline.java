package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Airline
{
    private String name;
    private List<Airplane> airplanes;

    public Airline(String name)
    {
        if(name != null)
        {
            this.name = name;
            airplanes = new ArrayList<>();
        }
        else throw new IllegalArgumentException("field \'name\' cannot be null!");
    }

    public boolean addAirplane(int human_capacity, int load_capacity,
                               double route_length, double fuel_consuming)
    {
        try
        {
            Airplane toAdd = new Airplane(human_capacity,load_capacity,
                    route_length,fuel_consuming);
            airplanes.add(toAdd);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void sortByRouteLength()
    {
        Collections.sort(airplanes, new RouteLengthComparator());
    }

    public Airplane findFittingFuelComsumingPlane(double min, double max)
    {
        return airplanes.stream().filter(plane ->
                (plane.getFuel_consuming() >= min && plane.getFuel_consuming() <= max))
                .findFirst().orElse(null);
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer("Airline: {\nname = ").append(this.name)
                                                                    .append("\n");
        airplanes.forEach(el ->
        {
            sb.append(el.toString());
            sb.append("\n\n");
        });
        sb.append("}");
        return sb.toString();
    }
}
