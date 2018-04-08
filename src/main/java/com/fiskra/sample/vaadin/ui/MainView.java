package com.fiskra.sample.vaadin.ui;

import com.fiskra.sample.vaadin.ui.design.AppMenuDesign;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fiskra.sample.vaadin.model.Student;
import com.fiskra.sample.vaadin.repo.StudentRepository;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;

@SpringView(name = MainView.VIEW_NAME)
public class MainView extends CustomComponent implements  View {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_NAME = "crud";
	
	private final StudentEditor editor;
	
	final Grid<Student> grid;

	final TextField filter;

	private final Button addNewBtn;
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public MainView(StudentRepository studentRepository, StudentEditor editor) {
		this.studentRepository = studentRepository;
		this.editor = editor;
		this.grid = new Grid<>(Student.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New student", FontAwesome.PLUS);

		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);

		TabSheet tabSheet = new TabSheet();
		tabSheet.setSizeFull();

		VerticalLayout mainLayout = new VerticalLayout(actions, tabSheet);
		AppMenuView appMenu = new AppMenuView();

		UsersView usersView = new UsersView();

		appMenu.getContentLayout().addComponent(usersView);


		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSizeFull();

		VerticalLayout verticalLayout1 = new VerticalLayout();
		verticalLayout1.setSizeFull();


		tabSheet.addTab(verticalLayout, "Students tab");
		tabSheet.addTab(verticalLayout1, "High charts tab");
		//verticalLayout.addComponent(new Label("Example"));
		//verticalLayout.addComponent(new Button("Button"));

		verticalLayout.addComponent(actions);
		verticalLayout.addComponent(grid);
		verticalLayout.addComponent(editor);

		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "firstName", "lastName", "phone", "eMail");
		grid.setWidth(1000,Unit.PIXELS);

		filter.setPlaceholder("Filter by last name");

		setCompositionRoot(appMenu);

		// Connect selected Student to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editStudent(e.getValue());
		});

		// Instantiate and edit new Student the new button is clicked
		addNewBtn.addClickListener(e -> editor.editStudent(new Student("", "", "", "", null)));
		
		editor.save.addClickListener(e -> {
			studentRepository.save(editor.student);
			listStudents(null);
			editor.setVisible(false);
		});
		
		editor.delete.addClickListener(e -> {
			studentRepository.delete(editor.student);
			listStudents(null);
			editor.setVisible(false);
		});
		
		editor.cancel.addClickListener(e -> {
			editor.editStudent(editor.student);
			listStudents(null);
			editor.setVisible(false);
		});

		// Listen changes made by the filter textbox, refresh data from backend
		filter.addValueChangeListener(event -> {
			editor.setVisible(false);
			listStudents(event.getValue());
		});
		// Initialize listing
		listStudents(null);

	}
	
    void listStudents(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(studentRepository.findAll());
		}
		else {
			grid.setItems(studentRepository.findByLastNameStartsWithIgnoreCase(filterText));
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
