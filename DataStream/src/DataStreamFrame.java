import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;

public class DataStreamFrame extends JFrame {
    JPanel mainPnl, topPnl, centerPnl, bottomPnl;
    JTextArea fileTA, resultTA;
    JTextField inputTF;
    JScrollPane scrollPane1,scrollPane2;
    JButton clearBtn, quitBtn, submitBtn, browseBtn;

    public DataStreamFrame()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int height= screenSize.height;
        int width = screenSize.width;
        setSize(800,600);
        setLocation((width/4),height/10);
        setTitle("Data Streams");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
    }

    private void createGUI()
    {
        mainPnl = new JPanel();

        createTopPanel();
        createCenterPanel();
        createBottomPanel();

        mainPnl.setLayout(new BorderLayout());
        mainPnl.add(topPnl, BorderLayout.NORTH);
        mainPnl.add(centerPnl, BorderLayout.CENTER);
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        add(mainPnl);

    }

    private void createTopPanel()
    {
        topPnl = new JPanel();

        fileTA = new JTextArea(1,20);
        fileTA.setFont(new Font("Serif", Font.PLAIN,14));
        fileTA.setEditable(false);
        browseBtn = new JButton("Browse");
        browseBtn.setFont(new Font("Serif", Font.PLAIN, 12));
        browseBtn.addActionListener(e ->
        {
            FileMethod.fileBrowser(fileTA,inputTF,resultTA);
        });
        topPnl.add(fileTA);
        topPnl.add(browseBtn);
    }

    private void createCenterPanel()
    {
        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        inputTF = new JTextField();
        inputTF.setFont(new Font("Serif", Font.PLAIN, 16));

        resultTA = new JTextArea();
        resultTA.setFont(new Font("Serif", Font.PLAIN, 16));
        resultTA.setEditable(false);
        resultTA.setLineWrap(true);

        scrollPane1 = new JScrollPane(inputTF);
        scrollPane2 = new JScrollPane(resultTA);

        centerPnl.add(scrollPane1,BorderLayout.NORTH);
        centerPnl.add(scrollPane2, BorderLayout.CENTER);
    }

    private void createBottomPanel()
    {
        bottomPnl = new JPanel();

        bottomPnl.setLayout(new GridLayout(1,3));
        submitBtn = new JButton("Search");
        submitBtn.addActionListener(e ->
        {
            FileMethod.fileFilter(inputTF, FileMethod.fileBrowser(), resultTA);
        });

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(e -> {
            inputTF.setText("");
            resultTA.setText("");
        });

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {
            System.exit(0);
        });

        bottomPnl.add(submitBtn);
        bottomPnl.add(clearBtn);
        bottomPnl.add(quitBtn);

    }
}