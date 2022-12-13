package fcmb.com.good.services.others;


import fcmb.com.good.model.dto.request.userRequest.UserRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.UserResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public interface UploadService {

    ApiResponse<UserResponse> uploadFile(UUID uuid, UserRequest request, MultipartFile file) throws IOException;

    public StreamingResponseBody previewPhoto(UUID uuid, HttpServletResponse response) throws FileNotFoundException;

}
