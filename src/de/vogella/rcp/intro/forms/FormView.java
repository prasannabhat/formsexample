package de.vogella.rcp.intro.forms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

public class FormView extends ViewPart {
	private FormToolkit toolkit;
	 private ScrolledForm form;
	 
	 public FormView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		  form = toolkit.createScrolledForm(parent);
		  form.setText("Hello, Eclipse Forms");
		  
		  GridLayout layout = new GridLayout();
		  layout.numColumns = 2;
		  form.getBody().setLayout(layout);
		  Hyperlink link = toolkit.createHyperlink(form.getBody(), 
		    "Click here.", SWT.WRAP);
		  link.addHyperlinkListener(new HyperlinkAdapter() {
		   public void linkActivated(HyperlinkEvent e) {
		    System.out.println("Link activated!");
		   }
		  });
		  GridData gd = new GridData();
		  gd.horizontalSpan = 2;
		  link.setLayoutData(gd);
		  
//		  Label label = new Label(form.getBody(), SWT.NULL);
//		  label.setText("Text field label:");
//		  Text text = new Text(form.getBody(), SWT.BORDER);
//		  text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		  Button button = new Button(form.getBody(), SWT.CHECK);
//		  button.setText("An example of a checkbox in a form");
//		  gd = new GridData();
//		  gd.horizontalSpan = 2;
//		  button.setLayoutData(gd);
		  
		  Label label = toolkit.createLabel(form.getBody(), "Text field label:");
		  Text text = toolkit.createText(form.getBody(), "");
		  text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		  Button button = toolkit.createButton(form.getBody(), "A checkbox in a form", SWT.CHECK);
		  gd = new GridData();
		  gd.horizontalSpan = 2;
		  button.setLayoutData(gd);

	}

	@Override
	public void setFocus() {
		form.setFocus();

	}
	
	@Override
	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}

}
