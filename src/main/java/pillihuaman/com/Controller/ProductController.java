package pillihuaman.com.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.Help.Constants;
import pillihuaman.com.Service.ProductService;
import pillihuaman.com.base.commons.MyJsonWebToken;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqProduct;
import pillihuaman.com.base.request.ReqStock;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespProduct;
import pillihuaman.com.base.response.ResponseStock;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Product", description = "")
//@RequestMapping("Product/")

public class ProductController {
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private ProductService productService;
	
	@Operation(summary = "Create product", description = "Create product", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}) })
	@PostMapping(path = { Constants.BASE_ENDPOINT + "/product/saveProduct" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespProduct>> SaveProduct(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<RespProduct> response = productService.SaveProduct( jwt,request);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Create product", description = "Create product", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}) })
	@GetMapping(path = { Constants.BASE_ENDPOINT + "/listProduct" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<Object>> GetProduct(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){
		
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		//RespBase<Object> response = productService.SaveProduct( jwt,request);
		return ResponseEntity.ok(null);
	}


	@Operation(summary = "list product by user", description = "list  product by user", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}) })
	@PostMapping(path = { Constants.BASE_ENDPOINT + "/listProductbyUser" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<List<RespProduct>>> listProductbyUser(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqProduct> request){

		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<List<RespProduct>> response = productService.listProductbyUser( jwt,request);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "SAVE STOCK", description = "SAVE STOCK", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}) })
	@PostMapping(path = { Constants.BASE_ENDPOINT + "/product/saveStock" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStock> saveStock(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqStock> request) throws Exception {

		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		ResponseStock response = productService.saveStock( jwt,request).getPayload();
		return ResponseEntity.ok(response);
	}


	    
}