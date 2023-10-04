package pillihuaman.com.support.Service;
import pillihuaman.com.basebd.common.MyJsonWebToken;
import jakarta.validation.Valid;
import pillihuaman.com.lib.request.CorouselImage;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqImagenByProduct;
import pillihuaman.com.lib.request.ReqUser;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespImagenGeneral;
import pillihuaman.com.lib.response.RespUser;
import pillihuaman.com.support.Help.Constants;
import pillihuaman.com.support.Service.UserService;

import java.util.List;

public interface ImagenService {
	RespBase<List<RespImagenGeneral>> getTopImagen(int page , int perage);
	RespBase<List<RespImagenGeneral>> getImagenHome(int page, int perage);
	Boolean saveClickCountImagen(CorouselImage imFile);
	Boolean saveImagenByProduct(MyJsonWebToken jwt, ReqImagenByProduct imFile) throws Exception;
}
