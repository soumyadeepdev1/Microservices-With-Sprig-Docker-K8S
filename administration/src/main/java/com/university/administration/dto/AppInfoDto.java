package com.university.administration.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RefreshScope
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "info")
public class AppInfoDto {
    private String name;
    private Map<String,String> env;
    private String appVersion;
    private String description;
}
