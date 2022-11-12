package pillihuaman.com.Service;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqProduct;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespProduct;
import pillihuaman.com.security.MyJsonWebToken;

import java.util.List;

public interface ProductService {
	RespBase<RespProduct> SaveProduct(MyJsonWebToken token, ReqBase<ReqProduct> request);
	RespBase<List<RespProduct>> listProductbyUser(MyJsonWebToken token, ReqBase<ReqProduct> request);


}



