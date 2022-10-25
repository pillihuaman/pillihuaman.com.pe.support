package pillihuaman.com.Help;

import pillihuaman.com.basebd.product.domain.Product;
import pillihuaman.com.basebd.user.domain.User;
import pillihuaman.com.model.request.ReqProduct;
import pillihuaman.com.model.request.ReqUser;
import pillihuaman.com.model.response.RespUser;



public class ConvertClass {

	

public static  Product	ProductDtoToProductTbl(ReqProduct request){
	Product resp= new Product();
	resp.setDescription(request.getDescription());
	resp.setExpirationDate(request.getExpirationDate());
	resp.setIdImagen(request.getIdImagen());
	resp.setIdPrice(request.getIdPrice());
	resp.setIdProduct(request.getIdProduct());
	resp.setIdSystem(request.getIdSystem());
	resp.setIdType(request.getIdType());
	resp.setIdUser(request.getIdUser());
	resp.setName(request.getName());
	return resp;
	
}

public static  User	userDtoToUserTbl(ReqUser request){
	User resp= new User();
	
	resp.setAlias(request.getAlias());
	resp.setId_system(request.getId_System());
	resp.setMail(request.getMail());
	resp.setMobil_phone(request.getMobilPhone());
	resp.setPassword(request.getPassword());
	resp.setUser_name(request.getUsername());

	return resp;
	
}
public static  RespUser 	respUserDtoToUser(User request){
	RespUser resp= new RespUser();
	
	resp.setAlias(request.getAlias());
	resp.setId_system(request.getId_system());
	resp.setMail(request.getMail());
	resp.setMobil_Phone(request.getMobil_phone());
	resp.setUsername(request.getUser_name());
	resp.setPassword(request.getPassword());


	return resp;
	
}


}
