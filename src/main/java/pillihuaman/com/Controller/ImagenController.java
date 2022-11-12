package pillihuaman.com.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import pillihuaman.com.Help.Constants;
import pillihuaman.com.Help.JsonUtil;
import pillihuaman.com.Service.ImagenService;

import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqImagen;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespImagen;
import pillihuaman.com.security.MyJsonWebToken;

@RestController
@Tag(name = "Imagen", description = "")
//@RequestMapping("Product/")

public class ImagenController {
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private ImagenService imagenService;
	@Autowired(required=false)
	protected final Log log = LogFactory.getLog(getClass());

	@Operation(summary = "Create Imagen", description = "Create Imagen", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class)) }),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class)) }) })

	@PostMapping(path = { Constants.BASE_ENDPOINT + "/imagen/saveImagenProduct" }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespImagen>> saveImagen(@RequestParam("archivo") MultipartFile[] archivo,
														   @RequestParam("imagenData") String imagenData) {
		ReqBase<ReqImagen> request = new ReqBase<ReqImagen>();
		log.info("imagenData:"+imagenData);
		ReqImagen requser = JsonUtil.toObject(imagenData, ReqImagen.class);

		request.setData(requser);
		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		RespBase<RespImagen> response = imagenService.saveImagen(jwt, request,archivo);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "get top imagen", description = "get top imagen", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class)) }),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class)) }) })
	@GetMapping(path = { Constants.BASE_ENDPOINT + "/getTopImagen" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<Object>> getTopImagen(@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqImagen> request) {

		MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		// RespBase<Object> response = productService.SaveProduct( jwt,request);
		return ResponseEntity.ok(null);
	}

}