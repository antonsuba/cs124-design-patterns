package methodvalidator.validators;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import methodvalidator.annotations.LessThan;

@ValidationAnnotation(target=LessThan.class)
public class LessThanValidator implements ValidationHandler {

    public void process(Object arg, Method m) throws Exception{
        // check if numeric type

        m.setAccessible(true);

        if (!(arg instanceof Number))
        {
            throw new RuntimeException(m.getName()+" must be a number");
        }

        LessThan r = m.getAnnotation(LessThan.class);

        double d = ((Number) arg).doubleValue();

        if (d>r.upperBound())
        {
            throw new RuntimeException(m.getName()+" must be less than "+r.upperBound());
        }


    }

}