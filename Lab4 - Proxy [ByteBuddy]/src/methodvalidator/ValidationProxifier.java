package methodvalidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import methodvalidator.validators.ValidationAnnotation;
import methodvalidator.validators.ValidationHandler;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

public class ValidationProxifier
{


	public static Object createObjectWithValidation(Object o) throws Exception
	{
		Class<?> type = o.getClass();
        ClassLoader classLoader = type.getClassLoader();

        Class<?> proxyType = new ByteBuddy()
                .subclass(type)
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(new ProxyInvocationHandler(o)))
                .make()
                .load(classLoader)
                .getLoaded();

		return proxyType.newInstance();
	}
	
	
	static class ProxyInvocationHandler implements InvocationHandler
	{
		private Object original;
		private HashMap<Class, ValidationHandler> map = new HashMap<>();
		
		public ProxyInvocationHandler(Object o)
		{
			original = o;

			ScanResult results = new FastClasspathScanner("methodvalidator.validators").scan();
			List<String> allResults = results.getNamesOfClassesWithAnnotation(ValidationAnnotation.class);
			for (String s : allResults)
			{
				Class c = null;
				try {
					c = Class.forName(s);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				assert c != null;
				ValidationAnnotation va = (ValidationAnnotation) c.getAnnotation(ValidationAnnotation.class);
				try {
					map.put(va.target(), (ValidationHandler) c.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// TODO Auto-generated method stub

			for(Object arg : args){
				Annotation[] annotationsList = method.getAnnotations();

				for(Annotation annotation : annotationsList){
					ValidationHandler validationHandler = map.get(annotation.annotationType());
					if(validationHandler != null){
						validationHandler.process(arg, method);
					}
				}

			}

			return method.invoke(original, args);
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		MyClass mc = new MyClass();
		mc = (MyClass) createObjectWithValidation(mc);

		mc.setMyString("test");
		mc.setMyField1(11);
	}
}
