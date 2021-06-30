package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();


    public void saveOrder(Order order) {
        orders.add(order);
    }


    public int orderCountByStatus(String status) {
        return (int) orders.stream()
                .filter(o -> o.getStatus().equals(status))
                .count();
    }

    public List<Order> ordersWithGivenCategoryProduct(String category) {

        return orders.stream()
                .filter(order -> order.getProducts()
                        .stream()
                        .anyMatch(product -> product.getCategory()
                                .equals(category)))                            //.contains(category))

                .collect(Collectors.toList());
    }


    public List<Order> getOrders() {
        return orders;
    }

    public List<Product> productsWithGivenPriceOver(int input) {
        return orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getPrice() > input)
                .distinct()
                .collect(Collectors.toList());
    }

    public double incomeBetweenTwoDates(LocalDate inputStartDate, LocalDate inputEndDate) {
        int income = 0;
        return orders.stream()
                .filter(order -> !order.getOrderDate().isBefore(inputStartDate)
                        && !order.getOrderDate().isAfter(inputEndDate))
                .flatMap(order -> order.getProducts().stream())
                .map(product -> product.getPrice())
                .reduce(0.0, (x, y) -> x + y);
    }

    public Product findProductByName(String input) {
        return orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getName().equals(input))
                .findFirst()
                .get();
    }

    public Optional<Order> orderWithMostExpensiveProduct() {
        return orders.stream()
                .max((o1, o2) -> {
                    double o1Price = o1.getProducts().stream().mapToDouble(Product::getPrice).max().getAsDouble();
                    double o2Price = o2.getProducts().stream().mapToDouble(Product::getPrice).max().getAsDouble();
                    return (int) (o2Price + o1Price);
                });
    }
}
