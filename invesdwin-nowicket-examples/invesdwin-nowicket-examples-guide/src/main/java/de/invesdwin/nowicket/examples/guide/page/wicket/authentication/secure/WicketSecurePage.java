package de.invesdwin.nowicket.examples.guide.page.wicket.authentication.secure;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("wicketsecure")
@AuthorizeInstantiation(Roles.ADMIN)
public class WicketSecurePage extends AExampleWebPage {

    public WicketSecurePage() {
        this(Model.of(new WicketSecure()));
    }

    public WicketSecurePage(final IModel<WicketSecure> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
