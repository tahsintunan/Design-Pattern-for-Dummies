package TemplateMethodPattern.Code;

public abstract class Payment {

    public void makePayment() {
        verifyPaymentMethod();
        executePayment();
    }

    protected abstract void verifyPaymentMethod();
    protected abstract void executePayment();

}
