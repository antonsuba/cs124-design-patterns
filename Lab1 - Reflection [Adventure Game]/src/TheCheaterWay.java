import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TheCheaterWay {
    public static void main(String[] args) throws Exception {
        Class<?> room2Class = Class.forName("Room2");
        Room2 room2 = (Room2) room2Class.newInstance();
        Field room2word = room2Class.getDeclaredField("wordFound");
        room2word.setAccessible(true);
        room2word.set(room2, true);

        Class<?> room3Class = Class.forName("Room3");
        Room3 room3 = (Room3) room3Class.newInstance();
        Field room3word = room3Class.getDeclaredField("wordFound");
        room3word.setAccessible(true);
        room3word.set(room3, true);

        Class<?> room4Class = Class.forName("Room4");
        Room4 room4 = (Room4) room4Class.newInstance();
        Field room4word = room4Class.getDeclaredField("wordFound");
        room4word.setAccessible(true);
        room4word.set(room4, true);

        Class<?> room5Class = Class.forName("Room5");
        Constructor<?> constructor = room5Class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Room5 room5 = (Room5) constructor.newInstance();
        Class<?>[] parameterTypes2 = {String.class, String.class, String.class};
        Method m = room5Class.getDeclaredMethod("passphrase", parameterTypes2);
        m.setAccessible(true);
        m.invoke(room5, "Ala", "Ka", "Zam");
    }
}
