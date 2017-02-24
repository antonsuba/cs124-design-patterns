import Annotations.GreaterThan;
import Annotations.LessThan;
import Annotations.NotNull;
import Annotations.Range;

import java.lang.reflect.Field;

public class Validator {

    private void validate(Object o) throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();

        for(Field field : fields){
            if(field.isAnnotationPresent(NotNull.class)){
                field.setAccessible(true);
                if(field.getType().isPrimitive() || field.get(o) == null){
                    throw new RuntimeException();
                }
            }
            if(field.isAnnotationPresent(Range.class)){
                field.setAccessible(true);

                if(field.getType() == boolean.class || field.getType() == char.class || !field.getType().isPrimitive()){
                    throw new RuntimeException();
                }

                Range range = field.getAnnotation(Range.class);
                double value = field.getDouble(o);
                if(value < range.lowerBound() || value > range.upperBound()){
                    throw new RuntimeException();
                }
            }
            if(field.isAnnotationPresent(GreaterThan.class)){
                field.setAccessible(true);

                if(field.getType() == boolean.class || field.getType() == char.class || !field.getType().isPrimitive()){
                    throw new RuntimeException();
                }

                GreaterThan range = field.getAnnotation(GreaterThan.class);
                double value = field.getDouble(o);
                if(value < range.lowerBound()){
                    throw new RuntimeException();
                }
            }
            if(field.isAnnotationPresent(LessThan.class)){
                field.setAccessible(true);

                if(field.getType() == boolean.class || field.getType() == char.class || !field.getType().isPrimitive()){
                    throw new RuntimeException();
                }

                LessThan range = field.getAnnotation(LessThan.class);
                double value = field.getDouble(o);
                if(value > range.upperBound()){
                    throw new RuntimeException();
                }
            }
        }
    }

    public static void main(String[] args){
        Validator validator = new Validator();
        MyClass myClass = new MyClass();
        myClass.setMyField1(2);
        myClass.setMyField2(1);
        myClass.setMyField3(10);
        myClass.setMyString("Anton");

        try {
            validator.validate(myClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
