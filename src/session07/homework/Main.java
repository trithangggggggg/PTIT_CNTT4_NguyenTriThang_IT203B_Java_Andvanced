package session07.homework;
public class Main {

    public static void main(String[] args) {

        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);

        Customer customer =
                new Customer("Nguyễn Trí Thắng", "trithangne@gmail.com", "Hà Nội");

        Order order = new Order("ORD001", customer);

        order.addItem(p1, 1);
        order.addItem(p2, 2);

        OrderCalculator calculator = new OrderCalculator();

        double total = calculator.calculateTotal(order);

        DiscountStrategy discount = new PercentageDiscount(10);

        double finalAmount = discount.applyDiscount(total);

        InvoiceGenerator invoice = new InvoiceGenerator();

        invoice.printInvoice(order, total, finalAmount);

        PaymentMethod payment = new CreditCardPayment();

        OrderRepository repo = new FileOrderRepository();

        NotificationService notification = new EmailNotification();

        OrderService service =
                new OrderService(repo, notification);

        service.processOrder(order, payment, finalAmount);
    }
}