# BANK LOAN ELIGIBILITY - BACKLOG

###  [\[ OK \] BANK-01:  Loan application input form](backlog/__DONE______BANK-01.md)

   As a user, I want to see the input form for loan application -  
    - with fields for a personal code (SSN), loan amount (in Euros) and loan period (in months)  
    - so that I can enter my desired loan information, currently without any restrictions

### [\[ OK \] BANK-02: Requested loan parameters validation](backlog/__DONE______BANK-02.md)

   As a user, I want to enter loan applicant's personal code (SSN), loan amount (in Euros)  
   and period (in months), and immediately understand whether the provided values are valid  
   or not, as per the system's rules:

   - Personal code (SSN) must be a valid Estonian SSN (in valid format)
   - Requested loan amount can be from **2,000EUR up to 10,000EUR**
   - Requested loan period can be from **12 months up to 60 months**


### [\[ OK \] BANK-03: Default loan elibility result](backlog/__DONE______BANK-03.md)

   As a user, I want to be able to submit valid personal code (SSN), loan amount (in Euros)  
   and period (in months), and receive a default, positive credit result with the requested  
   loan amount and period, which is independent of a given loan applicant's SSN.


### [\[ OK \] BANK-04: Personalized loan eligibility result](backlog/__DONE______BANK-04.md)

  As a user, I want to be able to submit valid personal code (SSN), loan amount (in Euros)  
  and period (in months), and receive a personal credit result, which is calculated based on  
  the credit score for given loan applicant.


### [\[ OK \] BANK-05: Personalized loan eligibility result, with maximum allowed loan amount details](backlog/__DONE______BANK-05.md)

   As a user, I want to be able to submit valid personal code (SSN), loan amount (in Euros)  
   and period (in months) - and receive a personal credit result, which is calculated  
   based on the credit score for given loan applicant. 

   This should be calculated based on the requirements described in [BANK-04](backlog/__DONE______BANK-04.md) - however,  
   now I should additionally receive details of maximum available loan amount


### [\[ OK \] BANK-06: Personalized loan eligibility result, with loan period availability details](backlog/__DONE______BANK-06.md) 

   As a user, I want to be able to submit valid personal code (SSN), loan amount (in Euros)  
   and period (in months), but receive a different available loan period than requested,  
   as part of loan eligibility result, depending on loan applicant (SSN).
