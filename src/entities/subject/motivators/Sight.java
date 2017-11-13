package entities.subject.motivators;

public class Sight extends AbsMotivator{
	public Sight(String name, float currentIntensity, float maxIntensity) {
		super(name, currentIntensity, maxIntensity);
	}
	
	public Sight(float maxIntensity) {
		super(maxIntensity);
	}
	
}
