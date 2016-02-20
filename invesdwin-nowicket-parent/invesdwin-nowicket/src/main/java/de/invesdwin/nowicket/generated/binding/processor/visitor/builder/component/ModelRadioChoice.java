package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.RadioChoice;

import de.invesdwin.nowicket.generated.binding.processor.element.AChoiceHtmlElement;

@NotThreadSafe
public class ModelRadioChoice extends RadioChoice<Object> {

    public ModelRadioChoice(final AChoiceHtmlElement<?> element) {
        super(element.getWicketId(), element.getModel(), element.getChoiceModel(), element.getChoiceRenderer());
        //fix layout for bootstrap
        setPrefix("<label class=\"" + getCheckboxClass() + "\">");
        setSuffix("</label>");
    }

    /**
     * Override this to change to checkbox-inline if needed
     */
    protected String getCheckboxClass() {
        return "checkbox";
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().clear();
        tag.getAttributes().put("class", getCheckboxClass());
        super.onComponentTag(tag);
    }

}
