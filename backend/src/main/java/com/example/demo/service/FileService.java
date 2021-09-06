package com.example.demo.service;


import com.example.demo.domain.file.File;
import com.example.demo.domain.file.FileRepository;
import com.example.demo.dto.common.FileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    @Value("${storage.network-storage.basePath}")
    private String basePath;

    private final FileRepository fileRepository;

    public File registFile(FileDTO fileDTO) {
        log.debug("FileService registFile invoked...");

        boolean checkFileDirectory = checkFileDirectory(basePath);
        String fileName = fileDTO.getFileName();
        String fileSize = Integer.toString(Math.round(fileDTO.getFileSize() / 1000));
        String targetFileName = basePath + "/" + fileName;


        String partSeparator = ",";
        String base64 = fileDTO.getBase64();
        if (!base64.contains(partSeparator)) throw new IllegalStateException("base64 문자열이 아닙니다.");
        String base64String = base64.split(partSeparator)[1];

        File file = File.builder()
                .fileName(fileName)
                .filePath(targetFileName)
                .fileSize(fileSize)
                .downloadCnt(0)
                .fileType(base64.split(partSeparator)[0])
                .build();

        if(checkFileDirectory) {
            uploadFile(base64String, targetFileName);
        }

        return saveFile(file);
    }

    @Transactional
    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    public void uploadFile(String base64, String path) {
        try {
            byte[] fileData = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
            java.io.File upFile = new java.io.File(path);
            FileOutputStream fos = new FileOutputStream(upFile);
            fos.write(fileData);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("파일 업로드에 실패하였습니다.", e);
        }
    }

    public String encodeFileToBase64(File file) {
        if(file == null) return null;
        try {
            java.io.File findFile = new java.io.File(file.getFilePath());
            InputStream in = new FileInputStream(findFile);
            ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();

            int len=0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf)) != -1){
                byteOutStream.write(buf, 0, len);
            }

            byte[] fileArray=byteOutStream.toByteArray();
            byte[] base64 = Base64.getEncoder().encode(fileArray);
            String base64String = new String(base64);
            return file.getFileType() + "," + base64String;

        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽을 수 없습니다.", e);
        }
    }

    public boolean checkFileDirectory(String path) {
        try {
            java.io.File dir = new java.io.File(path);
            dir.setReadable(true, false);
            dir.setWritable(true, false);
            dir.setExecutable(true, false);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            log.debug(path);
            return true;
        } catch (Exception e) {
            log.debug(e.getMessage());
            return false;
        }
    }

}


