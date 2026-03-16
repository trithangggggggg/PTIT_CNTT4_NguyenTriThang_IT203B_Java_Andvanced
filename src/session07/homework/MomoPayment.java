package session07.homework;

public class MomoPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán MoMo: " + (long) amount);
    }
}