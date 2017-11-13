package entities.subject.motivators;

public class Smell extends AbsMotivator{
	public Smell(String name, float currentIntensity, float maxIntensity) {
		super(name, currentIntensity, maxIntensity);
	}
	
	public Smell(float maxIntensity) {
		super(maxIntensity);
	}
	
}
