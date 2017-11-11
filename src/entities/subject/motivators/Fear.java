package entities.subject.motivators;

public class Fear extends AbsMotivator{
	public Fear(String name, float currentIntensity, float maxIntensity) {
		super(name, currentIntensity, maxIntensity);
	}
	
	public Fear(float maxIntensity) {
		super(maxIntensity);
	}
	
}
