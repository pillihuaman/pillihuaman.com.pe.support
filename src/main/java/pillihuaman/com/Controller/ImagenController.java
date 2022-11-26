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
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pillihuaman.com.Help.Constants;
import pillihuaman.com.Service.ImagenService;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqImagen;
import pillihuaman.com.base.request.ReqImagenByProduct;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pillihuaman.com.Help.Constants;
import pillihuaman.com.Service.ImagenService;
>>>>>>> 8059a459b9d358fb8b0353862aaa97268c3f9478
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespImagenGeneral;
import pillihuaman.com.security.MyJsonWebToken;

import javax.servlet.http.HttpServletRequest;
<<<<<<< HEAD
import javax.validation.Valid;
=======
import javax.ws.rs.QueryParam;
import java.util.List;
>>>>>>> 8059a459b9d358fb8b0353862aaa97268c3f9478

@RestController
@Tag(name = "Imagen", description = "")
//@RequestMapping("Product/")

public class ImagenController {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private ImagenService imagenService;
    @Autowired(required = false)
    protected final Log log = LogFactory.getLog(getClass());


    @Operation(summary = "get top imagen", description = "get top imagen", tags = {""}, security = {
            @SecurityRequirement(name = Constants.BEARER_JWT)})
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
            @ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
            @ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))})})
    @GetMapping(path = {Constants.BASE_ENDPOINT + "/search/getTopImagen"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RespBase<List<RespImagenGeneral>>> getTopImagen(@PathVariable String access,
                                                                   @QueryParam("page")  int page, @QueryParam("perPage")  int perPage) {

        MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
        RespBase<List<RespImagenGeneral>> response = imagenService.getTopImagen(page,perPage);
        return ResponseEntity.ok(response);
    }


}