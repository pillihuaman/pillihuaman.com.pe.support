package pillihuaman.com.support.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.lib.commons.MyJsonWebToken;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqControl;
import pillihuaman.com.lib.request.ReqProduct;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespControl;
import pillihuaman.com.support.Help.Constants;
import pillihuaman.com.support.Service.ControlService;


@RestController

//@RequestMapping("Product/")

public class ControlController {
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private ControlService controlService;

	@PostMapping(path = { Constants.BASE_ENDPOINT + "/control/saveControl" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespControl>> saveControl(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqControl> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<RespControl> response = controlService.saveControl( jwt,request);
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = { Constants.BASE_ENDPOINT + "/listControl" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<Object>> GetProduct(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		//RespBase<Object> response = productService.SaveProduct( jwt,request);
		return ResponseEntity.ok(null);
	}





	    
}