package pillihuaman.com.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.Help.Constants;
import pillihuaman.com.Service.ImagenService;
import pillihuaman.com.base.commons.Parameters;
import pillihuaman.com.base.request.ImagenDetail;
import pillihuaman.com.base.request.ReqImagenByProduct;
import pillihuaman.com.base.response.CorouselImage;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespImagenGeneral;
import pillihuaman.com.basebd.common.ProductStock;
import pillihuaman.com.basebd.help.AuditEntity;
import pillihuaman.com.basebd.product.domain.Color;
import pillihuaman.com.basebd.product.domain.Size;
import pillihuaman.com.basebd.product.domain.Stock;
import pillihuaman.com.security.MyJsonWebToken;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
                                                                          @QueryParam("page") int page, @QueryParam("perPage") int perPage) throws JsonProcessingException {

        MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");

        ObjectMapper mapper = new ObjectMapper();
        ProductStock productStock = new ProductStock();
        productStock.set_id(new ObjectId());
        productStock.setIdProduct(new ObjectId());
        productStock.setState(1);
        productStock.setCreationDate(new Date());
        productStock.setExpirationDate(new Date());
        AuditEntity aud = new AuditEntity();
        aud.setDateRegister(new Date());
        aud.setDateUpdate(new Date());
        aud.setCodUser(new ObjectId());
        Stock sto = new Stock();
        Size six = new Size();

        six.setId(new ObjectId());
        Color co= new Color();
        List<Color> colors= new ArrayList<>();
        co.set_id(new ObjectId());
        co.setIdProduct(new ObjectId());
        List<ImagenDetail> lstim= new ArrayList<>();
        ImagenDetail im= new ImagenDetail();
        im.setIndex(1);
        im.setName("nom");
        im.setValue("gdfgdf");
        lstim.add(im);
        co.setListImagen(lstim);
        colors.add(co);
        six.setColor(colors);
        six.setId(new ObjectId());
        Parameters arameter= new Parameters();
        arameter.set_id(new ObjectId());
        arameter.setDescription("Descripcion Parameter");
        arameter.setName("NOmbre Paarameter");
        arameter.setIdCode("fdfd");
        //arameter.setIdParameter(new ObjectId());
        six.setParameter(arameter);
        List<Size> lstsix = new ArrayList<>();
        lstsix.add(six);
        sto.setSize(lstsix);


        productStock.setStock(sto);
        productStock.setAuditEntity(aud);

        String json = mapper.writeValueAsString(productStock);
        RespBase<List<RespImagenGeneral>> response = imagenService.getTopImagen(page, perPage);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "get top imagen", description = "get top imagen", tags = {""}, security = {
            @SecurityRequirement(name = Constants.BEARER_JWT)})
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
            @ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
            @ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))})})
    @PostMapping(path = {Constants.BASE_ENDPOINT + "/save/saveClickCountImagen"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> saveClickCountImagen(@RequestBody CorouselImage corouselImage) {

        MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
        Boolean response = imagenService.saveClickCountImagen(corouselImage);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "SAVE IMAGE by product", description = "SAVE IMAGE by product", tags = {""}, security = {
            @SecurityRequirement(name = Constants.BEARER_JWT)})
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
            @ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
            @ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))})})
    @PostMapping(path = {Constants.BASE_ENDPOINT + "/imagen/saveImagenByProduct"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> saveImagenByProduct(
            @PathVariable String access,
            @Valid @RequestBody ReqImagenByProduct request) throws Exception {

        MyJsonWebToken jwt = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
        Boolean response = imagenService.saveImagenByProduct(jwt, request);
        return ResponseEntity.ok(response);
    }

}