public class pickup implements deliveryStrategy {
    @Override
    public void deliver() {
        System.out.println("Pick up from store");
    }
}