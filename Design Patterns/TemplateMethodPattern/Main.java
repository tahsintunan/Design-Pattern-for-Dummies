package TemplateMethodPattern;

import TemplateMethodPattern.Code.CreditCardPayment;
import TemplateMethodPattern.Code.EWalletPayment;

public class Main {
    public static void main(String[] args) {

        var payment1 = new CreditCardPayment();
        var payment2 = new EWalletPayment();

        payment1.makePayment();
        payment2.makePayment();

    }
}
