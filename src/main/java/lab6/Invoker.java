package lab6;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Invoker {

    public static void run() throws Exception {
        MyClass obj = new MyClass();
        for (Method method : MyClass.class.getDeclaredMethods()) {
            int modifiers = method.getModifiers();
            if (!method.isAnnotationPresent(Repeat.class)
                    || (!Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers))) {
                continue;
            }

            Repeat repeat = method.getAnnotation(Repeat.class);
            method.setAccessible(true);

            Class<?>[] types = method.getParameterTypes();
            Object[] arguments = new Object[types.length];
            for (int i = 0; i < types.length; i++) {
                if (types[i] == String.class) {
                    arguments[i] = "мир";
                } else if (types[i] == int.class) {
                    arguments[i] = 5;
                }
            }

            for (int i = 0; i < repeat.times(); i++) {
                method.invoke(obj, arguments);
            }
        }
    }
}
