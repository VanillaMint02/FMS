package com.fms.controller;

import com.fms.domain.File;
import com.fms.mapper.FileMapper;
import com.fms.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.fms.validators.Validators.acceptedFileTypesList;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final FileMapper fileMapper;

    @GetMapping("{id}")
    public ResponseEntity<Page<File>>getAllFilesByTypeAndPage(@PathVariable("id") UUID userId,@RequestParam("type") String type,@RequestParam("page") Integer page ,@RequestParam("size") Integer size ){
        return new ResponseEntity<>(fileService.getAllFilesByPageAndType(userId,type,page,size),HttpStatus.OK);
    }
    @PostMapping(value ="/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<File>postFile(@PathVariable("id") UUID userId ,@RequestParam("file") MultipartFile multiPartFile) throws IOException {
        return new ResponseEntity<>(fileService.createNewFile(userId,multiPartFile),HttpStatus.OK);
    }
    @GetMapping("/types")
    public ResponseEntity<List<String>>getAllAcceptedFileTypes(){
        return new ResponseEntity<>(acceptedFileTypesList,HttpStatus.OK);
    }
}
