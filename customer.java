public class customer implements observer {

    @Override
    public void update(String message) {
        System.out.println("Notification: " + message);
    }
}
