package com.subgraph.vega.ui.http.builder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.subgraph.vega.api.http.requests.IHttpRequestBuilder;

/**
 * Manages RequestAddressEditor and RequestMessageEditor widgets to edit a request.
 */
public class RequestEditor extends Composite implements IHttpBuilderPart {
	private RequestAddressEditor requestAddressEditor;
	private RequestMessageEditor messageEditor;

	public RequestEditor(Composite parent, final IHttpRequestBuilder requestBuilder) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(1, false));
		requestAddressEditor = new RequestAddressEditor(this, requestBuilder);
		requestAddressEditor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		messageEditor = new RequestMessageEditor(this, requestBuilder);
		messageEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}
	
	@Override
	public Control getControl() {
		return this;
	}
	
	@Override
	public void setEditable(boolean editable) {
		requestAddressEditor.setEditable(editable);
		messageEditor.setEditable(editable);
	}

	@Override
	public void refresh() {
		requestAddressEditor.refresh();
		messageEditor.refresh();
	}

	@Override
	public void processContents() {
		requestAddressEditor.processContents();
		messageEditor.processContents();
	}

}
