# Template Method Design Pattern

## ❇️ Description:
**Template Method pattern** is a behavioral design pattern that defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure. The base class declares algorithm 'placeholders', and derived classes implement the placeholders.


## ❇️ Problem
Let's say we're building a digital payment system. We can use various modes of payment to make payments with this software. When we're making payments with this system, the software first verifies the payment method. Upon verification is completed, it executes the payment process and finalizes it. So we have two methods that'll always have to be run in the same order to make a payment:
1. Verifying the method/mode of payment
2. Executing payment process

So we can bundle them together within a single method. Let's call this method `makePayment()`, and let's name our class `Payment`.
```java
public class Payment {
    // this method is run whenever someone tries to make payment
    public void makePayment() {
        verifyPaymentMethod();
        executePayment();
    }

    private void verifyPaymentMethod() {
        // here goes the logic for verifying payment method
        System.out.println("Payment verified");
    }
    
    private void executePayment() {
        // here goes the logic for executing payment
        System.out.println("Payment successfully completed");
    }
}
```
But we have a problem. Our software is supposed to support multiple modes of payment. If we had to support 2 payment modes (i.e. Credit card payment and E-wallet payment), they'd have to be written in a if-else manner inside those methods. So our methods (and therefore, the class) would quickly become big and unmaintainable. Let's look at a bad example:

```java
public enum PaymentMode {
    CREDIT_CARD_PAYMENT,
    E_WALLET_PAYMENT
}

public class Payment {
    private PaymentMode paymentMode;

    public Payment(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    // this method is run whenever someone tries to make payment
    public void makePayment() {
        verifyPaymentMethod();
        executePayment();
    }

    private void verifyPaymentMethod() {
        // here goes the logic for verifying payment method
        if (paymentMode == PaymentMode.CREDIT_CARD_PAYMENT) {
            System.out.println("Credit Card verified");
        }
        else if (paymentMode == PaymentMode.E_WALLET_PAYMENT) {
            System.out.println("E-Wallet verified");
        }
    }
    
    private void executePayment() {
        // here goes the logic for executing payment
        if (paymentMode == PaymentMode.CREDIT_CARD_PAYMENT) {
            System.out.println("Payment successfully completed with credit card");
        }
        else if (paymentMode == PaymentMode.E_WALLET_PAYMENT) {
            System.out.println("Payment successfully completed with E-Wallet");
        }
    }
}
```
The driver code would look like this:
```java
public class Main {
    public static void main(String[] args) {
        var payment1 = new Payment();
        var payment2 = new Payment();

        payment1.makePayment(PaymentMode.CREDIT_CARD_PAYMENT); // makes credit card payment
        payment2.makePayment(PaymentMode.E_WALLET_PAYMENT); // makes e-wallet payment
    }
}
```
Our current approach has the following problems:
1. It's neither extendable nor maintainable. Filling our methods with if-else statements is not only a bad approach, if we were to have 20 different payment modes instead of 2, our code would become unreadable and difficult to maintain.
2. Violates the **Open-Closed principle**. If we want to add new payment modes, we'll have to modify our existing code again and again. 

###### _(N.B. In our `Payment` class, we're just printing out various things when the methods are run for demonstration purposes. In real life, they'd contain real functionalities.)_
