
import java.util.ArrayList;

public class Scene {
    private ArrayList<Displayable> displayables;
    private ArrayList<LightSource> lightSources;

    public Scene() {
        this.displayables = new ArrayList<>();
        this.lightSources = new ArrayList<>();
    }

    public void addDisplayable(Displayable displayable) {
        this.displayables.add(displayable);
    }
    public void addLightSource(LightSource lightSource) {
        this.lightSources.add(lightSource);
    }

    public ArrayList<Displayable> getDisplayables() {
        return displayables;
    }

    public ArrayList<LightSource> getLightSources() {
        return lightSources;
    }
}
