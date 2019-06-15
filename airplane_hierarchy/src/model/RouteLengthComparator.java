package model;

import java.util.Comparator;

public class RouteLengthComparator implements Comparator<Airplane>
{

    @Override
    public int compare(Airplane airplane1, Airplane airplane2)
    {
        if(Math.abs(airplane1.getRoute_length() - airplane2.getRoute_length()) <= 0.00001)
            return 0;
        else if(airplane1.getRoute_length() < airplane2.getRoute_length())
            return -1;
        else
            return 1;
    }
}
