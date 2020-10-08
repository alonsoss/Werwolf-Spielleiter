package de.werwolf_spielleiter.character;

import javafx.scene.image.Image;

/**
 * This class represents a Character in the Game
 * @author Henrik Möhlmann
 */
public abstract class Character {
    private String name;
    private Image characterCardBack;
    private Image characterCardFront;

    /**
     * Constructor
     * @author Henrik Möhlmann
     * @param name of the character (ex. Werwolf)
     */
    public Character(String name) {
        this.name = name;
    }

    public abstract String winText();
    public abstract boolean isSameClass(Object obj);

    /**
     * @author Henrik Möhlmann
     * @return the name of the character
     */
    public String getName() {
        return this.name;
    }

    /**
     * @author Henrik Möhlmann
     * @return the character cards back image
     */
    public Image getCharacterCardBack() {
        return characterCardBack;
    }

    /**
     * Set the character cards back image.
     * @author Henrik Möhlmann
     * @param characterCardBack is the cards back image to be set
     */
    public void setCharacterCardBack(Image characterCardBack) {
        this.characterCardBack = characterCardBack;
    }

    /**
     * Set the character cards front image
     * @author Henrik Möhlmann
     * @param characterCardFront is the cards front image to be set
     */
    public void setCharacterCardFront(Image characterCardFront) {
        this.characterCardFront = characterCardFront;
    }

    /**
     * @author Henrik Möhlmann
     * @return the character cards front image
     */
    public Image getCharacterCardFront() {
        return characterCardFront;
    }

    /**
     * @return the characters name as string
     * @author Henrik Möhlmann
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Get a string representation of the character.
     * @author Henrik Möhlmann
     * @return string representation
     */
    public String print() {
        return "Char<" + this.name + ">";
    }
}
