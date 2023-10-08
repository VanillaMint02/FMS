package com.fms.controller;

import com.fms.domain.File;
import com.fms.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("api/files")
    public ResponseEntity<List<File>> getAllFiles(){
        return new ResponseEntity<>(fileService.getAllFiles(), HttpStatus.OK);
    }
}
