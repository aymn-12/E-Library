import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

class ELibrarySystem extends JFrame {

    // Modern Color Palette
    private static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private static final Color PRIMARY_DARK = new Color(29, 78, 216);
    private static final Color PRIMARY_LIGHT = new Color(59, 130, 246);
    private static final Color ACCENT_COLOR = new Color(139, 92, 246);
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
    private static final Color WARNING_COLOR = new Color(251, 146, 60);

    private static final Color BACKGROUND = new Color(248, 250, 252);
    private static final Color CARD_BG = Color.WHITE;
    private static final Color TEXT_PRIMARY = new Color(15, 23, 42);
    private static final Color TEXT_SECONDARY = new Color(100, 116, 139);
    private static final Color BORDER_COLOR = new Color(226, 232, 240);
    private static final Color HOVER_BG = new Color(241, 245, 249);

    // Fonts
    private static final Font HEADER_FONT;
    private static final Font TITLE_FONT;
    private static final Font BODY_FONT;
    private static final Font SMALL_FONT;
    private static final Font BUTTON_FONT;

    static {
        String fontName = "Segoe UI";
        Font testFont = new Font(fontName, Font.PLAIN, 12);
        if (!testFont.getFamily().equals(fontName)) {
            fontName = "Arial";
        }
        HEADER_FONT = new Font(fontName, Font.BOLD, 28);
        TITLE_FONT = new Font(fontName, Font.BOLD, 20);
        BODY_FONT = new Font(fontName, Font.PLAIN, 14);
        SMALL_FONT = new Font(fontName, Font.PLAIN, 12);
        BUTTON_FONT = new Font(fontName, Font.BOLD, 14);
    }

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // متغير عام للوحة المكتبة لتحديثها لاحقاً
    private JPanel libraryListPanel;

    private List<Book> allBooks;
    private List<Book> myLibrary;
    private Book currentBook;

    private String userName = "John Doe";
    private String studentId = "2024-CS-001";
    private String email = "john.doe@university.edu";
    private String university = "Tech University";
    private int booksRead = 12;
    private int booksDownloaded = 8;
    private int hoursRead = 45;

    static class Book {
        String title, author, category, year, language, description, imageFile;
        int progress, totalPages, currentPage;
        Color themeColor;

        Book(String title, String author, String category, Color themeColor, String imageFile) {
            this.title = title;
            this.author = author;
            this.category = category;
            this.themeColor = themeColor;
            this.imageFile = imageFile;
            this.year = "2023";
            this.language = "English";
            this.description = "An essential guide covering comprehensive topics in " + category + " with practical examples and in-depth explanations.";
            this.progress = 0;
            this.totalPages = 15;
            this.currentPage = 1;
        }
    }

