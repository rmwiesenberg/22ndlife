package entities.subject.motivators;

public class Taste extends AbsMotivator{
	public Taste(String name, float currentIntensity, float maxIntensity) {
		super(name, currentIntensity, maxIntensity);
	}
	
	public Taste(float maxIntensity) {
		super(maxIntensity);
	}
	
}
