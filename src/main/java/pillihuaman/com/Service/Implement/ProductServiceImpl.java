package pillihuaman.com.Service.Implement;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.Service.ProductService;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqProduct;
import pillihuaman.com.base.request.ReqStock;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespProduct;
import pillihuaman.com.base.response.ResponseStock;
import pillihuaman.com.basebd.common.ProductStock;
import pillihuaman.com.basebd.help.ConvertClass;
import pillihuaman.com.basebd.product.domain.Product;
import pillihuaman.com.basebd.product.domain.dao.ProductSupportDAO;
import pillihuaman.com.basebd.product.domain.dao.StockSupportDAO;
import pillihuaman.com.security.MyJsonWebToken;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductSupportDAO productSupportDAO;
	@Autowired
	private StockSupportDAO stockSupportDAO;

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public RespBase<RespProduct> SaveProduct(MyJsonWebToken token, ReqBase<ReqProduct> request) {
		RespBase<RespProduct> response = new RespBase<RespProduct>();
		try {
			request.getData();
			Product tblproduct = new Product();
			tblproduct = ConvertClass.ProductDtoToProductTbl(request.getData());
			tblproduct.setIdProduct(new ObjectId());

			List<Product> list = productSupportDAO
					.getCorrelativeProduct(new Product());
			productSupportDAO.SaveProduct(tblproduct);
			/*if (list != null && list.size() > 0) {
				tblproduct.setIdProduct(list.get(0).getIdProduct() + 1);
				productSupportDAO.SaveProduct(tblproduct);
			} else {
				tblproduct.setIdProduct(1);
				productSupportDAO.SaveProduct(tblproduct);
			}*/

			response.getStatus().setSuccess(Boolean.TRUE);
			response.setPayload(new RespProduct());
		} catch (Exception e) {


			response.getStatus().setSuccess(Boolean.FALSE);
			throw e;

			// response.getStatus().getError().getMessages().add(e.getMessage());
		}

		return response;

	}

	@Override
	public RespBase<ResponseStock> saveStock(MyJsonWebToken token, ReqBase<ReqStock> request) {
		RespBase<ResponseStock> response = new RespBase<ResponseStock>();
		try {
			request.getData();
			ProductStock tblproductStock = new ProductStock();
			tblproductStock = ConvertClass.ProductStockRequestDtoToProductStock(request.getData());
			stockSupportDAO.saveStock(tblproductStock);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return null;

	}

	@Override
	public RespBase<List<RespProduct>> listProductbyUser(MyJsonWebToken token, ReqBase<ReqProduct> request) {
		Product pro= new Product();
		pro=ConvertClass.ProductDtoToProductTbl(request.getData());
		RespBase<List<RespProduct>> res = new RespBase<>();
		res.setPayload(ConvertClass.listProductoRespProduct(productSupportDAO.getallProductbyUser(pro)));
		return res;
	}


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
				//pro.setIdProduct(Integer.valueOf(doc.getString("idProduct")));

				break;
			}

		} catch (MongoException e) {
			// handle MongoDB exception
		}
	}

}
