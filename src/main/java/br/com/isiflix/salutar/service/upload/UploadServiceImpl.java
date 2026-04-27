package br.com.isiflix.salutar.service.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.cert.Extension;
import java.util.UUID;

@Component
public class UploadServiceImpl implements IUploadService{

    @Override
    public String uploadFile(MultipartFile arquivo) {
        try{
            System.out.println("DEBUG - Realizando o upload do arquivo: " + arquivo.getOriginalFilename());
            Path pastaDestino = Paths.get(System.getProperty("user.home"), "Documents", "salutar", "images");
            Files.createDirectories(pastaDestino);
            String extension = arquivo.getOriginalFilename().substring(arquivo.getOriginalFilename().lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extension;
            Path path = pastaDestino.resolve(newFileName);
            Files.copy(arquivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
