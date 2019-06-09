package model;

public class Record
{
    private String name;
    private String nickname;
    private String comment;
    private String home_num;
    private String mobile_num;
    private String email;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nick_name)
    {
        this.nickname = nick_name;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getHome_num()
    {
        return home_num;
    }

    public void setHome_num(String home_num)
    {
        this.home_num = home_num;
    }

    public String getMobile_num()
    {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num)
    {
        this.mobile_num = mobile_num;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
