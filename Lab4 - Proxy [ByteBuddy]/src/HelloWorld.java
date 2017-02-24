import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

public class HelloWorld 
{
	public void run() throws Exception
	{
		Class<?> dynamicType = new ByteBuddy()
				  .subclass(Object.class)
				  .method(ElementMatchers.named("toString"))
				  .intercept(FixedValue.value("Hello World!"))
				  .make()
				  .load(getClass().getClassLoader())
				  .getLoaded();
				 
		Object o = dynamicType.newInstance();
		System.out.println("Class: "+o.getClass());
		System.out.println("Superclass: "+o.getClass().getSuperclass());		
		System.out.println("toString(): "+o.toString());
	}
	
	
	public static void main(String[] args) throws Exception
	{
			new HelloWorld().run();
	}
}
