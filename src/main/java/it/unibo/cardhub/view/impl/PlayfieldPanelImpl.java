package it.unibo.cardhub.view.impl;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.view.api.PlayerPanelNotifier;
import it.unibo.cardhub.view.api.PlayfieldPanel;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;
import it.unibo.cardhub.view.util.ImageResolver;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of PlayfieldPanel.
 */
@SuppressFBWarnings(
    value = "SE_TRANSIENT_FIELD_NOT_RESTORED",
    justification = "This class is never saved on fil or transmited: is just "
        + "a Swing view. It is 'Serializable' just by inheritance form JPanel, "
        + "not by choice or because it is usefull."
)
final class PlayfieldPanelImpl extends CHPanel implements PlayfieldPanel {

    private static final long serialVersionUID = 1L;
    private static final int HIGHLIGHT_BORDER = 2;

    private final transient MatchController controller;
    private final transient Map<PlayerEnum, Card<?>> selectedCards = new EnumMap<>(PlayerEnum.class);

    private final PlayfieldAreaPanel bottomArea;
    private final PlayfieldAreaPanel topArea;
    private final Map<PlayerEnum, PlayfieldAreaPanel> playfieldAreas;

    private final DiscardPileAreaPanel playerOneDiscardPileArea;
    private final DiscardPileAreaPanel playerTwoDiscardPileArea;

