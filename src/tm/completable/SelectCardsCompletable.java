package tm.completable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tm.Card;
import tm.Game;

public abstract class SelectCardsCompletable extends JPanel implements Completable {

    private final Game game;
    protected final Set<Card> selectedCards = new HashSet<>();
    protected final List<? extends Card> selection;
    private Card cardToRender;

    private static class SelectionWindow extends JFrame {
        private Card cardToRender;
        private Set<Card> selectedCards = new HashSet<>();

        public SelectionWindow(List<? extends Card> selection) {
            // Create panel which renders last selected card
            final JPanel cardPanel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    if (cardToRender != null) {
                        cardToRender.renderTitle(g, 0, 0);
                        cardToRender.renderContent(g, 0, 22, null);
                    }
                }
            };
            cardPanel.setPreferredSize(new Dimension(202, 230));
            cardPanel.setBackground(Color.BLACK);

            // Create list which contains the selection
            final DefaultListModel<Card> listModel = new DefaultListModel<>();
            for (Card card : selection) {
                listModel.addElement(card);
            }
            final JList<Card> cardList = new JList<>(listModel);
            cardList.setFixedCellHeight(23);
            cardList.setFixedCellWidth(204);
            cardList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            cardList.setBackground(Color.BLACK);
            cardList.setCellRenderer((_selection, card, index, isSelected, cellHasFocus) -> new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    if (isSelected) {
                        g.setColor(cellHasFocus ? Color.WHITE : Color.YELLOW);
                        g.drawRect(0, 0, 202, 21);
                    }
                    if (cellHasFocus) {
                        cardToRender = card;
                        cardPanel.repaint();
                    }
                    card.renderTitle(g, 1, 1);
                }
            });
            cardList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    final int[] indices = ((JList<?>) e.getSource()).getSelectedIndices();
                    selectedCards.clear();
                    for (int i : indices) {
                        selectedCards.add(selection.get(i));
                    }
                }
            });

            // Add scroll bar to the list if needed
            final JScrollPane scrollPane = new JScrollPane(cardList);
            scrollPane.setPreferredSize(new Dimension(224, 280));
            scrollPane.setBorder(null);

            // Create a panel with two buttons
            final JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(new JButton("Confirm"));
            buttonPanel.add(new JButton("Cancel"));
            buttonPanel.setBackground(Color.BLACK);

            // Create a panel which contains selected card and buttons
            final JPanel cardAndButtonsPanel = new JPanel();
            final BoxLayout boxLayout = new BoxLayout(cardAndButtonsPanel, BoxLayout.Y_AXIS);
            cardAndButtonsPanel.add(cardPanel);
            cardAndButtonsPanel.add(buttonPanel);
            cardAndButtonsPanel.setBackground(Color.BLACK);
            cardAndButtonsPanel.setPreferredSize(new Dimension(202, 280));

            // Finally, add the created panels to the frame
            setLayout(new FlowLayout());
            add(scrollPane);
            add(cardAndButtonsPanel);
            pack();
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Play 1 card");
            setLocationRelativeTo(null);
            setAlwaysOnTop(true);
            getContentPane().setBackground(Color.BLACK);
            setVisible(true);
        }
    }

    protected SelectCardsCompletable(Game game, List<? extends Card> selection) {
        this.game = game;
        this.selection = selection;
        new SelectionWindow(selection);

    }

    protected void selectionChanged() {
    }

    public abstract int maxSelection();

    public abstract boolean check();

    @Override
    public void cancel() {
    }

    public abstract String getTitle();

}
