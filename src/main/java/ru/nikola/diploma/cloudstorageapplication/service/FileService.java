package ru.nikola.diploma.cloudstorageapplication.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nikola.diploma.cloudstorageapplication.dto.FileDto;
import ru.nikola.diploma.cloudstorageapplication.entity.StorageFile;
import ru.nikola.diploma.cloudstorageapplication.exception.InvalidCredentials;
import ru.nikola.diploma.cloudstorageapplication.exception.ServerFail;
import ru.nikola.diploma.cloudstorageapplication.repository.FileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileService {
    private FileRepository repository;

    public void uploadFile(String fileName, MultipartFile file) {
        if (repository.findStorageFileByFileName(fileName).isPresent()) {
            throw new InvalidCredentials("Filename is already taken");
        }
        if (file.isEmpty()) {
            throw new InvalidCredentials("File must not be empty");
        }
        long fileSize = file.getSize();
        StorageFile storageFile = new StorageFile();
        if (fileName == null) {
            storageFile.setFileName(file.getName());
        }
        storageFile.setFileName(fileName);
        storageFile.setSize(fileSize);
        try {
            storageFile.setDate(file.getBytes());
        } catch (IOException e) {
            throw new ServerFail("Error upload file");
        } finally {
            if (storageFile.getDate() != null) {
                repository.save(storageFile);
            }
        }
    }

    public void deleteFile(String fileName) {
        if (repository.findStorageFileByFileName(fileName).isEmpty()) {
            throw new InvalidCredentials("File not found");
        }
        repository.deleteStorageFileByFileName(fileName);
    }

    public byte[] getFile(String fileName) {
        Optional<StorageFile> storageFiles = repository.findStorageFileByFileName(fileName);
        if (storageFiles.isEmpty()) {
            throw new InvalidCredentials("File not found");
        }
        StorageFile storageFile = storageFiles.get();
        return storageFile.getDate();
    }

    public void renameFile(String currentFileName, Map<String, String> body) {
        Optional<StorageFile> storageFiles = repository.findStorageFileByFileName(currentFileName);
        if (storageFiles.isEmpty()) {
            throw new InvalidCredentials("File not found");
        }
        String newFileName = body.get("filename");
        repository.updateFileNameById(currentFileName, newFileName);
    }

    public List<FileDto> getFiles(int limit) {
        if (limit <= 0) {
            throw new InvalidCredentials("Number of requested items must be positive");
        }
        List<FileDto> files = new ArrayList<>();

        for (StorageFile sf : repository.findAll(PageRequest.ofSize(limit))) {
            files.add(new FileDto(
                    sf.getId(),
                    sf.getFileName(),
                    sf.getDateCreate(),
                    sf.getDateEdit(),
                    sf.getSize()));
        }
        return files;
    }
}
