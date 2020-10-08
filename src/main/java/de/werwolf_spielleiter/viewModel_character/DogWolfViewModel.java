package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.role.RoleDogWolf;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;

import java.util.stream.Collectors;

/**
 * This class handles the fraction choosing of the dog wolf.
 *
 * @author Henrik Möhlmann
 */
public class DogWolfViewModel extends ViewModel {
    private PlayerLayoutViewModel dogWolf;

    /**
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        dogWolf = boardLayoutViewModel.getViewModelList().stream()
                .filter(x -> x.getPlayer().hasRole(new RoleDogWolf()))
                .collect(Collectors.toList()).get(0);
    }

    /**
     * @author Henrik Möhlmann
     */
    public void handleChoosingVillager() {
        next();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void handleChoosingWerewolf() {
        model.getFraction().removeVillager(dogWolf.getPlayer());
        dogWolf.getPlayer().addRole(new RoleWerewolf());
        model.getFraction().addPlayer(dogWolf.getPlayer());
        next();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void next() {
        boardLayoutViewModel.sleepAll();
        scene = GameScene.CUPID;
        nextScene();
    }
}
