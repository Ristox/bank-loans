package ee.rsx.kata.bank.app.rest.loans.validation;

import ee.rsx.kata.bank.loans.validation.LoadValidationLimits;
import ee.rsx.kata.bank.loans.validation.ValidationLimitsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationLimitsEndpoint {

  private final LoadValidationLimits loadValidationLimits;

  public ValidationLimitsEndpoint(LoadValidationLimits LoadValidationLimits) {
    this.loadValidationLimits = LoadValidationLimits;
  }

  @GetMapping(value = "/loans/validation/limits")
  public ValidationLimitsDTO loadValidationLimits() {
    return loadValidationLimits.invoke();
  }
}
