package de.voolk.marbles.web.app;

import de.voolk.marbles.persistence.beans.Role;
import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.persistence.services.IAuthentificationService;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.servlet.http.Cookie;
import java.util.Collection;
import java.util.HashSet;

public class IdentSession extends AuthenticatedWebSession {
	public static final String SYSTEM_ROLE = "system";

	private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 7; // one week

	private static final long serialVersionUID = 2L;

    @SpringBean
    transient private IAuthentificationService authentificationService;
    transient private User user;
    transient private Collection<String> userRoles;
    private String login;
    public static final String IDENT_COOKIE = "marbles-ident";

    public IdentSession(Request request) {
        super(request);
    }

    public Collection<String> getUserRoles() {
        if(userRoles == null) {
            userRoles = new HashSet<String>();
            for(Role role: getAuthentificationService().findRolesForUser(
            		getUser().getId())) {
                userRoles.add(role.getName());
            }
        }
        return userRoles;
    }

    public IAuthentificationService getAuthentificationService() {
        if (authentificationService == null) {
            InjectorHolder.getInjector().inject(this);
        }
        return authentificationService;
    }

    public User getUser() {
        if(user == null) {
               user = getAuthentificationService().findUserByName(getLogin());
        }
        return user;
    }

    @Override
    public boolean authenticate(String loginName, String password) {
        boolean auth = getAuthentificationService().authenticate(loginName, password);
        if(auth) {
            this.login = loginName;
            String identKey = getUser().getIdentKey();
            Cookie cookie = new Cookie(IDENT_COOKIE, identKey);
            cookie.setMaxAge(COOKIE_MAX_AGE);
			((WebResponse)RequestCycle.get().getResponse()).addCookie(cookie);
        }
        signIn(auth);
        return auth;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn() || hasCookie()) {
        	return new Roles(StringUtils.join(getUserRoles(), ','));
        } else {
            return null;
        }
    }

    public boolean isAdmin() {
        return getUserRoles().contains("admin");
    }

    private boolean hasCookie() {
        Cookie identCookie = ((WebRequest)RequestCycle.get().getRequest()
        		).getCookie(IDENT_COOKIE);
        if(identCookie != null) {
            User user = getAuthentificationService().findUserByIdentKey(
            		identCookie.getValue());
            if(user != null) {
                login = user.getName();
                signIn(true);
                return true;
            }
        }
        return false;
    }
}