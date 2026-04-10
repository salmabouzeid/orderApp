public class cheese extends foodDecorator {
    public cheese(Food food) { super(food); }

    @Override
    public String getDescription() {
        return food.getDescription() + ", Cheese";
    }
    @Override
    public double getPrice() {
        return food.getPrice() + 5;
    }
}
