package pillihuaman.com.support.Controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.basebd.common.MyJsonWebToken;
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
	
/*
	@PostMapping(path = { Constants.BASE_ENDPOINT +Constants.ENDPOINT+ "/user/register" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespUser>> saveUser(
			@Valid @RequestBody ReqBase<ReqUser> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<RespUser> response = userService.registerUser(request.getData());
		return ResponseEntity.ok(response);
	}*/

    @GetMapping(path = {Constants.BASE_ENDPOINT + Constants.ENDPOINT + "/user/listByStatus"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> listByStatus(@PathParam(value = "enable") boolean enable) {
        MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
        return ResponseEntity.ok(userService.listByStatus(enable));
    }


}