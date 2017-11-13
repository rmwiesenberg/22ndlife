package entities.subject.motivators;

public abstract class AbsMotivator {
	
	protected String name;
	protected float maxIntensity;
	protected float currentIntensity;
	
	public AbsMotivator(String name, float currentIntensity, float maxIntensity) {
		this.name = name;
		this.currentIntensity = currentIntensity;
		this.maxIntensity = maxIntensity;
	}
	
	public AbsMotivator(float maxIntensity) {
		this.maxIntensity = maxIntensity;
		this.currentIntensity = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public float getCurrentIntensity() {
		return currentIntensity;
	}
	
	public float getMaxIntensity() {
		return maxIntensity;
	}
	
	public void changeCurrentIntensity(float intensityToChange){
		
		float val = (currentIntensity + intensityToChange);
		
		if (val < 0) {
			currentIntensity = 0;
		}else{
			currentIntensity = val;
		}
	}
	
}
