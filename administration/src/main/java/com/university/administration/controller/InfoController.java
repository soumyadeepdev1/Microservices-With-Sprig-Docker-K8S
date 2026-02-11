package com.university.administration.controller;

import com.university.administration.dto.AppInfoDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/metadata")
@RefreshScope
public class InfoController {

    @Autowired
    private AppInfoDto info;

    @Retry(name = "getApplicationInfo",fallbackMethod = "getAppInfoFallback")
    @GetMapping("/get-app-info")
    public ResponseEntity<AppInfoDto> getAppInfo(){
        System.out.println("Fetching app info");
//        throw new RuntimeException("Failed to get app info");
        return new ResponseEntity<>(new AppInfoDto(
                info.getName(),
                info.getEnv(),
                info.getAppVersion(),
                info.getDescription()
        ), HttpStatus.OK);
    }
    public ResponseEntity<AppInfoDto> getAppInfoFallback(Throwable throwable){
        return new ResponseEntity<>(new AppInfoDto(
                "Dummy",
                Map.of("name","Local"),
                "0.0.0",
                "N/A"
        ), HttpStatus.OK);
    }
}
