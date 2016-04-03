package de.invesdwin.nowicket.generated.binding.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Objects;

@NotThreadSafe
public abstract class AHtmlElement<E extends IModelElement<?>, M> implements IHtmlElement<E, M> {

    private final HtmlContext context;
    private transient Element element;
    private final String wicketId;

    private boolean firstAccept = true;

    public AHtmlElement(final HtmlContext context, final Element element)
        this.context = context;
        this.element = element;
        this.wicketId = getElement().attr(ATTR_WICKET_ID);
    }

    public AHtmlElement(final HtmlContext context, final String wicketId) {
        this.context = context;
        this.element = null;
        this.wicketId = wicketId;
    }

    @Override
    public final HtmlContext getContext() {
        return context;
    }

    @Override
    public final String getWicketId() {
        return wicketId;
    }

    @Override
    public Element getElement() {
        Assertions.assertThat(element)
                .as("%s is only available until serialization occurs!", Element.class)
                .isNotNull();
        return element;
    }

    @Override
    public final boolean accept(final IHtmlVisitor... visitors) {
        try {
            if (shouldBeAddedToElementRegistry()) {
                getContext().getElementRegistry().addElement(this);
            }
            if (firstAccept) {
                onFirstAccept();
                firstAccept = false;
            }
            for (final IHtmlVisitor visitor : visitors) {
                innerAccept(visitor);
            }
            return true;
        } catch (final Throwable t) {
            throw new RuntimeException("On " + getClass().getSimpleName() + ": " + getWicketId(), t);
        }
    }

    protected boolean shouldBeAddedToElementRegistry() {
        return getModelElement().getBeanPathElement().shouldBeAddedToElementRegistry();
    }

    /**
     * Can be overriden to do additional things on first accept.
     */
    protected void onFirstAccept() {}

    protected abstract void innerAccept(final IHtmlVisitor visitor);

    @Override
    public final String toString() {
        return Objects.toStringHelper(this).add("wicketId", getWicketId()).toString();
    }

}
