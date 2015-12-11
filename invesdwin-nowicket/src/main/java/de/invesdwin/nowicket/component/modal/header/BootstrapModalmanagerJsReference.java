package de.invesdwin.nowicket.component.modal.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import de.invesdwin.nowicket.application.auth.AWebApplication;

@Immutable
public final class BootstrapModalmanagerJsReference extends WebjarsJavaScriptResourceReference implements
IHeaderContributor {

    public static final BootstrapModalmanagerJsReference INSTANCE = new BootstrapModalmanagerJsReference();

    private BootstrapModalmanagerJsReference() {
        super("/bootstrap-modal/current/js/bootstrap-modal.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return Dependencies.combine(
                super.getDependencies(),
                JavaScriptHeaderItem.forReference(AWebApplication.get()
                        .getJavaScriptLibrarySettings()
                        .getJQueryReference()));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }

}
