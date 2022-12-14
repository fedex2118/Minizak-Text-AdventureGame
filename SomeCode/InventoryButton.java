package it.uniroma1.textadv.javaFxInventory;

import static it.uniroma1.textadv.javaFxInventory.InventoryGiocatoreFx.INVENTORY_PATH;
import static it.uniroma1.textadv.javaFxMain.ImageFxController.setInventorySlotXY;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/**
 * Questa classe rappresenta i bottoni (slot) di un inventario grafico qualunque esso sia.
 * Questi bottoni hanno poca utilità al di fuori di una griglia di un inventario grafico e ad essi vengono associato degli
 * OggettiInventarioGrafici che altro non rappresentano che oggetti o personaggi grafici del mondo di gioco solo che hanno
 * delle immagini legate allo slot dell'inventario cosi da permettere più funzionalità all'inventario grafico.
 * Qui vi sono tutte le costanti che rappresentano le componenti degli slot e i loro png.
 * La classe estende i ToggleButton e ne a aggiunge non poche nuove funzionalità.
 * @author Federico Ziegler
 *
 */
public class InventoryButton extends ToggleButton
{	
	// SlotIcon calcoli dimensione
    public static final double INVENTORY_SLOT_DIM = 100;
    
    public static final double INVENTORY_SLOT_X = setInventorySlotXY(INVENTORY_SLOT_DIM); // -3 // + 6
    public static final double INVENTORY_SLOT_Y = setInventorySlotXY(INVENTORY_SLOT_DIM); // -3 // + 6
	
	// SlotIcon Png
	private static final String INVENTORY_SLOT_PNG = INVENTORY_PATH + "InventorySlot.png";
	private static final String INVENTORY_SLOT_HOVERED_PNG = INVENTORY_PATH + "InventorySlotHovered.png";
	private static final String INVENTORY_SLOT_HAND_PNG = INVENTORY_PATH + "SlotHand.png";
	private static final String INVENTORY_SLOT_HAND_HOVERED_PNG = INVENTORY_PATH + "SlotHandHovered.png";
	private static final String FORGE_SLOT_PNG = INVENTORY_PATH + "ForgeSlot.png";
	private static final String FORGE_SLOT_HOVERED_PNG = INVENTORY_PATH + "ForgeSlotHovered.png";
	private static final String FORGE_SLOT_HAND_PNG = INVENTORY_PATH + "SlotHandForge.png";
	private static final String FORGE_SLOT_HAND_HOVERED_PNG = INVENTORY_PATH + "SlotHandForgeHovered.png";
	
	// SlotIcon Image
    public static final Image INVENTORY_SLOT_IMAGE = new Image(InventoryButton.class.getResource(INVENTORY_SLOT_PNG).toExternalForm(), INVENTORY_SLOT_X, INVENTORY_SLOT_Y, true, false);
    public static final Image INVENTORY_SLOT_HOVERED_IMAGE = new Image(InventoryButton.class.getResource(INVENTORY_SLOT_HOVERED_PNG).toExternalForm(), INVENTORY_SLOT_X, INVENTORY_SLOT_Y, true, false);
    
    public static final Image INVENTORY_SLOT_HAND_IMAGE = new Image(InventoryButton.class.getResource(INVENTORY_SLOT_HAND_PNG).toExternalForm(), INVENTORY_SLOT_X, INVENTORY_SLOT_Y, true, false);
    public static final Image INVENTORY_SLOT_HAND_HOVERED_IMAGE = new Image(InventoryButton.class.getResource(INVENTORY_SLOT_HAND_HOVERED_PNG).toExternalForm(), INVENTORY_SLOT_X, INVENTORY_SLOT_Y, true, false);
    
    public static final Image FORGE_SLOT_IMAGE = new Image(InventoryButton.class.getResource(FORGE_SLOT_PNG).toExternalForm(), INVENTORY_SLOT_X, INVENTORY_SLOT_Y, true, false);
    public static final Image FORGE_SLOT_HOVERED_IMAGE = new Image(InventoryButton.class.getResource(FORGE_SLOT_HOVERED_PNG).toExternalForm(), INVENTORY_SLOT_X, INVENTORY_SLOT_Y, true, false);
    
