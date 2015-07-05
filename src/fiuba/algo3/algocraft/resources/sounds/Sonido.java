package fiuba.algo3.algocraft.resources.sounds;

import java.applet.AudioClip;

public class Sonido {
	AudioClip sonido;

	public void init() {
    	sonido = java.applet.Applet.newAudioClip(getClass().getResource("/fiuba/algo3/algocraft/resources/sounds/Mapa.wav"));
	}

    public void start() {
        sonido.loop();
        }

    public void stop() {
        sonido.stop();
        }
    }