package methodvalidator.validators;

import java.lang.reflect.Method;

import methodvalidator.annotations.GreaterThan;

@ValidationAnnotation(target=GreaterThan.class)
public class GreaterThanValidator implements ValidationHandler {


    public void process(Object arg, Method m) throws Exception {
        // check if numeric type
        m.setAccessible(true);

        if (!(arg instanceof Number))
        {
            throw new RuntimeException(m.getName()+" must be a number");
        }

        GreaterThan r = m.getAnnotation(GreaterThan.class);

        double d = ((Number) arg).doubleValue();

        if (d<r.lowerBound())
        {
            throw new RuntimeException(m.getName()+" must be greater then "+r.lowerBound());
        }

    }

}

