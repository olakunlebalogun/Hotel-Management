package fcmb.com.good.services.others;


import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UploadService {

    ApiResponse uploadFile(MultipartFile file) throws IOException;

    StreamingResponseBody downloadPhoto(String fileName, HttpServletResponse response) throws IOException;
}
