package net.sf.launch4j;

import javax.swing.JTextArea;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SwingLogUtils {

	public static void setTextArea(JTextArea textArea) {
		SwingLogbackUtils.setTextArea(textArea);

	}

}
