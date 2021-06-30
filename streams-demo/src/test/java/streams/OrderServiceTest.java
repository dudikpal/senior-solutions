package streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderServiceTest {

    OrderService ordersService = new OrderService();


    @BeforeEach
    public void init() {


        Product p1 = new Product("Tv", "IT", 2000);
        Product p2 = new Product("Laptop", "IT", 2400);
        Product p3 = new Product("Phone", "IT", 400);
        Product p4 = new Product("Lord of The Rings", "Book", 20);
        Product p5 = new Product("Harry Potter Collection", "Book", 120);

        Order o1 = new Order("pending", LocalDate.of(2021, 06, 07));
        o1.addProduct(p1);
        o1.addProduct(p2);
        o1.addProduct(p5);

        Order o2 = new Order("on delivery", LocalDate.of(2021, 06, 01));
        o2.addProduct(p3);
        o2.addProduct(p1);
        o2.addProduct(p2);

        Order o3 = new Order("pending", LocalDate.of(2021, 06, 8));
        o3.addProduct(p1);
        o3.addProduct(p3);
        o3.addProduct(p4);

        ordersService.saveOrder(o1);
        ordersService.saveOrder(o2);
        ordersService.saveOrder(o3);

    }

    @Test
    void test_order_count_by_status() {
        String input = "pending";
        int expected = 2;
        int actual = ordersService.orderCountByStatus("pending");
        assertEquals(expected, actual);
    }

    @Test
    void test_orders_with_given_category_product() {
        String input = "Book";
        int expected = 2;
        List<Order> actual = ordersService.ordersWithGivenCategoryProduct(input);
        assertEquals(expected, actual.size());
    }

    @Test
    void test_products_with_given_price_over() {
        int input = 1000;
        int expected = 2;
        assertEquals(expected, ordersService.productsWithGivenPriceOver(input).size());
    }

    @Test
    void test_income_between_dates() {
        LocalDate inputStartDate = LocalDate.of(2021, 6, 7);
        LocalDate inputEndDate = LocalDate.of(2021, 6, 8);
        double expected = 6940;
        assertEquals(expected, ordersService.incomeBetweenTwoDates(inputStartDate, inputEndDate));
    }

    @Test
    void test_find_product_by_name() {
        String input = "Laptop";
        Product product = ordersService.findProductByName(input);
        int expectedPrice = 2400;
        String expectedCategory = "IT";
        assertEquals(expectedPrice, product.getPrice());
        assertEquals(expectedCategory, product.getCategory());
    }

    @Test
    void test_order_with_most_expensive_product() {
        double expectedPrice = 2400;
        assertEquals(expectedPrice, ordersService.orderWithMostExpensiveProduct().get()
                .getProducts().stream().mapToDouble(Product::getPrice).max().getAsDouble());
    }

}