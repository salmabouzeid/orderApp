class cheese extends foodDecorator {
    public cheese(Food food) { super(food); }

    public String getDescription() {
        return food.getDescription() + ", Cheese";
    }

    public double getPrice() {
        return food.getPrice() + 5;
    }
}
