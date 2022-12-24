package pillihuaman.com.Service;

import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqImagenByProduct;
import pillihuaman.com.base.response.CorouselImage;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespImagenGeneral;
import pillihuaman.com.security.MyJsonWebToken;

import java.util.List;

public interface ImagenService {
	RespBase<List<RespImagenGeneral>> getTopImagen(int page , int perage);
	RespBase<List<RespImagenGeneral>> getImagenHome(int page, int perage);
	Boolean saveClickCountImagen(CorouselImage imFile);
	Boolean saveImagenByProduct(MyJsonWebToken jwt,ReqImagenByProduct imFile) throws Exception;
}
