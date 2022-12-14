package it.uniroma1.textadv.javaFxInventory;

import static it.uniroma1.textadv.javaFxButtons.ButtonController.BUTTON_IMAGE_HEIGHT;
import static it.uniroma1.textadv.javaFxButtons.ButtonController.BUTTON_IMAGE_WIDTH;
import static it.uniroma1.textadv.javaFxMain.ImageFxController.calculateImageFxDimX;
import static it.uniroma1.textadv.javaFxMain.ImageFxController.calculateImageFxDimY;
import static it.uniroma1.textadv.javaFxMain.ImageFxController.calculateX;
import static it.uniroma1.textadv.javaFxMain.SceneController.BUTTON_TESTING_CSS;

import java.util.ArrayList;
import java.util.List;

import it.uniroma1.textadv.javaFxMain.SceneController;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Questa classe si occupa di gestire le risorse e la logica di ogni possibile inventario grafico del giocatore.
 * L'inventario grafico ha un panello, una griglia fatta di slot (InventoryButton) e due TextArea una Top e una Bottom.
 * @author Federico Ziegler
 *
 */
public class InventoryGiocatoreFx
{
	// TextAreaTop default Texts:
	public static final String TEXT_AREA_TOP_INVENTORY_OPENED_TEXT = "Premi l'icona della mano per\nuscire dall'inventario.\nPassa il cursore su un\noggetto per leggerne la\ndescrizione:";
	public static final String TEXT_AREA_TOP_INVENTORY_APRI_TEXT = "Premi l'icona della mano per\naprire qualcosa senza og-\ngetti.\nPremi un oggetto per apri-\nre qualcosa con quell'og-\ngetto.";
	public static final String TEXT_AREA_TOP_INVENTORY_ROMPI_TEXT = "Premi l'icona della mano per\nrompere qualcosa senza\noggetti.\nPremi un oggetto per rom-\npere qualcosa con quell'og-\ngetto.";
	public static final String TEXT_AREA_TOP_INVENTORY_DAI_TEXT = "Premi l'icona della mano per\nuscire.\nPremi un oggetto per dare\nquell'oggetto a qualcuno.";
	public static final String TEXT_AREA_TOP_INVENTORY_USA_TEXT = "Premi l'icona della mano per\nusare qualcosa nella stan-\nza.\nPremi un oggetto per usa-\nre quell'oggetto su qualco-\nsa.";
	public static final String TEXT_AREA_TOP_FORGE_TEXT = "Glondir: mi servono blueprint\nper forgiare.\nPortami blueprint e premi l'og-\ngetto che vuoi che io ti forgi.";
	
	// Inventory Path
	public static final String INVENTORY_PATH = "/Sprites/Inventory/";
	
	// Inventory calcoli dimensione
	private static final double INVENTORY_PANEL_DIM = 1000;
	private static final double INVENTORY_TEXT_BOX_DIM = 380;
	
    private static final double INVENTORY_PANEL_X = calculateX(calculateImageFxDimX(INVENTORY_PANEL_DIM));
    private static final double INVENTORY_PANEL_Y = calculateImageFxDimY(INVENTORY_PANEL_DIM);
    private static final double INVENTORY_TEXT_BOX_X = calculateX(calculateImageFxDimX(INVENTORY_TEXT_BOX_DIM));
    private static final double INVENTORY_TEXT_BOX_Y = calculateImageFxDimY(INVENTORY_TEXT_BOX_DIM);
    
	// Inventory Png
	private static final String INVENTORY_PANEL_PNG = INVENTORY_PATH + "InventoryPanel.png";
	private static final String INVENTORY_TEXT_BOX_PNG = INVENTORY_PATH + "InventoryTextBox.png";
	// Forge Png
	private static final String FORGE_PANEL_PNG = INVENTORY_PATH + "InventoryPanelForge.png";
	private static final String FORGE_TEXT_BOX_PNG = INVENTORY_PATH + "ForgeTextBox.png";
    
	// Inventory Image
    private static final Image INVENTORY_PANEL_IMAGE = new Image(InventoryGiocatoreFx.class.getResource(INVENTORY_PANEL_PNG).toExternalForm(), INVENTORY_PANEL_X, INVENTORY_PANEL_Y, true, false);
    public static final Image INVENTORY_TEXT_BOX_IMAGE = new Image(InventoryGiocatoreFx.class.getResource(INVENTORY_TEXT_BOX_PNG).toExternalForm(), INVENTORY_TEXT_BOX_X, INVENTORY_TEXT_BOX_Y, true, false);
    // Forge Image
    private static final Image FORGE_PANEL_IMAGE = new Image(InventoryGiocatoreFx.class.getResource(FORGE_PANEL_PNG).toExternalForm(), INVENTORY_PANEL_X, INVENTORY_PANEL_Y, true, false);
    public static final Image FORGE_TEXT_BOX_IMAGE = new Image(InventoryGiocatoreFx.class.getResource(FORGE_TEXT_BOX_PNG).toExternalForm(), INVENTORY_TEXT_BOX_X, INVENTORY_TEXT_BOX_Y, true, false);
    
