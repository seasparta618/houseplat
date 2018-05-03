package au.leon.platform.config;

import javax.servlet.ServletRegistration;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;

/**
 * 
 * @author Leon Wang
 * @email dev.leon618@gmail.com seasparta618@gmail.com
 * @date 2018年5月3日
 * @create 下午2:04:54
 *
 */
@Configuration
public class ConnectionPoolConfig {

	@ConfigurationProperties(prefix = "spring.druid")
	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setProxyFilters(Lists.newArrayList(statFilter()));
		return druidDataSource;
	}

	@Bean
	public Filter statFilter() {
		StatFilter statFilter = new StatFilter();
		statFilter.setSlowSqlMillis(50000);
		statFilter.setLogSlowSql(true);
		statFilter.setMergeSql(true);
		return statFilter;
	}

	// 添加监控
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	}
}
