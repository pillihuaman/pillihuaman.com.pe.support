package pillihuaman.com.Service.Implement;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pillihuaman.com.Help.ConvertClass;
import pillihuaman.com.Service.ProductService;
import pillihuaman.com.model.request.ReqBase;
import pillihuaman.com.model.request.ReqProduct;
import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.model.response.RespProduct;
import pillihuaman.com.product.domain.Product;
import pillihuaman.com.product.domain.dao.ProductSupportDAO;
import pillihuaman.com.security.MyJsonWebToken;

@Component
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductSupportDAO productSupportDAO;

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public RespBase<RespProduct> SaveProduct(MyJsonWebToken token, ReqBase<ReqProduct> request) {
		RespBase<RespProduct> response = new RespBase<RespProduct>();
		try {
			request.getData();
			Product tblproduct = new Product();
			tblproduct = ConvertClass.ProductDtoToProductTbl(request.getData());
			
		
			List<Product> list = productSupportDAO
					.getCorrelativeProduct(new Product());
			if (list != null && list.size() > 0) {
				tblproduct.setIdProduct(list.get(0).getIdProduct() + 1);
				productSupportDAO.SaveProduct(tblproduct);
			} else {
				tblproduct.setIdProduct(1);
				productSupportDAO.SaveProduct(tblproduct);
			}

			response.getStatus().setSuccess(Boolean.TRUE);
			response.setPayload(new RespProduct());
		} catch (Exception e) {

			
			response.getStatus().setSuccess(Boolean.FALSE);
			throw e;

			// response.getStatus().getError().getMessages().add(e.getMessage());
		}

		return response;

	}

	/*
	 * private int idProduct;
	 * 
	 * private String description;
	 * 
	 * 
	 * private Date expirationDate;
	 * 
	 * 
	 * private BigDecimal idImagen;
	 * 
	 * private BigDecimal idPrice;
	 * 
	 * private BigDecimal idSystem;
	 * 
	 * private BigDecimal idType;
	 * 
	 * 
	 * private BigDecimal idUser;
	 */

	public void mongodb(Product tblproduct) {

		try {
			MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
			MongoClient mongoClient = new MongoClient(connectionString);

			MongoDatabase database = mongoClient.getDatabase("gamachicas");
			MongoCollection<Document> collection = database.getCollection("product");

			// Created with NoSQLBooster, the essential IDE for MongoDB -
			// https://nosqlbooster.com
			Document query = new Document();
			query.append("idProduct", tblproduct.getIdProduct());
			query.append("description", tblproduct.getDescription());
			query.append("idImagen", tblproduct.getIdImagen());
			query.append("name", tblproduct.getName());

			Document sort = new Document().append("_id", -1);
			collection.insertOne(query);

			FindIterable<Document> lstProduct = collection.find(query).sort(sort).limit(100);
			for (Document doc : lstProduct) {
				Product pro = new Product();
				pro.setIdProduct(Integer.valueOf(doc.getString("idProduct")));

				break;
			}

		} catch (MongoException e) {
			// handle MongoDB exception
		}
	}

}
