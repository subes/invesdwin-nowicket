package de.invesdwin.nowicket.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IStyledColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.util.lang.Objects;

@NotThreadSafe
public class ReusingDelegateColumn<T, S> implements IStyledColumn<T, S> {

    private final List<PopulatedItem<T>> oldPopulatedItems = new ArrayList<PopulatedItem<T>>();
    private final List<PopulatedItem<T>> populatedItems = new ArrayList<PopulatedItem<T>>();

    private final IColumn<T, S> delegate;
    private Integer prevRequestCycleIdentity;

    public ReusingDelegateColumn(final IColumn<T, S> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
            final IModel<T> rowModel) {
        final int newRequestCycleIdentity = System.identityHashCode(RequestCycle.get());
        if (prevRequestCycleIdentity == null || prevRequestCycleIdentity != newRequestCycleIdentity) {
            oldPopulatedItems.clear();
            oldPopulatedItems.addAll(populatedItems);
            populatedItems.clear();
            prevRequestCycleIdentity = newRequestCycleIdentity;
        }

        //fill from old items
        final Object newModelObject = rowModel.getObject();
        for (final PopulatedItem<T> oldPopulatedItem : oldPopulatedItems) {
            final Object oldModelObject = oldPopulatedItem.getModel().getObject();
            if (Objects.equals(oldModelObject, newModelObject)) {
                //update model object to detect changes
                oldPopulatedItem.getModel().setDelegate(rowModel);
                cellItem.add(oldPopulatedItem.getComponent());
                populatedItems.add(oldPopulatedItem);
                return;
            }
        }

        //create new item
        final DelegateModel<T> delegateRowModel = new DelegateModel<>(rowModel);
        delegate.populateItem(cellItem, componentId, delegateRowModel);
        final Component component = cellItem.get(componentId);
        populatedItems.add(new PopulatedItem<T>(component, delegateRowModel));
    }

    @Override
    public void detach() {
        delegate.detach();
    }

    @Override
    public Component getHeader(final String componentId) {
        return delegate.getHeader(componentId);
    }

    @Override
    public S getSortProperty() {
        return delegate.getSortProperty();
    }

    @Override
    public boolean isSortable() {
        return delegate.isSortable();
    }

    public IColumn<T, S> getDelegate() {
        return delegate;
    }

    @Override
    public String getCssClass() {
        if (delegate instanceof IStyledColumn) {
            final IStyledColumn<?, ?> cDelegate = (IStyledColumn<?, ?>) delegate;
            return cDelegate.getCssClass();
        } else {
            return null;
        }
    }

    private static final class PopulatedItem<_T> implements Serializable {
        private final Component component;
        private final DelegateModel<_T> model;

        private PopulatedItem(final Component component, final DelegateModel<_T> model) {
            this.component = component;
            this.model = model;
        }

        public Component getComponent() {
            return component;
        }

        public DelegateModel<_T> getModel() {
            return model;
        }
    }

    public static <_T, _S> ReusingDelegateColumn<_T, _S> maybeWrap(final IColumn<_T, _S> column) {
        if (column instanceof ReusingDelegateColumn) {
            return (ReusingDelegateColumn<_T, _S>) column;
        } else {
            return new ReusingDelegateColumn<>(column);
        }
    }

}
