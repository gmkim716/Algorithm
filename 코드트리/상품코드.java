import java.util.Scanner; 

class Product {
    String name;
    int code;

    public Product() {
        this.name = "codetree";
        this.code = 50;
    }
    
    public Product(String name, int code) {
        this.name = name;
        this.code = code;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int code = sc.nextInt();

        Product product1 = new Product();
        Product product2 = new Product(name, code);

        System.out.println("product " + product1.code + " is " + product1.name);
        System.out.println("product " + product2.code + " is " + product2.name);
    }
}