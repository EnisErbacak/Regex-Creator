import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester 
{
	private Highlighter lighter;
	Highlighter.HighlightPainter painter;
	
	public void tester(String regexInput,JTextArea textField)
	{
		Pattern pattern=Pattern.compile(regexInput);
		Matcher matcher=pattern.matcher(textField.getText());
		String lighted=textField.getText();
		
		lighter=textField.getHighlighter();
		painter=new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
		
		int first=0;
		int last=lighted.length();
		
		
	//	lighter.removeAllHighlights();
		if(matcher.find()==true)
		{
			lighter.removeAllHighlights();
			matcher.reset();
		while(matcher.find()==true)
		{
							
				try
				{
					
					System.out.println("MATCHED__"+matcher.group());
				//	System.out.println(matcher.group()+"__"+matcher.end());
				lighter.addHighlight(matcher.start(),matcher.end(), painter);
			//	textField.updateUI();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}			
		
		}
	}
	}


