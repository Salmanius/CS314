package com.TeamTrinity;

/**
 * Created by mjdun on 2/13/2017.
 */
public class Presenter {
    private View view;
    private Model model;
    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }
    public void start() {
        String result = model.getMessage();
        view.display (result);
    }
}
