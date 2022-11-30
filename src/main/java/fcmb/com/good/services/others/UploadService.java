package fcmb.com.good.services.others;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UploadService {

    ResponseEntity<?> uploadFile(MultipartFile file) throws IOException;


}
