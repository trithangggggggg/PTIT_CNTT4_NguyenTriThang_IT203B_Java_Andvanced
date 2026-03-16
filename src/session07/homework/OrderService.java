package session07.homework;

public class OrderService {

    private OrderRepository repository;
    private NotificationService notification;

    public OrderService(OrderRepository repository,
                        NotificationService notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void processOrder(Order order, PaymentMethod payment, double amount) {

        repository.save(order);

        payment.pay(amount);

        notification.send(
                "Đơn hàng " + order.getOrderId() + " đã được tạo",
                order.getCustomer().getEmail()
        );
    }
}