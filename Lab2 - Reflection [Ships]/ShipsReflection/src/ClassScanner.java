import java.lang.reflect.*;
import java.util.List;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;


public class ClassScanner {

	public void printHierarchy(Class c){
		if(c != null) {
			printHierarchy(c.getSuperclass());
			System.out.println("Class: " + c.getName());

			if(c.getDeclaredFields().length > 0){
				System.out.println("Fields:");
			}
			for(Field field : c.getDeclaredFields()){
				System.out.println("	" + Modifier.toString(field.getModifiers()) + " " + field.getType() + " " + field.getName() + ";");
			}

			if(c.getDeclaredConstructors().length > 0){
				System.out.println("Constructors:");
			}

			for(Constructor constructor : c.getDeclaredConstructors()){
				System.out.println("	" + constructor + ";");
			}

			if(c.getDeclaredMethods().length > 0){
				System.out.println("Methods:");
			}
			for(Method method : c.getDeclaredMethods()){
				String parameters = "";
				for(Class parameter : method.getParameterTypes()){
					parameters += parameter.getTypeName() + ",";
				}
				System.out.println("	" + Modifier.toString(method.getModifiers()) + " " + method.getReturnType() + " "
						+ method.getName() + "(" + parameters + ")" + ";");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		FastClasspathScanner scanner = new FastClasspathScanner("lab");
		ScanResult result = scanner.scan();
		ClassScanner classScanner = new ClassScanner();
		
		List<String> allClasses = result.getNamesOfAllClasses();		
		System.out.println(allClasses);

		for(String s : allClasses) {
			Class<?> currentClass = Class.forName(s);
			classScanner.printHierarchy(currentClass);
			System.out.println("------");
		}
	}

}
