import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TheLongWay {
    public static void main(String[] args) throws Exception
    {
        Class<?> room1Class = Class.forName("Room1");
        Room1 room1 = (Room1)room1Class.newInstance();

        Class<?> room2Class = Class.forName("Room2");
        Room2 room2 = (Room2) room2Class.newInstance();

        Method m = room2Class.getMethod("swim");
        m.invoke(room2);

        m = room2Class.getMethod("takeSword");
        m.invoke(room2);

        m = room2Class.getMethod("look");
        m.invoke(room2);

        m = room2Class.getDeclaredMethod("dig");
        m.setAccessible(true);
        m.invoke(room2);

        Class<?> room3Class = Class.forName("Room3");
        Room3 room3 = (Room3) room3Class.newInstance();

        m = room3Class.getDeclaredMethod("attack");
        m.setAccessible(true);
        //m.invoke(room3);

        m = room3Class.getMethod("look");
        m.invoke(room3);

        m = room3Class.getMethod("openChest");
        m.invoke(room3);

        Class<?> room4Class = Class.forName("Room4");
        Room4 room4 = (Room4) room4Class.newInstance();

        m = room4Class.getMethod("look");
        m.invoke(room4);

        Class<?>[] parameterTypes = {Integer.TYPE};
        m = room4Class.getMethod("answer", parameterTypes);
        m.invoke(room4, 200754);

        Class<?> room5Class = Class.forName("Room5");
        Constructor<?> constructor = room5Class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Room5 room5 = (Room5) constructor.newInstance();

        Class<?>[] parameterTypes2 = {String.class, String.class, String.class};
        m = room5Class.getDeclaredMethod("passphrase", parameterTypes2);
        m.setAccessible(true);
        m.invoke(room5, "Ala", "Ka", "Zam");
    }

}
