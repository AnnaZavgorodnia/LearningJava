import controller.Controller;
import model.RecordBook;
import view.View;

public class Main
{
    public static void main(String[] args)
    {
        Controller controller = new Controller(new RecordBook(), new View());
        controller.processUser();
    }
}
