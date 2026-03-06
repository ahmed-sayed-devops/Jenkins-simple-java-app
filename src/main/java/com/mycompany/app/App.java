package com.mycompany.app;

public class App {

    private final String message = "Jenkins Pipeline Run Successfully";
    private final String name = "Ahmed Sayed";

    public App() {}

    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.getMessage());
        System.out.println(app.getName());
    }

    private String getMessage() {
        return message;
    }

    private String getName() {
        return name;
    }
}