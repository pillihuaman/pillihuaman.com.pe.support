package pillihuaman.com.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.Service.ImagenService;
;
import pillihuaman.com.base.response.CorouselImage;
import pillihuaman.com.base.response.RespBase;

import pillihuaman.com.base.response.RespBase.Status;
import pillihuaman.com.base.response.RespImagenGeneral;
import pillihuaman.com.basebd.help.ConvertClass;
import pillihuaman.com.basebd.imagen.domain.Imagen;
import pillihuaman.com.basebd.imagen.domain.dao.ImagenSupportDAO;
import pillihuaman.com.basebd.imagenProducer.domain.ImagenFile;
import pillihuaman.com.basebd.imagenProducer.domain.dao.ImagenProducerDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        List<RespImagenGeneral> lstGenera= new ArrayList<>();
        try {
          List<Imagen> lsts=  imagenSupportDAO.getTopImagen(page, perage);
            for (Imagen img :
                    lsts ) {
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
    /*@Override
    public RespBase<List<RespImagenGeneral>> getImagenHome(int page, int perage) {
        RespBase<List<RespImagenGeneral>> re = new RespBase<>();
        try {
<<<<<<< HEAD
            Imagen tbImg = new Imagen();
            int idImagen = 0;
            ObjectId _objectId;
            tbImg.setDescription(reqImagenByProduct.getDescription());
            tbImg.setName(reqImagenByProduct.getName());
            tbImg.setIdProduct(reqImagenByProduct.getIdProduct());
            tbImg.setUniqueKeyHash(reqImagenByProduct.getUniqueKeyHash());
            tbImg.setCountRanking(0);
            tbImg.setClickCount(0);


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
                    detIma.setCountRanking(0);
                    detIma.setClickCount(0);
                    detIma.setUniqueKeyHash(reqImagenByProduct.getUniqueKeyHash());
                    detIma.setName(multipartFile.getName());
                    detIma.setIndex(multipartFile.getIndex());
                    imagenSupportDAO.saveImagenFile(detIma);
=======
            List<CorouselImage> lst = ConvertClass.respListImagenFileToImagenGeneral(imagenProducerDAO.getTopImagenMainPage(page, perage));
            if(lst!=null && lst.size()>0) {
                for (CorouselImage corouselImage:
                        lst  ) {

                    RespImagenGeneral respoG = new RespImagenGeneral();
                    respoG.setCorouselImage(corouselImage);
                    respoG.setTokenCol(UUID.randomUUID().toString());
                    //re.setPayload(respoG);
>>>>>>> 8059a459b9d358fb8b0353862aaa97268c3f9478
                }

            }
            //re.setStatus();
        } catch (Exception ex) {
            re.setPayload(null);
        }
        return re;
    }*/
}
