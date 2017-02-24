import Annotations.GreaterThan;
import Annotations.LessThan;
import Annotations.NotNull;
import Annotations.Range;

public class MyClass
{
    @GreaterThan(lowerBound=0)
    private int myField1;

    @LessThan(upperBound=10)
    private int myField2;

    @Range(lowerBound=0,upperBound=10)
    private int myField3;

    @NotNull
    private String myString;

    //@Range(lowerBound = 10, upperBound = 20)
    //@LessThan(upperBound = 10)
    private String myString2;

    @GreaterThan(lowerBound = 0)
    private char sampleBool = 'a';

    public int getMyField1()
    {
        return myField1;
    }
    public void setMyField1(int myField1)
    {
        this.myField1 = myField1;
    }
    public int getMyField2()
    {
        return myField2;
    }
    public void setMyField2(int myField2)
    {
        this.myField2 = myField2;
    }
    public int getMyField3()
    {
        return myField3;
    }
    public void setMyField3(int myField3)
    {
        this.myField3 = myField3;
    }
    public String getMyString()
    {
        return myString;
    }
    public void setMyString(String myString)
    {
        this.myString = myString;
    }

    public String getMyString2()
    {
        return myString2;
    }
    public void setMyString2(String myString)
    {
        this.myString2 = myString;
    }

}
