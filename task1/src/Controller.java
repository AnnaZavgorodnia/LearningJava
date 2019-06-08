import java.util.Scanner;

public class Controller
{
    private Model model;
    private View view;
    private Scanner scanner;

    private final String FIRST_CORRECT_STRING = "Hello";
    private final String SECOND_CORRECT_STRING = "World!";

    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }

    public void processUser()
    {
        scanner = new Scanner(System.in);

        model.setFirstWord( getWord(FIRST_CORRECT_STRING, View.FIRST_INTPUT_MESSAGE));
        model.setSecondWord( getWord(SECOND_CORRECT_STRING, View.SECOND_INTPUT_MESSAGE));

        view.printMessage("Result is " + model.getResult());

        scanner.close();
    }

    public String getWord(String correctString, String message)
    {
        view.printMessage(message);
        String word = "";
        while(scanner.hasNext())
        {
            word = scanner.nextLine();
            if(word.equals(correctString))
            {
                view.printMessage(view.CORRECT_INTPUT_MESSAGE);
                break;
            } else
            {
                view.printMessage(view.WRONG_INTPUT_MESSAGE);
            }
        }
        return word;
    }
}
