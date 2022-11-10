import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vasili_zlobin.spring.model.Cart;
import ru.vasili_zlobin.spring.model.ProductRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        workCart();
    }

    public static void workCart() {
        Scanner scanner = new Scanner(System.in);
        boolean close = false;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.vasili_zlobin.spring.model");
        Cart cart = context.getBean(Cart.class);
        while (!close) {
            cart.printInfo();
            System.out.println("Выберите операцию: 0 - выход, 1 - добавить товар, 2 - удалить товар, 3 - новая корзина");
            switch (getIntegerNumber(scanner, 0, 3)) {
                case 0:
                    close = true;
                    break;
                case 1:
                    cart.add(enterProductId(scanner));
                    break;
                case 2:
                    cart.remove(enterProductId(scanner));
                    break;
                case 3:
                    cart = context.getBean(Cart.class);
                    break;
            }
        }
    }

    private static int enterProductId(Scanner scanner) {
        System.out.println("Введите ID товара.");
        return getIntegerNumber(scanner, 0, ProductRepository.SIZE - 1);
    }

    private static int getIntegerNumber(Scanner scanner, int min, int max) {
        boolean error = true;
        int result = 0;
        while (error) {
            System.out.print("Введите число от " + min + " до " + max + ": ");
            result = scanner.nextInt();
            error = result < min || result > max;
        }
        return result;
    }
}
