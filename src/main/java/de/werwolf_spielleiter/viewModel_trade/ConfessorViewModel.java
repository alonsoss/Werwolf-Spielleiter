package de.werwolf_spielleiter.viewModel_trade;

import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.trade.Trades;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfessorViewModel extends ViewModel {
    @FXML
    public Button buttonBack;

    private boolean noTarget = true;
    private Player target = null;

    @Override
    public void getStart() {
        buttonBack.setDisable(true);
    }

    /**
     * @author Janik Dohrmann
     * @param playerLayoutViewModel the ViewModel of the Player that was clicked
     */
    public void handleClick(PlayerLayoutViewModel playerLayoutViewModel) {
        if (noTarget) {
            target = playerLayoutViewModel.getPlayer();
            Player confessor = Trades.getInstance().getConfessor();
            if (!target.equals(confessor)) {
                target.revealCharacterCard(true);
                noTarget = false;
                buttonBack.setDisable(false);
            }
        }
        playerLayoutViewModel.resetClick();
    }

    public void back(ActionEvent actionEvent) {
        if (!boardLayoutViewModel.isCardsRevealed() && target != null) {
            target.revealCharacterCard(false);
        }
        if (boardLayoutViewModel.isJobsRevealed()) {
            target.showHideJobs(true, boardLayoutViewModel.isCardsRevealed());
        }
        scene = view.getJumpBackScene();
        noTarget = true;
        model.setAllowConfession(false);
        boardLayoutViewModel.disableConfessorMenu();
        nextScene();
    }
}
