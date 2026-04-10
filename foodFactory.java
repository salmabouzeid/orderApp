public class foodFactory {
    public static Food createFood(String type) {
        if (type.equalsIgnoreCase("burger")) return new burger();
        if (type.equalsIgnoreCase("pizza")) return new pizza();
        if (type.equalsIgnoreCase("pasta")) return new pasta();
        return null;
    }
}