	// Inventory ImageView
	public static final ImageView INVENTORY_PANEL_IMAGE_VIEW = new ImageView(INVENTORY_PANEL_IMAGE);
	public static final ImageView INVENTORY_TEXT_BOX_IMAGE_VIEW = new ImageView(INVENTORY_TEXT_BOX_IMAGE);
	// Forge ImageView
	public static final ImageView FORGE_PANEL_IMAGE_VIEW = new ImageView(FORGE_PANEL_IMAGE);
	public static final ImageView FORGE_TEXT_BOX_IMAGE_VIEW = new ImageView(FORGE_TEXT_BOX_IMAGE);
    /**
     * L'immagine del panello.
     */
	private ImageView inventoryPanel;
	/**
	 * L'immagine del "box" del testo.
	 */
	private ImageView inventoryTextBoxImageView;
	/**
	 * La griglia grafica.
	 */
	private GridPane inventoryGrid = new GridPane();
	/**
	 * La lista degli slot.
	 */
	private List<InventoryButton> buttonList = new ArrayList<>();
	/**
	 * La TextArea Top.
	 */
	private TextArea textAreaTop = new TextArea();
	/**
	 * La TextArea Bottom
	 */
	private TextArea textAreaBottom = new TextArea();
	/**
	 * La lista delle TextArea (Top, Bottom).
	 */
	private List<TextArea> textAreaList = new ArrayList<>(List.of(textAreaTop, textAreaBottom));
	/**
	 * Boolean per indicare se l'inventario è connesso all'inventario testuale (logico) del giocatore o meno.
	 */
	private boolean isPlayerInventory = false;
	/**
	 * Numero righe nella griglia.
	 */
	private int numRighe = 3;
	/**
	 * Numero colonne nella griglia.
	 */
	private int numColonne = 5;
	/**
	 * Numero slot occupati (inizialmente a 0).
	 */
	private int numeroSlotOccupati = 0;
	/**
	 * Numero slot dell'inventario grafico (numero righe per numero colonne).
	 */
	private int numeroSlot = numRighe * numColonne;
	/**
	 * Costrutto per l'inventario grafico.
	 * Necessariamente avrà la lista degli slot creata in precedenza, un panello grafico e un box grafico per il testo.
	 * @param buttonList
	 * @param inventoryPanel
	 * @param inventoryTextBoxImageView
	 * @throws InventoryFxException
	 */
	public InventoryGiocatoreFx(List<InventoryButton> buttonList, ImageView inventoryPanel, ImageView inventoryTextBoxImageView) throws InventoryFxException
	{	
		setupInventoryGiocatoreFx(buttonList);
		
		this.inventoryPanel = inventoryPanel;
		this.inventoryTextBoxImageView = inventoryTextBoxImageView;
	}
	/**
	 * Metodo che inizializza la logica dell'inventario o meglio crea la griglia a partire della lista di slot e
	 * se troppo corta lancia un eccezione.
	 * Gestita la griglia mette l'ultimo slot a locked, fa il setup delle 2 TextArea e setta il cursore grafico
	 * da mettere a display sugli slot a contatto con il mouse.
	 * @param buttonList
	 * @throws InventoryFxException
	 */
	private void setupInventoryGiocatoreFx(List<InventoryButton> buttonList) throws InventoryFxException
	{
		if(buttonList.size() < numeroSlot)
			throw new InventoryFxException("Size lista bottoniInventario troppo corta!");
		
		inventoryGrid.setHgap(15);
		inventoryGrid.setVgap(15);
		
		List<InventoryButton> listaSlotRiga = new ArrayList<>();
		int i = 0;
		for(int j = 0; j < numeroSlot; j++)
		{
			InventoryButton slot = buttonList.get(j);
			slot.setButtonNumber(j);
			this.buttonList.add(slot);
			listaSlotRiga.add(slot);
			if((j + 1) % numColonne == 0)
			{
				InventoryButton[] arraySlotRiga = new InventoryButton[listaSlotRiga.size()];
				arraySlotRiga = listaSlotRiga.toArray(arraySlotRiga);
				inventoryGrid.addRow(i, arraySlotRiga);
				
				listaSlotRiga.clear();
				i++;
			}
		}
		
		getLastSlot().setSlotLock(true);
		
		setupTextAreas();
		
		this.buttonList.forEach(buttonInventory -> buttonInventory.setCursor(SceneController.IMAGE_CURSOR_HAND));
	}
	/**
	 * Metodo che inizializza la logica e lo Style delle 2 TextArea.
	 */
	private void setupTextAreas()
	{
		textAreaList.forEach(textArea -> textArea.getStylesheets()
				.add(BUTTON_TESTING_CSS));

		textAreaList.forEach(textArea -> textArea.setStyle(SceneController.FX_FONT_SIZE_STRING + SceneController.TEXT_SIZE_INVENTORY_BOX));
		
		textAreaList.forEach(textArea -> textArea.setEditable(false));
	}
	/**
	 * Getter che restituisce l'ultimo slot dell'inventario.
	 * @return ultimo bottone della buttonList.
	 */
	public InventoryButton getLastSlot()
	{
		return buttonList.get(buttonList.size() - 1);
	}
	/**
	 * Getter per il panello grafico.
	 * @return inventoryPanel.
	 */
	public ImageView getInventoryPanel()
	{
		return inventoryPanel;
	}
	/**
	 * Getter per l'immagine del textBox.
	 * @return nventoryTextBoxImageView.
	 */
	public ImageView getInventoryTextBoxImageView()
	{
		return inventoryTextBoxImageView;
	}
	/**
	 * Getter della griglia.
	 * @return inventoryGrid.
	 */
	public GridPane getInventoryGrid()
	{
		return inventoryGrid;
	}
	/**
	 * Getter per la lista degli slot.
	 * @return buttonList.
	 */
	public List<InventoryButton> getButtonList()
	{
		return buttonList;
	}
	/**
	 * Getter per il numero degli slot occupati (locked).
	 * @return numeroSlotOccupati.
	 */
	public int getNumeroSlotOccupati()
	{
		return numeroSlotOccupati;
	}
	/**
	 * Getter per la lista delle TextArea.
	 * @return textAreaList.
	 */
	public List<TextArea> getTextAreaList()
	{
		return textAreaList;
	}
	/**
	 * Getter per la TextArea Top.
	 * @return textAreaTop.
	 */
	public TextArea getTextAreaTop()
	{
		return textAreaTop;
	}
	/**
	 * Getter per la TextArea Bottom.
	 * @return textAreaBottom.
	 */
	public TextArea getTextAreaBottom()
	{
		return textAreaBottom;
	}
	/**
	 * Setter per la TextArea Top.
	 * @param text
	 */
	public void setTextAreaTop(String text)
	{
		textAreaTop.setText(text);
	}
	/**
	 * Setter per la TextArea Bottom.
	 * @param text
	 */
	public void setTextAreaBottom(String text)
	{
		textAreaBottom.setText(text);
	}
	/**
	 * Getter per la booleana isPlayerInventory.
	 * @return isPlayerInventory.
	 */
	public boolean getIsPlayerInventory()
	{
		return isPlayerInventory;
	}
	/**
	 * Setter che pone a true la booleana isPlayerInventory.
	 */
	public void setIsPlayerInventory()
	{
		isPlayerInventory = true;
	}
	/**
	 * Metodo che incrementa di uno l'intero che conta numero di slot occupati nell'inventario.
	 */
	public void incrementaNumeroSlotOccupati()
	{
		numeroSlotOccupati++;
	}
	/**
	 * Metodo che inizializza il layout della griglia dell'inventario.
	 */
	public void setLayoutForInventoryGiocatoreGridFx()
	{
		inventoryGrid.setLayoutX(inventoryPanel.getLayoutX() + BUTTON_IMAGE_WIDTH * 2 + BUTTON_IMAGE_WIDTH / 1.7);
		inventoryGrid.setLayoutY(inventoryPanel.getLayoutY() + BUTTON_IMAGE_HEIGHT / 1.3);
	}
	/**
	 * Metodo che decrementa di uno il numero di slot occupati (locked).
	 * Per safety measure lancia un eccezione se il numero di slot occupati è minore di 0.
	 * @throws InventoryFxException
	 */
	public void decrementaNumeroSlotOccupati() throws InventoryFxException
	{	
		numeroSlotOccupati--;
		
		if(numeroSlotOccupati < 0)
			throw new InventoryFxException("Numero slot occupati minore di 0!");
	}
}
