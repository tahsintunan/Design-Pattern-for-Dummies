package TemplateMethodPattern.Code;

public class EWalletPayment extends Payment {

    @Override
    protected void verifyPaymentMethod() {
        // Here goes the code for verifying the E-Wallet
        System.out.println("E-Wallet verified");
    }

    @Override
    protected void executePayment() {
        // Here goes the code for executing payment with the E-Wallet
        System.out.println("Payment successfully completed with E-Wallet");
    }
}
