package net.sf.launch4j;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;

public class SwingLogbackUtils {

	private static TextAreaAppender appender = null;

	public static synchronized void setTextArea(JTextArea textArea) {
		if (appender == null) {
			initAppender();
		}

		appender.setTextArea(textArea);
	}

	private static void initAppender() {
		appender = new TextAreaAppender();

		try {
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
			rootLogger.addAppender(appender);
			appender.setContext(loggerContext);
			appender.start();
		} catch (Exception e) {
		}
	}

	public static class TextAreaAppender extends AppenderBase<ILoggingEvent> {

		@Setter
		@Getter
		private JTextArea textArea = null;

		public TextAreaAppender() {
		}

		@Override
		protected void append(ILoggingEvent eventObject) {
			if (textArea == null) {
				return;
			}

			String line = eventObject.getFormattedMessage();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					textArea.append(line + "\n");
				}
			});

		}

	}
}
