package edu.northeastern.cs5200.web.feign.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableCasesInAllUSStatesData.class)
@JsonDeserialize(as = ImmutableCasesInAllUSStatesData.class)
public interface CasesInAllUSStatesData {

  List<CasesInAllUSStatesTable> data();
}
