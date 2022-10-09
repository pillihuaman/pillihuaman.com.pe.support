package pillihuaman.com.Service.Implement;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import pillihuaman.com.Service.ImagenService;
import pillihuaman.com.imagen.domain.DetailImage;
import pillihuaman.com.imagen.domain.Imagen;
import pillihuaman.com.imagen.domain.dao.ImagenSupportDAO;
import pillihuaman.com.model.request.ReqBase;
import pillihuaman.com.model.request.ReqImagen;
import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.model.response.RespImagen;
import pillihuaman.com.security.MyJsonWebToken;

@Component
public class ImagenServiceImpl implements ImagenService {
	@Autowired
	private ImagenSupportDAO imagenSupportDAO;
	@Autowired(required = false)
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public RespBase<RespImagen> saveImagen(MyJsonWebToken token, ReqBase<ReqImagen> request, MultipartFile[] archivo) {
		RespBase<RespImagen> response = new RespBase<RespImagen>();
		try {
			request.getData();
			Imagen tbImg = new Imagen();
			int idImagen = 0;
			ObjectId _objectId;
			tbImg.setName("cic");
			tbImg.setIdProduct(request.getData().getIdProduct());
			List<Imagen> lst = imagenSupportDAO.getCorrelativeImagen(tbImg);
			if (lst != null && lst.size() > 0) {
				idImagen = lst.get(0).getIdHeadImagen() + 1;
				tbImg.setIdHeadImagen(idImagen);
				Document doc = imagenSupportDAO.saveImagenHeader(tbImg);
				_objectId = (ObjectId)doc.get("_id");
			} else {
				idImagen = 1;
				tbImg.setIdHeadImagen(idImagen);
				Document doc = imagenSupportDAO.saveImagenHeader(tbImg);
				_objectId = (ObjectId)doc.get("_id");
			}

			for (MultipartFile multipartFile : archivo) {
				DetailImage detIma = new DetailImage();
				detIma.setFiles(multipartFile.getBytes());
				detIma.setIdHeadImagen(idImagen);
				detIma.setIdDetail(_objectId);
				detIma.setName(multipartFile.getOriginalFilename());
				// InputStream iss = new ByteArrayInputStream(multipartFile.getBytes());
				imagenSupportDAO.saveImagenFile(detIma);
			}

			// DetailImage det

			response.getStatus().setSuccess(Boolean.TRUE);
			response.setPayload(new RespImagen());
		} catch (Exception e) {

			response.getStatus().setSuccess(Boolean.FALSE);
			log.error(e.getStackTrace());
			// throw e;

			// response.getStatus().getError().getMessages().add(e.getMessage());
		}

		return response;
	}

}
