public class sauce extends foodDecorator {
    public sauce(Food food) { super(food); }
    @Override
    public String getDescription() {
        return food.getDescription() + ", Sauce";
    }
    @Override
    public double getPrice() {
        return food.getPrice() + 3;
    }
}