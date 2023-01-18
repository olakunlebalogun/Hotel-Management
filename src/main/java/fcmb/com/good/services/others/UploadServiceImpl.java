package fcmb.com.good.services.others;

import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.entity.others.Document;
import fcmb.com.good.repo.others.DocumentRepository;
import fcmb.com.good.repo.user.CustomerRepository;
import fcmb.com.good.repo.user.UserRepository;
import fcmb.com.good.utills.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UploadServiceImpl  implements UploadService {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final CustomerRepository customerRepository;

    @Value("${FILE_UPLOAD_LOCATION}")
    private String uploadLocation;


    @Override
    public ApiResponse uploadFile(MultipartFile file) throws IOException {

        String originalName = file.getOriginalFilename().replaceAll("[\\\\/><\\|\\s\"'{}()\\[\\]]+", "_");
        String filePath = getStoreLocationPath() + File.separator + Utils.getNewFileName(getStoreLocationPath(), originalName);

        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
        document.setSize(String.valueOf(file.getSize()));
        document.setDescription(file.getName());
        document.setFilePath(filePath);

        file.transferTo(new File(filePath));
        documentRepository.save(document);

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Photo Uploaded successfully");

    }


    @Override
    public StreamingResponseBody downloadPhoto(String fileName, HttpServletResponse response) throws IOException {

        //Optional<Document> documentOptional = documentRepository.findByName(fileName);

////        String filePath = String.valueOf(documentOptional.get().getFilePath());
////        byte[] image = Files.readAllBytes(new File(filePath).toPath());
////        return image;
//
//        String filePath = documentOptional.get().getFilePath();
//        //return Files.readAllBytes(new File(filePath).toPath());
//        byte[] image = Files.readAllBytes(new File(filePath).toPath());
//        return image;



        File file = Utils.baseDir(getStoreLocationPath() + "/photograph/" + fileName).getFile();
        String path = "";
        if (file.exists()) {
            path = file.getAbsolutePath();
        } else {
            file = Utils.baseDir(getStoreLocationPath() + "/photograph/default.jpg").getFile();
            path = file.getAbsolutePath();
        }

        fileName = file.getName();
        InputStream in = new FileInputStream(file);
        response.setHeader("Cache-Control",
                "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        //response.setContentType(UploadService.getContext().getMimeType(path));
        //  String fileExtension=
        response.setHeader("Content-Disposition",
                "inline; filename=" + fileName);

        return outputStream -> {
            int nRead;
            byte[] data = new byte[in.available()];
            while ((nRead = in.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            outputStream.flush();
            outputStream.close();
        };

    }


    private String getStoreLocationPath() {return Utils.baseDir(uploadLocation).getPath();
    }

}
