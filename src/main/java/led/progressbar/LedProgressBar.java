package led.progressbar;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ProgressBar;

public class LedProgressBar extends ProgressBar {
    private static final int DEFAULT_LED_COUNT = 10;
    private static final double DEFAULT_MIN_WIDTH = 75.0;
    private static final double DEFAULT_MAX_WIDTH = 200.0;
    private static final double DEFAULT_LED_SIZE = 10.0;
    private static final double DEFAULT_MIN_HEIGHT = DEFAULT_LED_SIZE;
    private static final double DEFAULT_MAX_HEIGHT = DEFAULT_LED_SIZE;
    //...
    private IntegerProperty ledCount;

    public LedProgressBar() {
        super();
        //...
        setMinWidth(DEFAULT_MIN_WIDTH);
        setMaxWidth(DEFAULT_MAX_WIDTH);
        //...
        setMinHeight(DEFAULT_MIN_HEIGHT);
        setMaxHeight(DEFAULT_MAX_HEIGHT);
        //...
        getStyleClass().add("ledprogressbar");
        initialize();
        setSkin(new LedProgressBarSkin(this));
    }

    private void initialize() {
        ledCount = new SimpleIntegerProperty(DEFAULT_LED_COUNT);
    }

    public int getLedCount() {
        return ledCount.get();
    }

    public IntegerProperty ledCountProperty() {
        return ledCount;
    }

    public void setLedCount(int ledCount) {
        this.ledCount.set(ledCount);
    }
}
