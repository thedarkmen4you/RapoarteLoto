package com.funclub.rapoarteloto.event;

import com.funclub.rapoarteloto.domain.User;
import com.vaadin.server.VaadinSession;

public class BasicAccessControl implements AccessControl {
	
	VaadinSession s = VaadinSession.getCurrent();
	public static final String CURRENT_USER_SESSION_ATTRIBUTE_KEY = BasicAccessControl.class.getCanonicalName();

    @Override
    public boolean signIn(String username, String password) {
        if (username == null || username.isEmpty())
            return false;

        User myUser = new User();
        myUser.setFirstName("rares");
        myUser.setRole("admin");
        s.getSession().setAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY, myUser);
        
        return true;
    }

    @Override
    public boolean isUserSignedIn() {
        return s.getSession().getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY)!=null;
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override
    public String getPrincipalName() {
        return ((User) s.getSession().getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY)).getRole();
    }

}