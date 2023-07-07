package pillihuaman.com.Service;

import pillihuaman.com.base.commons.MyJsonWebToken;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqProduct;
import pillihuaman.com.base.request.ReqStock;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespProduct;
import pillihuaman.com.base.response.ResponseStock;

import java.util.List;

public interface StockService {
	RespBase<RespProduct> SaveProduct(MyJsonWebToken token, ReqBase<ReqProduct> request);
	RespBase<List<RespProduct>> listProductbyUser(MyJsonWebToken token, ReqBase<ReqProduct> request);

	RespBase<ResponseStock> saveStock(MyJsonWebToken token, ReqBase<ReqStock> request) ;
}



