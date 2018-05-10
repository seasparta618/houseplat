package au.leon.platform.common.utils;

import java.nio.charset.Charset;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * 加密类 给用户密码加盐 并且转化成Hash密码
 * @author Leon Wang	
 * @email dev.leon618@gmail.com seasparta618@gmail.com
 * @date 2018年5月10日
 * @create 下午8:00:11
 *
 */
public class HashUtils {

	private static final HashFunction FUNCTION = Hashing.md5();
	/**
	 * add salt to the password, the longer password is not easy to be decrypted.
	 */
	private static final String SALT = "au.leon.salt";
	
	/**
	 * @param passWord
	 * @return a new password
	 */
	public static String encryptPassword(String passWord){
		HashCode hashCode = FUNCTION.hashString(passWord+SALT,Charset.forName("UTF-8"));
		return hashCode.toString();
	}
}
