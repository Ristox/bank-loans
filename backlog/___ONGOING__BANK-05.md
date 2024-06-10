### BANK-05: Personalized loan eligibility result, with maximum allowed loan amount details

As a user, I want to be able to submit valid personal code (SSN), loan amount (in Euros)  
and period (in months) - and receive a personal credit result, which is calculated  
based on the credit score for given loan applicant.  
This should be calculated based on the requirements described in [BANK-04](__DONE______BANK-04.md) - however,  
I should additionally receive -


* A positive result, if `creditScore > 1` - along with **a maximum available loan amount**  
  that would not yet result in a credit score of 1 (meaning loan denial).  
  (That maximum available loan amount should therefore be larger than the requested amount)


* A negative result, if `creditScore <= 1` - along with maximum allowed loan amount  
  that would result in a creditScore > 1  
  (That maximum available loan amount should therefore be less than the requested amount)


#### Subtasks

* **\[ _ ] BANK-05-A: distinguish DENIED loan requests in UI**
    * Instead of "green response", show a color more suitable for forbidden / denied state  
      eg yellow / orange
  
* **\[ _ ] BANK-05-B: when loan request is DENIED, calculate and show highest loan amount which would be approved **
    * in loan eligibility calculations, when credit score is 1 or less, calculate the highest loan amount
      which would instead result in credit score greater than 1, eg loan approval.  
      Therefore, this is the "highest available loan amount", which in current case shall be  
      less than requested amount
   * in loan eligibility calculations, when credit score is more than 1, calculate the lowest loan amount
      which would instead result in credit score greater than 1, eg loan approval.  
      Therefore, this is the "highest available loan amount", which in current case shall be  
      more than requested amount
    * add the calculated amount as "highest available loan amount" to result and show in UI