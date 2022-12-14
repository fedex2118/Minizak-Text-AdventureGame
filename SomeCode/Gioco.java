package it.uniroma1.textadv;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static it.uniroma1.textadv.personaggi.Giocatore.*;
import static it.uniroma1.textadv.Mondo.*;

import it.uniroma1.textadv.javaFxButtons.ButtonController;
import it.uniroma1.textadv.javaFxDeco.DecorationFxController;
import it.uniroma1.textadv.javaFxGiocatore.GiocatoreFxController;
import it.uniroma1.textadv.javaFxInventory.OggettoInventoryFxController;
import it.uniroma1.textadv.javaFxLinks.LinkFxController;
import it.uniroma1.textadv.javaFxMain.SceneController;
import it.uniroma1.textadv.javaFxOggetti.OggettoFx;
import it.uniroma1.textadv.javaFxOggetti.OggettoFxController;
import it.uniroma1.textadv.javaFxPersonaggi.PersonaggioFx;
import it.uniroma1.textadv.javaFxPersonaggi.PersonaggioFxController;
import it.uniroma1.textadv.javaFxStanze.StanzaFx;
import it.uniroma1.textadv.javaFxStanze.StanzaFxController;
import it.uniroma1.textadv.zak.MotoreTestuale;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Questa classe si occupa di avviare il gioco tramite motore testuale con o senza parte grafica.
 * Proprio perché può avviare il gioco con parte grafica in un Thread dedicato ad essa, 
 * questa classe estende Application e contiene l'override dei metodi start e stop di quest'ultima.
 * @author Federico Ziegler e Maximiliano Ziegler
 *
 */
