A trivial banking payment system that tracks accounts and allows fund transfer between them.
Storage is in memory and various assumptions regarding concurrent access and transactionality have been assumed for simplicity.

This is demo of coding philosophy around data structure choice, implementation clarity, exceptions and design of search functionality using functors and 'happy path' test for minimal guarantee.

Source description :
Package com.banking.payment.fakepay under 'src' has all interfaces and 'impl' directory has implementation of the system. Happy path test under 'test'.
