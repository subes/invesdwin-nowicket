package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class GridColumnBorder extends Border {

    private final GridColumnHtmlElement element;
    private final Label help;

    public GridColumnBorder(final GridColumnHtmlElement element) {
        super(element.getWicketId());
        this.element = element;

        final AttributeModifier hasError = AttributeModifier.append("class", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                final Component component = element.getContext()
                        .getComponentRegistry()
                        .getComponent(element.getModelWicketId());
                final FormComponent<?> formComponent = Components.asFormComponent(component);
                if (formComponent != null) {
                    if (!formComponent.isValid()) {
                        return "has-error";
                    }
                }
                return null;
            }
        });
        add(hasError);
        this.help = new Label("help", Model.of()); //needs to escape markup or modals do not close
        addToBorder(help);
    }

    @Override
    protected void onConfigure() {
        final Component component = element.getContext()
                .getComponentRegistry()
                .getComponent(element.getModelWicketId());
        final FormComponent<?> formComponent = Components.asFormComponent(component);
        if (formComponent != null) {
            final StringBuilder sb = new StringBuilder();
            if (!formComponent.isValid()) {
                boolean firstMessage = true;
                for (final FeedbackMessage message : formComponent.getFeedbackMessages()) {
                    if (!firstMessage) {
                        sb.append("<br>");
                    }
                    firstMessage = false;
                    sb.append(message.getMessage());
                }
            }
            help.setDefaultModelObject(sb);
            help.setVisible(sb.length() > 0);
        }
    }

    @Override
    public boolean isVisible() {
        return element.getContext().getComponentRegistry().getComponent(element.getModelWicketId()).isVisible();
    }

}