package com.atguigu.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import java.util.stream.Stream;

/**
 * 利用@ValueSource等注解，指定入参，我们将可以使用不同的参数进行多次单元测试，而不需要每新增一个参数就新增一个单元测试，省去了很多冗余代码。
 * @ValueSource: 为参数化测试指定入参来源，支持八大基础类以及String类型,Class类型
 * @NullSource: 表示为参数化测试提供一个null的入参
 * @EnumSource: 表示为参数化测试提供一个枚举入参
 * @CsvFileSource：表示读取指定CSV文件内容作为参数化测试入参
 * @MethodSource：表示读取指定方法的返回值作为参数化测试入参(注意方法返回需要是一个流)
 */
@DisplayName("参数化测试")
public class ParameterizedTest1 {

    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    @DisplayName("参数化测试1")
    public void parameterizedTest1(String string) {
        System.out.println(string);
        Assertions.assertTrue(StringUtils.isNotBlank(string));
    }


    @ParameterizedTest
    @MethodSource("method")    //指定方法名(传入的参数是由这个指定的方法执行完后返回的)
    @DisplayName("方法来源参数")
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println(name);
        Assertions.assertNotNull(name);
    }

    static Stream<String> method() {
        return Stream.of("apple", "banana");
    }



    /**
     * 在进行迁移的时候需要注意如下的变化：（其他版本的junit变成junit5）
     * • 注解在 org.junit.jupiter.api 包中，断言在 org.junit.jupiter.api.Assertions 类中，前置条件在 org.junit.jupiter.api.Assumptions
     * 类中。
     * • 把@Before 和@After 替换成@BeforeEach 和@AfterEach。
     * • 把@BeforeClass 和@AfterClass 替换成@BeforeAll 和@AfterAll。
     * • 把@Ignore 替换成@Disabled。
     * • 把@Category 替换成@Tag。
     * • 把@RunWith、@Rule 和@ClassRule 替换成@ExtendWith。
     */

}
