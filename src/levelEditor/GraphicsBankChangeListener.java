package levelEditor;

public interface GraphicsBankChangeListener {

	public void tilesetUpdated(GraphicsBank bank);

	public void tileRemoved(GraphicsBank bank, Tile removed);

	public void tileAdded(GraphicsBank bank, Tile added);
}
