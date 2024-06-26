package ee.rsx.kata.bank.app.rest.loans.eligibility;

import ee.rsx.kata.bank.loans.eligibility.CalculateLoanEligibility;
import ee.rsx.kata.bank.loans.eligibility.LoanEligibilityRequestDTO;
import ee.rsx.kata.bank.loans.eligibility.LoanEligibilityResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/loans/eligibility")
@RequiredArgsConstructor
public class LoanEligibilityEndpoint {

  private final CalculateLoanEligibility calculateLoanEligibility;

  @PostMapping
  public ResponseEntity<LoanEligibilityResultDTO> calculateLoanEligibility(
    @RequestBody LoanEligibilityRequestDTO request
  ) {
    var eligibility = calculateLoanEligibility.on(request);

    var httpStatus = switch (eligibility.result()) {
      case APPROVED -> OK;
      case INVALID -> BAD_REQUEST;
      case DENIED -> NOT_ACCEPTABLE;
    };

    return status(httpStatus)
      .contentType(APPLICATION_JSON)
      .body(eligibility);
  }
}


