package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

public class CharSeer extends Character{

    public CharSeer() {
        super(PrivateGameConstants.CHAR_SEER_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_SEER))));
    }

    @Override
    public String winText() {
        return PrivateGameConstants.CHAR_VILLAGER_WIN_TEXT;
    }

    @Override
    public boolean isSameClass(Object obj) {
        return obj instanceof CharSeer;
    }
}
