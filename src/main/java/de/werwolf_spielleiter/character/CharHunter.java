package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Eric De Ron
 */
public class CharHunter extends Character{
    /**
     * Constructor to create a hunter character
     * @author Eric De Ron
     */
    public CharHunter() {
        super(PrivateGameConstants.CHAR_HUNTER_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_HUNTER))));
    }

    /**
     * @author Eric De Ron
     * @return Text when no fraction wins
     */
    @Override
    public String winText() { return PrivateGameConstants.CHAR_HUNTER_WIN_TEXT; }

    /**
     * @author Eric De Ron
     * @param obj Given object to test
     * @return True if given class is instance of this
     */
    @Override
    public boolean isSameClass(Object obj) { return obj instanceof CharHunter; }
}
