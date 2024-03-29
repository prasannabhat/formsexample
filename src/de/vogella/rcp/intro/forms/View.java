package de.vogella.rcp.intro.forms;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "de.vogella.rcp.intro.forms.view";

	private TableViewer viewer;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent instanceof Object[]) {
				return (Object[]) parent;
			}
	        return new Object[0];
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
	    ScrolledForm form = toolkit.createScrolledForm(parent);
	    form.setText("Eclipse Forms API Example");

	    // Lets make a layout for the first section of the screen
	    GridLayout layout = new GridLayout();
	    layout.numColumns = 1;
	    layout.marginWidth = 2;
	    layout.marginHeight = 2;
	    // Creating the Screen
	    Section section = toolkit.createSection(parent, Section.DESCRIPTION
	        | Section.TITLE_BAR);
	    section.setText("Section 1 for demonstration"); //$NON-NLS-1$
	    section.setDescription("This demonstrates the usage of section");
	    // Composite for storing the data
	    Composite client = toolkit.createComposite(section, SWT.WRAP);
	    layout = new GridLayout();
	    layout.numColumns = 2;
	    layout.marginWidth = 2;
	    layout.marginHeight = 2;
	    client.setLayout(layout);
	    Table t = toolkit.createTable(client, SWT.NONE);
	    GridData gd = new GridData(GridData.FILL_BOTH);
	    gd.heightHint = 20;
	    gd.widthHint = 100;
	    t.setLayoutData(gd);
	    toolkit.paintBordersFor(client);
	    Button b = toolkit.createButton(client, "Do something", SWT.PUSH); //$NON-NLS-1$
	    gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
	    b.setLayoutData(gd);
	    section.setClient(client);
	    viewer = new TableViewer(t);

	    viewer.setContentProvider(new ArrayContentProvider());

	    TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
	    viewerColumn.getColumn().setWidth(100);
	    viewerColumn.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	        return element.toString();
	      };

	      public Image getImage(Object element) {
	        return PlatformUI.getWorkbench().getSharedImages()
	            .getImage(ISharedImages.IMG_OBJ_ELEMENT);
	      };
	    });
	    viewer.setInput(getViewSite());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}