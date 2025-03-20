package com.clab.backend.svn.controller;

import com.clab.backend.common.util.FileHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svn")
@RequiredArgsConstructor
public class SvnUploadController {

    //private final FileHandler svnFileHandler;
/*
    @PostMapping("/exp/excel/upload")
    public ResponseEntity<?> expExcelUpload(){

        return ResponseEntity.ok().build();
    }*/
}
