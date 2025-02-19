package com.petrtitov.comfortSoft.web;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class ExcelController {

    @GetMapping("/findNthMax")
    public void findNthMax(
            @RequestParam String filePath,
            @RequestParam int n) {

    }
}