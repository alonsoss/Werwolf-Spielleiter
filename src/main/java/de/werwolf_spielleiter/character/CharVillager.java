package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 * @author Janik Dohrmann
 */
public class CharVillager extends Character{

    /**
     * Constructor to use to create a villager character.
     * @author Henrik Möhlmann
     */
    public CharVillager() {
        super(PrivateGameConstants.CHAR_VILLAGER_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_VILLAGER))));
    }

    /**
     * @author Janik Dohrmann
     * @return
     */
    @Override
    public String winText() {
        return PrivateGameConstants.CHAR_VILLAGER_WIN_TEXT;
    }

    /**
     * @author Janik Dohrmann
     * @param obj
     * @return
     */
    public boolean isSameClass(Object obj) {
        return obj instanceof CharVillager;
    }
}
