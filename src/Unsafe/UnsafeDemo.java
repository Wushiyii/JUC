package Unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        //获取UNSAFE对应的field对象
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        //设置field可以被访问
        field.setAccessible(true);
        //通过field得到该field对象对应的具体对象
        Unsafe unsafe = (Unsafe) field.get(null);

        //创建对象
        User user = (User) unsafe.allocateInstance(User.class);
        Class<? extends User> userClass = user.getClass();
        Field name = userClass.getDeclaredField("name");
        Field age = userClass.getDeclaredField("age");
        Field id = userClass.getDeclaredField("ID");

        //获取实例变量name age在内存中的偏移量，并赋值
        unsafe.putObject(user, unsafe.objectFieldOffset(name), "AAAAA");
        unsafe.putInt(user, unsafe.objectFieldOffset(age), 20);

        Object staticFieldBase = unsafe.staticFieldBase(id);
        System.out.println("staticFieldBase" + staticFieldBase);

        //获取静态变量id的偏移量
        long staticFieldOffset = unsafe.staticFieldOffset(User.class.getDeclaredField("ID"));
        System.out.println("设置前ID为 ：" + unsafe.getObject(staticFieldBase, staticFieldOffset));

        unsafe.putObject(staticFieldBase, staticFieldOffset, "ABCDEFG");
        System.out.println("设置后ID为 ：" + unsafe.getObject(staticFieldBase, staticFieldOffset));
        System.out.println("USER" + user);

        long data = 1000;
        byte size = 1;

        long memoryAddress = unsafe.allocateMemory(size);
        unsafe.putAddress(memoryAddress, data);

        long addrData = unsafe.getAddress(memoryAddress);
        System.out.println("data" + addrData);

    }

}

class User {
    public User() {
        System.out.println("User construct function be invoked");
    }

    private String name;
    private int age;
    private static String ID = "USER_ID";

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", id=" + ID + '\'' +
                '}';
    }
}