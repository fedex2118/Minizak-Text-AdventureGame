package it.uniroma1.textadv.javaFxMain;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
/**
 * Questa classe rappresenta l'anima delle animazioni di questo progetto. Essa estende la classe Transition che è una classe
 * di default per molti tipi di animazione in javaFx.
 * La classe aggiunge tantissime funzionalità per la gestione delle animazioni in javaFx e anche dei
 * "quality of life" cambiamenti.
 * Ad esempio si potranno gestire i frame da mettere a display, la duration, il frame da "aspettare" per l'inizio di nuove
 * animazioni e molto altro grazie a questa classe.
 * @author Federico Ziegler
 *
 */
public class SpriteAnimation extends Transition
{
	/**
	 * L'immagine a cui la sprite è legata.
	 */
	private ImageView imageView;
	/**
	 * Il numero di frame dell'immagine.
	 */
    private int count;
    /**
     * Quanti frame sono in una riga nell'immagine.
     */
    private int columns;
    /**
     * OffsetX: in caso l'immagine avesse avuto qualche offset orizzontale.
     */
    private int offSetX;
    /**
     * OffsetY: in caso l'immagine avesse avuto qualche offset verticale.
     */
    private int offSetY;
    /**
     * La larghezza dell'immagine.
     */
    private double width;
    /**
     * L'altezza dell'immagine.
     */
    private double height;
    /**
     * La durata dell'intera animazione (tutti i frame per la durata del singolo), solitamente da convertire in millisecondi.
     */
    private Duration duration;
    /**
     * La durata del singolo frame.
     */
    private double frameDurationMillis;
    /**
     * Booleana per indicare se ha un frame da attendere prima che si avvii un altra animazione.
     */
    private boolean hasFrameToWait;
    /**
     * Il frame da aspettare per far partire altre animazioni.
     */
    private int frameToWait;
    /**
     * Il frame corrente.
     */
    private int currentIndex;
    /**
     * Il primo frame.
     */
    private int firstIndex = 0;
    /**
     * L'ultimo frame.
     */
    private int lastIndex = count - 1;
    /**
     * Costruttore per SpriteAnimation a cui manca il frameToWait dato che non ne ha, ha anche un setCycleDuration() e 
     * un setInterpolator() come da manuale per le transitions.
     * @param imageView
     * @param duration
     * @param count
     * @param columns
     * @param offSetX
     * @param offSetY
     * @param width
     * @param height
     * @param frameDurationMillis
     */
    public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns, 
    		int offSetX, int offSetY, double width, double height, double frameDurationMillis)
    {
    	this.imageView = imageView;
    	this.count = count;
    	this.columns = columns;
    	this.offSetX = offSetX;
    	this.offSetY = offSetY;
    	this.width = width;
    	this.height = height;
    	this.duration = duration;
    	this.frameDurationMillis = frameDurationMillis;
    	
    	setCycleDuration(this.duration);
    	setInterpolator(Interpolator.LINEAR);
    }
    /**
     * Costruttore per SpriteAnimation a cui vi è il frameToWait e quindi il hasFrameToWait a true, 
     * ha anche un setCycleDuration() e un setInterpolator() come da manuale per le transitions.
     * @param imageView
     * @param duration
     * @param count
     * @param columns
     * @param offSetX
     * @param offSetY
     * @param width
     * @param height
     * @param frameDurationMillis
     * @param frameToWait
     */
    public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns, 
    		int offSetX, int offSetY, double width, double height, double frameDurationMillis, int frameToWait)
    {
    	this.imageView = imageView;
    	this.count = count;
    	this.columns = columns;
    	this.offSetX = offSetX;
    	this.offSetY = offSetY;
    	this.width = width;
    	this.height = height;
    	this.duration = duration;
    	this.frameDurationMillis = frameDurationMillis;
    	this.frameToWait = frameToWait;
    	this.hasFrameToWait = true;
    	
    	setCycleDuration(this.duration);
    	setInterpolator(Interpolator.LINEAR);
    }
	/**
	 * Override del metodo interpolate che nel tempo fa un calcolo per trovare il nuovo frame dato un double k
	 * e prima invoca il metodo di utilità calculateViewXY() che determina il frame da mettere a display, poi 
	 * setta il currentIndex al frame calcolato.
	 */
    @Override
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1); // Calcola il nuovo frame
        if (index != currentIndex) {
            calculateViewXY(index);
            currentIndex = index;
        }
    }
    /**
     * Metodo che dato un frame calcola la X e la Y corrette di quel frame per il display.
     * La X è calcolata tramite le colonne e la larghezza (in maggiorluogo per la larghezza) più offset se esistente.
     * La Y è calcolata tramite le colonne e l'altezza più offset se esistente.
     * Infine viene invocato il metodo dell'immagine setViewPort che setta il nuovo "rettangolo" per il display, 
     * ovvero questa è la nuova immagine da far vedere.
     * @param index
     */
    private void calculateViewXY(int index)
    {
    	final int x = (index % columns) * (int)width  + offSetX;
        final int y = (index / columns) * (int)height + offSetY;
    	imageView.setViewport(new Rectangle2D(x, y, width, height)); // Setta l'immagine per il display.
    }
    /**
     * Metodo creato per disfarsi di un calcolo erroneo sulla larghezza delle immagini che contengono animazione.
     * Queste immagini hanno tanti frame e quindi una larghezza abbastanza grande, anche qualche piccolo sforo di calcolo
     * e il display dell'animazione verrebbe shiftato.
     * L'errore esiste perche i calcoli fatti da java/javaFx per determinare la giusta larghezza dell'immagine basandosi
     * solo su una X scelta in precedenza non bastano sempre e a volte sono anche di pochissimo errati 
     * (si notano ancora di più in alcune risoluzioni rispetto ad altre).
     * Tale per quanto piccolo errore porta a shiftare le animazioni di poco e dare risultati inadeguati.
     * Per migliorare questo errore questo metodo fa il giusto calcolo della larghezza dell'immagine moltiplicando
     * la larghezza del singolo frame per il numero di indici presenti -1.
     * La larghezza del singolo frame è ottenuta dalla X della dimensione dell'immagine (scelta in precedenza) diviso
     * il numero di frame.
     * @param widthFrame
     * @param lastIndex
     * @return
     */
    public static double applicaCorrezioneMaxX(double widthFrame, int lastIndex)
    {
    	return lastIndex * widthFrame;
    }
    /**
     * Metodo che invoca setImageFrameToDisplay(primo frame) e mette come immagine visibile all'inizio il primo frame.
     */
    public void setImageStart()
    {
    	setImageFrameToDisplay(firstIndex);
    }
    /**
     * Metodo di utilità che dato un index (frame) da mettere a display lo mette a display invocando calculateViewXY su
     * quel frame solo se quel frame non è minore di 0 o maggiore dei frame disponibili, altrimenti infatti mette a display
     * solo il primo frame.
     * @param index
     */
    public void setImageFrameToDisplay(int index)
    {
    	if(index >= count || index < 0)    // se index è non esistente
    	{
    		currentIndex = firstIndex;
    		calculateViewXY(firstIndex);
    		return;
    	}
    	calculateViewXY(index);
    }
    /**
     * Metodo di utilità che restituisce il tempo da aspettare, di solito dopo ciò accade che parta un altra animazione o
     * succedano altre cose nel mondo di gioco.
     * Principalmente usato in un Thread di attesa (sleep).
     * @return il calcolo che indica quanto tempo (solitamente in millisecondi) da aspettare.
     */
    public double setTimeToSleep()
    {
    	return frameDurationMillis * frameToWait;
    }
    /**
     * Overload del metodo setTimeToSleep() solo che questa volta il frame da aspettare non è stato deciso a costruzione
     * dell'istanza della sprite animation.
     * @param frame
     * @return il calcolo che indica quanto tempo (solitamente in millisecondi) da aspettare.
     */
    public double setTimeToSleep(int frame)
    {
    	return frameDurationMillis * frame;
    }
    /**
     * Metodo che aumenta il current index al successivo e setta l'immagine da mettere a display a quella
     * successiva (frame successivo) invocando il metodo setImageFrameToDisplay(currentIndex aumentato).
     */
    public void displayNextFrame()
    {
    	currentIndex++;
    	setImageFrameToDisplay(currentIndex);
    }
    
    // Getters & Setters
    /**
     * Getter per il numero di frame.
     * @return count.
     */
    public int getCount()
    {
    	return count;
    }
    /**
     * Getter per il frame corrente.
     * @return currentIndex.
     */
    public int getCurrentIndex()
    {
    	return currentIndex;
    }
    /**
     * Getter per l'ultimo frame.
     * @return lastIndex.
     */
    public int getLastIndex()
    {
    	return lastIndex;
    }
    /**
     * Getter per la Duration.
     * @return duration.
     */
    public Duration getDuration()
    {
    	return duration;
    }
    /**
     * Getter per frameDurationMillis.
     * @return frameDurationMillis.
     */
    public double getFrameDurationInMillis()
    {
    	return frameDurationMillis;
    }
    /**
     * Getter per hasFrameToWait.
     * @return hasFrameToWait.
     */
    public boolean getHasFrameTowait()
    {
    	return hasFrameToWait;
    }
}
