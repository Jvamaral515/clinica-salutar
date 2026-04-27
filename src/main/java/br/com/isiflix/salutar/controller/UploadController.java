package br.com.isiflix.salutar.controller;

import br.com.isiflix.salutar.dto.PathToFile;
import br.com.isiflix.salutar.service.upload.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class UploadController {

    @Autowired
    private IUploadService service;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(name = "arquivo") MultipartFile arquivo){
        String fileName = service.uploadFile(arquivo);
        if (fileName != null)
            return ResponseEntity.status(201).body(new PathToFile(fileName));

        return ResponseEntity.badRequest().build();
    }
}