public class Gioco extends Application
	{
	// Se devi aggiungere un nuovo gioco, qui vanno i path, i .game e gli url.
	private static final String MINIZAK_PATH = "/GameFiles/minizak.game";
	private static final String MINIZAK_FF_PATH = "/FastForwardFiles/minizak.ff";
	
	public static final String MINIZAK_NEW_STORY_PATH = "/GameFiles/minizak_storia_nuova.game";
	private static final String MINIZAK_NEW_STORY_FF_PATH = "/FastForwardFiles/minizak_storia_nuova.ff";
	
	public static final String MINIZAK_GAME = "minizak.game";
	public static final String MINIZAK_NEW_STORY_GAME = "minizak_storia_nuova.game";
	
	// Gioco normale:
	public static final URL MINIZAK_URL = Gioco.class.getResource(MINIZAK_PATH);
	// FF gioco normale:
	public static final URL MINIZAK_FF_URL = Gioco.class.getResource(MINIZAK_FF_PATH);
	
	// Gioco nuova story:
	public static final URL MINIZAK_NEW_STORY_URL = Gioco.class.getResource(MINIZAK_NEW_STORY_PATH);
	// FF gioco nuova story:
	public static final URL MINIZAK_NEW_STORY_FF_URL = Gioco.class.getResource(MINIZAK_NEW_STORY_FF_PATH);
	/**
	 * Questo metodo si occupa di avviare il gioco.
	 * Viene chiamato il metodo avvia(mondo) per avviare il motore testuale.
	 * @param mondo
	 * @throws Exception
	 */
	public void play(Mondo mondo) throws Exception
	{
		MotoreTestuale motoreTestuale = MotoreTestuale.getInstance();
		motoreTestuale.avvia(mondo);
	}
	/**
	 * Questo metodo si occupa di avviare il gioco in modalità fast forward.
	 * Il metodo chiama il metodo initializeFastForwardGame() e poi chiama il metodo avvia() per avviare il motore testuale.
	 * @param mondo
	 * @param path
	 * @throws Exception
	 */
	public void play(Mondo mondo, Path path) throws Exception
	{
		MotoreTestuale motoreTestuale = MotoreTestuale.getInstance();
		initializeFastForwardGame(path);
		motoreTestuale.avvia(mondo);
	}
	/**
	 * Questo metodo si occupa di analizzare un file usato per la giocata fast forward.
	 * Ogni riga del file analizzato viene rielaborata e aggiunta a una lista che viene passata al motore testuale
	 * con il metodo setRigheFastFoward() e viene chiamato il metodo setGiocataFastFoward() per permettere 
	 * al motore testuale di leggere le righe di questo file e non l'input inserito dal giocatore.
	 * @param path
	 * @throws IOException
	 */
	private void initializeFastForwardGame(Path path) throws IOException
	{
			List<String> righe = Files.readAllLines(path, StandardCharsets.UTF_8);
			List<String> rigaModificata = new ArrayList<>();
			for(String riga : righe)
			{
				riga = riga.replaceAll(TAB, SPAZIO);
				if(riga.contains(BARRA_SLASH_STRINGA))
					riga = riga.substring(0,riga.indexOf(BARRA_SLASH_STRINGA));
				rigaModificata.add(riga);
			}
			MotoreTestuale motoreTestuale = MotoreTestuale.getInstance();
			motoreTestuale.setGiocataFastFoward(true);
			motoreTestuale.setRigheFastFoward(rigaModificata);
	}
	/**
	 * Overload del metodo play che preso l'array di String in input a un main invoca il metodo launch su quest'ultimo
	 * avviando così di fatto l'applicazione che invocherà il metodo start.
	 * @param args
	 */
	public void play(String[] args)
	{
		launch(args);
	}
	/**
	 * Overload del metodo play che in aggiunta al metodo play con solo l'array in input a un main ha bisogno di un Path
	 * per inizializzare la FastForward dato che quel Path.
	 * @param args
	 * @param path
	 * @throws IOException
	 */
	public void play(String[] args, Path path) throws IOException
	{
		initializeFastForwardGame(path);
		launch(args);
	}
	/**
	 * Override del metodo stop di Application.
	 * Si occupa di far uscire all'utente dall'applicazione in modo più safe possibile qualsiasi sia il caso della chiusura,
	 * quindi avviando i Thread che non sono mai stati iniziati cosi da farli "morire" e notificando quelli che altrimenti
	 * rimarebbero in un'attesa infinita.
	 */
	@Override
	public void stop() {
	    SceneController.getSceneControllerInstance().setExitBoolean(true); // se true, si termina il background thread.
		
	    if(!ButtonController.BUTTON_INIZIO.isDisabled()) {
	    	synchronized (SceneController.INIZIO_THREAD) { SceneController.INIZIO_THREAD.notify(); }
	    }
	    
	    if(SceneController.INIZIO_THREAD.getState().equals(Thread.State.NEW))
	    	SceneController.INIZIO_THREAD.start();
	    
	    synchronized (SceneController.ED_TXT_FIELD_THREAD) {
	    	SceneController.ED_TXT_FIELD_THREAD.notify();
	    }
	    
	    synchronized (SceneController.FF_THREAD) { SceneController.FF_THREAD.notify(); }
	    
	    if(ButtonController.BUTTON_ESCI_THREAD.getState().equals(Thread.State.NEW)) // in caso non fosse stato inizializzato!
	    	ButtonController.BUTTON_ESCI_THREAD.start();
	    
	    if(SceneController.FF_THREAD.getState().equals(Thread.State.NEW)) // in caso non fosse stato inizializzato!
	    	SceneController.FF_THREAD.start();
	}
	/**
	 * Override del metodo start, override necessario alle classi che estendono Application.
	 * Questo metodo carica il Mondo da un Url e fa un "setup" di tutta la parte grafica del gioco, creando SceneController,
	 * StanzeFx, LinkFx ecc... tutte le componenti grafiche, e poi la loro logica verrà gestita dal SceneController
	 * con il metodo setStage().
	 * Tutto questo viene fatto in un Thread grafico che non può permettersi di gestire la parte testuale, proprio per questo
	 * alla fine viene avviato un Thread in background che si occupa della parte logica (testuale) del gioco, e infatti ad
	 * essa viene affidato il MotoreTestuale che si avvierà.
	 */
	@Override
	public void start(Stage theStage) throws Exception
	{	
		MotoreTestuale motoreTestuale = MotoreTestuale.getInstance();
		
		// Caricamento del mondo:
//		Mondo mondo = Mondo.fromFile("minizak.game"); // Gioco normale.
		Mondo mondo = Mondo.fromFile("minizak_storia_nuova.game"); // Gioco storia_nuova.
		
		// CARICAMENTO STANZEFX
		StanzaFxController.setupStanzeFx(mondo);
		StanzaFxController.setStanzeAdiacenti();
		
		List<StanzaFx> listaStanzeFx = StanzaFxController.getListaStanzeFx();
		SceneController sceneController = SceneController.getSceneControllerInstance();
		
		// CARICAMENTO LINKSFX
		LinkFxController.setupLinkFx(mondo);
		LinkFxController.setupLinksFxForStanzeFx(listaStanzeFx);
		
		// CARICAMENTO OGGETTIFX
		OggettoFxController.setupOggettiFx(mondo);
		List<OggettoFx> listaOggettiFx = OggettoFxController.getListaOggettiFx();
		
		OggettoFxController.setupOggettiFxForStanzeFx(listaStanzeFx);
		OggettoFxController.setupPiccolaStanzaMisteriosaOrderOfDisplay(listaStanzeFx);
		OggettoFxController.setupOggettiFxInterniAdOggettiFx();
		
		// CARICAMENTO DECORATIONFX
		DecorationFxController.riempiListaDecoFx();
		DecorationFxController.setupDecoFxForStanzeFx(listaStanzeFx);
		
		// CARICAMENTO PERSONAGGIFX
		PersonaggioFxController.setupPersonaggiFx(mondo);
		List<PersonaggioFx> listaPersonaggiFx = PersonaggioFxController.getListaPersonaggiFx();
		
		// Caricamento personaggiFx
		PersonaggioFxController.setupPersonaggiFxForStanzeFx(listaStanzeFx);
		
		// Caricamento inventario dei personaggiFx
		PersonaggioFxController.setupOggettiFxInInventarioPersonaggiFx(listaStanzeFx, listaOggettiFx);
		
		// CARICAMENTO FORGEFX
		PersonaggioFxController.setupGlondirInventoryFx();
		
		// CARICAMENTO GIOCATOREFX + Inventario grafico
		GiocatoreFxController.setupGiocatore(mondo);
		
		// CARICAMENTO OGGETTI INVENTARIO
		OggettoInventoryFxController.setupOggettiInventoryFx(listaOggettiFx, listaPersonaggiFx);
		
		// CARICAMENTO STANZA GEMMA GIALLA
		StanzaFxController.setupStanzeFxGemmaGialla();
		OggettoFxController.setupStanzaFxGemmaGiallaForSceneController(sceneController);
		
		// SETUP STANZAFX DI PARTENZA
		StanzaFxController.setupStanzaFxDiPartenza(sceneController);
		
		sceneController.setStage(theStage);
		
		new Thread(new Runnable() { // Background Thread
		       public void run() {
		    	  try {
					motoreTestuale.avvia(mondo, sceneController);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		       }
		}).start();
	}
}
