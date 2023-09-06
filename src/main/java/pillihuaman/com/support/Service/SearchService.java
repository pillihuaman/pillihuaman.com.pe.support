package pillihuaman.com.support.Service;


import pillihuaman.com.lib.request.ReqUser;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespUser;

public interface SearchService {
	RespBase<RespUser> getUserByMail(String mail);
	RespBase<RespUser> getUserByUserName(String mail);
	RespBase<RespUser>  registerUser ( ReqUser request);

}
