package entities.subject.motivators;

public class Curiosity extends AbsMotivator{
	public Curiosity(String name, float currentIntensity, float maxIntensity) {
		super(name, currentIntensity, maxIntensity);
	}
	
	public Curiosity(float maxIntensity) {
		super(maxIntensity);
	}
	
}
