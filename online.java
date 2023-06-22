import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int productId) {
        products.removeIf(p -> p.getId() == productId);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void displayCart() {
        if (products.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Your shopping cart contains:");
            for (Product product : products) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
            System.out.println("Total price: $" + calculateTotalPrice());
        }
    }
}

class OnlineShoppingSystem {
    private List<Product> availableProducts;
    private ShoppingCart cart;

    public OnlineShoppingSystem() {
        availableProducts = new ArrayList<>();
        cart = new ShoppingCart();
    }

    public void addProduct(Product product) {
        availableProducts.add(product);
    }

    public void removeProduct(int productId) {
        availableProducts.removeIf(p -> p.getId() == productId);
    }

    public void displayAvailableProducts() {
        if (availableProducts.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available products:");
            for (Product product : availableProducts) {
                System.out.println(product.getId() + ": " + product.getName() + " - $" + product.getPrice());
            }
        }
    }

    public void displayShoppingCart() {
        cart.displayCart();
    }

    public void addToCart(int productId) {
        Product product = availableProducts.stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);

        if (product == null) {
            System.out.println("Invalid product ID.");
        } else {
            cart.addProduct(product);
            System.out.println("Product added to cart.");
        }
    }

    public void removeFromCart(int productId) {
        cart.removeProduct(productId);
        System.out.println("Product removed from cart.");
    }

    public static void main(String[] args) {
        OnlineShoppingSystem shoppingSystem = new OnlineShoppingSystem();

        // Adding sample products
        shoppingSystem.addProduct(new Product(1, "Product 1", 10.99));
        shoppingSystem.addProduct(new Product(2, "Product 2", 19.99));
        shoppingSystem.addProduct(new Product(3, "Product 3", 5.99));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display available products");
            System.out.println("2. Display shopping cart");
            System.out.println("3. Add product to cart");
            System.out.println("4. Remove product from cart");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    shoppingSystem.displayAvailableProducts();
                    break;
                case 2:
                    shoppingSystem.displayShoppingCart();
                    break;
                case 3:
                    System.out.print("Enter product ID to add to cart: ");
                    int productId = scanner.nextInt();
                    shoppingSystem.addToCart(productId);
                    break;
                case 4:
                    System.out.print("Enter product ID to remove from cart: ");
                    productId = scanner.nextInt();
                    shoppingSystem.removeFromCart(productId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }
}
