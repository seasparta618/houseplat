package au.leon.platform.biz.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.beanutils.BeanHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Lists;

import au.leon.platform.biz.mapper.UserMapper;
import au.leon.platform.biz.service.FileService;
import au.leon.platform.biz.service.MailService;
import au.leon.platform.biz.service.UserService;
import au.leon.platform.common.bean.User;
import au.leon.platform.common.utils.BeanAutoConfiger;
import au.leon.platform.common.utils.HashUtils;

/**
 * 
 * @author Leon Wang
 * @email dev.leon618@gmail.com seasparta618@gmail.com
 * @date 2018年5月3日
 * @create 下午1:45:41
 */
@Service
public class UserServiceImpl implements UserService {
	
	//put a cache, cache is between the user email and key
	//store the cache for 15 minutes
	//if the user account is not activated in 15 minutes, delete
	private final Cache<String,String> registerCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15,TimeUnit.MINUTES)
			.removalListener(new RemovalListener<String, String>() {

				@Override
				public void onRemoval(RemovalNotification<String, String> notification) {
					// TODO Auto-generated method stub 
					userMapper.delete(notification.getValue());
					
				}
			}).build();

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private FileService fileService;
	@Autowired
	private MailService mailService;
	@Value("${domain.name}")
	private String domain;

	public List<User> getUsers() {
		return userMapper.selectUsers();
	}

	/**
	 * @param account
	 * @return
	 * 1，insert into the database, set enable to 0, add salt to the password to strengthen the password encryption
	 * 2, save the user avator to local nginx directory
	 * 3, generate the activate key
	 * 4, send the email to user
	 */
	@Override
	// add transaction annotation, roll back when exception taken
	@Transactional(rollbackFor = Exception.class)
	public boolean addAccount(User account) {
		// TODO Auto-generated method stub
		account.setPasswd(HashUtils.encryptPassword(account.getPasswd()));
		List<String> imgList = fileService.getImgPath(Lists.newArrayList(account.getAvatorFile()));
		if (!imgList.isEmpty()) {
			account.setAvator(imgList.get(0));
		}
		// 自己定义的工具类，用于填充null的值
		BeanAutoConfiger.setDefaultProp(account, User.class);
		BeanAutoConfiger.onInsert(account);
		account.setEnable(0);
		userMapper.insert(account);
		registerNotify(account.getEmail());
		return false;
	}

	/**
	 * 1,缓存key与email的关系
	 * 2,using spring mail to send email
	 * 3,using the asyn framework
	 * @param email
	 */
	@Async
	@Override
	public void registerNotify(String email) {
		// TODO Auto-generated method stub
		String randomKey = RandomStringUtils.randomAlphabetic(10);
		//bound the email and the key together
		registerCache.put(randomKey, email);
		String activationUrl = "http://" + domain + "/account/verify?key="+randomKey;
		mailService.sendMail("Activation Email for the house platform",activationUrl,email);
		
	}
}
