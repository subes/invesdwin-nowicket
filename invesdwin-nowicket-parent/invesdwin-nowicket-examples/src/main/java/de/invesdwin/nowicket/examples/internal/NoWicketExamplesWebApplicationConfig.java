package de.invesdwin.nowicket.examples.internal;

import org.apache.wicket.markup.html.WebPage;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.examples.pages.home.HomePage;

public class NoWicketExamplesWebApplicationConfig extends WebApplicationConfigSupport {
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}
}