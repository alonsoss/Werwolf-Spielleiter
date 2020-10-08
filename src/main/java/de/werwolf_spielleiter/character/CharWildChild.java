package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Eric De Ron
 */
public class CharWildChild extends Character{
    /**
     * Constructor to create a wild child character
     * @author Eric De Ron
     */
    public CharWildChild() {
        super(PrivateGameConstants.CHAR_WILD_CHILD_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_WILD_CHILD))));
    }

    /**
     * @author Eric De Ron
     * @return Wild child doesn't need a specific
     * win text, it uses the win text of it's current
     * fraction
     */
    @Override
    public String winText() { return ""; }

    /**
     * @author Eric De Ron
     * @param obj Given object to test
     * @return True if given class is instance of this
     */
    @Override
    public boolean isSameClass(Object obj) { return obj instanceof CharWildChild; }
}
