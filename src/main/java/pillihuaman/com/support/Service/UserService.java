package pillihuaman.com.support.Service;

import pillihuaman.com.lib.commons.MyJsonWebToken;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqProduct;
import pillihuaman.com.lib.request.ReqStock;
import pillihuaman.com.lib.request.ReqUser;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespProduct;
import pillihuaman.com.lib.response.RespUser;
import pillihuaman.com.lib.response.ResponseStock;



public interface UserService {
	RespBase<RespUser> getUserByMail(String mail);
	RespBase<RespUser> getUserByUserName(String mail);
	RespBase<RespUser>  registerUser ( ReqUser request);

}
