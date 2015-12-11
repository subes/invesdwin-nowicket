package de.invesdwin.nowicket.application;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.ResourceReference;
import org.springframework.security.authentication.AuthenticationManager;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.filter.init.WebApplicationInitializer;

@NotThreadSafe
public class WebApplicationSupport implements IWebApplication {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return null;
    }

    @Override
    public Class<? extends WebPage> getSignInPage() {
        return null;
    }

    @Override
    public Class<? extends WebPage> getSignOutPage() {
        return null;
    }

    @Override
    public ResourceReference getFavicon() {
        return null;
    }

    @Override
    public WebApplicationInitializer getInitializerOverride() {
        return null;
    }

    @Override
    public void postProcessNewSession(final AWebSession session) {}

    @Override
    public AuthenticationManager getAuthenticationManager() {
        return null;
    }

    @Override
    public Class<? extends WebPage> getPageExpiredPage() {
        return null;
    }

    @Override
    public Class<? extends WebPage> getInternalErrorPage() {
        return null;
    }

    @Override
    public Class<? extends WebPage> getAccessDeniedPage() {
        return null;
    }

    @Override
    public Class<? extends WebPage> getPageNotFoundPage() {
        return null;
    }

    @Override
    public List<String> getWicketFilterIgnorePaths() {
        return null;
    }

}
