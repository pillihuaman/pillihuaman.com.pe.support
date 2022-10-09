package pillihuaman.com.Service;
import org.springframework.web.multipart.MultipartFile;

import pillihuaman.com.model.request.ReqBase;
import pillihuaman.com.model.request.ReqImagen;
import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.model.response.RespImagen;
import pillihuaman.com.security.MyJsonWebToken;

public interface ImagenService {
	RespBase<RespImagen> saveImagen(MyJsonWebToken token, ReqBase<ReqImagen> request ,MultipartFile[] archivo);


}
