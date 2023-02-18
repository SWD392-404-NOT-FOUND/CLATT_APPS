package container.code.function.account.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/hihi")
public class FileUploadController {
    @Value("${firebase.credentials.path}")
    private String firebaseCredentialsPath;

    @Value("${firebase.storage.bucket}")
    private String firebaseStorageBucket;

    private Storage storage;

    @PostConstruct
    public void init() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(
                getClass().getResourceAsStream(firebaseCredentialsPath)
        );

        storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        BlobInfo blobInfo = BlobInfo.newBuilder(firebaseStorageBucket, file.getOriginalFilename()).build();
        Blob blob = storage.create(blobInfo, file.getBytes());
        String publicUrl = blob.getMediaLink();


        return new ResponseEntity<>(publicUrl, HttpStatus.OK);
    }

}
