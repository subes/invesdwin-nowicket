package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackHeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;
import de.invesdwin.nowicket.NoWicketProperties;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;

@NotThreadSafe
public class ModelDataTable extends DataTable<Object, String> {

    private final TableHtmlElement element;

    public ModelDataTable(final TableHtmlElement element, final long rowsPerPage) {
        super(element.getWicketId(), element.createWicketColumns(), new ModelSortableDataProvider(element), rowsPerPage);
        this.element = element;
        setOutputMarkupId(true);
        final AbstractToolbar headersToolbar = newHeadersToolbar();
        if (headersToolbar != null) {
            addTopToolbar(headersToolbar);
        }
        final NoRecordsToolbar noRecordsToolbar = newNoRecordsToolbar();
        if (noRecordsToolbar != null) {
            addBottomToolbar(noRecordsToolbar);
        }
        final NavigationToolbar navigationToolbar = newNavigationToolbar();
        if (navigationToolbar != null) {
            addBottomToolbar(navigationToolbar);
        }
    }

    public ModelDataTable(final TableHtmlElement element) {
        this(element, NoWicketProperties.DEFAULT_TABLE_ROWS_PER_PAGE);
    }

    public TableHtmlElement getElement() {
        return element;
    }

    public ISortableDataProvider<Object, String> getSortableDataProvider() {
        return (ISortableDataProvider<Object, String>) getDataProvider();
    }

    protected AbstractToolbar newHeadersToolbar() {
        return new AjaxFallbackHeadersToolbar<String>(this, (ISortableDataProvider<Object, String>) getDataProvider()) {
            @Override
            protected WebMarkupContainer newSortableHeader(final String headerId, final String property,
                    final ISortStateLocator<String> locator) {
                return ModelDataTable.this.newSortableHeader(getTable(), headerId, property, locator);
            }

        };
    }

    protected WebMarkupContainer newSortableHeader(final DataTable<?, ?> table, final String headerId,
            final String property, final ISortStateLocator<String> locator) {
        return new IconOrderByBorder<String>(table, headerId, property, locator);
    }

    protected NoRecordsToolbar newNoRecordsToolbar() {
        return new NoRecordsToolbar(this);
    }

    protected NavigationToolbar newNavigationToolbar() {
        return new NavigationToolbar(this) {
            @Override
            protected PagingNavigator newPagingNavigator(final String navigatorId, final DataTable<?, ?> table) {
                return new BootstrapAjaxPagingNavigator(navigatorId, table) {
                    @Override
                    protected void onComponentTag(final ComponentTag tag) {
                        //fix complaints about the tag being a div and not an ul
                        tag.setName("ul");
                        super.onComponentTag(tag);
                    }
                };
            }

            @Override
            protected WebComponent newNavigatorLabel(final String navigatorId, final DataTable<?, ?> table) {
                //hide label
                final WebComponent component = super.newNavigatorLabel(navigatorId, table);
                component.setVisible(false);
                return component;
            }
        };
    }

    @Override
    protected Item<Object> newRowItem(final String id, final int index, final IModel<Object> model) {
        return new OddEvenItem<Object>(id, index, model);
    }

}