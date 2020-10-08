package de.werwolf_spielleiter.character;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 */
public class CharLittleGirl extends Character {

    /**
     * Constructor to create a little girl character.
     * @author Henrik Möhlmann
     */
    public CharLittleGirl() {
        super(CHAR_LITTLE_GIRL_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(CHAR_CARD_FRONT_LITTLE_GIRL))));
    }

    /**
     * @author Henrik Möhlmann
     * @return the winning text
     * @author Henrik Möhlmann
     */
    @Override
    public String winText() {
        return CHAR_VILLAGER_WIN_TEXT;
    }

    /**
     *
     * @param obj object to check if it is instance of CharLittleGirl
     * @return true if obj instanceof CharLittleGirl
     *          false otherwise
     * @author Henrik Möhlmann
     */
    @Override
    public boolean isSameClass(Object obj) {
        return obj instanceof CharLittleGirl;
    }
}
