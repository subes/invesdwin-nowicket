package de.invesdwin.nowicket.examples.guide.pages.wicket.forminput;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class LinePanel extends Panel {

    public LinePanel(final String id, final IModel<Line> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
