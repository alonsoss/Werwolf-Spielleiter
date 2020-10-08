package de.werwolf_spielleiter.trade;

import javafx.scene.image.Image;
import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

public class TradeConfessor extends Trade{
    /**
     *
     * @author Janik Dohrmann
     */
    public TradeConfessor() {
        super("Beichtvater");
        setSign(new Image(TRADE_SIGN_CONFESSOR));
    }

    /**
     * tests if an object is of class Vagabond
     * @author Janik Dohrmann
     * @param obj the to test
     * @return true if the object is of type Confessor
     *          false, else
     */
    @Override
    public boolean isSameClass(Object obj) {
        return obj instanceof TradeConfessor;
    }
}
