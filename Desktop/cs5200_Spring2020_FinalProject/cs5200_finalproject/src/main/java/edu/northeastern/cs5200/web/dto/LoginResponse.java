package edu.northeastern.cs5200.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = edu.northeastern.cs5200.web.dto.ImmutableLoginResponse.class)
@JsonDeserialize(as = edu.northeastern.cs5200.web.dto.ImmutableLoginResponse.class)
public interface LoginResponse {

  String token();

  String userId();

  String role();

}
