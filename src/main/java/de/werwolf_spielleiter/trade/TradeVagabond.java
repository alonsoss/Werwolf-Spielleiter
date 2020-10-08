package de.werwolf_spielleiter.trade;

import javafx.scene.image.Image;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

public class TradeVagabond extends Trade{

    /**
     * Creates a new Vagabond object
     * @author Janik Dohrmann
     */
    public TradeVagabond() {
        super("Vagabund");
        setSign(new Image(TRADE_SIGN_VAGABOND));
    }

    /**
     * tests if an object is of class Vagabond
     * @author Janik Dohrmann
     * @param obj the to test
     * @return true if the object is of type Vagabond
     *          false, else
     */
    @Override
    public boolean isSameClass(Object obj) {
        return obj instanceof TradeVagabond;
    }
}
