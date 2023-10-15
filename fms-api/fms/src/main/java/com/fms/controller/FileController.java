package com.fms.controller;

import com.fms.domain.File;
import com.fms.dto.FileDto;
import com.fms.mapper.FileMapper;
import com.fms.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final FileMapper fileMapper;
   @GetMapping("/all/{id}")
   public ResponseEntity<List<File>> getAllFiles(@PathVariable("id") UUID userId){
       return new ResponseEntity<>(fileService.getAllFiles(userId), HttpStatus.OK);
   }
    @GetMapping("{id}")
    public ResponseEntity<Page<File>>getAllFilesByTypeAndPage(@PathVariable("id") UUID userId,@RequestParam("type") String type,@RequestParam("page") Integer page ,@RequestParam("size") Integer size ){
        return new ResponseEntity<>(fileService.getAllFilesByPageAndType(userId,type,page,size),HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<File>postFile(@RequestBody FileDto fileDto){
        File file=fileMapper.toEntity(fileDto);
        return new ResponseEntity<>(fileService.createNewFile(file),HttpStatus.OK);
    }
}
