package edu.northeastern.cs5200.web.feign;

import edu.northeastern.cs5200.web.feign.dto.CasesInAllUSStatesData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://covid19-server.chrismichael.now.sh/api/v1/CasesInAllUSStates",
    name = "covid-19",
    configuration = COVID19ClientConfig.class)
public interface COVID19Client {

  @GetMapping("/")
  CasesInAllUSStatesData getAllCasesInAmerica();

}
