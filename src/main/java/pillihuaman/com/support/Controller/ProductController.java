package pillihuaman.com.support.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.lib.request.ReqProduct;
import pillihuaman.com.lib.request.ReqStock;
import pillihuaman.com.lib.response.RespProduct;
import pillihuaman.com.lib.response.ResponseStock;
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
import java.util.List;

@RestController

//@RequestMapping("Product/")

public class ProductController {
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private ProductService productService;
	

	@PostMapping(path = { Constants.BASE_ENDPOINT + "/product/saveProduct" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespProduct>> SaveProduct(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<RespProduct> response = productService.SaveProduct( jwt,request);
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<RespBase<Object>> GetProduct(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		//RespBase<Object> response = productService.SaveProduct( jwt,request);
		return ResponseEntity.ok(null);
	}


	public ResponseEntity<RespBase<List<RespProduct>>> listProductbyUser(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){

		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<List<RespProduct>> response = productService.listProductbyUser( jwt,request);
		return ResponseEntity.ok(response);
	}


	public ResponseEntity<ResponseStock> saveStock(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqStock> request) throws Exception {

		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		ResponseStock response = productService.saveStock( jwt,request).getPayload();
		return ResponseEntity.ok(response);
	}


	    
}