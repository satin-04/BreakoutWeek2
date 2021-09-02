package input;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyPolling {
    
    // Singleton Pattern
    private static KeyPolling uniqueInstance;
    private static Scene scene;
    private final static Set<KeyCode> PRESSED_KEYS = new HashSet<>();
    
    private KeyPolling() {    
    }
    
    public static KeyPolling getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new KeyPolling();
        }
        return uniqueInstance;
    }
    
    public void pollScene(Scene scene) {
        clearKeys();
        removeCurrentKeyHandlers();
        setScene(scene);
    }
    
    private void clearKeys() {
        PRESSED_KEYS.clear();
    }
    
    private void removeCurrentKeyHandlers() {
        if(scene != null) {
            scene.setOnKeyPressed(null);
            scene.setOnKeyReleased(null);
        }
    }
    
    private void setScene(Scene scene) {
        KeyPolling.scene = scene;
        KeyPolling.scene.setOnKeyPressed((keyEvent-> {
        	// System.out.println("Adding key " + keyEvent.getCode());
            PRESSED_KEYS.add(keyEvent.getCode());
        }));
        KeyPolling.scene.setOnKeyReleased((keyEvent-> {
        	// System.out.println("Removing key " + keyEvent.getCode());
            PRESSED_KEYS.remove(keyEvent.getCode());
        }));
    }
    
    public boolean isDown(KeyCode keyCode) {
        return PRESSED_KEYS.contains(keyCode);
    }
}
