package DynamicObjectModule.Animations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	public static final int INFINITE = -1;
	private double frameCount; // Counts ticks for change
	private double frameDelay; // frame delay 1-12 (You will have to play around
								// with this)
	private int currentFrame; // animations current frame
	private int animationDirection; // animation direction (i.e counting forward
									// or backward)
	private int totalFrames; // total amount of frames for your animation
	private boolean stopped; // has animations stopped
	private int loopCount;

	private List<Frame> frames = new ArrayList<Frame>(); // Arraylist of frames

	public Animation(List<BufferedImage> frames, double frameDelay) {
		this.frameDelay = frameDelay;
		this.stopped = true;
		for (int i = 0; i < frames.size(); i++) {
			addFrame(frames.get(i), frameDelay);
		}
		frameCount = 0;
		this.frameDelay = frameDelay;
		currentFrame = 0;
		animationDirection = 1;
		totalFrames = this.frames.size();
		assert totalFrames > 0;
		loopCount = 0;
	}

	public void start() {
		if (!stopped) {
			return;
		}

		if (frames.size() == 0) {
			return;
		}

		stopped = false;
	}

	public void loop() {
		loopCount = INFINITE;
		start();
	}

	public void loop(int count) {
		assert (count > 0);
		loopCount = count;
		start();
	}

	public void stop() {
		if (frames.size() == 0) {
			return;
		}

		stopped = true;
	}
	
	public boolean isStopped() {
		return stopped;
	}

	public void restart() {
		if (frames.size() == 0) {
			return;
		}

		stopped = false;
		currentFrame = 0;
	}

	public void reset() {
		this.stopped = true;
		this.frameCount = 0;
		this.currentFrame = 0;
	}

	private void addFrame(BufferedImage frame, double duration) {
		if (duration <= 0) {
			System.err.println("Invalid duration: " + duration);
			throw new RuntimeException("Invalid duration: " + duration);
		}

		frames.add(new Frame(frame, duration));
		currentFrame = 0;
	}

	public BufferedImage getImage() {

		return frames.get(currentFrame).getFrame();
	}

	public void update() {
		if (!stopped) {
			frameCount++;
			if (frameCount > frameDelay) {
				frameCount = 0;
				currentFrame += animationDirection;

				if (currentFrame > totalFrames - 1) {
					if (loopCount > 0) {
						loopCount -= 1;
					}

					if (loopCount == INFINITE || loopCount > 0) {
						restart();
					} else {
						currentFrame = totalFrames - 1;
						stop();
					}
				} else if (currentFrame < 0) {
					currentFrame = totalFrames - 1;
				}
			}
		}

	}
}
