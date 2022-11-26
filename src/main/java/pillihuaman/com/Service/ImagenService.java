package pillihuaman.com.Service;


import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespImagenGeneral;


import java.util.List;

public interface ImagenService {
	RespBase<List<RespImagenGeneral>> getTopImagen(int page , int perage);
	RespBase<List<RespImagenGeneral>> getImagenHome(int page, int perage);

}
