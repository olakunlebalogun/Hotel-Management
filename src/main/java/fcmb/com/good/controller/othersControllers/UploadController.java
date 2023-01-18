package fcmb.com.good.controller.othersControllers;

import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.services.others.UploadService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class UploadController {

	private final UploadService uploadService;

	@PostMapping("/upload/data")
	@ApiOperation(value = "Upload profile picture of the user", response = String.class,
			produces = "application/json", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse uploadFile(@RequestPart(value = "file", required = true) MultipartFile file) throws IOException {
		return uploadService.uploadFile(file);
	}

	@GetMapping("/download/data")
	@ApiOperation(value = "Download picture of User", response = String.class,
			produces = "application/json", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public StreamingResponseBody downLoadFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		return uploadService.downloadPhoto(fileName, response);
	}




}