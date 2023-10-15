package com.fms.service;

import com.fms.domain.File;
import com.fms.domain.User;
import com.fms.error.custom.errors.InvalidDataException;
import com.fms.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.fms.error.message.ErrorMessages.FILES_CAN_ONLY_HAVE_THE_FOLLOWING_EXTENSIONS;
import static com.fms.validators.Validators.acceptedFileTypesList;
import static com.fms.validators.Validators.validFileType;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;
    private final UserService userService;

    public List<File> getAllFiles(UUID userId){

        User foundUser=userService.findUserById(userId);
        return fileRepository.findByUserId(foundUser.getId());
    }
    public Page<File> getAllFilesByPageAndType(UUID userId ,String type,Integer page,Integer size){

        User foundUser=userService.findUserById(userId);
        Pageable pageable=createPageRequest(page,size);
        return fileRepository.findByUserIdAndType(foundUser.getId(),type,pageable);
    }
    public File createNewFile(File file){

        file.setUser(userService.findUserById(file.getUser().getId()));
        if (!validFileType(file.getType())) {
            throw new InvalidDataException(FILES_CAN_ONLY_HAVE_THE_FOLLOWING_EXTENSIONS + acceptedFileTypesList);
        }
        file= fileRepository.save(file);
        userService.addFileToUser(file);
        return file;
    }
    Pageable createPageRequest(int page,int size){

        return PageRequest.of(page,size);
    }
    void createNewDirectoryForUser(String userId){
        String directoryPath = getUserDirectoryPath(userId);
        java.io.File userDirectory=new java.io.File(directoryPath);
        if (!userDirectory.exists()){
            userDirectory.mkdirs();
        }
    }

    private static String getUserDirectoryPath(String userId) {
        return System.getProperty("user.dir") + java.io.File.separator + "assets" + java.io.File.separator + userId;
    }
}
