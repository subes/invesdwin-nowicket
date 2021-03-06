package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step1.CreateUserWizardStep1Constants;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;

@NotThreadSafe
public class CreateUserWizardStepBindingInterceptor extends BindingInterceptor {

    private final MarkupContainer container;

    public CreateUserWizardStepBindingInterceptor(final MarkupContainer container) {
        this.container = container;
    }

    @Override
    protected Component create(final IHtmlElement<?, ?> e) {
        if (CreateUserWizardStep1Constants.control.equals(e.getWicketId())) {
            return new CreateUserWizardStepControlPanel(e.getWicketId(), new IModel<CreateUserWizardStepControl>() {
                @Override
                public CreateUserWizardStepControl getObject() {
                    final ICreateUserWizardStep model = (ICreateUserWizardStep) container.getDefaultModelObject();
                    return model.getControl();
                }
            });
        }
        return super.create(e);
    }

}
