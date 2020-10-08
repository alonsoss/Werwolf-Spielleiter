package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 */
public class CharDogWolf extends Character {

    /**
     * Constructor to create a dog wolf character.
     * @author Henrik Möhlmann
     */
    public CharDogWolf() {
        super(PrivateGameConstants.CHAR_DOG_WOLF_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_DOG_WOLF))));
    }

    /**
     * @author Henrik Möhlmann
     * @return dog wolf does not need a specific win text, it uses the win text of it's current fraction.
     */
    @Override
    public String winText() {
        return "";
    }

    /**
     * @author Henrik Möhlmann
     * @param obj - given object to test
     * @return true if given objects class is instance of this
     */
    public boolean isSameClass(Object obj) {
        return obj instanceof CharDogWolf;
    }
}
