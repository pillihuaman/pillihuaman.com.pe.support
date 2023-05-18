package pillihuaman.com.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.Service.ParameterService;
import pillihuaman.com.base.request.ReqParameter;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.ResponseParameter;
import pillihuaman.com.basebd.help.Constants;
import pillihuaman.com.security.MyJsonWebToken;

import javax.servlet.http.HttpServletRequest;


@RestController
@Tag(name = "Imagen", description = "")
@RequestMapping("parameter/")

public class ParameterController {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private ParameterService parameterService;
    @Autowired(required = false)
    protected final Log log = LogFactory.getLog(getClass());

   @Operation(summary = "Save parameter", description = "Save parameter", tags = {""}, security = {
            @SecurityRequirement(name = Constants.BEARER_JWT)})
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
            @ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
            @ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))})})
    @PostMapping(path = {Constants.BASE_ENDPOINT + "saveParameter"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RespBase<ResponseParameter>> saveParameter(
            @PathVariable String access,
            @RequestBody ReqParameter request) throws Exception {

        MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
        RespBase<ResponseParameter>  response = parameterService.SaveParameter(jwt, request);
        return ResponseEntity.ok(response);
    }

}