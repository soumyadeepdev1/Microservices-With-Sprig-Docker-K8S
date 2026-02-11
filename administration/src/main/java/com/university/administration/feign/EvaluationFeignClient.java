package com.university.administration.feign;


import com.university.administration.dto.PerformanceDto;
import com.university.administration.fallback.EvaluationFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="evaluation",fallback =  EvaluationFallback.class)
public interface EvaluationFeignClient {
    @GetMapping("api/getSpecificEvaluation")
    public List<PerformanceDto> getPerformanceByEnrollmentId(@RequestHeader String corrId, @RequestParam String EnrollmentId);
}
