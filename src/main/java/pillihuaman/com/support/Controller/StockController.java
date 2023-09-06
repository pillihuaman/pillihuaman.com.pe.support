package pillihuaman.com.support.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.lib.request.ReqProduct;
import pillihuaman.com.lib.response.RespProduct;
import pillihuaman.com.support.Help.Constants;
import pillihuaman.com.support.Service.ProductService;
import pillihuaman.com.lib.commons.MyJsonWebToken;
import jakarta.validation.Valid;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqUser;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespUser;
import pillihuaman.com.support.Help.Constants;
import pillihuaman.com.support.Service.UserService;

@RestController

@RequestMapping("stock/")

public class StockController {
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private ProductService productService;

	@PostMapping(path = { Constants.BASE_ENDPOINT + "saveStock" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespProduct>> saveStock(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<RespProduct> response = productService.SaveProduct( jwt,request);
		return ResponseEntity.ok(response);
	}
	




	    
}