package TJ.Controller;

import TJ.Model.Board;
import TJ.View.View;

public class Controller {
    private Board model;
    private View view;

    public Controller( Board model,View view) {
        this.view = view;
        this.model = model;
    }

    public void init(){
        model.init();
        view.init();
    }


}
