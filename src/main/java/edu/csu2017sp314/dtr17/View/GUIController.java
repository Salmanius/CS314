package main.java.edu.csu2017sp314.dtr17.View;

import javafx.event.ActionEvent;
import javafx.scene.web.WebView;
import main.java.edu.csu2017sp314.dtr17.Presenter.Presenter;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by mjdun on 4/1/2017.
 */
public class GUIController {
    public WebView SVGWebView;

    protected Presenter presenter;

    public GUIController(){
        System.out.println("controller constructor called");
    }

    public void displaySVG(String filePath) {
        String url = "";
        try {
            url = new File(filePath).toURI().toURL().toString();
        }catch (MalformedURLException exception){
            url = "";
        }
        SVGWebView.getEngine().load(url);
    }

    public void loadMapPressed(ActionEvent actionEvent) {
        presenter.createSVGButtonPressed(false, false);
    }

    public void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }
}
