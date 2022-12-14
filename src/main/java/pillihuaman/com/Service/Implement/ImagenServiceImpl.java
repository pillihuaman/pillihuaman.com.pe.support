package pillihuaman.com.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.Service.ImagenService;
import pillihuaman.com.base.request.ImagenDetail;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqImagenByProduct;
import pillihuaman.com.base.response.CorouselImage;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespImagenGeneral;
import pillihuaman.com.basebd.help.AuditEntity;
import pillihuaman.com.basebd.help.ConvertClass;
import pillihuaman.com.basebd.imagen.domain.DetailImage;
import pillihuaman.com.basebd.imagen.domain.Imagen;
import pillihuaman.com.basebd.imagen.domain.dao.ImagenSupportDAO;
import pillihuaman.com.basebd.imagenProducer.domain.ImagenFile;
import pillihuaman.com.basebd.imagenProducer.domain.dao.ImagenProducerDAO;
import pillihuaman.com.security.MyJsonWebToken;

import java.util.*;

;

@Component
public class ImagenServiceImpl implements ImagenService {
    @Autowired
    private ImagenProducerDAO imagenProducerDAO;
    @Autowired
    private ImagenSupportDAO imagenSupportDAO;
    @Autowired(required = false)
    protected final Log log = LogFactory.getLog(getClass());


    @Override
    public RespBase<List<RespImagenGeneral>> getTopImagen(int page, int perage) {
        RespBase<List<RespImagenGeneral>> re = new RespBase<>();
        List<RespImagenGeneral> lstGenera = new ArrayList<>();
        try {
            List<Imagen> lsts = imagenSupportDAO.getTopImagen(page, perage);
            for (Imagen img :
                    lsts) {
                List<CorouselImage> lst = ConvertClass.respListImagenFileToImagenGeneral(imagenProducerDAO.getTopImagen(page, perage, img.getId().toString()));
                if (lst != null && lst.size() > 0) {
                    RespImagenGeneral respoG = new RespImagenGeneral();
                    respoG.setLstCorouseImages(lst);
                    respoG.setTokenCol(UUID.randomUUID().toString());
                    lstGenera.add(respoG);
                }

               /* if(lst!=null && lst.size()>0) {
                    for (CorouselImage corouselImage:
                            lst  ) {

                        RespImagenGeneral respoG = new RespImagenGeneral();
                        respoG.setCorouselImage(corouselImage);
                        respoG.setTokenCol(UUID.randomUUID().toString());
                        lstGenera.add(respoG);
                    }

                }*/
                re.setPayload(lstGenera);

            }
            // imagenProducerDAO.getTopImagen(page,perage,lsts.get(0).getId().toString());


            //re.setStatus();
        } catch (Exception ex) {
            re.setPayload(null);
        }
        return re;
    }

    @Override
    public RespBase<List<RespImagenGeneral>> getImagenHome(int page, int perage) {
        return null;
    }

    @Override
    public Boolean saveClickCountImagen(CorouselImage corouselImage) {
        try {
            ImagenFile img = new ImagenFile();
            img.setId(new ObjectId((corouselImage.getImageSrc())));
            imagenProducerDAO.saveClickCountImagen(img);
            imagenSupportDAO.countImagenClickEventSave(corouselImage.getIdDetail());


            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Boolean saveImagenByProduct(MyJsonWebToken jwt, ReqImagenByProduct imFile) throws Exception {
        boolean booleans;
        try {
            booleans = false;
            Imagen imgg = new Imagen();
            imgg.setIdProduct(imFile.getIdProduct());
            imgg.setName(imFile.getName());
            imgg.setDescription(imFile.getDescription());
            imgg.setClickCount(0);
            imgg.setCountRanking(0);
            imgg.setIdSystem(1);
            AuditEntity au = new AuditEntity();
            au.setCodUsuRegis("ZPH");
            au.setFecRegis(new Date());


            imgg.setAuditEntity(au);
            List<Imagen> lss=  imagenSupportDAO.getCorrelativeImagen(imgg);
            int idImgan=0;
             if(!lss.isEmpty()) {
                 imgg.setIdHeadImagen(lss.get(0).getIdHeadImagen()+1);
                 idImgan=lss.get(0).getIdHeadImagen()+1;
             }else {
                 imgg.setIdHeadImagen(1);
                 idImgan=1;
             }

            Document doc = imagenSupportDAO.saveImagenHeader(imgg);
            String docDetail = doc.get("_id").toString();
            DetailImage det = new DetailImage();
            List<Imagen> i = imagenSupportDAO.getCorrelativeImagen(null);

int count=0;
            for (ImagenDetail c : imFile.getListImagen()
            ) {
                det.setIdImagen(idImgan);
                det.setIdHeadImagen(idImgan);
                det.setClickCount(0);
                det.setCountRanking(0);
                det.setName(c.getName());
                String fileString = "";
                if (!c.getValue().isEmpty()) {
                    String[] spli = c.getValue().split(":");
                    String[] seconSpli = spli[1].split(",");
                    byte[] decodedBytes = Base64.getDecoder().decode(seconSpli[1]);
                    AuditEntity audet = new AuditEntity();
                    audet.setCodUsuRegis("ZPH");
                    audet.setFecRegis(new Date());
                    imgg.setAuditEntity(au);
                    imgg.setAuditEntity(audet);
                    det.setIdDetail(new ObjectId(docDetail));
                    det.setFiles(decodedBytes);
                    det.setIndex(count);
                    imagenSupportDAO.saveImagenFile(det);
                    booleans = true;
                }
                count++;
            }
        } catch (Exception ex) {
            booleans = false;

        }
        return booleans;
    }

}
