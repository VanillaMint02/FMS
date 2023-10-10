package com.fms.service;

import com.fms.domain.File;
import com.fms.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;
    private final UserService userService;

    public List<File> getAllFiles(){
        return fileRepository.findAll();
    }
}
