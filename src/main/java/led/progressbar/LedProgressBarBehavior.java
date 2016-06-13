package led.progressbar;

import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.KeyBinding;

import java.util.List;

public class LedProgressBarBehavior extends BehaviorBase<LedProgressBar> {
    public LedProgressBarBehavior(LedProgressBar control, List<KeyBinding> keyBindings) {
        super(control, keyBindings);
    }
}
