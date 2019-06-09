package controller;

public class RegExpConsts
{
    public static final String NAME_EXP  = "^[A-Z]+[a-z]*$";
    public static final String NICKNAME__EXP  = "^[A-Za-z0-9-_]{5,12}$";
    public static final String COMMENT_EXP  = "^.{1,20}$";
    public static final String HOME_PHONENUM_EXP  = "^[0-9]-[0-9]{2}-[0-9]{2}$";
    public static final String MOB_PHONENUM_EXP  = "\\+[0-9]{2}[0-9]{3}[0-9]{7}";
    public static final String EMAIL_EXP  = "[A-Za-z-_0-9]+@[a-z]+\\.[a-z]+";
}
