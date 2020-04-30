import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUI extends JFrame {

    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();


    public GUI() {
        setSize(900, 600);
        setLayout(new GridLayout(1, 3));
        setVisible(true);

        Border border = BorderFactory.createLineBorder(Color.white, 1);

        JPanel left = new JPanel();
        JPanel middle = new JPanel();
        JPanel right = new JPanel();
        addJPanel(left, Color.gray, border, 600, 300);
        addJPanel(middle, Color.gray, border, 600, 300);
        addJPanel(right, Color.gray, border, 600, 300);


        left.setLayout(layout);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setLayout(new BorderLayout());
        jMenuBar.setPreferredSize(new Dimension(0, 50));

        JLabel Insomnia = new JLabel("  Insomnia");
        Insomnia.setFont(new Font("Arial", 14, 20));
        jMenuBar.add(Insomnia, BorderLayout.LINE_START);
        JMenu jMenu = new JMenu("▾");
        JMenuItem WorkSpaceSetting = new JMenuItem("WorkSpace Setting");
        jMenu.add(WorkSpaceSetting);
        jMenuBar.add(jMenu, BorderLayout.LINE_END);
        jMenuBar.setVisible(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        left.add(jMenuBar, gbc);

        JMenuBar jMenuBar1 = new JMenuBar();
        jMenuBar1.setPreferredSize(new Dimension(0, 30));
        jMenuBar1.setLayout(new BorderLayout());
        JMenu jMenu1 = new JMenu("No Environment ▾");
        jMenu1.setFont(new Font("Arial", 14, 14));
        jMenuBar1.add(jMenu1, BorderLayout.LINE_START);
        JButton jButton = new JButton("Cookies");
        jMenuBar1.add(jButton, BorderLayout.LINE_END);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        left.add(jMenuBar1, gbc);


        JMenuBar jMenuBar2 = new JMenuBar();
        jMenuBar2.setPreferredSize(new Dimension(0, 30));
        JTextArea jTextArea = new JTextArea("  ");
        jTextArea.setFont(new Font("Arial", 10, 10));
        jMenuBar2.add(jTextArea);
        gbc.gridy = 2;
        JMenu jMenu2 = new JMenu("⊕ ▾");
        jMenuBar2.add(jMenu2);
        left.add(jMenuBar2, gbc);


        JMenuBar jMenuBar3 = new JMenuBar();
        jMenuBar3.setPreferredSize(new Dimension(0, 30));
        jMenuBar3.setLayout(new BorderLayout());
        JLabel jLabel = new JLabel("  Get My Request");
        jMenuBar3.add(jLabel, BorderLayout.LINE_START);
        JMenu jMenu3 = new JMenu("▾");
        jMenuBar3.add(jMenu3, BorderLayout.LINE_END);
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        left.add(jMenuBar3, gbc);


        middle.setLayout(new GridBagLayout());
        JMenuBar URL = new JMenuBar();
        URL.setPreferredSize(new Dimension(0, 50));
        URL.setLayout(new BorderLayout());
        JTextArea urlField = new JTextArea();
        URL.add(urlField, BorderLayout.CENTER);
        JMenu methods = new JMenu(" Get  ▾");
        JMenuItem get = new JMenuItem("GET");
        JMenuItem post = new JMenuItem("POST");
        JMenuItem put = new JMenuItem("PUT");
        JMenuItem patch = new JMenuItem("PATCH");
        JMenuItem delete = new JMenuItem("DELETE");
        JMenuItem options = new JMenuItem("OPTIONS");
        JMenuItem head = new JMenuItem("HEAD");
        JMenuItem cm = new JMenuItem("Custom Method");
        methods.add(get);
        methods.add(post);
        methods.add(put);
        methods.add(patch);
        methods.add(delete);
        methods.add(options);
        methods.add(head);
        methods.add(cm);

        URL.add(methods, BorderLayout.LINE_START);
        JButton send = new JButton("Send");
        URL.add(send, BorderLayout.LINE_END);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        middle.add(URL, gbc);


        JTabbedPane tab = new JTabbedPane();
        JPanel body = new JPanel();
        tab.add("body", body);
        JPanel auth = new JPanel();
        tab.add("Auth", auth);
        JPanel query = new JPanel();
        tab.add("Query", query);
        JPanel header = new JPanel();
        tab.add("Header", header);
        JPanel docs = new JPanel();
        tab.add("Docs", docs);
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        middle.add(tab, gbc);



        right.setLayout(new GridBagLayout());
        JMenuBar data = new JMenuBar();
        data.setPreferredSize(new Dimension(0, 50));
        data.setLayout(new BorderLayout());
        JMenu time=new JMenu("14 Minutes Ago ▾");
        data.add(time,BorderLayout.LINE_END);
        JLabel error=new JLabel("Error  ");
        data.add(error,BorderLayout.LINE_START);
        JLabel tt=new JLabel("0 ms  0 B");
        data.add(tt,BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        right.add(data, gbc);

        JTabbedPane tab1 = new JTabbedPane();
        JPanel preview = new JPanel();
        tab1.add("Preview", preview);
        JPanel header2= new JPanel();
        tab1.add("Header", header2);
        JPanel cookies = new JPanel();
        tab1.add("Cookies", cookies);
        JPanel timeline = new JPanel();
        tab1.add("Timeline", timeline);
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        right.add(tab1, gbc);



        add(left);
        add(middle);
        add(right);
    }

    public void addJPanel(JPanel jPanel, Color color, Border border, int height, int weight) {
        jPanel.setBackground(color);
        jPanel.setBorder(border);
        jPanel.setSize(weight, height);
    }

}
