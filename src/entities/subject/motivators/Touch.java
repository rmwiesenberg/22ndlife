package entities.subject.motivators;

public class Touch extends AbsMotivator{
	public Touch(String name, float currentIntensity, float maxIntensity) {
		super(name, currentIntensity, maxIntensity);
	}
	
	public Touch(float maxIntensity) {
		super(maxIntensity);
	}
	
}
