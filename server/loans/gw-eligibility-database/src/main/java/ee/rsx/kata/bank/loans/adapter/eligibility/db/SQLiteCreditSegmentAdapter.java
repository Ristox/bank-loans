package ee.rsx.kata.bank.loans.adapter.eligibility.db;

import ee.rsx.kata.bank.loans.domain.segment.CreditSegment;
import ee.rsx.kata.bank.loans.domain.segment.CreditSegmentType;
import ee.rsx.kata.bank.loans.domain.segment.gateway.FindCreditSegment;
import ee.rsx.kata.bank.loans.domain.ssn.SocialSecurityNumber;
import jakarta.inject.Named;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.Map;
import java.util.Optional;

import static ee.rsx.kata.bank.loans.domain.segment.CreditSegmentType.*;
import static java.util.Optional.ofNullable;

@Named
@ConditionalOnProperty(
  value = "bank.loans.eligibility.credit-segment-adapter",
  havingValue = "sqlite"
)
class SQLiteCreditSegmentAdapter implements FindCreditSegment {

  private static final Map<String, CreditSegment> creditSegmentOfPerson = Map.ofEntries(
    segmentFor("49002010965", DEBT, 666),
    segmentFor("49002010976", SEGMENT_1, 100)
  );

  private static Map.Entry<String, CreditSegment> segmentFor(String ssn, CreditSegmentType withType, int withCreditModifier) {
    return Map.entry(ssn, new CreditSegment(new SocialSecurityNumber(ssn), withType, withCreditModifier));
  }

  @Override
  public Optional<CreditSegment> forPerson(SocialSecurityNumber ssn) {
    return ofNullable(creditSegmentOfPerson.get(ssn.value()));
  }
}
