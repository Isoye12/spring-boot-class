package kr.hs.dgsw.dockerspringboot01.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apt/v1/test")
public class TestController {
    @GetMapping("/hello")
    public ResponseEntity<Object> hello() {
        String result = "API 통신 테스트";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}