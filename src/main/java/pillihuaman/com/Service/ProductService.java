package pillihuaman.com.Service;
import pillihuaman.com.model.request.ReqBase;
import pillihuaman.com.model.request.ReqProduct;
import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.model.response.RespProduct;
import pillihuaman.com.security.MyJsonWebToken;

public interface ProductService {
	RespBase<RespProduct> SaveProduct(MyJsonWebToken token, ReqBase<ReqProduct> request);


}
