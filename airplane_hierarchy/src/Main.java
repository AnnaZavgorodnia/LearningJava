import model.Airline;

public class Main
{
    public static void main(String[] args)
    {
        Airline airline = new Airline("hot-spot");

        //System.out.println("Airplane was added: " +
        //        airline.addAirplane(23,23,45,13));
        airline.addAirplane(23,23,45,13);
        airline.addAirplane(23,45,8,53);

        //System.out.println(airline);

        airline.addAirplane(23,45,6,53);
        //airline.sortByRouteLength();

        //System.out.println(airline);

        System.out.println(airline.findFittingFuelComsumingPlane(50,55));
    }
}
