package pillihuaman.com.support.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.support.Help.Constants;
import pillihuaman.com.support.RequestResponse.dto.SystemRequest;
import pillihuaman.com.support.Service.SystemService;

@RestController
@Tag(name = "system", description = "")


public class pageController {

    @Autowired
    private SystemService systemService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    private final String urlBaseController = "/system";

    @Operation(summary = "Save and Update csystem", description = "Save and Update system", tags = {""}, security = {
            @SecurityRequirement(name = Constants.BEARER_JWT)})
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
            @ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
            @ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))})})
    @PostMapping(path = {Constants.BASE_ENDPOINT + Constants.ENDPOINT + urlBaseController + "/saveSystem"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveControl(
            @PathVariable String access,
            @Valid @RequestBody ReqBase<SystemRequest> request, @RequestHeader("Authorization") String authorizationHeader,
            HttpServletRequest httpServletRequest) {
        String authorizationHeaders = httpServletRequest.getHeader("Authorization");
        return ResponseEntity.ok(systemService.saveSystem(request, authorizationHeaders));
    }
}