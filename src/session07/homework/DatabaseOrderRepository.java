package session07.homework;

public class DatabaseOrderRepository implements OrderRepository {

    @Override
    public void save(Order order) {
        System.out.println("Lưu đơn hàng vào database: " + order.getOrderId());
    }
}