package entities.subject.motivators;

public class SelfPreservation extends AbsMotivator{
	public SelfPreservation(String name, float currentIntensity, float maxIntensity) {
		super(name, currentIntensity, maxIntensity);
	}
	
	public SelfPreservation(float maxIntensity) {
		super(maxIntensity);
	}
	
}
