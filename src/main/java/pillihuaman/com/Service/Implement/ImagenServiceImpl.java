package pillihuaman.com.Service.Implement;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import pillihuaman.com.Service.ImagenService;
import pillihuaman.com.base.request.ImagenDetail;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqImagen;
import pillihuaman.com.base.request.ReqImagenByProduct;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespImagen;
import pillihuaman.com.basebd.help.AuditEntity;
import pillihuaman.com.basebd.imagen.domain.DetailImage;
import pillihuaman.com.basebd.imagen.domain.Imagen;
import pillihuaman.com.basebd.imagen.domain.dao.ImagenSupportDAO;

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
                _objectId = (ObjectId) doc.get("_id");
            } else {
                idImagen = 1;
                tbImg.setIdHeadImagen(idImagen);
                Document doc = imagenSupportDAO.saveImagenHeader(tbImg);
                _objectId = (ObjectId) doc.get("_id");
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

    @Override
    public RespBase<RespImagen> saveImagenByProduct(MyJsonWebToken token, ReqImagenByProduct reqImagenByProduct) {
        RespBase<RespImagen> response = new RespBase<RespImagen>();
        try {
            Imagen tbImg = new Imagen();
            int idImagen = 0;
            ObjectId _objectId;
            tbImg.setDescription(reqImagenByProduct.getDescription());
            tbImg.setName(reqImagenByProduct.getName());
            tbImg.setIdProduct(reqImagenByProduct.getIdProduct());
            tbImg.setUniqueKeyHash(reqImagenByProduct.getUniqueKeyHash());
            AuditEntity adu= new AuditEntity();
            adu.setFecRegis(new Date());
            adu.setCodUsuRegis("ZPH");
            tbImg.setAuditEntity(adu);
            List<Imagen> lst = imagenSupportDAO.getCorrelativeImagen(tbImg);
            if (lst != null && lst.size() > 0) {
                idImagen = lst.get(0).getIdHeadImagen() + 1;
                tbImg.setIdHeadImagen(idImagen);
                Document doc = imagenSupportDAO.saveImagenHeader(tbImg);
                _objectId = (ObjectId) doc.get("_id");
            } else {
                idImagen = 1;
                tbImg.setIdHeadImagen(idImagen);
                Document doc = imagenSupportDAO.saveImagenHeader(tbImg);
                _objectId = (ObjectId) doc.get("_id");
            }
            if (reqImagenByProduct != null && !Objects.isNull(reqImagenByProduct.getListImagen()) && !reqImagenByProduct.getListImagen().isEmpty())
                for (ImagenDetail multipartFile : reqImagenByProduct.getListImagen()) {
                    DetailImage detIma = new DetailImage();
                    String contenImga = null;
                    String[] splitBase64 = multipartFile.getValue().split(";");
                    if (splitBase64 != null && splitBase64.length > 0) {
                        String[] splitImg = splitBase64[1].split(",");
                        if (splitImg != null && splitImg.length > 0) {
                            contenImga = splitImg[1];
                        }
                    }
                    byte[] decodedImagen = Base64.getDecoder().decode(contenImga.getBytes("UTF-8"));
                    detIma.setFiles(decodedImagen);
                    detIma.setIdHeadImagen(idImagen);
                    detIma.setIdDetail(_objectId);
                    detIma.setUniqueKeyHash(reqImagenByProduct.getUniqueKeyHash());
                    detIma.setName(multipartFile.getName());
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
        return  response;
    }
}
