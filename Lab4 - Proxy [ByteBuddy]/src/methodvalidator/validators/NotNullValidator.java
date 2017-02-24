package methodvalidator.validators;

import java.lang.reflect.Method;

import methodvalidator.annotations.NotNull;

@ValidationAnnotation(target=NotNull.class)
public class NotNullValidator implements ValidationHandler
{
    public void process(Object arg, Method m) throws Exception{
        // check if not a primitive
        if (arg instanceof Number || arg instanceof Character)
        {
            throw new RuntimeException("Cannot be used on a primitive");
        }

        m.setAccessible(true);

        if (arg==null)
        {
            throw new RuntimeException(m.getName()+ " cannot be null");
        }

    }
}
