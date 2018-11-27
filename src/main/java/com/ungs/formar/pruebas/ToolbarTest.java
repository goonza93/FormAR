package com.ungs.formar.pruebas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

import com.ungs.formar.vista.util.Ventana;

public class ToolbarTest extends Ventana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FontNames[] = { "Serif", "SansSerif", "Courier" };

	protected Font fonts[];

	protected JMenuItem[] fontMenus;

	protected JCheckBoxMenuItem boldMenu = new JCheckBoxMenuItem("B");

	protected JCheckBoxMenuItem italicMenu = new JCheckBoxMenuItem("I");

	protected JToolBar toolBar;
  
	protected JEditorPane text;

	public ToolbarTest() {
		super("Toolbar");
		setSize(450, 350);

		popularFonts();
		
		createToolBar();

		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(wndCloser);

		updateMonitor();
		setVisible(true);
  }

	private void popularFonts() {
		fonts = new Font[FontNames.length];
	    for (int i = 0; i < FontNames.length; i++){
	    	fonts[i] = new Font(FontNames[i], Font.PLAIN, 12);
	    }
	}

protected void createToolBar() {
	
	
	toolBar = new JToolBar();
	/*
    JButton btn1 = toolBar.add(actionNew);
    btn1.setToolTipText("New text");
    JButton btn2 = toolBar.add(actionOpen);
    btn2.setToolTipText("Open text file");
    JButton btn3 = toolBar.add(actionSave);
    btn3.setToolTipText("Save text file");*/

    ActionListener fontListener = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		updateMonitor();
    	}
    };

    JMenu mFont = new JMenu("Font");
    mFont.setMnemonic('o');

    ButtonGroup group = new ButtonGroup();
    fontMenus = new JMenuItem[FontNames.length];
    for (int k = 0; k < FontNames.length; k++) {
    	int m = k + 1;
    	fontMenus[k] = new JRadioButtonMenuItem(m + " " + FontNames[k]);
    	boolean selected = (k == 0);
    	fontMenus[k].setSelected(selected);
    	fontMenus[k].setMnemonic('1' + k);
    	fontMenus[k].setFont(fonts[k]);
    	fontMenus[k].addActionListener(fontListener);
    	group.add(fontMenus[k]);
    	mFont.add(fontMenus[k]);
    }

    mFont.addSeparator();

    boldMenu.setMnemonic('b');
    Font fn = fonts[1].deriveFont(Font.BOLD);
    boldMenu.setFont(fn);
    boldMenu.setSelected(false);
    boldMenu.addActionListener(fontListener);
    mFont.add(boldMenu);

    italicMenu.setMnemonic('i');
    fn = fonts[1].deriveFont(Font.ITALIC);
    italicMenu.setFont(fn);
    italicMenu.setSelected(false);
    italicMenu.addActionListener(fontListener);
    mFont.add(italicMenu);

    toolBar.add(mFont);

    getContentPane().add(toolBar, BorderLayout.NORTH);
}

  protected void updateMonitor() {
    int index = -1;
    for (int k = 0; k < fontMenus.length; k++) {
    	if (fontMenus[k].isSelected()) {
    		index = k;
    		break;
    	}
    }
    if (index == -1)
      return;

    if (index == 2) // Courier
    {
      boldMenu.setSelected(false);
      boldMenu.setEnabled(false);
      italicMenu.setSelected(false);
      italicMenu.setEnabled(false);
    } else {
      boldMenu.setEnabled(true);
      italicMenu.setEnabled(true);
    }

    int style = Font.PLAIN;
    if (boldMenu.isSelected())
      style |= Font.BOLD;
    if (italicMenu.isSelected())
      style |= Font.ITALIC;
    @SuppressWarnings("unused")
	Font fn = fonts[index].deriveFont(style);
  }

  public static void main(String argv[]) {
    new ToolbarTest();
  }
}