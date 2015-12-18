package de.invesdwin.nowicket.examples.war;

import org.apache.wicket.markup.html.WebPage;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.examples.war.page.HomePage;

public class ExampleWebApplicationConfig extends WebApplicationConfigSupport {
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}
	
}
