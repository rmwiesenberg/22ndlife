package entities.subject.motivators;

public class Desire extends AbsMotivator{
	public Desire(String name, float currentIntensity, float maxIntensity) {
		super(name, currentIntensity, maxIntensity);
	}
	
	public Desire(float maxIntensity) {
		super(maxIntensity);
	}
	
}
