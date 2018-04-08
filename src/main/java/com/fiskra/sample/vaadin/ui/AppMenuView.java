package com.fiskra.sample.vaadin.ui;

import com.fiskra.sample.vaadin.ui.design.AppMenuDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;

/**
 * Created by snikitin on 07.04.18.
 */
public class AppMenuView extends AppMenuDesign implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public CssLayout getContentLayout(){
        return this.content;
    }

}
