class sauce extends foodDecorator {
    public sauce(Food food) { super(food); }

    public String getDescription() {
        return food.getDescription() + ", Sauce";
    }

    public double getPrice() {
        return food.getPrice() + 3;
    }
}