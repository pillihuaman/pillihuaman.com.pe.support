package pillihuaman.com.Service.Implement;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pillihuaman.com.Service.UserService;
import pillihuaman.com.base.request.ReqUser;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespUser;
import pillihuaman.com.basebd.help.ConvertClass;
import pillihuaman.com.basebd.user.domain.User;
import pillihuaman.com.basebd.user.domain.dao.implement.UserDaoImplement;

import pillihuaman.com.security.PasswordUtils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDaoImplement userDaoImplement;
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public RespBase<RespUser> getUserByMail(String mail) {
		// TODO Auto-generated method stub
		RespBase<RespUser> user = new RespBase<RespUser>();
		User u = userDaoImplement.findUserByMail(mail).get(0);
		if (u != null) {
			user.setPayload(ConvertClass.respUserDtoToUser(u));
		}
		
		return user;
	}

	@Override
	public RespBase<RespUser> getUserByUserName(String mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespBase<RespUser> registerUser(ReqUser request) {
		RespBase<RespUser> response = new RespBase<RespUser>();
		try {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			User tbl = new User();
			tbl = ConvertClass.userDtoToUserTbl(request);

			String salt = PasswordUtils.getSalt(30);
			String mySecurePassword = PasswordUtils.generateSecurePassword(request.getPassword(), salt);
			String codeString = bCryptPasswordEncoder.encode(mySecurePassword);

			System.out.println("salt   " + salt);
			System.out.println("Api Password   " + mySecurePassword);
			System.out.println("Password   " + codeString);

			tbl.setApi_password(mySecurePassword);
			tbl.setSal_password(salt);
			tbl.setPassword(codeString);
			List<User> list = userDaoImplement.findLastUser();
			if (list != null && list.size() > 0) {
				tbl.setId_user(list.get(0).getId_user() + 1);
				userDaoImplement.saveUser(tbl);
			} else {
				tbl.setId_user(1);
				userDaoImplement.saveUser(tbl);
			}

			response.getStatus().setSuccess(Boolean.TRUE);
			response.setPayload(new RespUser());
		} catch (Exception e) {

			response.getStatus().setSuccess(Boolean.FALSE);
			throw e;

			// response.getStatus().getError().getMessages().add(e.getMessage());
		}

		return response;
	}

}
