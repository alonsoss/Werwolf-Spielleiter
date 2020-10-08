package de.werwolf_spielleiter.trade;

import javafx.scene.image.Image;

public abstract class Trade {
    private String name;
    private Image sign;

    /**
     *
     * @param name the name of the Trade
     * @author Janik Dohrmann
     */
    public Trade(String name) {
        this.name = name;
    }

    public abstract boolean isSameClass(Object obj);

    /**
     * @author Janik Dohrmann
     * @return the name as String
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * @author Janik Dohrmann
     * @return the sign of the trade
     */
    public Image getSign() {
        return sign;
    }

    /**
     * @author Janik Dohrmann
     * @param sign teh sign of the trade
     */
    public void setSign(Image sign) {
        if (sign != null) {
            this.sign = sign;
        }
    }

    /**
     * @author Janik Dohrmann
     * @param o the object to test
     * @return true if o is equal
     */
    @Override
    public boolean equals(Object o) {
        return isSameClass(o);
    }
}
