package pillihuaman.com.support.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.lib.commons.MyJsonWebToken;
import jakarta.validation.Valid;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqUser;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespUser;
import pillihuaman.com.support.Help.Constants;
import pillihuaman.com.support.Service.UserService;
@RestController
//@RequestMapping("/user/")

public class UserController {
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private UserService userService;
	

	@PostMapping(path = { Constants.BASE_ENDPOINT +Constants.ENDPOINT+ "/user/register" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespUser>> saveUser(
			@Valid @RequestBody ReqBase<ReqUser> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<RespUser> response = userService.registerUser(request.getData());
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = {  Constants.BASE_ENDPOINT +Constants.ENDPOINT+ "/user/list"}, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespUser>> listUser(){

		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<RespUser> response = userService.registerUser(null);
		return ResponseEntity.ok(response);
	}




}