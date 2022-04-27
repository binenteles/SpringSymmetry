package com.cf.symmetry.dto;

import com.cf.symmetry.factory.MethodEvaluation;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EvalRequest {

  @NotNull(message = "Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX")
  private String str;

  @NotNull
  private MethodEvaluation method = MethodEvaluation.FOR;


}
