package com.university.administration.fallback;

import com.university.administration.dto.PerformanceDto;
import com.university.administration.feign.EvaluationFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EvaluationFallback implements EvaluationFeignClient {
    @Override
    public List<PerformanceDto> getPerformanceByEnrollmentId(String corrId, String EnrollmentId) {
        return List.of();
    }
}
