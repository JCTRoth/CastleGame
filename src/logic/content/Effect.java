package logic.content;

public class Effect {
	
	private EffectType effectType;
	private boolean positive;
	
	public Effect(EffectType effectType, boolean positive) {
		this.effectType = effectType;
		this.positive = positive;
	}
	
	public boolean isPositive(){
		return positive;
	}
	
	/**
	 * sets the effect positive with true or negative with false
	 * @param positive
	 */
	public void setPositive(boolean positive){
		this.positive = positive;
	}
	
	public EffectType getEffectType(){
		return effectType;
	}
	
	public void setEffectType(EffectType effectType){
		this.effectType = effectType;
	}
	
	public String getName(){
		String s = effectType.getName();
		if (positive)
			s = "erhöhte" + s;
		else
			s = "niedrigere" + s;
		return s;
	}
	
}
