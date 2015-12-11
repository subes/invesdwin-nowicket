package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.link;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.AnchorHtmlElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModelExternalLink extends ExternalLink {

    private boolean disableDefaultDisabledStyle;

    public ModelExternalLink(final String id, final IModel<String> model, final IModel<String> titleModel) {
        super(id, model, titleModel);
    }

    public ModelExternalLink(final AnchorHtmlElement element) {
        this(element.getWicketId(), element.getUrlModel(), element.getTitleModel());
    }

    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        if (isBootstrapButtonStyle(openTag)) {
            disableDefaultDisabledStyle = true;
        }
        super.onComponentTagBody(markupStream, openTag);
        if (isBootstrapButtonStyle(openTag)) {
            disableDefaultDisabledStyle = false;
        }
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        ModelResourceLink.maybeSetTargetBlank(tag);
        super.onComponentTag(tag);
    }

    @Override
    public String getBeforeDisabledLink() {
        if (disableDefaultDisabledStyle) {
            return null;
        } else {
            return super.getBeforeDisabledLink();
        }
    }

    @Override
    public String getAfterDisabledLink() {
        if (disableDefaultDisabledStyle) {
            return null;
        } else {
            return super.getAfterDisabledLink();
        }
    }

    @Override
    protected void disableLink(final ComponentTag tag) {
        if (isBootstrapButtonStyle(tag)) {
            tag.setName("button");
            tag.put("type", "button");
            tag.append("class", "btn-disabled", " ");
        }
        super.disableLink(tag);
        tag.setName("a"); //for bootstrap it should be a and not span
    }

    protected boolean isBootstrapButtonStyle(final ComponentTag tag) {
        return Strings.contains(tag.getAttribute("class"), "btn");
    }

}
