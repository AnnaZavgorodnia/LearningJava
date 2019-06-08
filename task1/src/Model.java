public class Model
{
    private String firstWord;
    private String secondWord;

    public String getResult()
    {
        return firstWord + secondWord;
    }

    public void setFirstWord(String word)
    {
        firstWord = word;
    }

    public void setSecondWord(String word)
    {
        secondWord = word;
    }
}
