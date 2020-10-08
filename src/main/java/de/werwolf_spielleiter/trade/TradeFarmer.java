package de.werwolf_spielleiter.trade;

import javafx.scene.image.Image;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.TRADE_SIGN_FARMER;

public class TradeFarmer extends Trade {
    public TradeFarmer() {
        super("Bauer");
        setSign(new Image(TRADE_SIGN_FARMER));
    }

    @Override
    public boolean isSameClass(Object obj) {
        return obj instanceof TradeFarmer;
    }
}
