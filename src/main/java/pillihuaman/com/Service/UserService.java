package pillihuaman.com.Service;

import pillihuaman.com.base.request.ReqUser;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespUser;


public interface UserService {
	RespBase<RespUser> getUserByMail(String mail);
	RespBase<RespUser> getUserByUserName(String mail);
	RespBase<RespUser>  registerUser ( ReqUser request);

}