    /**
     * Constructs a new playfield panel.
     * 
     * @param controller the controller that can provide the players
     * @param notifier the PlayerPanelNotifier to be passed to the child panels
     */
    PlayfieldPanelImpl(final MatchController controller, final PlayerPanelNotifier notifier) {
        super(new BorderLayout());
        this.controller = Objects.requireNonNull(controller, "no such controller");

        this.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CHStyles.primaryColor()),
            BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                            CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));

        this.bottomArea = new PlayfieldAreaPanel(controller, PlayerEnum.PLAYER_ONE, this::onSelectionChanged, notifier);
        this.topArea = new PlayfieldAreaPanel(controller, PlayerEnum.PLAYER_TWO, this::onSelectionChanged, notifier);
        this.playfieldAreas = new EnumMap<>(PlayerEnum.class);
        this.playfieldAreas.put(PlayerEnum.PLAYER_ONE, this.bottomArea);
        this.playfieldAreas.put(PlayerEnum.PLAYER_TWO, this.topArea);
        final JPanel centralArea = new JPanel(
            new GridLayout(2, 1, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD));
        centralArea.add(this.topArea);
        centralArea.add(this.bottomArea);

        this.playerOneDiscardPileArea = new DiscardPileAreaPanel(
            BorderLayout.SOUTH, PlayerEnum.PLAYER_ONE, controller, this::getSelectedCard, this::deselectAll);
        this.playerTwoDiscardPileArea = new DiscardPileAreaPanel(
            BorderLayout.NORTH, PlayerEnum.PLAYER_TWO, controller, this::getSelectedCard, this::deselectAll);

        this.add(centralArea, BorderLayout.CENTER);
        this.add(this.playerOneDiscardPileArea, BorderLayout.EAST);
        this.add(this.playerTwoDiscardPileArea, BorderLayout.WEST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayerOneDiscardPile(final Optional<Card<?>> card) {
        this.playerOneDiscardPileArea.updateCard(Objects.requireNonNull(card, "Card cannot be null"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayerTwoDiscardPile(final Optional<Card<?>> card) {
        this.playerTwoDiscardPileArea.updateCard(Objects.requireNonNull(card, "Card cannot be null"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayfield(final PlayerEnum player, final List<Card<?>> cards) {
        Objects.requireNonNull(player, "Player cannot be null");
        Objects.requireNonNull(cards, "Cards list cannot be null");

        final PlayfieldAreaPanel area = this.playfieldAreas.get(player);
        if (area == null) {
            throw new IllegalArgumentException("Unknown player: " + player);
        }
        area.update(cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToPanel(final JPanel panel, final Object constraints) {
        panel.add(this, constraints);
    }

    /**
     * Handles a selection or deselection event coming from either playfield area.
     * When both players have an active selection, the controller is asked to
     * compare the two selected cards, and both selections are then cleared.
     *
     * @param player the player whose selection changed
     * @param card   the newly selected card, or {@link Optional#empty()} on deselection
     */
    private void onSelectionChanged(final PlayerEnum player, final Optional<Card<?>> card) {
        card.ifPresentOrElse(
            c -> this.selectedCards.put(player, c),
            () -> this.selectedCards.remove(player));

        if (this.selectedCards.containsKey(PlayerEnum.PLAYER_ONE)
            && this.selectedCards.containsKey(PlayerEnum.PLAYER_TWO)) {
            final Card<?> firstPlayerCard = this.selectedCards.get(PlayerEnum.PLAYER_ONE);
            final Card<?> secondPlayerCard = this.selectedCards.get(PlayerEnum.PLAYER_TWO);

            this.deselectAll();

            this.controller.compareCard(firstPlayerCard, secondPlayerCard);
        }
    }

    private Optional<Card<?>> getSelectedCard(final PlayerEnum player) {
        return Optional.ofNullable(this.selectedCards.get(player));
    }

    private void deselectAll() {
        this.selectedCards.clear();
        this.bottomArea.deselect();
        this.topArea.deselect();
    }

    private static final class PlayfieldAreaPanel extends CHPanel {
        private static final int ROWS = 1;

        private static final long serialVersionUID = 1L;

        private final PlayerEnum player;
        private final transient PlayerPanelNotifier notifier;
        private final transient BiConsumer<PlayerEnum, Optional<Card<?>>> selectionListener;

        private transient Optional<CHLabel> selectedLabel = Optional.empty();
        private transient Optional<Card<?>> selectedCard = Optional.empty();

        PlayfieldAreaPanel(final MatchController controller, final PlayerEnum player,
                            final BiConsumer<PlayerEnum, Optional<Card<?>>> selectionListener,
                            final PlayerPanelNotifier notifier) {
            super(new GridLayout(ROWS, controller.getPlayFieldSize(), CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD));
            this.player = Objects.requireNonNull(player, "Player must be provided to PlayfieldAreaPanel");
            this.selectionListener = Objects.requireNonNull(selectionListener, "Selection listener cannot be null");

            this.notifier = notifier;

            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CHStyles.primaryColor()),
                BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                                CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));
        }

        void update(final List<Card<?>> cards) {
            this.removeAll();
            this.clearSelection();

            cards.forEach(card -> {
                final CHLabel label = new CHLabel(ImageResolver.resolve(card));
                label.setPreferredSize(new Dimension(ImageResolver.CARD_WIDTH, ImageResolver.CARD_HEIGHT));
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(final MouseEvent e) {
                        toggleSelection(card, label);
                    }

                    @Override
                    public void mouseEntered(final MouseEvent e) {
                        notifier.mouseHovered(card, player);
                    }

                    @Override
                    public void mouseExited(final MouseEvent e) {
                        notifier.mouseExited(player);
                    }
                });
                this.add(label);
            });

            this.revalidate();
            this.repaint();
        }

        /**
         * Clears the current selection and notifies the listener.
         */
        void deselect() {
            this.clearSelection();
            this.selectionListener.accept(this.player, Optional.empty());
        }

        private void toggleSelection(final Card<?> card, final CHLabel label) {
            if (this.selectedLabel.isPresent() && label.equals(this.selectedLabel.get())) {
                this.clearSelection();
            } else {
                this.selectedLabel.ifPresent(this::unHighlightLabel);
                this.selectedLabel = Optional.of(label);
                this.selectedCard = Optional.of(card);
                highlightLabel(label);
                this.revalidate();
                this.repaint();
            }
            this.selectionListener.accept(this.player, this.selectedCard);
        }

        private void highlightLabel(final CHLabel label) {
            label.setBorder(BorderFactory.createLineBorder(CHStyles.tertiaryColor(), HIGHLIGHT_BORDER));
        }

        private void unHighlightLabel(final CHLabel label) {
            label.setBorder(null);
        }

        private void clearSelection() {
            this.selectedLabel.ifPresent(this::unHighlightLabel);
            this.selectedLabel = Optional.empty();
            this.selectedCard = Optional.empty();
            this.revalidate();
            this.repaint();
        }
    }

    private static final class DiscardPileAreaPanel extends CHPanel {

        private static final long serialVersionUID = 1L;
        private final CHLabel pile;
        private final CHButton reshuffle;
        private final PlayerEnum player;
        private final transient MatchController controller;
        private final transient Function<PlayerEnum, Optional<Card<?>>> selectedCardProvider;
        private final transient Runnable deselectAllCallback;

        DiscardPileAreaPanel(final String position, final PlayerEnum player, final MatchController controller,
                              final Function<PlayerEnum, Optional<Card<?>>> selectedCardProvider,
                              final Runnable deselectAllCallback) {
            super(new BorderLayout());

            this.controller = Objects.requireNonNull(controller, "Controller cannot be null");
            this.player = Objects.requireNonNull(player, "Player must be provided to DiscardPileAreaPanel");
            this.selectedCardProvider = Objects.requireNonNull(selectedCardProvider, "Selected card provider cannot be null");
            this.deselectAllCallback = Objects.requireNonNull(deselectAllCallback, "Deselect callback cannot be null");

            if (!Objects.equals(position, BorderLayout.NORTH) && !Objects.equals(position, BorderLayout.SOUTH)) {
                throw new IllegalArgumentException("Position must be either BorderLayout.NORTH or BorderLayout.SOUTH");
            }

            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CHStyles.primaryColor()),
                BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                                CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));

            this.pile = new DiscardPileLabel();
            this.pile.setPreferredSize(new Dimension(ImageResolver.CARD_WIDTH, ImageResolver.CARD_HEIGHT));
            this.pile.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    discardSelectedCard();
                }
            });
            this.reshuffle = new CHButton("Reshuffle into deck");
            this.reshuffle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if (controller.getTurnPlayer() != player) {
                        return;
                    }
                    controller.reshuffleIntoDeck(player);
                }
            });

            this.add(reshuffle, position);
            this.add(pile, BorderLayout.CENTER);
        }

        void updateCard(final Optional<Card<?>> card) {
            card.ifPresentOrElse(c -> this.pile.setIcon(ImageResolver.resolve(c)), () -> this.pile.setIcon(null));

            this.pile.revalidate();
            this.pile.repaint();
        }

        private void discardSelectedCard() {
            final Optional<Card<?>> selected = this.selectedCardProvider.apply(this.player);
            if (selected.isEmpty() || this.controller.getTurnPlayer() != this.player) {
                return;
            }

            this.controller.discardCard(this.player, selected.get());
            this.deselectAllCallback.run();
        }
    }

    private static final class DiscardPileLabel extends CHLabel {

        private static final long serialVersionUID = 1L;
        private static final int PILE_WIDTH = 66;
        private static final int PILE_HEIGHT = 96;

        DiscardPileLabel() {
            super();
            super.setPreferredSize(new Dimension(PILE_WIDTH, PILE_HEIGHT));
            super.setBackground(CHStyles.primaryColor());
        }
    }
}
