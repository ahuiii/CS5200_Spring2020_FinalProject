package edu.northeastern.cs5200.web.feign.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableCasesInAllUSStatesDto.class)
@JsonDeserialize(as = ImmutableCasesInAllUSStatesDto.class)
public interface CasesInAllUSStatesDto {

  String USAState();

  String TotalCases();

  String NewCases();

  String TotalDeaths();

  String NewDeaths();

  String ActiveCases();

  String TotalTests();

  String Tot_Cases_1M_Pop();

  String Deaths_1M_Pop();

  String Tests_1M_Pop();
}