    public ELibrarySystem() {
        initializeData();

        setTitle("E-Library System");
        setSize(450, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(BACKGROUND);

        mainPanel.add(createLoginScreen(), "LOGIN");
        mainPanel.add(createHomeScreen(), "HOME");
        mainPanel.add(createBookDetailsScreen(), "DETAILS");
        mainPanel.add(createReadingScreen(), "READING");
        mainPanel.add(createLibraryScreen(), "LIBRARY");
        mainPanel.add(createProfileScreen(), "PROFILE");
        mainPanel.add(createProfileSettingsScreen(), "SETTINGS");

        add(mainPanel);
        cardLayout.show(mainPanel, "LOGIN");
    }

    private void initializeData() {
        myLibrary = new ArrayList<>();
        allBooks = new ArrayList<>();

        allBooks.add(new Book("Java Programming", "Herbert Schildt", "IT", new Color(59, 130, 246), "src/picture/Java_programming.jpg"));
        allBooks.add(new Book("World History", "J.M. Roberts", "History", new Color(139, 92, 246), "src/picture/world_history.jpg"));
        allBooks.add(new Book("Physics Fundamentals", "David Halliday", "Science", new Color(34, 197, 94), "src/picture/Physics_Fundamental.jpg"));
        allBooks.add(new Book("Data Structures", "Clifford Stein", "IT", new Color(59, 130, 246), "src/picture/Data_structure.jpg"));
        allBooks.add(new Book("Ancient Civilizations", "Brian Fagan", "History", new Color(139, 92, 246), "src/picture/Ancient_Civilzation.jpg"));
        allBooks.add(new Book("Modern Literature", "John Smith", "Literature", new Color(251, 146, 60), "src/picture/Modern_Literature.jpg"));

        Book book1 = allBooks.get(0); book1.progress = 73; myLibrary.add(book1);
        Book book2 = allBooks.get(1); book2.progress = 45; myLibrary.add(book2);
        Book book3 = allBooks.get(2); book3.progress = 100; myLibrary.add(book3);
        Book book4 = allBooks.get(3); book4.progress = 30; myLibrary.add(book4);
        Book book5 = allBooks.get(4); book5.progress = 60; myLibrary.add(book5);

        currentBook = allBooks.get(0);
    }

    // ==================== LOGIN SCREEN ====================
    private JPanel createLoginScreen() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(BACKGROUND);

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(CARD_BG);
        card.setBounds(30, 150, 340, 350);
        card.setBorder(createModernBorder());

        JLabel titleLabel = new JLabel("E-Library");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setBounds(20, 20, 300, 40);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Sign in to continue");
        subtitleLabel.setFont(BODY_FONT);
        subtitleLabel.setForeground(TEXT_SECONDARY);
        subtitleLabel.setBounds(20, 60, 300, 30);
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(subtitleLabel);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(BODY_FONT);
        usernameLabel.setForeground(TEXT_PRIMARY);
        usernameLabel.setBounds(20, 110, 300, 25);
        card.add(usernameLabel);

        JTextField usernameField = createModernTextField();
        usernameField.setBounds(20, 135, 300, 42);
        card.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(BODY_FONT);
        passwordLabel.setForeground(TEXT_PRIMARY);
        passwordLabel.setBounds(20, 187, 300, 25);
        card.add(passwordLabel);

        JPasswordField passwordField = createModernPasswordField();
        passwordField.setBounds(20, 212, 300, 42);
        card.add(passwordField);

        JButton loginButton = createPrimaryButton("Login");
        loginButton.setBounds(20, 274, 300, 45);
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));
        card.add(loginButton);

        JLabel guestLabel = new JLabel("<html><u>Continue as Guest</u></html>");
        guestLabel.setFont(BODY_FONT);
        guestLabel.setForeground(PRIMARY_COLOR);
        guestLabel.setBounds(20, 324, 300, 25);
        guestLabel.setHorizontalAlignment(SwingConstants.CENTER);
        guestLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        guestLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, "HOME");
            }
        });
        card.add(guestLabel);

        loginPanel.add(card);
        return loginPanel;
    }

    private JPanel createHomeScreen() {
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(BACKGROUND);

        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY_COLOR, 0, getHeight(), PRIMARY_DARK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(450, 240));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(20, 25, 18, 25));

        JLabel welcomeLabel = new JLabel("Welcome back, " + userName + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.add(welcomeLabel);

        header.add(Box.createVerticalStrut(5));

        JLabel subtitleLabel = new JLabel("Discover your next favorite book");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitleLabel.setForeground(new Color(255, 255, 255, 220));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.add(subtitleLabel);

        header.add(Box.createVerticalStrut(16));

        JPanel searchPanel = new JPanel(new BorderLayout(12, 0));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(14, 18, 14, 18));
        searchPanel.setMaximumSize(new Dimension(450, 55));
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel searchIcon = new JLabel("");
        searchIcon.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JPanel booksGrid = new JPanel(new GridLayout(0, 2, 18, 18));
        booksGrid.setBackground(BACKGROUND);

        for (Book book : allBooks) {
            booksGrid.add(createModernBookCard(book));
        }

        JTextField searchField = new JTextField("Search for books...");
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        searchField.setForeground(TEXT_SECONDARY);
        searchField.setBorder(null);

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                String searchText = searchField.getText().toLowerCase().trim();
                booksGrid.removeAll();

                if (searchText.isEmpty() || searchText.equals("search for books...")) {
                    for (Book book : allBooks) {
                        booksGrid.add(createModernBookCard(book));
                    }
                } else {
                    boolean foundAny = false;
                    for (Book book : allBooks) {
                        if (book.title.toLowerCase().contains(searchText) ||
                                book.author.toLowerCase().contains(searchText) ||
                                book.category.toLowerCase().contains(searchText)) {
                            booksGrid.add(createModernBookCard(book));
                            foundAny = true;
                        }
                    }
                }
                booksGrid.revalidate();
                booksGrid.repaint();
            }
        });

        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search for books...")) {
                    searchField.setText("");
                    searchField.setForeground(TEXT_PRIMARY);
                }
            }
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search for books...");
                    searchField.setForeground(TEXT_SECONDARY);
                    booksGrid.removeAll();
                    for (Book book : allBooks) {
                        booksGrid.add(createModernBookCard(book));
                    }
                    booksGrid.revalidate();
                    booksGrid.repaint();
                }
            }
        });

        searchPanel.add(searchIcon, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        header.add(searchPanel);

        header.add(Box.createVerticalStrut(14));

        JPanel categoriesWrapper = new JPanel(new BorderLayout());
        categoriesWrapper.setOpaque(false);
        categoriesWrapper.setMaximumSize(new Dimension(450, 38));
        categoriesWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel categoriesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        categoriesPanel.setOpaque(false);

        String[] categories = {"All", "History", "Science", "IT", "Literature"};
        Color[] catColors = {Color.WHITE, ACCENT_COLOR, SUCCESS_COLOR, PRIMARY_LIGHT, WARNING_COLOR};

        for (int i = 0; i < categories.length; i++) {
            final String category = categories[i];
            final Color chipColor = catColors[i];
            final boolean isAll = category.equals("All");

            JPanel chip = new JPanel();
            chip.setOpaque(false);
            chip.setBackground(isAll ? Color.WHITE : new Color(chipColor.getRed(), chipColor.getGreen(), chipColor.getBlue(), 40));
            chip.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
            chip.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JLabel label = new JLabel(category);
            label.setFont(new Font("Segoe UI", Font.BOLD, 13));
            label.setForeground(isAll ? PRIMARY_COLOR : chipColor);
            chip.add(label);

            chip.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    chip.setBackground(isAll ? new Color(255, 255, 255, 230) :
                            new Color(chipColor.getRed(), chipColor.getGreen(), chipColor.getBlue(), 80));
                }
                public void mouseExited(MouseEvent e) {
                    chip.setBackground(isAll ? Color.WHITE :
                            new Color(chipColor.getRed(), chipColor.getGreen(), chipColor.getBlue(), 40));
                }
                public void mouseClicked(MouseEvent e) {
                    searchField.setText("Search for books...");
                    searchField.setForeground(TEXT_SECONDARY);
                    booksGrid.removeAll();
                    if (category.equals("All")) {
                        for (Book book : allBooks) {
                            booksGrid.add(createModernBookCard(book));
                        }
                    } else {
                        for (Book book : allBooks) {
                            if (book.category.equals(category)) {
                                booksGrid.add(createModernBookCard(book));
                            }
                        }
                    }
                    booksGrid.revalidate();
                    booksGrid.repaint();
                }
            });
            categoriesPanel.add(chip);
        }

        categoriesWrapper.add(categoriesPanel, BorderLayout.WEST);
        header.add(categoriesWrapper);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 90, 25));

        JLabel sectionTitle = new JLabel("Popular Books");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sectionTitle.setForeground(TEXT_PRIMARY);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(sectionTitle);

        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(booksGrid);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        homePanel.add(header, BorderLayout.NORTH);
        homePanel.add(scrollPane, BorderLayout.CENTER);
        homePanel.add(createBottomNavBar("HOME"), BorderLayout.SOUTH);

        return homePanel;
    }

    private JPanel createBookDetailsScreen() {
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBackground(BACKGROUND);

        JPanel header = createHeaderBar("Book Details", () -> cardLayout.show(mainPanel, "HOME"));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(CARD_BG);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        JPanel coverWrapper = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintRoundedImage(g, currentBook.imageFile, getWidth(), getHeight(), 16, currentBook.themeColor);
            }
        };
        coverWrapper.setPreferredSize(new Dimension(260, 350));
        coverWrapper.setMaximumSize(new Dimension(260, 350));
        coverWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        coverWrapper.setLayout(null);

        contentPanel.add(coverWrapper);
        contentPanel.add(Box.createVerticalStrut(28));

        JLabel titleLabel = new JLabel(currentBook.title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);

        contentPanel.add(Box.createVerticalStrut(8));

        JLabel authorLabel = new JLabel("by " + currentBook.author);
        authorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        authorLabel.setForeground(TEXT_SECONDARY);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(authorLabel);

        contentPanel.add(Box.createVerticalStrut(25));

        JPanel metaPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        metaPanel.setBackground(CARD_BG);
        metaPanel.setMaximumSize(new Dimension(400, 70));

        metaPanel.add(createInfoBadge("", currentBook.year));
        metaPanel.add(createInfoBadge("", currentBook.language));
        metaPanel.add(createInfoBadge("", currentBook.category));

        contentPanel.add(metaPanel);
        contentPanel.add(Box.createVerticalStrut(28));

        JLabel descLabel = new JLabel("About this book");
        descLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        descLabel.setForeground(TEXT_PRIMARY);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(descLabel);

        contentPanel.add(Box.createVerticalStrut(12));

        String htmlDesc = "<html><div style='width:360px; text-align:left;'>" +
                currentBook.description + "</div></html>";

        JLabel descArea = new JLabel(htmlDesc);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        descArea.setForeground(TEXT_SECONDARY);
        descArea.setVerticalAlignment(SwingConstants.TOP);
        descArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(descArea);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 18, 0));
        actionPanel.setBackground(CARD_BG);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(22, 35, 22, 35));

        JButton previewButton = createSecondaryButton("Preview");
        previewButton.setPreferredSize(new Dimension(0, 52));
        previewButton.addActionListener(e -> {
            currentBook.currentPage = 3;
            cardLayout.show(mainPanel, "READING");
        });

        JButton downloadButton = createPrimaryButton("Download");
        downloadButton.setPreferredSize(new Dimension(0, 52));
        downloadButton.addActionListener(e -> {
            if (!myLibrary.contains(currentBook)) {
                currentBook.progress = 0;
                myLibrary.add(currentBook);
                booksDownloaded++;


                refreshLibraryUI();

                showSuccessMessage("Book downloaded successfully!");
            } else {
                showInfoMessage("Already in your library!");
            }
        });

        actionPanel.add(previewButton);
        actionPanel.add(downloadButton);

        detailsPanel.add(header, BorderLayout.NORTH);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);
        detailsPanel.add(actionPanel, BorderLayout.SOUTH);

        return detailsPanel;
    }

    private JPanel createReadingScreen() {
        JPanel readingPanel = new JPanel(new BorderLayout());
        readingPanel.setBackground(CARD_BG);

        JPanel header = createHeaderBar("Reading Mode", () -> cardLayout.show(mainPanel, "DETAILS"));

        JPanel progressPanel = new JPanel(new BorderLayout(10, 0));
        progressPanel.setBackground(CARD_BG);
        progressPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));

        int percent = (currentBook.currentPage * 100) / currentBook.totalPages;

        JLabel pageLabel = new JLabel("Page " + currentBook.currentPage + " of " + currentBook.totalPages);
        pageLabel.setFont(SMALL_FONT);
        pageLabel.setForeground(TEXT_SECONDARY);

        JLabel percentLabel = new JLabel(percent + "%");
        percentLabel.setFont(SMALL_FONT);
        percentLabel.setForeground(TEXT_SECONDARY);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(percent);
        progressBar.setForeground(currentBook.themeColor);
        progressBar.setBackground(BORDER_COLOR);
        progressBar.setBorder(null);
        progressBar.setPreferredSize(new Dimension(0, 4));

        progressPanel.add(pageLabel, BorderLayout.WEST);
        progressPanel.add(progressBar, BorderLayout.CENTER);
        progressPanel.add(percentLabel, BorderLayout.EAST);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(CARD_BG);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel chapterLabel = new JLabel("Chapter " + currentBook.currentPage);
        chapterLabel.setFont(TITLE_FONT);
        chapterLabel.setForeground(TEXT_PRIMARY);
        chapterLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(chapterLabel);

        contentPanel.add(Box.createVerticalStrut(15));

        String[] paragraphs = {
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.",
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis."
        };

        for (String para : paragraphs) {
            JTextArea paraArea = new JTextArea(para);
            paraArea.setFont(BODY_FONT);
            paraArea.setForeground(TEXT_PRIMARY);
            paraArea.setLineWrap(true);
            paraArea.setWrapStyleWord(true);
            paraArea.setEditable(false);
            paraArea.setBackground(CARD_BG);
            paraArea.setBorder(null);
            paraArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentPanel.add(paraArea);
            contentPanel.add(Box.createVerticalStrut(15));
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel navPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        navPanel.setBackground(CARD_BG);
        navPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton prevButton = createSecondaryButton("Previous");
        prevButton.setEnabled(currentBook.currentPage > 1);
        prevButton.addActionListener(e -> {
            if (currentBook.currentPage > 1) {
                currentBook.currentPage--;
                currentBook.progress = (currentBook.currentPage * 100) / currentBook.totalPages;
                mainPanel.remove(3);
                mainPanel.add(createReadingScreen(), "READING", 3);
                cardLayout.show(mainPanel, "READING");
            }
        });

        JLabel pageIndicator = new JLabel(currentBook.currentPage + "/" + currentBook.totalPages, SwingConstants.CENTER);
        pageIndicator.setFont(BODY_FONT);
        pageIndicator.setForeground(TEXT_PRIMARY);

        JButton nextButton = createPrimaryButton("Next");
        nextButton.setEnabled(currentBook.currentPage < currentBook.totalPages);
        nextButton.addActionListener(e -> {
            if (currentBook.currentPage < currentBook.totalPages) {
                currentBook.currentPage++;
                currentBook.progress = (currentBook.currentPage * 100) / currentBook.totalPages;
                mainPanel.remove(3);
                mainPanel.add(createReadingScreen(), "READING", 3);
                cardLayout.show(mainPanel, "READING");
            }
        });

        navPanel.add(prevButton);
        navPanel.add(pageIndicator);
        navPanel.add(nextButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(header, BorderLayout.NORTH);
        topPanel.add(progressPanel, BorderLayout.SOUTH);

        readingPanel.add(topPanel, BorderLayout.NORTH);
        readingPanel.add(scrollPane, BorderLayout.CENTER);
        readingPanel.add(navPanel, BorderLayout.SOUTH);

        return readingPanel;
    }

    private JPanel createLibraryScreen() {
        JPanel libraryPanel = new JPanel(new BorderLayout());
        libraryPanel.setBackground(BACKGROUND);

        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, ACCENT_COLOR, 0, getHeight(), new Color(139, 92, 246, 200));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(450, 130));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("My Library");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.add(titleLabel);

        header.add(Box.createVerticalStrut(5));

        JLabel subtitleLabel = new JLabel(myLibrary.size() + " books available offline");
        subtitleLabel.setFont(BODY_FONT);
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.add(subtitleLabel);

        libraryListPanel = new JPanel();
        libraryListPanel.setLayout(new BoxLayout(libraryListPanel, BoxLayout.Y_AXIS));
        libraryListPanel.setBackground(BACKGROUND);
        libraryListPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 80, 15));

        for (Book book : myLibrary) {
            libraryListPanel.add(createLibraryBookCard(book));
            libraryListPanel.add(Box.createVerticalStrut(12));
        }

        JScrollPane scrollPane = new JScrollPane(libraryListPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        libraryPanel.add(header, BorderLayout.NORTH);
        libraryPanel.add(scrollPane, BorderLayout.CENTER);
        libraryPanel.add(createBottomNavBar("LIBRARY"), BorderLayout.SOUTH);

        return libraryPanel;
    }

    private JPanel createProfileScreen() {
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(BACKGROUND);

        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY_COLOR, 0, getHeight(), PRIMARY_DARK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(450, 190));
        header.setLayout(null);

        JPanel avatarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintRoundedImage(g, "src/picture/Profile_Icon.jpg", getWidth(), getHeight(), 90, PRIMARY_COLOR);
            }
        };
        avatarPanel.setBackground(new Color(0,0,0,0));
        avatarPanel.setBounds(175, 40, 100, 100);
        avatarPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        header.add(avatarPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 80, 15));

        JPanel infoCard = new JPanel();
        infoCard.setLayout(new BoxLayout(infoCard, BoxLayout.Y_AXIS));
        infoCard.setBackground(CARD_BG);
        infoCard.setBorder(createModernBorder());
        infoCard.setMaximumSize(new Dimension(450, 90));

        JLabel nameLabel = new JLabel(userName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        nameLabel.setForeground(TEXT_PRIMARY);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoCard.add(Box.createVerticalStrut(12));
        infoCard.add(nameLabel);

        JLabel emailLabel = new JLabel(email, SwingConstants.CENTER);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        emailLabel.setForeground(TEXT_SECONDARY);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoCard.add(emailLabel);
        infoCard.add(Box.createVerticalStrut(12));

        contentPanel.add(infoCard);
        contentPanel.add(Box.createVerticalStrut(15));

        JPanel statsCard = new JPanel(new GridLayout(1, 3, 12, 0));
        statsCard.setBackground(CARD_BG);
        statsCard.setBorder(createModernBorder());
        statsCard.setMaximumSize(new Dimension(450, 120));

        statsCard.add(createStatBox("", String.valueOf(booksRead), "Books Read", PRIMARY_COLOR));
        statsCard.add(createStatBox("", String.valueOf(booksDownloaded), "Downloaded", SUCCESS_COLOR));
        statsCard.add(createStatBox("", String.valueOf(hoursRead), "Hours", WARNING_COLOR));

        contentPanel.add(statsCard);
        contentPanel.add(Box.createVerticalStrut(25));

        JPanel personalInfoOption = createSettingsOption("", "Personal Information", PRIMARY_COLOR);
        personalInfoOption.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showPersonalInfoDialog();
            }
        });
        contentPanel.add(personalInfoOption);
        contentPanel.add(Box.createVerticalStrut(12));

        JPanel readingPrefsOption = createSettingsOption("", "Reading Preferences", ACCENT_COLOR);
        readingPrefsOption.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showReadingPreferencesDialog();
            }
        });
        contentPanel.add(readingPrefsOption);
        contentPanel.add(Box.createVerticalStrut(12));

        JPanel notificationsOption = createSettingsOption("", "Notifications", WARNING_COLOR);
        notificationsOption.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showNotificationsDialog();
            }
        });
        contentPanel.add(notificationsOption);
        contentPanel.add(Box.createVerticalStrut(20));

        JButton logoutButton = createDangerButton("Logout");
        logoutButton.setMaximumSize(new Dimension(450, 50));
        logoutButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoutButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                cardLayout.show(mainPanel, "LOGIN");
            }
        });
        contentPanel.add(logoutButton);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        profilePanel.add(header, BorderLayout.NORTH);
        profilePanel.add(scrollPane, BorderLayout.CENTER);
        profilePanel.add(createBottomNavBar("PROFILE"), BorderLayout.SOUTH);

        return profilePanel;
    }

    private JPanel createProfileSettingsScreen() {
        JPanel settingsPanel = new JPanel(new BorderLayout());
        settingsPanel.setBackground(BACKGROUND);

        JPanel header = createHeaderBar("Settings", () -> cardLayout.show(mainPanel, "PROFILE"));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 80, 15));

        JPanel fontCard = createSettingsCard("Font Size");
        JPanel fontButtons = new JPanel(new GridLayout(1, 3, 10, 0));
        fontButtons.setBackground(CARD_BG);
        fontButtons.setMaximumSize(new Dimension(400, 40));
        fontButtons.add(createToggleButton("S", false));
        fontButtons.add(createToggleButton("M", true));
        fontButtons.add(createToggleButton("L", false));
        fontCard.add(fontButtons);
        contentPanel.add(fontCard);
        contentPanel.add(Box.createVerticalStrut(15));

        JPanel notifCard = createSettingsCard("Notifications");
        addToggleOption(notifCard, "New Books Alert", true);
        addToggleOption(notifCard, "Due Date Reminder", true);
        addToggleOption(notifCard, "Reading Goals", false);
        contentPanel.add(notifCard);
        contentPanel.add(Box.createVerticalStrut(15));

        JPanel storageCard = createSettingsCard("Storage");
        JLabel storageLabel = new JLabel("2.4GB / 5GB used");
        storageLabel.setFont(BODY_FONT);
        storageLabel.setForeground(TEXT_SECONDARY);
        storageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        storageCard.add(storageLabel);
        storageCard.add(Box.createVerticalStrut(8));

        JProgressBar storageBar = new JProgressBar(0, 100);
        storageBar.setValue(48);
        storageBar.setForeground(PRIMARY_COLOR);
        storageBar.setBackground(BORDER_COLOR);
        storageBar.setBorder(null);
        storageBar.setPreferredSize(new Dimension(0, 8));
        storageBar.setMaximumSize(new Dimension(400, 8));
        storageBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        storageCard.add(storageBar);
        contentPanel.add(storageCard);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        settingsPanel.add(header, BorderLayout.NORTH);
        settingsPanel.add(scrollPane, BorderLayout.CENTER);
        settingsPanel.add(createBottomNavBar("PROFILE"), BorderLayout.SOUTH);

        return settingsPanel;
    }

    private void refreshLibraryUI() {
        if (libraryListPanel != null) {
            libraryListPanel.removeAll();
            for (Book book : myLibrary) {
                libraryListPanel.add(createLibraryBookCard(book));
                libraryListPanel.add(Box.createVerticalStrut(12));
            }
            libraryListPanel.revalidate();
            libraryListPanel.repaint();
        }
    }

    private void paintRoundedImage(Graphics g, String filename, int width, int height, int arc, Color fallbackColor) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ImageIcon icon = new ImageIcon(filename);

        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE && icon.getIconWidth() > 0) {
            Shape clip = new RoundRectangle2D.Float(0, 0, width, height, arc, arc);
            g2d.clip(clip);
            g2d.drawImage(icon.getImage(), 0, 0, width, height, null);
        } else {
            System.out.println("Could not load image: " + filename);
            GradientPaint gp = new GradientPaint(0, 0, fallbackColor, width, height,
                    new Color(fallbackColor.getRed(), fallbackColor.getGreen(), fallbackColor.getBlue(), 180));
            g2d.setPaint(gp);
            g2d.fillRoundRect(0, 0, width, height, arc, arc);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Segoe UI", Font.PLAIN, height / 3));
            FontMetrics fm = g2d.getFontMetrics();
            String emoji = "📖";
            int x = (width - fm.stringWidth(emoji)) / 2;
            int y = ((height - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(emoji, x, y);
        }
    }

    private Border createModernBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );
    }

    private JTextField createModernTextField() {
        JTextField field = new JTextField();
        field.setFont(BODY_FONT);
        field.setForeground(TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JPasswordField createModernPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(BODY_FONT);
        field.setForeground(TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(PRIMARY_DARK);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
            }
        });

        return button;
    }

    private JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(PRIMARY_COLOR);
        button.setBackground(CARD_BG);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(HOVER_BG);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(CARD_BG);
            }
        });

        return button;
    }

    private JButton createDangerButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(new Color(220, 38, 38));
        button.setBackground(CARD_BG);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 38, 38), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private JPanel createModernBookCard(Book book) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(18, 15, 18, 15)
        ));
        card.setPreferredSize(new Dimension(190, 270));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel cover = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintRoundedImage(g, book.imageFile, getWidth(), getHeight(), 12, book.themeColor);
            }
        };
        cover.setPreferredSize(new Dimension(160, 170));
        cover.setMaximumSize(new Dimension(160, 170));
        cover.setAlignmentX(Component.CENTER_ALIGNMENT);
        cover.setLayout(null);

        card.add(cover);
        card.add(Box.createVerticalStrut(14));

        JLabel title = new JLabel("<html><div style='text-align:center; width:145px;'>" + book.title + "</div></html>");
        title.setFont(new Font("Segoe UI", Font.BOLD, 15));
        title.setForeground(TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);

        card.add(Box.createVerticalStrut(6));

        JLabel author = new JLabel(book.author);
        author.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        author.setForeground(TEXT_SECONDARY);
        author.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(author);

        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                currentBook = book;
                mainPanel.remove(2);
                mainPanel.add(createBookDetailsScreen(), "DETAILS", 2);
                cardLayout.show(mainPanel, "DETAILS");
            }
            public void mouseEntered(MouseEvent e) {
                card.setBackground(HOVER_BG);
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(PRIMARY_LIGHT, 2),
                        BorderFactory.createEmptyBorder(17, 14, 17, 14)
                ));
            }
            public void mouseExited(MouseEvent e) {
                card.setBackground(CARD_BG);
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR, 1),
                        BorderFactory.createEmptyBorder(18, 15, 18, 15)
                ));
            }
        });

        return card;
    }

    private JPanel createLibraryBookCard(Book book) {
        JPanel card = new JPanel(new BorderLayout(12, 0));
        card.setBackground(CARD_BG);
        card.setBorder(createModernBorder());
        card.setMaximumSize(new Dimension(450, 110));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel cover = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintRoundedImage(g, book.imageFile, getWidth(), getHeight(), 6, book.themeColor);
            }
        };
        cover.setPreferredSize(new Dimension(65, 80));
        cover.setLayout(null);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(CARD_BG);

        JLabel titleLabel = new JLabel(book.title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(titleLabel);

        infoPanel.add(Box.createVerticalStrut(5));

        JLabel progressLabel = new JLabel("Reading Progress");
        progressLabel.setFont(SMALL_FONT);
        progressLabel.setForeground(TEXT_SECONDARY);
        progressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(progressLabel);

        infoPanel.add(Box.createVerticalStrut(5));

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(book.progress);
        progressBar.setForeground(book.progress == 100 ? SUCCESS_COLOR : book.themeColor);
        progressBar.setBackground(BORDER_COLOR);
        progressBar.setBorder(null);
        progressBar.setPreferredSize(new Dimension(0, 6));
        progressBar.setMaximumSize(new Dimension(400, 6));
        progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(progressBar);

        infoPanel.add(Box.createVerticalStrut(3));

        JLabel percentLabel = new JLabel(book.progress + "% complete");
        percentLabel.setFont(SMALL_FONT);
        percentLabel.setForeground(book.progress == 100 ? SUCCESS_COLOR : TEXT_SECONDARY);
        percentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(percentLabel);

        card.add(cover, BorderLayout.WEST);
        card.add(infoPanel, BorderLayout.CENTER);

        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                currentBook = book;
                currentBook.currentPage = Math.max(1, (book.progress * currentBook.totalPages) / 100);
                mainPanel.remove(3);
                mainPanel.add(createReadingScreen(), "READING", 3);
                cardLayout.show(mainPanel, "READING");
            }
            public void mouseEntered(MouseEvent e) {
                card.setBackground(HOVER_BG);
                infoPanel.setBackground(HOVER_BG);
            }
            public void mouseExited(MouseEvent e) {
                card.setBackground(CARD_BG);
                infoPanel.setBackground(CARD_BG);
            }
        });

        return card;
    }

    private JPanel createHeaderBar(String title, Runnable backAction) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(CARD_BG);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JButton backButton = new JButton("←");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        backButton.setForeground(TEXT_PRIMARY);
        backButton.setBackground(CARD_BG);
        backButton.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(40, 40));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> backAction.run());

        JLabel headerTitle = new JLabel(title);
        headerTitle.setFont(TITLE_FONT);
        headerTitle.setForeground(TEXT_PRIMARY);

        header.add(backButton, BorderLayout.WEST);
        header.add(headerTitle, BorderLayout.CENTER);

        return header;
    }

    private JPanel createInfoBadge(String icon, String text) {
        JPanel badge = new JPanel();
        badge.setLayout(new BoxLayout(badge, BoxLayout.Y_AXIS));
        badge.setBackground(HOVER_BG);
        badge.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        badge.add(iconLabel);

        badge.add(Box.createVerticalStrut(5));

        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        textLabel.setForeground(TEXT_PRIMARY);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        badge.add(textLabel);

        return badge;
    }

    private JPanel createStatBox(String icon, String value, String label, Color color) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(CARD_BG);
        box.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        valueLabel.setForeground(color);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(valueLabel);

        box.add(Box.createVerticalStrut(8));

        JLabel textLabel = new JLabel(label, SwingConstants.CENTER);
        textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textLabel.setForeground(TEXT_SECONDARY);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(textLabel);

        return box;
    }

    private JPanel createSettingsOption(String icon, String text, Color color) {
        JPanel option = new JPanel(new BorderLayout(15, 0));
        option.setBackground(CARD_BG);
        option.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));
        option.setMaximumSize(new Dimension(450, 70));
        option.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textLabel.setForeground(TEXT_PRIMARY);

        JLabel arrow = new JLabel("›");
        arrow.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        arrow.setForeground(TEXT_SECONDARY);

        option.add(iconLabel, BorderLayout.WEST);
        option.add(textLabel, BorderLayout.CENTER);
        option.add(arrow, BorderLayout.EAST);

        MouseAdapter hoverEffect = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                option.setBackground(HOVER_BG);
            }
            public void mouseExited(MouseEvent e) {
                option.setBackground(CARD_BG);
            }
        };
        option.addMouseListener(hoverEffect);

        return option;
    }

    private JPanel createSettingsCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_BG);
        card.setBorder(createModernBorder());
        card.setMaximumSize(new Dimension(400, 300));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(15));

        return card;
    }

    private void addToggleOption(JPanel panel, String text, boolean enabled) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(CARD_BG);
        row.setMaximumSize(new Dimension(400, 35));

        JLabel label = new JLabel(text);
        label.setFont(BODY_FONT);
        label.setForeground(TEXT_PRIMARY);

        JCheckBox toggle = new JCheckBox();
        toggle.setSelected(enabled);
        toggle.setBackground(CARD_BG);
        toggle.setFocusPainted(false);

        row.add(label, BorderLayout.WEST);
        row.add(toggle, BorderLayout.EAST);

        panel.add(row);
        panel.add(Box.createVerticalStrut(10));
    }

    private JButton createToggleButton(String text, boolean selected) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(selected ? Color.WHITE : TEXT_PRIMARY);
        button.setBackground(selected ? PRIMARY_COLOR : CARD_BG);
        button.setBorder(BorderFactory.createLineBorder(selected ? PRIMARY_COLOR : BORDER_COLOR, 1));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private JPanel createBottomNavBar(String currentScreen) {
        JPanel navBar = new JPanel(new GridLayout(1, 3));
        navBar.setBackground(CARD_BG);
        navBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, BORDER_COLOR),
                BorderFactory.createEmptyBorder(10, 0, 10, 0)
        ));
        navBar.setPreferredSize(new Dimension(450, 60));

        JButton homeButton = createNavButton("Home", "Home", currentScreen.equals("HOME"), PRIMARY_COLOR);
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        JButton libraryButton = createNavButton("Library", "Library", currentScreen.equals("LIBRARY"), ACCENT_COLOR);
        libraryButton.addActionListener(e -> cardLayout.show(mainPanel, "LIBRARY"));

        JButton profileButton = createNavButton("Profile", "Profile", currentScreen.equals("PROFILE"), SUCCESS_COLOR);
        profileButton.addActionListener(e -> cardLayout.show(mainPanel, "PROFILE"));

        navBar.add(homeButton);
        navBar.add(libraryButton);
        navBar.add(profileButton);

        return navBar;
    }

    private JButton createNavButton(String icon, String text, boolean isActive, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(isActive ? color : TEXT_SECONDARY);
        button.setBackground(CARD_BG);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showPersonalInfoDialog() {
        String info = "PERSONAL INFORMATION\n\n" +
                "Full Name: " + userName + "\n" +
                "Student ID: " + studentId + "\n" +
                "Email: " + email + "\n" +
                "University: " + university + "\n\n" +
                "Account Status: Active\n" +
                "Member Since: January 2024";
        JOptionPane.showMessageDialog(this, info, "Personal Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showReadingPreferencesDialog() {
        String prefs = "READING PREFERENCES\n\n" +
                "Font Size: Medium\n" +
                "Theme: Light Mode\n" +
                "Auto-Bookmark: Enabled\n" +
                "Page Turn Animation: Enabled\n" +
                "Reading Mode: Standard\n\n" +
                "Daily Reading Goal: 30 minutes\n" +
                "Preferred Categories: IT, History";
        JOptionPane.showMessageDialog(this, prefs, "Reading Preferences", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showNotificationsDialog() {
        String notifs = "NOTIFICATION SETTINGS\n\n" +
                "New Books Alert: ON\n" +
                "Due Date Reminder: ON\n" +
                "Reading Goals: OFF\n" +
                "Weekly Summary: ON\n" +
                "Download Complete: ON\n\n" +
                "Email Notifications: Enabled\n" +
                "Push Notifications: Enabled";
        JOptionPane.showMessageDialog(this, notifs, "Notifications", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            ELibrarySystem app = new ELibrarySystem();
            app.setVisible(true);
        });
    }
}