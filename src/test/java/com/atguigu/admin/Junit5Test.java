package com.atguigu.admin;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Spring Boot 2.2.0 版本开始引入 JUnit 5 作为单元测试默认库
 * 作为最新版本的JUnit框架，JUnit5与之前版本的Junit框架有很大的不同。由三个不同子项目的几个不同模块组成。
 * JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
 * JUnit Platform: Junit Platform是在JVM上启动测试框架的基础，不仅支持Junit自制的测试引擎，其他测试引擎也都可以接入。
 * JUnit Jupiter: JUnit Jupiter提供了JUnit5的新的编程模型，是JUnit5新特性的核心。内部 包含了一个测试引擎，用于在Junit Platform上运行。
 * JUnit Vintage: 由于JUint已经发展多年，为了照顾老的项目，JUnit Vintage提供了兼容JUnit4.x,Junit3.x的测试引擎。
 *
 *
 * 注意：
 * SpringBoot 2.4 以上版本移除了默认对 Vintage 的依赖。如果需要兼容junit4需要自行引入（不能使用junit4的功能 @Test）
 * JUnit 5’s Vintage Engine Removed from spring-boot-starter-test,如果需要继续兼容junit4需要自行引入vintage
 * <dependency>
 *     <groupId>org.junit.vintage</groupId>
 *     <artifactId>junit-vintage-engine</artifactId>
 *     <scope>test</scope>
 *     <exclusions>
 *         <exclusion>
 *             <groupId>org.hamcrest</groupId>
 *             <artifactId>hamcrest-core</artifactId>
 *         </exclusion>
 *     </exclusions>
 * </dependency>
 *
 */


/**
 * @BootstrapWith(SpringBootTestContextBootstrapper.class)
 * @ExtendWith({SpringExtension.class})
 * @ExtendWith :为测试类或测试方法提供扩展类引用
 *
 *
 * 项目发布前，可以打开项目Maven的Lifecycle，选择clean和test,将业务逻辑写在测试方法中，测试运行结束以后，会有一个详细的测试报告，用以判断方法是否可以正常执行业务逻辑
 *
 */
//@SpringBootTest   //加入后，就能取到spring框架中的东西
//为测试类或者测试方法设置展示名称
@DisplayName("Junit5功能测试类")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Junit5Test {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @DisplayName("测试DisplayName注解")
    @Test
    void testDisplayName() {
        System.out.println("1");
        System.out.println(jdbcTemplate);
    }

    @Disabled    //表示测试类或测试方法不执行，类似于JUnit4中的@Ignore
    @DisplayName("测试方法2")
    @Test
    void test2() {
        System.out.println("2");
    }

    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    //表示测试方法运行如果超过了指定时间将会返回错误java.util.concurrent.TimeoutException: testTimeout() timed out after
    // 500 milliseconds
    @Test
    void testTimeout() throws InterruptedException {
        //Thread.sleep(600);
    }

    @RepeatedTest(5)   //表示方法可重复执行
    @Test
    void test3() {
        System.out.println("3");
    }


    @BeforeEach
        //表示在每个单元测试之前执行
    void testBeforeEach() {
        System.out.println("测试就要开始了...");
    }

    @AfterEach
        //表示在每个单元测试之后执行
    void testAfterEach() {
        System.out.println("测试结束了....");
    }


    @BeforeAll  //表示在所有单元测试之前执行
    static void testBeforeAll() {
        System.out.println("测试BeforeAll....");
    }

    @AfterAll   //表示在所有单元测试之后执行
    static void testAfterAll() {
        System.out.println("测试AfterAll....");
    }

    /**
     * 断言：前面断言失败，后面的代码都不会执行
     * <p>
     * 前面放期望值，后面放实际值
     */
    @Test
    @DisplayName("simple assertion")
    public void simpleAssertion() {
        assertEquals(3, 1 + 2, "业务逻辑计算失败");  //判断两个对象或两个原始类型是否相等
        assertNotEquals(3, 1 + 1);  //判断两个对象或两个原始类型是否不相等

        assertNotSame(new Object(), new Object());  //判断两个对象引用是否指向不同的对象
        Object obj = new Object();
        assertSame(obj, obj);                   //判断两个对象引用是否指向同一个对象

        assertFalse(1 > 2);             //判断给定的布尔值是否为 false
        assertTrue(1 < 2);              //判断给定的布尔值是否为 true

        assertNull(null);           //判断给定的对象引用是否为 null
        assertNotNull(new Object());        //判断给定的对象引用是否不为 null
    }


    @Test
    @DisplayName("array assertion")
    public void array() {
        assertArrayEquals(new int[]{1, 2}, new int[]{1, 2});  //判断两个对象或原始类型的数组是否相等（比较对象中的内容）
        //assertArrayEquals(new int[]{2, 1}, new int[] {1, 2});
    }

    /**
     * assertAll 方法接受多个 org.junit.jupiter.api.Executable 函数式接口的实例作为要验证的断言，可以通过 lambda 表达式很容易的提供这些断言
     * 只有当中的断言都成功，才成功
     */
    @Test
    @DisplayName("assert all")
    public void all() {
        assertAll("Math",
                () -> assertEquals(2, 1 + 1, "业务逻辑计算失败"),
                () -> assertTrue(1 > 0, "结果不为true")
        );
    }

    /**
     * 在JUnit4时期，想要测试方法的异常情况时，需要用@Rule注解的ExpectedException变量还是比较麻烦的。而JUnit5提供了一种新的断言方式Assertions.assertThrows() ,
     * 配合函数式编程就可以进行使用。
     *
     * 如果逻辑当中的处理不抛ArithmeticException异常，则断言失败，下面代码停止运行（抛出个对应的异常才算断言成功，才可往下走）
     * 判断业务逻辑一定会出现异常，没有抛相对应的异常，则报错并输出message
     */
    @Test
    @DisplayName("异常测试")
    public void exceptionTest() {
        ArithmeticException exception = Assertions.assertThrows(
                //扔出断言异常
                ArithmeticException.class, () -> {System.out.println(1 % 0);},"逻辑居然是正常的");

    }

    /**
     * Assertions.assertTimeout() 为测试方法设置了超时时间
     */
    @Test
    @DisplayName("超时测试")
    public void timeoutTest() {
        //如果测试方法时间超过1s将会异常
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }

    /**
     *  通过 fail 方法直接使得测试失败，相当于直接抛异常
     */
    @Test
    @DisplayName("快速失败")
    public void shouldFail() {
        fail("This should fail");
    }



}
