package controller;

import model.RecordBook;
import view.View;

import java.util.Scanner;

public class Controller
{
    RecordBook model;
    View view;

    Scanner scanner;



    public Controller(RecordBook model, View view)
    {
        this.model = model;
        this.view = view;
    }

    public void processUser()
    {
        scanner  = new Scanner(System.in);

        String firstname = getInput(View.NAME_INPUT_MESSAGE+" "+View.NAME_INPUT_FORMAT_MESSAGE,
                                    RegExpConsts.NAME_EXP);
        String secondname = getInput(View.SECONDNAME_INPUT_MESSAGE+" "+View.NAME_INPUT_FORMAT_MESSAGE,
                RegExpConsts.NAME_EXP);
        String fathersname = getInput(View.FATHERSNAME_INPUT_MESSAGE+" "+View.NAME_INPUT_FORMAT_MESSAGE,
                RegExpConsts.NAME_EXP);
        String nickname = getInput(View.NICKNAME_INPUT_FORMAT_MESSAGE,RegExpConsts.NICKNAME__EXP);
        String comment = getInput(View.COMMENT_INPUT_FORMAT_MESSAGE,RegExpConsts.COMMENT_EXP);
        String home_num = getInput(View.HOME_PHONENUM_INPUT_FORMAT_MESSAGE,RegExpConsts.HOME_PHONENUM_EXP);
        String mobile_num = getInput(View.MOB_PHONENUM_INPUT_FORMAT_MESSAGE, RegExpConsts.MOB_PHONENUM_EXP);
        String email = getInput(View.EMAIL_INPUT_FORMAT_MESSAGE, RegExpConsts.EMAIL_EXP);

        model.addRecord(firstname, secondname, fathersname, nickname, comment, home_num, mobile_num, email);

        view.printMessage(View.SUCCESSFULLY_ADDED_MESSAGE);

        scanner.close();
    }

    private String getInput(String message, String reg_exp)
    {
        String value;
        view.printMessage(message);
        do {
            value = scanner.nextLine();
        } while(!checkValid(value,reg_exp));

        return value;
    }

    private boolean checkValid(String value, String reg_exp)
    {
        boolean matches = value.matches(reg_exp);
        if(!matches) view.printMessage(View.WRONG_INPUT_FORMAT_MESSAGE);
        return matches;
    }
}
