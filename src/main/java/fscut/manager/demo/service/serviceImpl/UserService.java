package fscut.manager.demo.service.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import fscut.manager.demo.dto.UserDto;
import fscut.manager.demo.util.JwtUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 用户信息接口
 */
@Service
public class UserService {
	
	private static final String encryptSalt = "F12839WhsnnEV$#23b";

    private RedisTemplate<String,String> redisTemplate;

    public UserService(RedisTemplate<String,String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * 保存user登录信息，返回token
     * @param
     */
    public String generateJwtToken(String username) {
    	String salt = JwtUtils.generateSalt();

    	redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);

    	return JwtUtils.sign(username, salt, 3600); //生成jwt token，设置过期时间为1小时
    }
    
    /**
     * 获取上次token生成时的salt值和登录用户信息
     * @param username
     * @return
     */
    public UserDto getJwtTokenInfo(String username) {

		String salt = redisTemplate.opsForValue().get("token:"+username);

    	UserDto user = getUserInfo(username);
    	user.setSalt(salt);
    	return user;
    }

    /**
     * 清除token信息
     * @param 
     * @param
     */
    public void deleteLoginInfo(String username) {


    	 redisTemplate.delete("token:"+username);

    }
    
    /**
     * 获取数据库中保存的用户信息，主要是加密后的密码
     * @param userName
     * @return
     */
    //todo
    public UserDto getUserInfo(String userName) {
    	UserDto user = new UserDto();
    	user.setUserId(1);
    	user.setUsername("admin");
    	user.setEncryptPwd(new Sha256Hash("123456", encryptSalt).toHex());
    	return user;
    }
    
    /**
     * 获取用户角色列表，强烈建议从缓存中获取
     * @param userId
     * @return
     */
    //todo
    public List<String> getUserRoles(Integer userId){
    	return Arrays.asList("admin");
    }

}
