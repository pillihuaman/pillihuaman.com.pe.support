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
            List<CorouselImage> lst = ConvertClass.respListImagenFileToImagenGeneral(imagenProducerDAO.getTopImagenMainPage(page, perage));
            if(lst!=null && lst.size()>0) {
                for (CorouselImage corouselImage:
                        lst  ) {

                    RespImagenGeneral respoG = new RespImagenGeneral();
                    respoG.setCorouselImage(corouselImage);
                    respoG.setTokenCol(UUID.randomUUID().toString());
                    //re.setPayload(respoG);
                }

            }
            //re.setStatus();
        } catch (Exception ex) {
            re.setPayload(null);
        }
        return re;
    }*/
}
