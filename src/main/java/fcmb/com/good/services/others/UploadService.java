package fcmb.com.good.services.others;


import fcmb.com.good.model.entity.others.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    Document uploadFile(MultipartFile file) throws IOException;

    byte[] downloadPhoto(String fileName) throws IOException;

}