    public static final Image FORGE_SLOT_HAND_IMAGE = new Image(InventoryButton.class.getResource(FORGE_SLOT_HAND_PNG).toExternalForm(), INVENTORY_SLOT_X, INVENTORY_SLOT_Y, true, false);
    public static final Image FORGE_SLOT_HAND_HOVERED_IMAGE = new Image(InventoryButton.class.getResource(FORGE_SLOT_HAND_HOVERED_PNG).toExternalForm(), INVENTORY_SLOT_X, INVENTORY_SLOT_Y, true, false);
    /**
     * Booleana che rappresenta lo stato di un bottone, occupato (locked) o non.
     */
	private boolean slotLock;
	/**
	 * L'immagine a display legata allo slot.
	 */
	private ImageView imageView = new ImageView();
	/**
	 * La Image dello slot di default (quando non locked).
	 */
	private Image slotImage;
	/**
	 * la image dello slot di default quando si passa il mouse sopra (quando non locked).
	 */
	private Image slotHoveredImage;
	/**
	 * La image dello slot quando occupato da un oggetto.
	 */
	private Image slotImageDisplayed;
	/**
	 * La image dello slot quando occupato da un oggetto e si passa il mouse sopra.
	 */
	private Image slotHoveredImageDisplayed;
	/**
	 * Il numero dello slot nella griglia.
	 */
	private int number;
	/**
	 * L'oggettoInventoryFx associato al bottone.
	 */
	private OggettoInventoryFx oggettoInventoryFx;
	/**
	 * L'eventType MouseEvent
	 */
	private EventType<MouseEvent> event;
	/**
	 * L'eventHandler MouseEvent
	 */
	private EventHandler<MouseEvent> eventHandler;
	/**
	 * La lista di EventHandler di entrata o uscita del mouse da uno slot.
	 */
	private List<EventHandler<MouseEvent>> enterExitEventHandler = new ArrayList<>();
	/**
	 * La lista di EventType di entrata o uscita del mouse da uno slot.
	 */
	private List<EventType<MouseEvent>> enterExitEventType = new ArrayList<>();
	/**
	 * Costruttore per gli slot, con l'immagine di default e quella di default a mouse hover.
	 * @param slotImage
	 * @param slotHoveredImage
	 */
	public InventoryButton(Image slotImage, Image slotHoveredImage)
	{
		this.slotImage = slotImage;
		this.slotHoveredImage = slotHoveredImage;
		this.setGraphic(imageView);
		setSlotDefaultGraphic();
	}
	/**
	 * Getter per lo stato di lock dello slot.
	 * @return slotLock.
	 */
	public boolean getSlotLock()
	{
		return slotLock;
	}
	/**
	 * Setter per lo stato di lock dello slot.
	 * @param slotLock
	 */
	public void setSlotLock(boolean slotLock)
	{
		this.slotLock = slotLock;
	}
	/**
	 * Getter per la image slot di default.
	 * @return slotImage.
	 */
	public Image getSlotImage()
	{
		return slotImage;
	}
	/**
	 * Getter per la image slot di default a mouse hover.
	 * @return slotHoveredImage.
	 */
	public Image getSlotHoveredImage()
	{
		return slotHoveredImage;
	}
	/**
	 * Getter per la image slot dell'oggetto che lo occupa.
	 * @return slotImageDisplayed.
	 */
	public Image getSlotImageDisplayed()
	{
		return slotImageDisplayed;
	}
	/**
	 * Getter per la image slot dell'oggetto che lo occupa a mouse hover.
	 * @return
	 */
	public Image getSlotHoveredImageDisplayed()
	{
		return slotHoveredImageDisplayed;
	}
	/**
	 * Getter per L'OggettoInventoryFx che occupa lo slot, può restituire null se non esiste.
	 * @return oggettoInventoryFx.
	 */
	public OggettoInventoryFx getOggettoInventoryFx()
	{	
		return oggettoInventoryFx;
	}
	/**
	 * Getter per il numero dello slot nella griglia.
	 * @return number.
	 */
	public int getButtonNumber()
	{
		return number;
	}
	/**
	 * Setter per il numero dello slot nella griglia.
	 * @param number
	 */
	public void setButtonNumber(int number)
	{
		this.number = number;
	}
	/**
	 * Getter per la lista di EventHandler.
	 * @return enterExitEventHandler.
	 */
	public List<EventHandler<MouseEvent>> getEnterExitEventHandler()
	{
		return enterExitEventHandler;
	}
	/**
	 * Getter per la lista di EventType.
	 * @return enterExitEventType.
	 */
	public List<EventType<MouseEvent>> getEnterExitEventType()
	{
		return enterExitEventType;
	}
	/**
	 * Metodo che aggiunge qualora non null eventType e Handler in coppia all'eventFilter del bottone.
	 * @param event
	 * @param eventHandler
	 */
	public void setEventsOfFilter(EventType<MouseEvent> event, EventHandler<MouseEvent> eventHandler)
	{
		if(this.event != null || this.eventHandler != null)
			this.removeEventFilter(this.event, this.eventHandler);
		
		this.event = event;
		this.eventHandler = eventHandler;
		
		this.addEventFilter(this.event, this.eventHandler);
	}
	/**
	 * Metodo che rimuove qualora non null eventType e Handler in coppia all'eventFilter del bottone.
	 */
	public void removeEventsFromFilter()
	{
		if(this.event != null || this.eventHandler != null)
		{
			this.removeEventFilter(event, eventHandler);
			
			event = null;
			eventHandler = null;
		}
	}
	/**
	 * Metodo che aggiunge tutti gli eventi eventType e Handler (di tipo enter exit) all 'event filter del bottone,
	 * però prima di ciò rimuove tutti quelli che possibilmente ci fossero già.
	 * Questo metodo serve per fare pulizia di vecchi eventi in modo programmatico ogni volta che uno slot si riempie e
	 * di aggiungerne nuovi quindi.
	 * @param eventTypeList
	 * @param eventHandlerList
	 * @throws Exception
	 */
	public void setupEnterExitEventsOnFilter(List<EventType<MouseEvent>> eventTypeList, List<EventHandler<MouseEvent>> eventHandlerList) throws Exception
	{
		if(eventTypeList.size() > eventHandlerList.size() || eventTypeList.size() < eventHandlerList.size())
			throw new Exception("Le liste devono avere lo stesso size!");
		if(!enterExitEventType.isEmpty() || !enterExitEventHandler.isEmpty())
		{
			enterExitEventType.clear();
			enterExitEventHandler.clear();
			
			for(int i = 0; i < eventTypeList.size(); i++)
				this.removeEventFilter(eventTypeList.get(i), eventHandlerList.get(i));
		}
		
		for(int i = 0; i < eventTypeList.size(); i++)
		{
			this.addEventFilter(eventTypeList.get(i), eventHandlerList.get(i));
			enterExitEventType.add(eventTypeList.get(i));
			enterExitEventHandler.add(eventHandlerList.get(i));
		}
	}
	/**
	 * Metodo che rimuove tutti gli eventi eventType e Handler (di tipo enter exit) all 'event filter del bottone.
	 * Questo metodo serve per fare pulizia di vecchi eventi in modo programmatico ogni volta che uno slot si libera.
	 * @param eventTypeList
	 * @param eventHandlerList
	 * @throws Exception
	 */
	public void removeEnterExitEventsFromFilter(List<EventType<MouseEvent>> eventTypeList, List<EventHandler<MouseEvent>> eventHandlerList) throws Exception
	{
		if(eventTypeList.size() > eventHandlerList.size() || eventTypeList.size() < eventHandlerList.size())
			throw new Exception("Le liste devono avere lo stesso size!");
		
		for(int i = 0; i < eventTypeList.size(); i++)
		{
			this.removeEventFilter(eventTypeList.get(i), eventHandlerList.get(i));
			enterExitEventType.remove(eventTypeList.get(i));
			enterExitEventHandler.remove(eventHandlerList.get(i));
		}
	}
	/**
	 * Questo metodo impone la grafica di default dello slot quando non locked.
	 */
	public void setSlotDefaultGraphic()
	{
		slotLock = false;
		oggettoInventoryFx = null;
		
		imageView.imageProperty().bind(Bindings.when(this.hoverProperty()).then(slotHoveredImage).otherwise(slotImage));
	}
	/**
	 * Questo metodo impone la grafica dell'oggettoInventoryFx allo slot quando non locked e lo mette a locked.
	 * @param slotImageDisplayed
	 * @param slotHoveredImageDisplayed
	 */
	public void setSlotImageDisplayed(Image slotImageDisplayed, Image slotHoveredImageDisplayed)
	{
		if(!slotLock)
		{
			oggettoInventoryFx.setSlotImage(slotImageDisplayed);
			oggettoInventoryFx.setSlotHoveredImage(slotHoveredImageDisplayed);
			
			this.slotImageDisplayed = slotImageDisplayed;
			this.slotHoveredImageDisplayed = slotHoveredImageDisplayed;

			imageView.imageProperty().bind(Bindings.when(this.hoverProperty()).then(this.slotHoveredImageDisplayed).otherwise(this.slotImageDisplayed));

			setSlotLock(true);
		}
	}
	/**
	 * Overload del metodo precedente che invece di due immagini da mettere a display prende un OggettoInventoryFx e poi
	 * impone la grafica di tale allo slot quando non locked e lo mette a locked.
	 * @param oggettoInventoryFx
	 */
	public void setSlotImageDisplayed(OggettoInventoryFx oggettoInventoryFx)
	{
		if(!slotLock)
		{
			this.oggettoInventoryFx = oggettoInventoryFx;
			
			this.slotImageDisplayed = oggettoInventoryFx.getSlotImage();
			this.slotHoveredImageDisplayed = oggettoInventoryFx.getSlotHoveredImage();

			imageView.imageProperty().bind(Bindings.when(this.hoverProperty()).then(this.slotHoveredImageDisplayed).otherwise(this.slotImageDisplayed));
			
			setSlotLock(true);
		}
	}
	/**
	 * Questo metodo serve per cambiare la descrizione dell'oggettoInventoryFx che sta occupando lo slot.
	 * @param descrizioneSlot
	 */
	public void setSlotNewDescrizioneSlot(String descrizioneSlot)
	{
		if(!slotLock)
			oggettoInventoryFx.setDescrizioneSlot(descrizioneSlot);
	}
}
