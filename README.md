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

***Needs to be deployed in a PCI DSS compliant environment.***

### CORE
//TODO
#### Requirements:
- Needs to be deployed in a PCI DSS compliant environment.

### Offline
//TODO

### Notification
//TODO


## License
[LICENSE](LICENSE)
