package com.funclub.rapoarteloto;

import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import com.funclub.rapoarteloto.event.AccessControl;
import com.funclub.rapoarteloto.event.BasicAccessControl;
import com.funclub.rapoarteloto.layouts.LoginView;
import com.funclub.rapoarteloto.layouts.LoginView.LoginListener;
import com.funclub.rapoarteloto.layouts.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@Theme("rapoarteloto")
@Title("RapoarteLoto")
@Viewport("user-scalable=no,initial-scale=1.0")
public class RapoartelotoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = RapoartelotoUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	private AccessControl accessControl = new BasicAccessControl();

	@Override
	protected void init(VaadinRequest request) {
		setLocale(Locale.US);

        Responsive.makeResponsive(this);
        if (!accessControl.isUserSignedIn()) {
            setContent(new LoginView(accessControl, new LoginListener() {
                @Override
                public void loginSuccessful() {
                    showMainView(); 
                }
            }));
        } else {
            showMainView();
        }
	}

	public void showMainView() {
    	addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainView());
//        getNavigator().navigateTo(getNavigator().getState());
    }

}