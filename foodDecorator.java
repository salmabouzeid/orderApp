public abstract class foodDecorator implements Food {
    protected Food food;
    public foodDecorator(Food food) { this.food = food; }
}

