package session07.homework;

public class InvoiceGenerator {

    public void printInvoice(Order order, double total, double finalAmount) {

        System.out.println("=== HÓA ĐƠN ===");

        System.out.println("Khách: " + order.getCustomer().getName());

        for (OrderItem item : order.getItems()) {

            System.out.println(
                    item.getProduct().getName()
                            + " - SL: " + item.getQuantity()
                            + " - Thành tiền: "
                            + (long) item.getTotal()
            );
        }

        System.out.println("Tổng tiền: " + (long) total);
        System.out.println("Cần thanh toán: " + (long) finalAmount);
    }
}