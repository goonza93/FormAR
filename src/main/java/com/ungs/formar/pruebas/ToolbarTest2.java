package com.ungs.formar.pruebas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.ungs.formar.vista.util.Ventana;

public class ToolbarTest2 extends Ventana {
  public JToolBar toolBar;
  public JEditorPane text;

  public ToolbarTest2() {
    super("Toolbar 2");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(200, 200);
    setVisible(true);
    
    text = new JEditorPane();
    text.setContentType("text/HTML");
	text.setEditorKit(new HTMLEditorKit());

    // Opcion bold
    SampleAction exampleAction = new SampleAction("B");
    JButton exampleButton = new JButton(exampleAction);
    
    toolBar = new JToolBar();
    toolBar.setBorder(new EtchedBorder());
    toolBar.add(exampleButton);
    add(toolBar, BorderLayout.NORTH);
    add(text);
  }

  class SampleAction extends AbstractAction {
	  public SampleAction(String text) {
		  super(text);
	  }
	  
	  public void actionPerformed(ActionEvent e) {
          int start = text.getSelectionStart();
          int end = text.getSelectionEnd();
          String txt = text.getSelectedText();
          if(end != start)
              try {
                  text.getDocument().remove(start, end-start);
                  HTMLEditorKit htmlkit = (HTMLEditorKit) text.getEditorKit();
                  htmlkit.insertHTML((HTMLDocument) text.getDocument(), start, "<b>"+txt+"</b>", 0, 0, HTML.Tag.B);
              } catch (Exception e1) {
                  e1.printStackTrace();
              }
	  }
  }

  public static void main(String s[]) {
    new ToolbarTest2();
  }
}