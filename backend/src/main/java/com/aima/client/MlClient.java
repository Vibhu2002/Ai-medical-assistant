package com.aima.client;

import com.aima.dto.MlPredictRequest;
import com.aima.dto.MlPredictResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mlClient", url = "${app.ml.base-url}")
public interface MlClient {
    @PostMapping("/predict")
    MlPredictResponse predict(MlPredictRequest request);
}
