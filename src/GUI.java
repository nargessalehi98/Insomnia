import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    JFrame window = new JFrame();
    JSplitPane jSplitPane2;
    JSplitPane jSplitPane1;
    JMenuItem mOptions;
    JMenuItem exit;
    JMenuItem tSidebar;
    JMenuItem tFullScreen;
    JMenuItem iHelp;
    JMenuItem about;
    JButton send;
    JButton save;
    JCheckBox followRedirect;
    JCheckBox systemTray;
    boolean checkSystemTray = false;
    JTextArea newHeader;
    JTextArea newValue;
    JPanel header;
    static int headerCounter = 1;
    JTabbedPane tab;
    static boolean fullScreen = false;
    static boolean toggleSidebar = false;
    static int runOnce;

    public GUI() {
        //creat a layout for panels
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        //first size of frame
        window.setSize(1200, 600);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        windowListener windowListener = new windowListener();
        window.addWindowListener(windowListener);
        //creat a new border
        Border border = BorderFactory.createLineBorder(Color.white, 1);
        //creat panels
        JPanel left = new JPanel();
        JPanel middle = new JPanel();
        JPanel right = new JPanel();
        addJPanel(left, Color.white, border, 600, 400);
        addJPanel(middle, Color.white, border, 600, 400);
        addJPanel(right, Color.white, border, 600, 400);


        //creat and design top j menu bar
        topMenuActionListener topMenuHandler = new topMenuActionListener();
        JMenuBar topJMenu = new JMenuBar();
        topJMenu.setPreferredSize(new Dimension(0, 25));
        window.add(topJMenu, BorderLayout.PAGE_START);
        JMenu application = new JMenu("Application");
        application.setMnemonic('a');
        mOptions = new JMenuItem("Options");
        mOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.SHIFT_MASK));
        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.SHIFT_MASK));
        exit.addActionListener(topMenuHandler);
        mOptions.addActionListener(topMenuHandler);
        application.add(mOptions);
        application.add(exit);
        JMenu view = new JMenu("View");
        view.setMnemonic('v');
        tSidebar = new JMenuItem("Toggle Sidebar");
        tSidebar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
        tFullScreen = new JMenuItem("Toggle Full Screen");
        tFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.SHIFT_MASK));
        tSidebar.addActionListener(topMenuHandler);
        tFullScreen.addActionListener(topMenuHandler);
        view.add(tSidebar);
        view.add(tFullScreen);
        JMenu help = new JMenu("Help");
        help.setMnemonic('h');
        iHelp = new JMenuItem("Help");
        iHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.SHIFT_MASK));
        about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.SHIFT_MASK));
        iHelp.addActionListener(topMenuHandler);
        about.addActionListener(topMenuHandler);
        help.add(iHelp);
        help.add(about);
        topJMenu.add(application);
        topJMenu.add(view);
        topJMenu.add(help);


        //designing left panel
        left.setLayout(layout);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setLayout(new BorderLayout());
        jMenuBar.setPreferredSize(new Dimension(0, 50));
        //creat and adding component
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
        gbc.anchor = GridBagConstraints.PAGE_START;
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
        JMenuItem newFolder = new JMenuItem("New Folder");
        JMenuItem newRequest = new JMenuItem("New Request");
        jMenu2.add(newFolder);
        jMenu2.add(newRequest);
        left.add(jMenuBar2, gbc);


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(0, 1));
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("My Folder");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Get My Request");
        node1.add(node2);
        JTree jTree = new JTree(node1);
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        jPanel.add(jTree);
        jTree.setCellRenderer(getDefaultTreeCellRenderer());
        jTree.setCellRenderer(getDefaultTreeCellRenderer());
        jTree.setCellRenderer(getDefaultTreeCellRenderer());
        jTree.addMouseListener(getMouseListener(jTree));
        jTree.addFocusListener(getFocusListener(jTree));
        left.add(jPanel, gbc);
        //****************************************************************************
        //middle
        middle.setLayout(new GridBagLayout());
        JMenuBar URL = new JMenuBar();
        URL.setPreferredSize(new Dimension(0, 50));
        JTextField urlField = new JTextField();
        urlField.setPreferredSize(new Dimension(0, 20));
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
        ButtonListener buttonListener = new ButtonListener();
        URL.add(methods, BorderLayout.LINE_START);
        URL.add(urlField, BorderLayout.CENTER);
        send = new JButton("Send");
        URL.add(send, BorderLayout.LINE_END);
        save = new JButton("Save");
        save.addActionListener(buttonListener);
        URL.add(save);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        middle.add(URL, gbc);

        //************************************************************************
        tab = new JTabbedPane();
        JPanel body = new JPanel();
        tab.add("body", body);
        JComboBox massageBodyType = new JComboBox();
        massageBodyType.addItem("Form Data");
        massageBodyType.addItem("JSON");
        massageBodyType.addItem("Binary Data");
        body.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        body.add(massageBodyType, gbc);
        JPanel auth = new JPanel();
        tab.add("Auth", auth);
        JPanel query = new JPanel();
        tab.add("Query", query);

        header = new JPanel();
        header.setLayout(null);
        mouseClicker clicker = new mouseClicker();
        newHeader = new JTextArea("Add New Header");
        newHeader.setBackground(Color.lightGray);
        newHeader.addMouseListener(clicker);
        tab.add("Header", header);
        newValue = new JTextArea("Add New Value");
        newValue.addMouseListener(clicker);
        newValue.setBackground(Color.lightGray);
        newHeader.setSize(120, 30);
        newValue.setSize(120, 30);
        newValue.setLocation(120, 0);
        header.add(newValue);
        header.add(newHeader);

        JPanel docs = new JPanel();
        tab.add("Docs", docs);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        middle.add(tab, gbc);


        //************************************************************************
        //right
        right.setLayout(new GridBagLayout());
        JMenuBar data = new JMenuBar();
        data.setPreferredSize(new Dimension(0, 50));
        data.setLayout(new BorderLayout());
        JMenu time = new JMenu("14 Minutes Ago ▾");
        data.add(time, BorderLayout.LINE_END);
        JLabel error = new JLabel("Error  ");
        data.add(error, BorderLayout.LINE_START);
        JLabel tt = new JLabel("0 ms  0 B");
        data.add(tt, BorderLayout.CENTER);
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
        preview.setLayout(new GridBagLayout());
        JTextArea massageBody = new JTextArea();
        massageBody.setBackground(Color.lightGray);
        tab1.add("Preview", preview);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Raw");
        comboBox.addItem("Visual Preview");
        comboBox.addItem("JSON");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        preview.add(comboBox, gbc);
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        preview.add(massageBody, gbc);

        JPanel header2 = new JPanel();
        header2.setLayout(new GridBagLayout());
        tab1.add("Header", header2);
        JLabel name = new JLabel("Name");
        JLabel value = new JLabel("value");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        header2.add(name, gbc);
        gbc.gridx = 1;
        header2.add(value, gbc);
        JButton copyToClipboard = new JButton("Copy to Clipboard");
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        header2.add(copyToClipboard, gbc);

        JPanel cookies = new JPanel();
        tab1.add("Cookies", cookies);
        JPanel timeline = new JPanel();
        tab1.add("Timeline", timeline);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        right.add(tab1, gbc);

        jSplitPane2 = new JSplitPane(SwingConstants.VERTICAL, middle, right);
        jSplitPane2.setDividerLocation(400);
        jSplitPane2.setDividerSize(2);
        jSplitPane1 = new JSplitPane(SwingConstants.VERTICAL, left, jSplitPane2);
        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setDividerSize(2);
        window.add(jSplitPane1);
    }
    //end of GUI

    public void addJPanel(JPanel jPanel, Color color, Border border, int height, int weight) {
        jPanel.setBackground(color);
        jPanel.setBorder(border);
        jPanel.setSize(weight, height);
    }

    private static DefaultTreeCellRenderer getDefaultTreeCellRenderer() {
        DefaultTreeCellRenderer defaultTreeCellRenderer = new DefaultTreeCellRenderer();
        defaultTreeCellRenderer.setBackgroundSelectionColor(Color.blue);
        defaultTreeCellRenderer.setBackgroundNonSelectionColor(Color.white);
        defaultTreeCellRenderer.setBackground(Color.white);
        defaultTreeCellRenderer.setForeground(Color.white);
        return defaultTreeCellRenderer;
    }

    private static FocusListener getFocusListener(JTree tree) {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("focus lost");
                tree.clearSelection();
            }
        };
    }

    private static MouseListener getMouseListener(JTree tree) {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("mouse clicked");
                if (tree.getRowForLocation(e.getX(), e.getY()) == -1) {
                    System.out.println("clicked outside a specific cell");
                    tree.clearSelection();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    /**
     * class perform listener for top menu
     */
    private class topMenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == iHelp) {

            }
            if (e.getSource().equals(exit)) {
                System.exit(0);
            }
            if (e.getSource().equals(mOptions)) {
                JFrame smallFrame = new JFrame();
                smallFrame.setLayout(new GridLayout(3, 1));
                smallFrame.setSize(300, 200);
                smallFrame.setLocation(120, 120);
                smallFrame.setVisible(true);
                followRedirect = new JCheckBox("Follow redirect");
                systemTray = new JCheckBox("System Tray");
                itemListener listener = new itemListener();
                systemTray.addItemListener(listener);
                followRedirect.addItemListener(listener);
                smallFrame.add(systemTray);
                smallFrame.add(followRedirect);
            }

            if (e.getSource().equals(tFullScreen)) {
                if (!fullScreen) {
                    window.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    window.setVisible(true);
                    fullScreen = true;
                } else {
                    window.setSize(1200, 600);
                    fullScreen = false;
                }
            }
            if (e.getSource().equals(tSidebar)) {
                if (!toggleSidebar) {
                    window.remove(jSplitPane1);
                    window.add(jSplitPane2);
                    window.setVisible(true);
                    toggleSidebar = true;
                } else {
                    jSplitPane1.setRightComponent(jSplitPane2);
                    jSplitPane1.setDividerLocation(400);
                    window.add(jSplitPane1);
                    window.setVisible(true);
                    toggleSidebar = false;
                }

            }
            if (e.getSource().equals(about)) {
                JFrame smallFrame = new JFrame();
                smallFrame.setSize(300, 150);
                smallFrame.setLocation(120, 120);
                smallFrame.setVisible(true);
                smallFrame.setLayout(new GridLayout(4, 21));
                JLabel note = new JLabel(" This program was developed by Narges Salehi");
                JLabel date = new JLabel(" Date:May 2020");
                JLabel email = new JLabel(" Email:nargessalehi98@yahoo.com");
                JLabel id = new JLabel(" Student ID:9628055");
                smallFrame.add(note);
                smallFrame.add(date);
                smallFrame.add(email);
                smallFrame.add(id);
            }
            if (e.getSource().equals(iHelp)) {
                JFrame smallFrame = new JFrame();
                smallFrame.setSize(100, 100);
                smallFrame.setLocation(120, 120);
                smallFrame.setVisible(true);
                JLabel loading = new JLabel(" Loading...");
                smallFrame.add(loading);
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(save)) {
                JFrame smallFrame = new JFrame("Save Request");
                smallFrame.setSize(300, 300);
                smallFrame.setLocation(120, 120);
                smallFrame.setVisible(true);
                smallFrame.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridy = 0;
                gbc.gridx = 0;
                gbc.weightx = 1;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.NORTHWEST;
                JLabel info1 = new JLabel(" Requests in Insomnia are saved in groups.");
                smallFrame.add(info1, gbc);
                JLabel name = new JLabel(" Request Name :");
                gbc.gridy = 1;
                gbc.gridx = 0;
                gbc.weightx = 1;
                gbc.weighty = 1;
                smallFrame.add(name, gbc);
                JTextArea requestName = new JTextArea();
                gbc.gridy = 2;
                gbc.gridx = 0;
                gbc.weightx = 1;
                gbc.weighty = 1;
                smallFrame.add(requestName, gbc);
                JLabel info2 = new JLabel(" Select a group of requests or creat one :");
                gbc.gridy = 3;
                gbc.gridx = 0;
                gbc.weightx = 1;
                gbc.weighty = 1;
                smallFrame.add(info2, gbc);
                JComboBox groups = new JComboBox();
                gbc.gridy = 4;
                gbc.gridx = 0;
                gbc.weightx = 1;
                gbc.weighty = 1;
                smallFrame.add(groups, gbc);
                JButton creat = new JButton("Creat +");
                gbc.gridy = 5;
                gbc.gridx = 0;
                gbc.weightx = 1;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.NONE;
                gbc.anchor = GridBagConstraints.SOUTHEAST;
                smallFrame.add(creat, gbc);
            }
        }
    }

    private class itemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getItem() == followRedirect) {
            }
            if (e.getItem() == systemTray) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    checkSystemTray = true;
                if (e.getStateChange() == ItemEvent.DESELECTED)
                    checkSystemTray = false;

            }
        }
    }

    private class mouseClicker extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            int temp = headerCounter * 30;
            JTextArea tempValue = new JTextArea();
            tempValue.setBackground(Color.lightGray);
            JTextArea tempName = new JTextArea();
            tempName.setBackground(Color.lightGray);
            JCheckBox tempActive = new JCheckBox();
            tempActive.addActionListener(e12 -> {
                if (tempName.isEditable() || tempValue.isEditable()) {
                    tempName.setEditable(false);
                    tempValue.setEditable(false);
                } else {
                    tempName.setEditable(true);
                    tempValue.setEditable(true);
                }
            });
            JButton tempTrash = new JButton("✕");
            tempTrash.addActionListener(e1 -> {
                if (e1.getSource().equals(tempTrash)) {
                    header.remove(tempName);
                    header.remove(tempValue);
                    header.remove(tempActive);
                    header.setVisible(true);
                    header.remove(tempTrash);
                    header.repaint();

                }
            });
            tempValue.setSize(120, 30);
            tempValue.setLocation(120, temp);
            tempName.setSize(120, 30);
            tempName.setLocation(0, temp);
            tempActive.setSize(30, 30);
            tempActive.setLocation(240, temp);
            tempTrash.setSize(40, 30);
            tempTrash.setLocation(260, temp);
            header.add(tempName);
            header.add(tempValue);
            header.add(tempActive);
            header.add(tempTrash);
            headerCounter++;

        }
    }

    private class windowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            if (runOnce == 0 && checkSystemTray) {
                setSystemTray();
                runOnce++;
            }
        }
    }

    public void setSystemTray() {
        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        Image icon = Toolkit.getDefaultToolkit().getImage("insomnia.png");
        final TrayIcon trayIcon = new TrayIcon(icon, "Tray", popup);
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("Insomnia");
        aboutItem.addActionListener(e -> {
            if (e.getSource().equals(aboutItem))
                window.setVisible(true);
        });
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        Menu displayMenu = new Menu("Display");
        MenuItem errorItem = new MenuItem("Error");
        MenuItem warningItem = new MenuItem("Warning");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> {
            if (e.getSource().equals(exitItem)) {
                System.exit(0);
            }
        });

        // Add components to pop-up menu
        popup.add(aboutItem);
//        popup.addSeparator();
//        popup.add(cb1);
//        popup.add(cb2);
//        popup.addSeparator();
//        popup.add(displayMenu);
//        displayMenu.add(errorItem);
//        displayMenu.add(warningItem);
//        displayMenu.add(infoItem);
//        displayMenu.add(noneItem);
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }

    }
}

