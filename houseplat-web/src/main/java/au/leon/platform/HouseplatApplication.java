package au.leon.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import au.leon.platform.web.autoconfig.EnableHttpClient;


/**
 * 
 * @author Leon Wang	
 * @email dev.leon618@gmail.com seasparta618@gmail.com
 * @date 2018年5月2日
 * @create 下午11:21:10
 *
 */
@SpringBootApplication
@EnableHttpClient
@EnableAsync
public class HouseplatApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseplatApplication.class, args);
	}
}
