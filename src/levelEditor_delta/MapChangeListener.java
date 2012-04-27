package levelEditor_delta;



interface MapChangeListener {
	public void mapChanging(boolean major);
	public void mapChanged(boolean major);
}
