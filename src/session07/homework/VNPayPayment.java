package session07.homework;

public class VNPayPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán VNPay: " + (long) amount);
    }
}