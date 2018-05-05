package au.leon.platform.common.utils;

import java.nio.charset.Charset;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashUtils {

	private static final HashFunction FUNCTION = Hashing.md5();
	
	private static final String SALT = "au.leon.salt";
	
	public static String encryptPassword(String passWord){
		HashCode hashCode = FUNCTION.hashString(passWord+SALT,Charset.forName("UTF-8"));
		return hashCode.toString();
	}
}
