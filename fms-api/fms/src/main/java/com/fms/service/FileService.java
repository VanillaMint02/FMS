package com.fms.service;

import com.fms.domain.File;
import com.fms.domain.User;
import com.fms.error.custom.errors.EmptyFileException;
import com.fms.error.custom.errors.InvalidDataException;
import com.fms.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.fms.error.message.ErrorMessages.FILES_CANNOT_BE_EMPTY;
import static com.fms.error.message.ErrorMessages.FILES_CAN_ONLY_HAVE_THE_FOLLOWING_EXTENSIONS;
import static com.fms.validators.Validators.acceptedFileTypesList;
import static com.fms.validators.Validators.validFileType;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;
    private final UserService userService;

    public List<File> getAllFiles(UUID userId) {

        User foundUser = userService.findUserById(userId);
        return fileRepository.findByUserId(foundUser.getId());
    }

    public Page<File> getAllFilesByPageAndType(UUID userId, String type, Integer page, Integer size) {

        User foundUser = userService.findUserById(userId);
        Pageable pageable = createPageRequest(page, size);
        return fileRepository.findByUserIdAndType(foundUser.getId(), type, pageable);
    }

    public File createNewFile(UUID userId, MultipartFile multipartFile) throws IOException {
        User foundUser = userService.findUserById(userId);

        String fileExtension = getFileExtension(multipartFile);
        if (!validFileType(fileExtension)) {
            throw new InvalidDataException(FILES_CAN_ONLY_HAVE_THE_FOLLOWING_EXTENSIONS
                    + acceptedFileTypesList);
        }
        File file = File.builder()
                .type(fileExtension)
                .name(Objects.requireNonNull(multipartFile.getOriginalFilename()))
                .path(getFileDirectoryPath(userId.toString(), multipartFile.getOriginalFilename()))
                .user(foundUser)
                .build();
        file = fileRepository.save(file);
        userService.addFileToUser(file);
        uploadFile(multipartFile, file.getUser().getId().toString());
        return file;
    }

    Pageable createPageRequest(int page, int size) {
        return PageRequest.of(page, size);
    }

    void createNewDirectoryForUser(String userId) {
        String directoryPath = getUserDirectoryPath(userId);
        java.io.File userDirectory = new java.io.File(directoryPath);
        if (!userDirectory.exists()) {
            userDirectory.mkdirs();
        }
    }

    public String getUserDirectoryPath(String userId) {
        return System.getProperty("user.dir") + java.io.File.separator + "assets" + java.io.File.separator + userId;
    }

    private String getFileDirectoryPath(String userId, String name) {
        return this.getUserDirectoryPath(userId) + java.io.File.separator + name;
    }

    private void uploadFile(MultipartFile file, String userId) throws IOException {
        if (file.isEmpty()) {
            throw new EmptyFileException(FILES_CANNOT_BE_EMPTY);
        }
        Path destination = Paths.get(getFileDirectoryPath(userId, file.getOriginalFilename())).toAbsolutePath();
        file.transferTo(destination);
    }

    private String getFileExtension(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename());
    }
}
