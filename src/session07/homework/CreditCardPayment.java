package session07.homework;

public class CreditCardPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán thẻ tín dụng: " + (long) amount);
    }
}