package pillihuaman.com.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import pillihuaman.com.Help.Constants;
import pillihuaman.com.Service.UserService;
import pillihuaman.com.model.request.ReqBase;
import pillihuaman.com.model.request.ReqUser;
import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.model.response.RespUser;
import pillihuaman.com.security.MyJsonWebToken;
@RestController
@Tag(name = "User", description = "")
//@RequestMapping("Product/")

public class UserController {
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private UserService userService;
	
	@Operation(summary = "Create User", description = "Create User", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}) })
	@PostMapping(path = { Constants.BASE_ENDPOINT + "/user/saveUser" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespUser>> saveUser(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqUser> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<RespUser> response = userService.registerUser(request.getData());
		return ResponseEntity.ok(response);
	}
	
	

	    
	    
}