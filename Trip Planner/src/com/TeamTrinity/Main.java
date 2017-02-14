package com.TeamTrinity;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        // argument handling?
        Model model = new Model();
        View view = new View();
        Presenter presenter = new Presenter(view, model);
        presenter.start();
    }
}
