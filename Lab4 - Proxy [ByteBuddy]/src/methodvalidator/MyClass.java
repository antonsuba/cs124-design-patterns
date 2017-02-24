package methodvalidator;

import methodvalidator.annotations.GreaterThan;
import methodvalidator.annotations.LessThan;
import methodvalidator.annotations.NotNull;
import methodvalidator.annotations.Range;

public class MyClass {


    private int myField1;
    private int myField2;
    private int myField3;
    private String myString;
    private String myString2;
    
    public int getMyField1()
    {
        return myField1;
    }
    
	@GreaterThan(lowerBound=10)
    public void setMyField1(int myField1)
    {
        this.myField1 = myField1;
    }
    public int getMyField2()
    {
        return myField2;
    }
    
    @LessThan(upperBound=-1)    
    public void setMyField2(int myField2)
    {
        this.myField2 = myField2;
    }
    public int getMyField3()
    {
        return myField3;
    }
    
    @Range(lowerBound=5,upperBound=10)        
    public void setMyField3(int myField3)
    {
        this.myField3 = myField3;
    }
    
    public String getMyString()
    {
        return myString;
    }
    
    @NotNull    
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
