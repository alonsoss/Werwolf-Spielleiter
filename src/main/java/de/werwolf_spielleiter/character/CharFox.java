package de.werwolf_spielleiter.character;

import javafx.scene.image.Image;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

/**
 * @author Matthias Hinrichs
 */
public class CharFox extends Character {
    private boolean abilityUsable = true;
    private boolean checkedThisRound = false;

    /**
     * Constructor to create fox character
     * @author Matthias Hinrichs
     */
    public CharFox() {
        super(CHAR_FOX_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(CHAR_CARD_FRONT_FOX))));
    }

    @Override
    public String winText() {
        return CHAR_VILLAGER_WIN_TEXT;
    }

    @Override
    public boolean isSameClass(Object obj) {
        return obj instanceof CharFox;
    }

    /**
     * Method to get if the character can check other players
     * @return abilityUsable boolean representing if the character can check other players
     * @author Matthias Hinrichs
     */
    public boolean canCheck() {
        return abilityUsable;
    }

    /**
     * Sets the boolean abilityUsable to false to prevent further checks by the character
     * @author Matthias Hinrichs
     */
    public void forbidChecks() {
        abilityUsable = false;
    }

    public boolean hasCheckedThisRound() {
        return checkedThisRound;
    }

    public void setCheckedThisRound(boolean checkedThisRound) {
        this.checkedThisRound = checkedThisRound;
    }
}
