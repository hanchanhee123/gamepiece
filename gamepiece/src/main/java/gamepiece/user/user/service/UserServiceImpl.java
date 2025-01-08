package gamepiece.user.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.user.domain.User;
import gamepiece.user.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;

	@Override
	public Map<String, Object> checkUser(String id, String userPswd) {

		boolean isMatched = false;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User userInfo = userMapper.checkUser(id);
		if(userInfo != null) {
			String checkPw = userInfo.getUserPswd();
			if(checkPw.equals(userPswd)) {
				isMatched = true;
				resultMap.put("userInfo", userInfo);
			}
		}
		resultMap.put("isMatched", isMatched);
		
		return resultMap;
	}

}
