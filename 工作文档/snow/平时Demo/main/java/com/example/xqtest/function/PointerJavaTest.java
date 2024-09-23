package com.example.xqtest.function;

/**
 * @author: hepan
 * @date: 2022/9/5
 * @desc:
 */
public class PointerJavaTest {

    public void testFundParseByValue() {
        User user = new User("hepan");
        changeUserName(user);
        System.out.println("hepan user name = "+user.getName());
    }

    /**
     * Java is "pass-by-value" and not "pass-by reference".
     * 并不能
     */
    void changeUserName(User user) {
        // 可以修改成员变量
        user.setName("ooo");
        // 改变指针并不影响入参传递的对象
        user = new User("1");
    }
}
