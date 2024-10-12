import java.util.Scanner; 

class Info {
    String id;
    int level;
    
    public Info() {
        this.id = "codetree";
        this.level = 10; 
    }

    public Info(String id, int level) {
        this.id = id;
        this.level = level;
    }
}

public class Main {
    public static void main(String[] args) {    
        Scanner sc = new Scanner(System.in);
        
        String id = sc.next();
        int lv = sc.nextInt();

        Info user1 = new Info();
        Info user2 = new Info(id, lv);

        System.out.println("user " + user1.id+" lv " + user1.level);
        System.out.println("user " + user2.id+" lv " + user2.level);
    }
}