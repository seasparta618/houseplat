package au.leon.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import au.leon.platform.config.EnableHttpClient;
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
public class HouseplatApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseplatApplication.class, args);
	}
}
