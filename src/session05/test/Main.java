package session05.test;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Sử dụng ArrayList<Product> để quản lý danh sách
        ArrayList<Product> products = new ArrayList<>();
        int choice = 0;
        do {
            System.out.println("========= PRODUCT MANAGEMENT SYSTEM =========");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("5. Thoát chương trình");
            System.out.println("=============================================");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        addProduct(sc, products);
                        break;
                    case 2:
                        displayProducts(products);
                        break;
                    case 3:
                        updateQuantity(sc, products);
                        break;
                    case 4:
                        deleteOutOfStockProducts(products);
                        break;
                    case 5:
                        System.out.println("Thoát chương trình. Hẹn gặp lại!");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.\n");
                }
            } catch (InvalidProductException e) {
                System.out.println("Lỗi: " + e.getMessage() + "\n");
            } catch (Exception e) {
                System.out.println("Lỗi: Dữ liệu nhập không hợp lệ. Vui lòng thử lại.\n");
                sc.nextLine();
            }
        } while (choice != 5);

        sc.close();
    }


    // case 1
    static void addProduct(Scanner sc, ArrayList<Product> products) throws InvalidProductException {
        System.out.print("Nhập ID sản phẩm: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (products.stream().anyMatch(p -> p.getId() == id)) {
            throw new InvalidProductException("ID đã tồn tại. Vui lòng nhập ID khác.");
        }

        System.out.print("Nhập tên sản phẩm: ");
        String name = sc.nextLine();

        System.out.print("Nhập giá sản phẩm: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.print("Nhập số lượng: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhập danh mục: ");
        String category = sc.nextLine();

        Product product = new Product(id, name, price, quantity, category);
        products.add(product);
        System.out.println("Thêm sản phẩm thành công !\n");
    }

    // case 2
    static void displayProducts(ArrayList<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.\n");
            return;
        }

        System.out.println("\n========= DANH SÁCH SẢN PHẨM =========");
        System.out.println("| ID    | Name                 | Price      | Quantity   | Category        |");
        System.out.println("|-------|----------------------|------------|------------|-----------------|");
        products.stream().forEach(System.out::println);
        System.out.println("|-------|----------------------|------------|------------|-----------------|");
        System.out.println("Tổng sản phẩm: " + products.size() + "\n");
    }

    // case 3
    static void updateQuantity(Scanner sc,ArrayList<Product> products){
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        int id = sc.nextInt();
        sc.nextLine();

        Product product = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm với ID: " + id + "\n");
            return;
        }

        System.out.print("Nhập số lượng mới: ");
        int newQuantity = sc.nextInt();
        sc.nextLine();

        product.setQuantity(newQuantity);
        System.out.println("Cập nhật số lượng thành công !\n");
    }

    // casse 4

    static void deleteOutOfStockProducts(ArrayList<Product> products) {
        long count = products.stream().filter(p -> p.getQuantity() == 0).count();
        if (count == 0) {
            System.out.println("Không có sản phẩm nào hết hàng.\n");
            return;
        }

        products.removeIf(p -> p.getQuantity() == 0);
        System.out.println("Đã xóa " + count + " sản phẩm đã hết hàng.\n");
    }
}
