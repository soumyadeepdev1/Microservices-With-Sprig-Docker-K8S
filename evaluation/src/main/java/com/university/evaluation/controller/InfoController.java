package com.university.evaluation.controller;

import com.university.evaluation.dto.AppInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/metadata")
public class InfoController {
    @Autowired
    private AppInfoDto info;

    @GetMapping("/get-app-info")
    public ResponseEntity<AppInfoDto> getAppInfo(){
        return new ResponseEntity<>(new AppInfoDto(
                info.getName(),
                info.getEnv(),
                info.getAppVersion(),
                info.getDescription()
        ), HttpStatus.OK);
    }
}
