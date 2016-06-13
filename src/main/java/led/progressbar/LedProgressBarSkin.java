package led.progressbar;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.scene.layout.HBox;
import led.Led;

public class LedProgressBarSkin extends BehaviorSkinBase<LedProgressBar, LedProgressBarBehavior> {
    private static final double DEFAULT_LED_SIZE = 10.0;
    //Container
    private HBox hBox;

    //Leds
    private Led[] leds;

    public LedProgressBarSkin(LedProgressBar control) {
        super(control, new LedProgressBarBehavior(control, null));
        initLeds(getSkinnable().getLedCount());
        initEvents();
    }

    private void initEvents() {
        getSkinnable().progressProperty().addListener(observable -> {
            flashLeds(getSkinnable().getProgress());
        });
        getSkinnable().ledCountProperty().addListener(observable -> {
            initLeds(getSkinnable().getLedCount());
            flashLeds(getSkinnable().getProgress());
            setup();
        });
    }

    //LEDs init
    private void initLeds(int ledCount) {
        leds = new Led[ledCount];
        for (int ledNumber = 0; ledNumber < ledCount; ledNumber++) {
            leds[ledNumber] = new Led();
            leds[ledNumber].getStyleClass().add("led");
        }
    }

    //LEDs layout
    private void adjustLedsHeights(double height) {
        if (leds == null) {
            return;
        }
        double ledSize = ledSize(height);
        for (Led led : leds) {
            led.setMinWidth(ledSize);
            led.setMaxWidth(ledSize);
            led.setMinHeight(ledSize);
            led.setMaxHeight(ledSize);
        }
    }

    //LEDs flash
    private void flashLeds(int count) {
        if (leds == null) {
            return;
        }
        if (count < 0) {
            return;
        }
        if (count > leds.length) {
            count = leds.length;
        }
        for (int ledNumber = 0; ledNumber < count; ledNumber++) {
            leds[ledNumber].on();
        }
        for (int ledNumber = count; ledNumber < leds.length; ledNumber++) {
            leds[ledNumber].off();
        }
    }

    private void flashLeds(double part) {
        if (leds == null) {
            return;
        }
        if (part < 0.0) {
            part = 0.0;
        }
        if (part > 1.0) {
            part = 1.0;
        }
        flashLeds((int) (leds.length * part));
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        resize(contentWidth, contentHeight);
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);
    }

    private void resize(double width, double height) {
        if (hBox == null) {
            if (!setup()) {
                return;
            }
        }
        adjustLedsHeights(height);
        getSkinnable().setMinWidth(getLedLength(height));
        hBox.setSpacing(calcSpacing(width, height));
    }

    private boolean setup() {
        if (leds == null) {
            return false;
        }
        hBox = new HBox();
        for (Led led : leds) {
            hBox.getChildren().add(led);
        }
        getChildren().clear();
        getChildren().add(hBox);
        return true;
    }

    private double ledSize(double height) {
        if (height <= 0.0) {
            return DEFAULT_LED_SIZE;
        }
        return height;
    }

    private double getLedLength(double height) {
        if (leds == null) {
            return 0.0;
        }
        return ledSize(height) * leds.length;
    }

    private double calcSpacing(double width, double height) {
        double ledLength = getLedLength(height);
        double freeSpaceLength = width - ledLength;
        return freeSpaceLength / (getSkinnable().getLedCount() - 1);
    }
}
