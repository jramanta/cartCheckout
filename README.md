# Cart Checkout Application

## About

A command line program that implements a supermarket cart checkout functionality.

## How to build / run

```bash
cd cartCheckout
mvn clean install
java -cp ./target/cartCheckout.jar com.jramanta.cartCheckout.CartCheckoutApplication
```
## Command line Reference

The commands you can run are:

**HELP**: It prints a list of the available commands with explanation of how to use them and an example command.

**C p c o**: It updates the pricing of C product with price equal to p and offer on c count on products with o offer price.
```bash
A 30 4 110
```

**C p**: It updates the pricing of C product with price equal to p. Any existing offer for C product is removed.
```bash
A 30
```

**C...**: Prints the total cost for the cart which we want to checkout. This command is given in the format of a list of C product codes separated by space.
```bash
A B C
```

**QUIT**: Terminates the application.

## Notes on the implementation

The application's available products are not hard coded anywhere. The list of available products are the ones set in the initialization file. 
The initialization file specifies the list of products available to the application as well as the initial pricing rules. The only restriction on the products
is that the names are individual letters of the alphabet such as A, B, C etc.
