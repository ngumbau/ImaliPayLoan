# ImaliPay Rest API - Technical Assessment

Imalipay Rest API is a backend service that facilitates an organization to offer and keep track of one or more loan products issued to the organizations members.

The organization manages members, loan products, loans and transactions through this api.


## Endpoints
The rest endpoints are offered below and documentation can be found listed below:

### 1. Create user
Create a new user
> http://localhost:8080/users
<pre>
Request object
{
        "firstName" : "Tom",
        "lastName" : "Mukuha",
        "email": "tmkuha@gmail.com",
        "phoneNumber" : "1234567",
        "nationalId" : "+2541234568"
}
</pre>

### 2. Show user
Show a user's details
> http://localhost:8080/users/{id}
### 3. Create a loan product
Create a loan product
> http://localhost:8080/loan_products
<pre>
Request object
{
    "name": "Tetheka Fund",
    "minimum": "2500",
    "maximum": "80000",
    "interest_rate": "12",
    "duration": "3"
}
</pre>
### 4. List Loan products
List all available loan products
> http://localhost:8080/loan_products
### 5. Show Loan product
Show an available loan product
> http://localhost:8080/loan_products/{id}
### 6. Create user loan
Create a new loan account
> https://localhost:8080/users/{user_id}/loans
<pre>
Request object
{
    "userId" : "1",
    "productID" : "1",
    "amount" : "20000"
}
</pre>

### 7. Show loan
Shows a user's loan
> https://localhost:8080/loan_transactions
### 8. Create a loan transaction
Create a loan transaction and credit/debit the loan account.
> https://localhost:8080/loan_transactions
<pre>
Request object
{
    "loanId": "1",
    "type": "Payment",
    "amount": "12000",
    "paymentMethod": "BANK"
}
</pre>
### 9. Search for loan transaction
Search for loan transaction
> https://localhost:8080/loan_transactions/{id}

## Backlog
Due to the length of the assessment, I would have loved to have more time to finish the following tickets to improve on
APIs functionality. I plan to finish the ticket for practice once the invigilator has finished the assessment.

- [ ] (Optional) Use Swagger or API Blueprint to document API routes, parameters, requests and responses.
- [ ] (Optional) Abstract Date, Time and Timestamp functionality to a separate Util package for code organization.
- [ ] (Mandatory) Calculate accrued interest past the payment due date.
- [ ] (Optional) Create a payment schedule/payment timetable.
- [ ] (Optional) Create a status for user state 'PENDING APPROVAL','INACTIVE', 'REJECTED'
- [ ] (Mandatory) Create a status for loan state 'ACTIVE, 'CLOSED_REJECTED', 'CLOSED_REPAID', 'CLOSED_WRITTEN_OFF'
- [ ] (Optional) Abstract Exceptions to give better messages and abstracts. [Refer to this article](https://www.toptal.com/java/spring-boot-rest-api-error-handling)
- [ ] (Mandatory) Create a check to ensure that the loan transaction do not surpass the total amount due on a loan account.
- [ ] (Optional) Dockerize postgres database




