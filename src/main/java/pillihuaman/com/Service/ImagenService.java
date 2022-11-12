package pillihuaman.com.Service;
import org.springframework.web.multipart.MultipartFile;


import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqImagen;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespImagen;
import pillihuaman.com.security.MyJsonWebToken;

public interface ImagenService {
	RespBase<RespImagen> saveImagen(MyJsonWebToken token, ReqBase<ReqImagen> request , MultipartFile[] archivo);


}
