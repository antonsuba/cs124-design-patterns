package methodvalidator.validators;

import java.lang.reflect.Method;

public interface ValidationHandler
{
    public void process(Object arg, Method m) throws Exception;
}
