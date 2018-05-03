package au.leon.platform.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @author Leon Wang	
 * @email dev.leon618@gmail.com seasparta618@gmail.com
 * @date 2018年5月2日
 * @create 下午11:21:06
 *
 */
@Configuration
public class FilterBeanConfig {
	@Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new LogFilter());
		List<String> urlList = new ArrayList<String>();
		urlList.add("*");
		filterRegistrationBean.setUrlPatterns(urlList);
		return filterRegistrationBean;
	}
}
