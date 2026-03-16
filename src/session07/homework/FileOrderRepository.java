package session07.homework;

public class FileOrderRepository implements OrderRepository {

    @Override
    public void save(Order order) {
        System.out.println("Lưu đơn hàng vào file: " + order.getOrderId());
    }
}
