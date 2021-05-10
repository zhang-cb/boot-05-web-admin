package com.atguigu.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

//加入starter，不需要自己写配置类
@Deprecated
//@Configuration
public class MyDataSourceConfig {

    /**
     *   DataSourceConfiguration类中导入默认的HikariDataSource时，注解会先判断是否满足 @ConditionalOnMissingBean(DataSource.class)再导入
     *   如果有自己定义的数据源，则不会导入Hikari
     * @return
     */
    //写完下面的注解后，application.yml中设置的值，就会与下面dataSource对象中的属性进行绑定
    //@ConfigurationProperties("spring.datasource")
    //@Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        //设置后可以在监控页面看到sql语句的执行情况
        //wall是防火墙
        //也可以直接在yaml文件中写
        //druidDataSource.setFilters("stat,wall");
        return druidDataSource;
    }

    //添加一个servlet，打开druid的内置监控页
    //http://localhost:8888/druid/index.html
    //@Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> statViewServletRegistrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        // 配置监控页面访问密码
        //允许清空统计数据
        statViewServletRegistrationBean.addInitParameter("resetEnable","true");
        //用户名
        statViewServletRegistrationBean.addInitParameter("loginUsername","druid");
        //密码
        statViewServletRegistrationBean.addInitParameter("loginPassword","druid");
        return statViewServletRegistrationBean;
    }

    //配置WebStatFilter用于采集web-jdbc关联监控的数据
    //@Bean
    public FilterRegistrationBean webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(webStatFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
