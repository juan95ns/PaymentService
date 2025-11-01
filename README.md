# PaymentService

## About The Project

PaymentService is a group of microservices that provides payment processing capabilities. 
It is designed to handle various payment methods and integrate with different payment gateways.

***This is made for educational purposes and is not intended for production use.***

## Architecture
//TODO
![Architecture](docs/architecture.png)

## Microservices
### Tokenizer
Provides tokenization services for sensitive payment information. This is meant to be on a separate 
PCI DSS compliant environment along with all the communications coming from and to this micro. This way, 
the rest of the microservices that interact with PaymentService can be PCI DSS compliant without having to be in a 
PCI DSS ready environment.

For example, if you have an e-commerce application that needs to process payments, you can have your application with 
all the microservices that you need (like order management, inventory, user management, etc.) in a regular environment. 
When it comes to processing payments, your application can communicate with the Tokenizer microservice in the PCI DSS 
compliant environment to tokenize payment information. This way, your application can remain PCI DSS compliant without 
having to handle sensitive payment data directly. And then PaymentService Core can handle the rest of the payment 
processing in a secure manner.

#### Security requirements:
***Needs to be deployed in a PCI DSS compliant environment.***

### CORE
//TODO
#### Security requirements:
- Needs to be deployed in a PCI DSS compliant environment.

### Offline
//TODO

### Notification
//TODO


## License
[LICENSE](LICENSE)
