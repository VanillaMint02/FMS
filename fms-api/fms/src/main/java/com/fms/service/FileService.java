package com.fms.service;

import com.fms.domain.File;
import com.fms.domain.User;
import com.fms.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        file= fileRepository.save(file);
        userService.addFileToUser(file);
        return file;
    }
    Pageable createPageRequest(int page,int size){
        return PageRequest.of(page,size);
    }
}
