package Test;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class StackWindow extends JFrame implements Thread.UncaughtExceptionHandler
{
    private JTextArea textArea;

    public StackWindow(String title, final int width, final int height)
    {
        super(title);
        setSize(width, height);
        textArea = new JTextArea();
        JScrollPane pane = new JScrollPane(textArea);
        textArea.setEditable(false);
        getContentPane().add(pane);
    }

    public static void main(String[] args) {
        new StackWindow("title", 500, 1000).addStackInfo(new Exception());

    }

    public void uncaughtException(Thread t, Throwable e)
    {
        addStackInfo(e);
    }

    public void addStackInfo(final Throwable t)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() {
                // Bring window to foreground
                setVisible(true);
                toFront();
                // Convert stack dump to string
                StringWriter sw = new StringWriter();
                PrintWriter out = new PrintWriter(sw);
                t.printStackTrace(out);
                // Add string to end of text area
                textArea.append(sw.toString());
            }
        });
    }
}

