package tm.completable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;

public abstract class SelectCardsCompletable extends JPanel implements Completable {

    protected final Set<Card> selectedCards = new HashSet<>();
    private final Game game;
    protected final List<? extends Card> selection;
    private final int min;
    private final int max;
    private final String title;
    @Nullable
    private SelectionWindow window;

    private class SelectionWindow extends JFrame {
        private Card cardToRender;

        public SelectionWindow(Game game, List<? extends Card> selection, int min, int max, String title) {
            // Create confirm button
            final JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (check()) {
                        game.getActionHandler().completed(SelectCardsCompletable.this);
                        game.repaint();
                        cancel();
                    }
                }
            });

            // Create cancel button
            final JButton cancelButton = new JButton("Cancel");
            cancelButton.setEnabled(game.getActionHandler().isCancelEnabled());
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (game.getActionHandler().cancel()) {
                        game.repaint();
                        cancel();
                    }
                }
            });

            // Create a panel with two buttons
            final JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);
            buttonPanel.setBackground(Color.BLACK);

            // Create panel which renders last selected card
            final JPanel cardPanel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    if (cardToRender != null) {
                        cardToRender.renderTitle(g, 0, 0);
                        cardToRender.renderContent(g, 0, 22, game);
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
            final int selectionMode = max > 1 ? ListSelectionModel.MULTIPLE_INTERVAL_SELECTION : ListSelectionModel.SINGLE_SELECTION;
            cardList.setSelectionMode(selectionMode);
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
                    selectionChanged();
                    game.repaint();
                    confirmButton.setEnabled(indices.length >= min && indices.length <= max);
                    //confirmButton.setEnabled(check());
                }
            });

            // Add scroll bar to the list if needed
            final JScrollPane scrollPane = new JScrollPane(cardList);
            scrollPane.setPreferredSize(new Dimension(224, 280));
            scrollPane.setBorder(null);

            // Create a panel which contains selected card and buttons
            final JPanel cardAndButtonsPanel = new JPanel();
            new BoxLayout(cardAndButtonsPanel, BoxLayout.Y_AXIS);
            cardAndButtonsPanel.add(cardPanel);
            cardAndButtonsPanel.add(buttonPanel);
            cardAndButtonsPanel.setBackground(Color.BLACK);
            cardAndButtonsPanel.setPreferredSize(new Dimension(202, 280));

            // Finally, add the created panels to the frame
            setLayout(new FlowLayout());
            add(scrollPane);
            add(cardAndButtonsPanel);
            pack();
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            setTitle(title);
            setLocationRelativeTo(game);
            setAlwaysOnTop(true);
            getContentPane().setBackground(Color.BLACK);
        }
    }

    protected SelectCardsCompletable(Game game, List<? extends Card> selection, int min, int max, String title) {
        this.game = game;
        this.selection = selection;
        this.min = min;
        this.max = max;
        this.title = title;
        createWindow();
    }

    protected void createWindow() {
        window = new SelectionWindow(game, selection, min, max, title);
        window.setVisible(true);
    }

    protected void selectionChanged() {
    }

    public abstract boolean check();

    @Override
    public void cancel() {
        if (window != null) {
            window.setVisible(false);
            window.dispose();
            window = null;
        }
    }
}
