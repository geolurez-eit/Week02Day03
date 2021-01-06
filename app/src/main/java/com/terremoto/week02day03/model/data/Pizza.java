package com.terremoto.week02day03.model.data;

public class Pizza {
    private String pizzaFlavor;
    private double pizzaPrice;
    
    private int calories;
    private String ingredients;
    private String imageUrl;
    
    public Pizza(String pizzaFlavor, double pizzaPrice, int calories, String ingredients, String imageUrl) {
        this.pizzaFlavor = pizzaFlavor;
        this.pizzaPrice = pizzaPrice;
        this.calories = calories;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
    }
    
    public int getCalories() {
        return calories;
    }
    
    public void setCalories(int calories) {
        this.calories = calories;
    }
    
    public String getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getPizzaFlavor() {
        return pizzaFlavor;
    }
    
    public void setPizzaFlavor(String pizzaFlavor) {
        this.pizzaFlavor = pizzaFlavor;
    }
    
    public double getPizzaPrice() {
        return pizzaPrice;
    }
    
    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }
}
