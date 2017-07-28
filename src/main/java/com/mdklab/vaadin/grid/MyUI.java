package com.mdklab.vaadin.grid;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.SerializableSupplier;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.getColumns().forEach(tColumn -> tColumn.setCaption(tColumn.getId()));
        grid.setDataProvider((Grid.FetchItemsCallback<Person>) (sortOrder, offset, limit) -> getItems(offset, limit).stream()
                , (SerializableSupplier<Integer>) this::getCount);

        setContent(grid);
    }

    private Collection<Person> getItems(int offset, int limit) {
        Collection<Person> res = new ArrayList<>(limit);
        for (int i = offset; i < offset + limit; i++) {
            res.add(new Person("Name " + i));
        }
        return res;
    }

    private Integer getCount() {
        return 442_000;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
