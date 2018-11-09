package com.ungs.formar.vista.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class FormatoLimitado extends PlainDocument {

	private static final long serialVersionUID = 1L;
	private final int limit;

	public FormatoLimitado(int limit) {
		this.limit = limit;
	}
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offs, str, a);
		}
	}
}
