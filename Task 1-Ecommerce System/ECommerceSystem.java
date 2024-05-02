import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private ArrayList<Product> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public double getTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("Shopping Cart:");
        for (Product item : items) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        System.out.println("Total: $" + getTotal());
    }
}

public class ECommerceSystem {
    private static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize products
        products.add(new Product("Product 1", 10.99));
        products.add(new Product("Product 2", 20.49));
        products.add(new Product("Product 3", 5.99));

        // Start shopping
        Scanner scan = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        System.out.println("Welcome to the E-Commerce System!");
        while (true) {
            System.out.println("\nAvailable Products:");
            displayProducts();
            System.out.println("1. Add to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the product number to add to cart: ");
                    int productNumber = scan.nextInt();
                    if (productNumber > 0 && productNumber <= products.size()) {
                        cart.addItem(products.get(productNumber - 1));
                        System.out.println("Product added to cart!");
                    } else {
                        System.out.println("Invalid product number!");
                    }
                    break;
                case 2:
                    cart.displayCart();
                    break;
                case 3:
                    System.out.println("Total amount to pay: $" + cart.getTotal());
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayProducts() {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
        }
    }
}
