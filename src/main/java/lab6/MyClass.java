package lab6;

public class MyClass {

    public void publicInfo(String name, int age) {
        System.out.println("Публичный метод publicInfo(): имя " + name + ", возраст " + age);
    }

    public void publicPrint(String text) {
        System.out.println("Публичный метод publicPrint(): " + text);
    }

    @Repeat(times = 3)
    protected void protectedSum(int a, int b) {
        System.out.println("Защищённый метод protectedSum(): " + a + " + " + b + " = " + (a + b));
    }

    protected void protectedPrint(String text) {
        System.out.println("Защищённый метод protectedPrint(): " + text);
    }

    @Repeat(times = 2)
    private void privateMessage(String message) {
        System.out.println("Приватный метод privateMessage(): " + message);
    }

    @Repeat(times = 1)
    private void privateMultiply(int a, int b) {
        System.out.println("Приватный метод privateMultiply(): " + a + " * " + b + " = " + (a * b));
    }
}
