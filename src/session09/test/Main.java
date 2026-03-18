package session09.test;

import session09.test.database.ProductDatabase;
import session09.test.entity.Product;
import session09.test.factory.ProductFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();
        int choice;
        do {
            System.out.println("----------- QUẢN LÝ SẢN PHẨM -----------");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Thoát");
            System.out.println("-----------------------------------------");
            System.out.print("Lựa chọn của bạn : ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Chọn loại sản phẩm:");
                    System.out.println("1. Physical Product");
                    System.out.println("2. Digital Product");

                    int type = sc.nextInt();
                    sc.nextLine();

                    System.out.print("ID: ");
                    String id = sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print(type == 1 ? "Weight: " : "Size(MB): ");
                    double extra = sc.nextDouble();

                    Product p = ProductFactory.createProduct(type, id, name, price, extra);

                    db.addProduct(p);

                    System.out.println("Thêm thành công!");
                    break;

                case 2:
                    List<Product> list = db.getAllProducts();

                    if (list.isEmpty()) {
                        System.out.println("Danh sách rỗng");
                    } else {
                        for (Product product : list) {
                            product.displayInfo();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID cần cập nhật: ");
                    String updateId = sc.nextLine();

                    Product product = db.findById(updateId);

                    if (product == null) {
                        System.out.println("Không tìm thấy");
                    } else {
                        System.out.print("Tên mới: ");
                        String newName = sc.nextLine();

                        System.out.print("Giá mới: ");
                        double newPrice = sc.nextDouble();

                        product.setName(newName);
                        product.setPrice(newPrice);
                        System.out.println("Cập nhật thành công");
                    }
                    break;

                case 4:
                    System.out.print("Nhập ID cần xoá: ");
                    String deleteId = sc.nextLine();

                    db.deleteProduct(deleteId);
                    System.out.println("Đã xoá");

                    break;
                case 5:

                    System.out.println("Thoát chương trình");
                    break;
            }
        } while (choice != 5);
    }
}