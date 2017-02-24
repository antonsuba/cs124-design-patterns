package methodvalidator.validators;

import java.lang.reflect.Method;

import methodvalidator.annotations.Range;

@ValidationAnnotation(target=Range.class)
public class RangeValidator implements ValidationHandler {


    public void process(Object arg, Method m) throws Exception {
        // check if numeric type
        m.setAccessible(true);

        if (!(arg instanceof Number))
        {
            throw new RuntimeException(m.getName()+" must be a number");
        }

        Range r = m.getAnnotation(Range.class);

        double d = ((Number) arg).doubleValue();

        if (d>r.upperBound() || d<r.lowerBound())
        {
            throw new RuntimeException(m.getName()+" must be between "+r.lowerBound()+" and "+r.upperBound());
        }


    }
}
