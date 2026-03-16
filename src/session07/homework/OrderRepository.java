package session07.homework;

public class OrderRepository {

    public void save(Order order) {
        System.out.println("Đã lưu đơn hàng " + order.getOrderId());
    }
}