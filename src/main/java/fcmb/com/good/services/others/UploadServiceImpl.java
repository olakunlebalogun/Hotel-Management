package fcmb.com.good.services.others;

import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.userRequest.UserRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.UserResponse;
import fcmb.com.good.model.entity.others.Document;
import fcmb.com.good.model.entity.user.User;
import fcmb.com.good.repo.others.DocumentRepository;
import fcmb.com.good.repo.user.CustomerRepository;
import fcmb.com.good.repo.user.UserRepository;
import fcmb.com.good.utills.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UploadServiceImpl  implements UploadService {

    private UserRepository userRepository;
    private DocumentRepository documentRepository;
    private CustomerRepository customerRepository;

    @Value("${FILE_UPLOAD_LOCATION}")
    private String uploadLocation;


    @Override
    public ApiResponse<UserResponse> uploadFile(UUID uuid, UserRequest request, MultipartFile file) throws IOException {

        DeferredResult<ResponseEntity> result = new DeferredResult<>();
        Optional<User> user = userRepository.findByUuid(uuid);
        if (user.isPresent()) {
            String originalName = file.getOriginalFilename().replaceAll("[\\\\/><\\|\\s\"'{}()\\[\\]]+", "_");
            String files = getStoreLocationPath() + File.separator + Utils.getNewFileName(getStoreLocationPath(), originalName);
            File testFile = new File(files);
            FileUtils.writeByteArrayToFile(testFile, file.getBytes());
            User us = user.get();
            Document document = new Document();
            document.setDateCreated(us.getDateCreated());
            document.setLastModified(us.getLastModified());
            document.setPostedBy(us.getPostedBy());
            document.setRecord_id(document.getRecord_id());
            document.setDescription(document.getDescription());
            document.setFile_name(testFile.getName());
            document.setFile_size(document.getFile_size().replaceAll(" ", ""));
            document.setFile_type(FilenameUtils.getExtension(testFile.getName()));
            document = documentRepository.save(document);
            return new ApiResponse<UserResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(document,UserResponse.class));
        }
        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Override
    public StreamingResponseBody previewPhoto(UUID uuid, HttpServletResponse response) throws FileNotFoundException {
        return null;
    }


    private String getStoreLocationPath() {return Utils.baseDir(uploadLocation).getPath();
    }
}
