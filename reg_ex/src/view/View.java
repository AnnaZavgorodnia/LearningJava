package view;

public class View
{
    public static final String WRONG_INPUT_FORMAT_MESSAGE  = "Sorry, wrong input format!";
    public static final String NAME_INPUT_FORMAT_MESSAGE  = "It should start with capital letter!";
    public static final String NAME_INPUT_MESSAGE  = "Enter your name";
    public static final String SECONDNAME_INPUT_MESSAGE  = "Enter your second name";
    public static final String FATHERSNAME_INPUT_MESSAGE  = "Enter your fathers name";
    public static final String NICKNAME_INPUT_FORMAT_MESSAGE  = "Enter nickname: it can contain \'-\',\'_\' , be "+
                                                                "longer than 5 chars and shorter than 13";
    public static final String COMMENT_INPUT_FORMAT_MESSAGE  = "Enter comment: should be shorter than 20 chars";
    public static final String HOME_PHONENUM_INPUT_FORMAT_MESSAGE  = "Enter home number: format \'d-dd-dd\'";
    public static final String MOB_PHONENUM_INPUT_FORMAT_MESSAGE  = "Enter mobile number: should start with \'+\'";
    public static final String EMAIL_INPUT_FORMAT_MESSAGE = "Enter email: format ex-ample_123@smth.smth";
    public static final String SUCCESSFULLY_ADDED_MESSAGE = "Record was successfully added";

    public void printMessage(String message)
    {
        System.out.println(message);
    }
}
