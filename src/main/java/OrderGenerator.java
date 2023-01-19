import java.util.List;

public class OrderGenerator {
    public static OrderData getNewOrder(List<String> colour){
        return new OrderData(
                "Marty",
                "McFly",
                "Hill-Valley",
                1,
                "+1 234 567 89 10",
                1,
                "2023-01-20",
                "Можно быстрее",
                colour);
    }
}
