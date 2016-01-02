package DynamicObjectModule.Animations;
import java.awt.image.BufferedImage;

public class Frame {

    private BufferedImage frame;
    private double duration;

    public Frame(BufferedImage frame, double duration) {
        this.frame = frame;
        this.duration = duration;
    }

    public BufferedImage getFrame() {
        return frame;
    }

    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}