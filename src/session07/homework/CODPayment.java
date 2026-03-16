package session07.homework;

public class CODPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán COD: " + (long) amount);
    }
}