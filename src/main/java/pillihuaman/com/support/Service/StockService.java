package pillihuaman.com.support.Service;



import pillihuaman.com.basebd.common.MyJsonWebToken;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqProduct;
import pillihuaman.com.lib.request.ReqStock;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespProduct;
import pillihuaman.com.lib.response.ResponseStock;

import java.util.List;

public interface StockService {
	RespBase<RespProduct> SaveProduct(MyJsonWebToken token, ReqBase<ReqProduct> request);
	RespBase<List<RespProduct>> listProductbyUser(MyJsonWebToken token, ReqBase<ReqProduct> request);

	RespBase<ResponseStock> saveStock(MyJsonWebToken token, ReqBase<ReqStock> request) ;
}



