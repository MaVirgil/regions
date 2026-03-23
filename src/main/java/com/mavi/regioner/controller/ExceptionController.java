package com.mavi.regioner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("")
    public String hello() {
        return "hello";
    }

    @GetMapping("/file/{filename}")
    public String getFile(@PathVariable String filename) {

        String result = "";

        try (FileInputStream fileInputStream = new FileInputStream("/Users/magnusvirgil/Documents/files/" + filename);) {
            byte[] bytes = fileInputStream.readAllBytes();
            result = new String(bytes);
        } catch (IOException e) {
            result = "Could not read file!";
        }

        return result;
    }
    @GetMapping("/div/{num1}/{num2}")
    public int divideNums(@PathVariable int num1, @PathVariable int num2) {

        int result;

        result = num1 / num2;

        return result;
    }
}
