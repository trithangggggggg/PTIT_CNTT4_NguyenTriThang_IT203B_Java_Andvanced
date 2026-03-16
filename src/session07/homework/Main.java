package session07.homework;

public class Main {

    public static void main(String[] args) {

        System.out.println("Tạo sản phẩm");
        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);
        Product p3 = new Product("SP03", "Màn hình ", 300000);

        System.out.println("Đã thêm sản phẩm SP01, SP02");

        Customer customer =new Customer("Nguyễn Trí Thắng ", "trithangne@gmail.com", "Hà Nội");

        System.out.println("Đã thêm khách hàng");

        Order order = new Order("ORD001", customer);

        order.addItem(p1, 1);
        order.addItem(p2, 2);
        order.addItem(p3, 2);

        System.out.println("Đơn hàng ORD001 được tạo");

        OrderCalculator calculator = new OrderCalculator();

        double total = calculator.calculateTotal(order);

        System.out.println("Tổng tiền: " + (long)total);

        OrderRepository repo = new OrderRepository();
        repo.save(order);

        EmailService email = new EmailService();
        email.sendOrderConfirmation(order);
    }
}