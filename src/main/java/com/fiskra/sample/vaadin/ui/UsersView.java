package com.fiskra.sample.vaadin.ui;

import com.fiskra.sample.vaadin.model.User;
import com.fiskra.sample.vaadin.repo.UserRepository;
import com.fiskra.sample.vaadin.ui.design.UsersDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by snikitin on 07.04.18.
 */
@SpringView(name = UsersView.VIEW_NAME)
public class UsersView extends UsersDesign implements View {

    public static final String VIEW_NAME = "crud";

    @Autowired
    private  UserRepository users;

    public UsersView() {
        this.list = new Grid(User.class);
        this.list.setColumns("id", "userName");
        this.list.setItems(users.findAll());
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {


    }

}
