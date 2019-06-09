package model;

import java.util.ArrayList;
import java.util.List;

public class RecordBook
{
    private List<Record> records;

    public RecordBook()
    {
        records = new ArrayList<>();
    }

    public void addRecord(String first_name, String second_name, String fathers_name, String nickname,
                          String comment, String home_num, String mobile_num, String email)
    {
        Record record = new Record();

        record.setName(second_name+""+first_name.charAt(0)+". "+fathers_name.charAt(0)+".");
        record.setNickname(nickname);
        record.setComment(comment);
        record.setHome_num(home_num);
        record.setMobile_num(mobile_num);
        record.setEmail(email);

        records.add(record);
    }
}
