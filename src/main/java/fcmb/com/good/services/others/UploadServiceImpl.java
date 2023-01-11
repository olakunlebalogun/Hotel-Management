package fcmb.com.good.services.others;

import fcmb.com.good.model.entity.others.Document;
import fcmb.com.good.repo.others.DocumentRepository;
import fcmb.com.good.repo.user.CustomerRepository;
import fcmb.com.good.repo.user.UserRepository;
import fcmb.com.good.utills.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UploadServiceImpl  implements UploadService {

    private UserRepository userRepository;
    private DocumentRepository documentRepository;
    private CustomerRepository customerRepository;

    @Value("${FILE_UPLOAD_LOCATION}")
    private String uploadLocation;

    private final String PATH = "C:\\Users\\USER\\Documents\\uploads";


    @Override
    public Document uploadFile(MultipartFile file) throws IOException {

//        String filePath = PATH +file.getOriginalFilename();
//
//        Document document = documentRepository.save(Document.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .size(file.getSize())
//                .description(file.getName())
//                .filePath(filePath).build());
//
//        file.transferTo(new File(filePath));
//
//            if(document != null){
//                return new File("File Upload Successfully :" + filePath);
//            }
//              return  null;


        String filePath = PATH+file.getOriginalFilename();
        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
        document.setSize(String.valueOf(file.getSize()));
        document.setDescription(file.getName());
        document.setFilePath(filePath);

        file.transferTo(new File(filePath));
        return documentRepository.save(document);

    }


    @Override
    public  byte[] downloadPhoto(String fileName) throws IOException {
        Optional<Document> documentOptional = documentRepository.findByName(fileName);
        String filePath = String.valueOf(documentOptional.get().getFilePath());
        byte[] image = Files.readAllBytes(new File(filePath).toPath());
        return image;
    }


    private String getStoreLocationPath() {return Utils.baseDir(uploadLocation).getPath();
    }

}
