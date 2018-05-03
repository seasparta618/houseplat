package au.leon.platform.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Leon Wang
 * @email dev.leon618@gmail.com seasparta618@gmail.com
 * @date 2018年5月3日
 * @create 下午3:28:34
 *
 */
@Configuration
@ConditionalOnClass({ HttpClient.class })
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientAutoConfig {

	private final HttpClientProperties properties;

	public HttpClientAutoConfig(HttpClientProperties properties) {
		this.properties = properties;
	}

	/**
	 * httpclient bean 自定义
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(HttpClient.class)
	public HttpClient httpClient() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(properties.getConnectTimeOut())
				.setSocketTimeout(properties.getSocketTimeOut()).build();
		HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
				.setUserAgent(properties.getAgent()).setMaxConnPerRoute(properties.getMaxConnPerRoute())
				.setConnectionReuseStrategy(new NoConnectionReuseStrategy()).build();
		return httpClient;
	}

}
