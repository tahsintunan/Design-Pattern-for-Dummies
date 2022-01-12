
public class CreditCardPayment extends Payment {

    @Override
    protected void verifyPaymentMethod() {
        // Here goes the code for verifying the Credit Card
        System.out.println("Credit Card verified.");
    }

    @Override
    protected void executePayment() {
        // Here goes the code for executing payment with the Credit Card
        System.out.println("Payment successfully completed with credit card.");
    }
}
