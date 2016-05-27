package uk.co.mruoc.view;

import io.dropwizard.views.View;

public class IndexView extends View {

    public IndexView() {
        super("index.ftl");
    }

    public String getName() {
        return "Web Template";
    }

}
