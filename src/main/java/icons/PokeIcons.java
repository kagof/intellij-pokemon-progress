package icons;

import com.intellij.ui.AnimatedIcon;
import com.intellij.ui.IconManager;
import java.util.List;
import javax.swing.Icon;

public interface PokeIcons {
    Icon PokeballStep1 = IconManager.getInstance()
        .getIcon("/com/kagof/intellij/plugins/pokeprogress/icons/pokeball_step_1.svg", PokeIcons.class.getClassLoader());
    Icon PokeballStep2 = IconManager.getInstance()
        .getIcon("/com/kagof/intellij/plugins/pokeprogress/icons/pokeball_step_2.svg", PokeIcons.class.getClassLoader());
    Icon PokeballStep3 = IconManager.getInstance()
        .getIcon("/com/kagof/intellij/plugins/pokeprogress/icons/pokeball_step_3.svg", PokeIcons.class.getClassLoader());
    Icon PokeballStep4 = IconManager.getInstance()
        .getIcon("/com/kagof/intellij/plugins/pokeprogress/icons/pokeball_step_4.svg", PokeIcons.class.getClassLoader());
    Icon PokeballStep5 = IconManager.getInstance()
        .getIcon("/com/kagof/intellij/plugins/pokeprogress/icons/pokeball_step_5.svg", PokeIcons.class.getClassLoader());
    Icon PokeballStep6 = IconManager.getInstance()
        .getIcon("/com/kagof/intellij/plugins/pokeprogress/icons/pokeball_step_6.svg", PokeIcons.class.getClassLoader());
    Icon PokeballStep7 = IconManager.getInstance()
        .getIcon("/com/kagof/intellij/plugins/pokeprogress/icons/pokeball_step_7.svg", PokeIcons.class.getClassLoader());
    Icon PokeballStep8 = IconManager.getInstance()
        .getIcon("/com/kagof/intellij/plugins/pokeprogress/icons/pokeball_step_8.svg", PokeIcons.class.getClassLoader());

    Icon SpinningPokeball = new AnimatedIcon(130, getPokeballIcons().toArray(new Icon[0]));

    static List<Icon> getPokeballIcons() {
        return List.of(
            PokeballStep1,
            PokeballStep2,
            PokeballStep3,
            PokeballStep4,
            PokeballStep5,
            PokeballStep6,
            PokeballStep7,
            PokeballStep8);
    }
}
