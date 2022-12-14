package it.uniroma1.textadv.zak;

import static it.uniroma1.textadv.javaFxButtons.ButtonController.*;
import static it.uniroma1.textadv.personaggi.Giocatore.*;
import static it.uniroma1.textadv.javaFxStanze.StanzaFxController.*;
import static it.uniroma1.textadv.javaFxLinks.LinkFxController.*;
import static it.uniroma1.textadv.javaFxPersonaggi.PersonaggioFxController.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.textadv.Mondo;
import it.uniroma1.textadv.javaFxGiocatore.GiocatoreFx;
import it.uniroma1.textadv.javaFxGiocatore.GiocatoreFxController;
import it.uniroma1.textadv.javaFxInventory.InventoryGiocatoreFx;
import it.uniroma1.textadv.javaFxMain.SceneController;
import it.uniroma1.textadv.javaFxPersonaggi.PersonaggioFx;
import it.uniroma1.textadv.javaFxStanze.StanzaFx;
import it.uniroma1.textadv.personaggi.Giocatore;
import javafx.application.Platform;

/**
 * Questa classe rappresenta il motore testuale del gioco.
 * Si occupa di elaborare l'input testuale dato dall'utente.
 * È dotata di metodi di utilità (setters) e molte varibili statiche di utilità.
 * @author Maximiliano Ziegler
 *
 */
public class MotoreTestuale
{
	public static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

	public static final String DUE_PUNTI = ":";
	public static final String PROVA_A_SCRIVERE = "Prova a scrivere uno dei seguenti comandi";
	public static final String INVIO = "\n";
	public static final String PER_ALCUNI_COMANDI_SCRIVI = "Per alcuni comandi devi scrivere qualcosa/qualcuno che si trova nella stanza";
	public static final String PER_COMANDI_COMPLESSI = "Per comandi più complessi usa le preposizioni ( tipo : prendi qualcosa da qualcosa)";
	public static final String TI_CONSIGLIO_GUARDA = "Ti consiglio di iniziare con guarda";
	public static final String SE_CAMBI_STANZA = "Se devi cambiare stanza puoi usare vai seguito dalle direzioni, oppure le direzioni da sole";
	public static final String ALTRIMENTI_USA_ENTRA = "Altrimenti usa entra seguito dal nome di una stanza o collegamento";
	public static final String POSSIBILE_ERRORE_SCRIPT = "Possibile errore nello script di gioco";
	public static final String NON_HAI_VINTO = "Non sei riuscito a vincere";
	public static final String ESCI = "esci";
	public static final String HAI_SCRITTO_TROPPO = "Hai scritto troppo";
	public static final String RITENTA = "ritenta";
	public static final String IL_CARATTERE = "Il carattere";
	public static final String NON_AMMESSO = "non è ammesso";
	public static final String PRIMA_PAROLA_COMANDO = "La prima parola deve essere un comando valido";
	public static final String SCRIVI_DIREZIONE = "Altrimenti scrivi una direzione";
	public static final String MAIUSCOLE_VALIDE = "Va bene anche in maiuscolo";
	public static final String APOSTROFO = "'";
	public static final String COMANDO_NON_VALIDO = "Comando non valido";
	public static final String COMANDO_ENTRA = "entra";
	public static final String COMANDI_SENZA_PARAMETRI = "I comandi che vanno bene da soli sono";
	public static final String SCRIVI_NOME_SPECIFICO = "Per gli altri comandi scrivi un nome specifico dopo il comando";
	public static final String INSERITI_TROPPI_NOMI = "Hai inserito troppi nomi";
	public static final String COMANDO_VAI = "vai";
	public static final String DIREZIONE_WEST = "w"; // usato per convertire west a ovest in versione italiana...

	// Costanti Parte Grafica
	// Comandi
	public static final String GUARDA = "guarda";
	public static final String INVENTARIO = "inventario";
	public static final String PARLA = "parla";
	public static final String APRI = "apri";
	public static final String ROMPI = "rompi";
	public static final String DAI = "dai";
	public static final String USA = "usa";
	public static final String TOCCA = "tocca";
	public static final String ENTRA = "entra";
	public static final String PRENDI = "prendi";
	public static final String ACCAREZZA = "accarezza";
	public static final String FORGIA = "forgia";

	public static final String N = "n";
	public static final String S = "s";
	public static final String O = "o";

	// Altro
	public static final String A = "a";
	public static final String SEI_ANDATO = "Sei andato";
	public static final String SEI_ENTRATO = "Sei entrato";
	public static final String ZAK_DUE_PUNTI = "Zak:";
	public static final String PORTA_STRING = "porta";
	public static final String GLONDIR_DUE_PUNTI = "Glondir:";
	public static final String IL_BLUEPRINT = "il blueprint";
	public static final String GOBLIN_DUE_PUNTI = "goblin:";
	public static final String IL_RAGNO = "il ragno";
	public static final String IL_SECCHIO = "Il secchio";
	public static final String IL_TELETRASPORTO = "Il teletrasporto";
	public static final String NONNO_DUE_PUNTI = "nonno:";
	public static final String ODDIO_ZAK = "Oddìo Zak!";
	public static final String GRAZIE_ZAK = "Grazie Zak!";
	public static final String CHE_BELLO_RIVEDERTI_ZAK = "Che bello rivederti Zak!!";
	public static final String TUTORIAL_TIP = "Suggerimento: il bottone della lente d'ingrandimento ti aiuterà ad orientarti nel mondo...";
	/**
	 * Indica che il comando in output del motore testuale non è un exception o qualcosa che non è plausibile.
	 */
	private boolean comandoCorretto;
	/**
	 * Serve per il file FF con parte grafica, da essa dipende l'attesa dell'FF_THREAD cosi da farlo attendere se ci sono
	 * animazioni che si stanno svolgendo.
	 */
	private boolean parteGraficaAvviata;

	/**
	 * Indica le possibili vocali dell'alfabeto.
	 */
	private HashSet<String> vocali = new HashSet<>(Arrays.asList(A,E,"i",O,"u"));
	/**
	 * Indica le direzioni del mondo e i modi in cui il giocatore può scriverle.
	 */
	private HashSet<String> direzioni = new HashSet<>(Arrays.asList(E,O,S,"n","w","est","ovest","sud","nord","west"));
	/**
	 * Indica cosa rappresentano i personaggi del gioco che hanno un nome (Esempio: il giocatore può scrivere venditore seguito dal nome quando si riferisce a quel personaggio che è di classe venditore).
	 */
	private HashSet<String> personaggiConNome = new HashSet<>(Arrays.asList("cane","gatto","venditore","fabbro")); // parte nuova aggiunto fabbro, non dovrebbe causare problemi...
	/**
	 * Indica i nomi di stanze che sono più complessi (Esempio: stanza di Zak).
	 */
	private HashSet<String> stanzeConPossibiliArticoli = new HashSet<>(Arrays.asList("stanza","salone","dungeon"));
	/**
	 * Indica gli articoli e preposizioni che potrebbero essere scritti dal giocatore in situazioni particolari (Esempio: entra nella stanza...).
	 */
	private HashSet<String> articoliEPreposizioniPerCasiSpeciali = new HashSet<>(Arrays.asList("nel","nello","nella","in"));
	/**
	 * Indica gli articoli e preposizioni che il giocatore potrebbe voler scrivere quando si riferisce a un nome (Esempio : apri la scrivania o apri la porta di ingresso).
	 */
	private HashSet<String> articoliEPreposizioniIniziali = new HashSet<>(Arrays.asList("do","del","di","della","dello","il","lo","la","le","gli","nel","in","nella","nello","negli"));
	/**
	 * Indica le preposizioni che il giocatore deve scrivere quando vuole compiere azioni più complesse (Esempio : apri porta ingresso con chiave).
	 */
	private HashSet<String> preposizioniSecondarie = new HashSet<>(Arrays.asList(A,"ad","al","alla","allo","da","con","su","per","tra","fra","dalla","dallo","dal","dalle","dagli","sulla","sul","sullo","sui","sulle"));
	/**
	 * Indica tutti i metodi (comandi) presenti nel gioco.
	 */
	private HashSet<String> tuttiIMetodi = new HashSet<>(Arrays.asList(APRI,USA,GUARDA,"vai",PRENDI,PARLA,ACCAREZZA,ENTRA,INVENTARIO,DAI,ROMPI,FORGIA,TOCCA));
	/**
	 * Indica tutti i metodi che hanno zero parametri (non richiedono che il giocatore scriva altro oltre al comando).
	 */
	private HashSet<String> metodiConZeroParametri = new HashSet<>(Arrays.asList(INVENTARIO,GUARDA, FORGIA));
	/**
	 * Indica tutti i metodi che hanno un parametro (richiedono che il giocatore scriva un nome oltre al comando).
	 */
	private HashSet<String> metodiConUnParametro = new HashSet<>(Arrays.asList(APRI,USA,GUARDA,"vai",PRENDI,PARLA,ACCAREZZA,ENTRA,ROMPI, FORGIA,TOCCA));
	/**
	 * Indica tutti i metodi che hanno due parametri ( richiedono che il giocatore scriva un nome seguito da una preposizione e un altro nome oltre al comando).
	 */
	private HashSet<String> metodiConDueParametri = new HashSet<>(Arrays.asList(APRI,USA,PRENDI,DAI,ROMPI));
	/**
	 * Indica i caratteri che il giocatore non dovrebbe scrivere ( non sono ammessi quindi una exception viene lanciata).
	 */
	private HashSet<String> caratteriNonAmmessi = new HashSet<>(Arrays.asList("\t"," '","''","\"",",","|","!","?","(",")",";",".",":","-","_","§","ù","°","ò","@","#","ç","è","[","]","{","é","}","*","+","ì","^","=","&","%","$","£","\\","<",">","/","0","1","2","3","4","5","6","7","8","9"));
	/**
	 * Indica tutte le lettere dell'alfabeto.
	 */
	private HashSet<String> lettereAlfabeto = new HashSet<>(Arrays.asList("q","w","e","r","t","y","u","i","o","p",A,"s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"));
	/**
	 * Indica le direzioni sotto forma di stringa (usato per dire al giocatore quali sono le direzioni in cui può andare).
	 */
	private String direzioniValide = direzioni.stream().collect(Collectors.joining(", "));
	/**
	 * Indica tutti i comandi che il giocatore può scrivere (usato per dire al giocatore quali sono i comandi che può scrivere).
	 */
	private String comandiValidi = tuttiIMetodi.stream().collect(Collectors.joining(", "));
	/**
	 * Indica la stringa risultato del metodo corrispondente al comando che il giocatore ha scritto.
	 */
	private String eseguiComando = "";
	/**
	 * Indica ciò che il giocatore ha scritto o ogni riga del file ff.
	 */
	private String riga = "";
	/**
	 * Indica la lista che contiene la stringa finale ottenuta dalla lavorazione della riga scritta dal giocatore (Esempio : apri la porta di ingresso con la chiave diventa [apri,porta ingresso,chiave]).
	 */
	private List<String> listaCorretta = new ArrayList<>();
	/**
	 * Indica se stiamo giocando in modalità fast forward o meno.
	 */
	private boolean giocataFastForward;
	/**
	 * È la lista che contiene le righe del file ff.
	 */
	private List<String> righeFastFoward = new ArrayList<>();

	public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * L'unica istanza del motore testuale.
	 */
	private static MotoreTestuale motoreTestualeInstance;

	/**
	 * Getter per l'unica istanza del motore testuale.
	 * @return Crea un istanza qualora null e la restituisce, altrimenti restituisce l'unica istanza del motore testuale.
	 */
	public static MotoreTestuale getInstance()
	{
		if(motoreTestualeInstance == null)
			motoreTestualeInstance = new MotoreTestuale();
		return motoreTestualeInstance;
	}
	/**
	 * Costruttore privato del motore testuale.
	 */
	private MotoreTestuale() {}
	/**
	 * Setter del booleano giocataFastForward.
	 * @param booleano
	 */
	public void setGiocataFastFoward(boolean booleano)
	{
		giocataFastForward = booleano;
	}
	/**
	 * Setter per la lista righeFastFoward.
	 * @param listaFastFoward
	 */
	public void setRigheFastFoward(List<String> listaFastFoward)
	{
		righeFastFoward = listaFastFoward;
	}

	/**
	 * Questo metodo si occupa di avviare il motore testuale e di gestirlo.
	 * Per prima cosa vengono date informazioni al giocatore sul gioco e
	 * sui possibili comandi che il giocatore può scrivere.
	 * Dopodichè viene analizzata la riga di testo scritta dal giocatore e
	 * viene rielaborata (spazi, articoli, apostrofi, preposizioni, ecc... doppioni vengono eliminati).
	 * Alla fine rimangono solo il comando valido e i nomi scritti dal giocatore che finiscono nella listaCorretta.
	 * Gli elementi della lista vengono poi usati per chiamare il metodo corrispondente della classe giocatore
	 * e la stringa risultato viene stampata su console.
	 * Se il giocatore ha scritto caratteri non ammessi o ha scritto un comando non valido, o ha scritto troppo,
	 * o troppi nomi, o ancora ha scritto un comando in modo sbagliato
	 * (ha scritto un comando che necessita essere seguito da un nome o il contrario) viene lanciata un exception adeguata
	 * che informa il giocatore dell'errore.
	 * @param mondo
	 * @throws Exception
	 */
	public void avvia(Mondo mondo) throws Exception
	{
		boolean fermaGiocoFastFoward = false;
		boolean comandoErratoPerTroppiParametri = true;
		boolean direzioneComeComando = false;
		int contatore = 0;
		int contatoreFastFoward = 0;
		System.out.println(mondo.getDescizione());
		String informazioni = tuttiIMetodi.stream().collect(Collectors.joining(Giocatore.VIRGOLA+Giocatore.SPAZIO));
		System.out.println(PROVA_A_SCRIVERE+DUE_PUNTI+Giocatore.SPAZIO+informazioni+Giocatore.PUNTO+INVIO+PER_ALCUNI_COMANDI_SCRIVI+Giocatore.PUNTO+INVIO+PER_COMANDI_COMPLESSI+Giocatore.PUNTO+INVIO+TI_CONSIGLIO_GUARDA+Giocatore.PUNTO+INVIO+SE_CAMBI_STANZA+Giocatore.PUNTO+INVIO+ALTRIMENTI_USA_ENTRA+Giocatore.PUNTO);
		while(true)
		{
			try
			{
				if(fermaGiocoFastFoward)
				{
					giocataFastForward = false;
					break;
				}
				if(giocataFastForward && contatoreFastFoward < righeFastFoward.size())
				{
					riga = righeFastFoward.get(contatoreFastFoward).toLowerCase();
					contatoreFastFoward++;
				}
				else
				{
					if(contatoreFastFoward != 0 && contatoreFastFoward >= righeFastFoward.size())
					{
						fermaGiocoFastFoward = true;
						throw new NonFunzionaException(POSSIBILE_ERRORE_SCRIPT+PUNTO+SPAZIO+NON_HAI_VINTO+PUNTO);
					}
					else
						riga = reader.readLine().toLowerCase();
				}
				if(riga.equals(ESCI))
					break;
				System.out.println(riga.length());
				if(riga.length() >= 80)
					throw new NonFunzionaException(HAI_SCRITTO_TROPPO+Giocatore.VIRGOLA+Giocatore.SPAZIO+RITENTA+Giocatore.PUNTO);
				for(String carattereNonAmmesso : caratteriNonAmmessi)
					if(riga.contains(carattereNonAmmesso))
						throw new NonFunzionaException(IL_CARATTERE+Giocatore.SPAZIO+carattereNonAmmesso+Giocatore.SPAZIO+NON_AMMESSO+Giocatore.PUNTO);
				riga = riga.strip();
				contatore = 0;
				direzioneComeComando = false;
				for(String direzione : direzioni)
					if(riga.startsWith(direzione))
						direzioneComeComando = true;
				if(!direzioneComeComando)
					for(String metodo : tuttiIMetodi)
					{
						if((riga.startsWith(metodo)))
							break;
						else
						if(contatore == tuttiIMetodi.size()-1)
							throw new NonFunzionaException(PRIMA_PAROLA_COMANDO+DUE_PUNTI+INVIO+comandiValidi+Giocatore.PUNTO+INVIO+SCRIVI_DIREZIONE+DUE_PUNTI+SPAZIO+direzioniValide+PUNTO+INVIO+MAIUSCOLE_VALIDE+PUNTO);
						contatore++;
					}
				while(riga.contains(APOSTROFO))
				{
					riga = riga.replace(APOSTROFO, VOCALE_O+SPAZIO);
				}
				while(riga.contains(SPAZIO+SPAZIO))
				{
					riga = riga.replace(SPAZIO+SPAZIO, SPAZIO);
				}
				String articoloOPreposizioneOVocaleOdirezioneDoppia = "";
				for(String articoliEPreposizioni : articoliEPreposizioniIniziali)
				{
					if(riga.endsWith(articoliEPreposizioni)) // per risolvere caso doppioni sfortunati(parla aaaa aa aaaa a a a a aaaaaaa aa aa aa a)
						riga += SPAZIO;
					articoloOPreposizioneOVocaleOdirezioneDoppia = articoliEPreposizioni+articoliEPreposizioni;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, articoliEPreposizioni);

					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = SPAZIO+articoliEPreposizioni+SPAZIO+articoliEPreposizioni+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, SPAZIO+articoliEPreposizioni+Giocatore.SPAZIO);
					}
				}
				for(String preposizioneSecondaria : preposizioniSecondarie)
				{
					if(riga.endsWith(preposizioneSecondaria)) // per risolvere caso doppioni sfortunati(parla aaaa aa aaaa a a a a aaaaaaa aa aa aa a)
						riga += SPAZIO;
					articoloOPreposizioneOVocaleOdirezioneDoppia = preposizioneSecondaria+preposizioneSecondaria;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, preposizioneSecondaria);

					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = SPAZIO+preposizioneSecondaria+SPAZIO+preposizioneSecondaria+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, SPAZIO+preposizioneSecondaria+Giocatore.SPAZIO);
					}
				}
				for(String vocale : vocali)
				{
					if(riga.endsWith(vocale)) // per risolvere caso doppioni sfortunati(parla aaaa aa aaaa a a a a aaaaaaa aa aa aa a)
						riga += Giocatore.SPAZIO;
					articoloOPreposizioneOVocaleOdirezioneDoppia = vocale+vocale;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, vocale);

					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+vocale+Giocatore.SPAZIO+vocale+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+vocale+Giocatore.SPAZIO);
					}
				}
				for(String direzione : direzioni)
				{
					if(riga.endsWith(direzione)) // per risolvere caso doppioni sfortunati(vai ssss ss ssss s s s s sssssss ss ss ss s)
						riga += Giocatore.SPAZIO;
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+direzione+direzione;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+direzione);
					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = direzione+direzione+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, direzione+Giocatore.SPAZIO);
					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+direzione+direzione+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+direzione+Giocatore.SPAZIO);
					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+direzione+Giocatore.SPAZIO+direzione+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+direzione+Giocatore.SPAZIO);
					}
				}
				riga = riga.strip(); // per eliminare possibili spazi alla fine della riga o all'inizio
				String[] lavorazioneRiga = riga.split(Giocatore.SPAZIO);
				listaCorretta = new ArrayList<>();
				String nomeTecnicamenteCorretto = "";
				if(lavorazioneRiga.length == 2 && preposizioniSecondarie.contains(lavorazioneRiga[1]))
					throw new NonFunzionaException(COMANDO_NON_VALIDO+Giocatore.PUNTO);

				for(int k = 0; k<lavorazioneRiga.length; k++)
				{
					String stringaDaControllare = lavorazioneRiga[k];
					if(k == 0)
					{
						listaCorretta.add(stringaDaControllare);
						continue;
					}
					if(!direzioni.contains(stringaDaControllare))
					{
						if(!vocali.contains(stringaDaControllare) && !articoliEPreposizioniIniziali.contains(stringaDaControllare) && !tuttiIMetodi.contains(stringaDaControllare) && !preposizioniSecondarie.contains(stringaDaControllare) && !lettereAlfabeto.contains(stringaDaControllare)) // && !personaggiConNome.contains(lavorazioneRiga[k])
						{
							if(personaggiConNome.contains(stringaDaControllare) && k == lavorazioneRiga.length-1) // caso gatto,venditore ecc... aggiungiamo questo nome cosi da dare messaggi normali ( di solito si elimina... "dai gatto neo a tom" diventa "dai neo a tom"...
								nomeTecnicamenteCorretto += stringaDaControllare+SPAZIO; // Generiamo il nome giusto aggiungendo spazi ( armadio luccicante...)
							if(!personaggiConNome.contains(stringaDaControllare))
								nomeTecnicamenteCorretto += stringaDaControllare+Giocatore.SPAZIO;
						}
					}
					else
						nomeTecnicamenteCorretto += stringaDaControllare+SPAZIO;
					if(preposizioniSecondarie.contains(stringaDaControllare) && !nomeTecnicamenteCorretto.equals(STRINGA_VUOTA)) // se abbiamo una preposizione la usiamo per terminare la creazione del nome e lo aggiungiamo alla listaCorretta...
					{
						listaCorretta.add(nomeTecnicamenteCorretto.substring(0, nomeTecnicamenteCorretto.length()-1));
						nomeTecnicamenteCorretto = "";
						continue;
					}
					if(articoliEPreposizioniPerCasiSpeciali.contains(stringaDaControllare) && !nomeTecnicamenteCorretto.equals(STRINGA_VUOTA)) // come sopra ( per metodi normali : prendi chiave nella scrivania...)
					{
						listaCorretta.add(nomeTecnicamenteCorretto.substring(0, nomeTecnicamenteCorretto.length()-1));
						nomeTecnicamenteCorretto = "";
						continue;
					}
					if(articoliEPreposizioniIniziali.contains(stringaDaControllare) && !nomeTecnicamenteCorretto.equals(STRINGA_VUOTA) && lavorazioneRiga[0].equals(COMANDO_ENTRA) && stanzeConPossibiliArticoli.contains(lavorazioneRiga[k-1]))
					{
						nomeTecnicamenteCorretto += stringaDaControllare+SPAZIO; // aggiungiamo articolo(preposizione) alle stanze dove serve ( stanza di Zak...) nel momento in cui usiamo il comando "entra"...
						continue;
					}
					if(k == lavorazioneRiga.length-1 && !nomeTecnicamenteCorretto.equals(STRINGA_VUOTA))
						listaCorretta.add(nomeTecnicamenteCorretto.substring(0, nomeTecnicamenteCorretto.length()-1)); // meno uno per lo spazio finale
				}

				if(listaCorretta.size() == 1 && !metodiConZeroParametri.contains(listaCorretta.get(0)) && !direzioni.contains(listaCorretta.get(0)))
				{
					String comandiValidiDaSoli = metodiConZeroParametri.stream().collect(Collectors.joining(", "));
					throw new NonFunzionaException(COMANDO_NON_VALIDO+Giocatore.PUNTO+Giocatore.SPAZIO+COMANDI_SENZA_PARAMETRI+DUE_PUNTI+Giocatore.SPAZIO+comandiValidiDaSoli+Giocatore.PUNTO+INVIO+SCRIVI_NOME_SPECIFICO+Giocatore.PUNTO+INVIO+PER_COMANDI_COMPLESSI+Giocatore.PUNTO);
				}
				if(listaCorretta.size() > 3)
					throw new NonFunzionaException(COMANDO_NON_VALIDO+Giocatore.PUNTO+Giocatore.SPAZIO+INSERITI_TROPPI_NOMI+Giocatore.PUNTO);
				if(direzioni.contains(listaCorretta.get(0)) && listaCorretta.size() == 1) // necessario per avere il comando vai direzione anche con solo direzione...
				{
					listaCorretta.add(0, COMANDO_VAI);
					String direzioneCorretta = "";
					if(listaCorretta.get(1).substring(0, 1).equals(DIREZIONE_WEST)) // trasformo west in w
						direzioneCorretta = Giocatore.VOCALE_O; // trasformo w in o...
					else
						direzioneCorretta = listaCorretta.get(1).substring(0, 1); // altrimenti prendo tipo da est solo e ecc...
					listaCorretta.remove(1);
					listaCorretta.add(direzioneCorretta);
				}
				if(listaCorretta.size() == 2 && direzioni.contains(listaCorretta.get(1)) && listaCorretta.get(0).equals(COMANDO_VAI)) // necessario per trasformare est,ovest ecc... in singole vocali cosi il metodo funzionera...
				{
					String direzioneCorretta = "";
					if(listaCorretta.get(1).substring(0, 1).equals(DIREZIONE_WEST)) // come sopra...
						direzioneCorretta = Giocatore.VOCALE_O;
					else
						direzioneCorretta = listaCorretta.get(1).substring(0, 1);
					listaCorretta.remove(1);
					listaCorretta.add(direzioneCorretta);
				}
				String comando = listaCorretta.get(0);
				comandoErratoPerTroppiParametri = true;
				if(metodiConZeroParametri.contains(comando) && listaCorretta.size() == 1) // invochiamo metodi che prendono zero parametri...
				{
					Object[] parametri = {};
					eseguiComando = (String)mondo.getGiocatore().getClass().getDeclaredMethod(comando).invoke(mondo.getGiocatore(),parametri);
					if(!eseguiComando.equals(STRINGA_VUOTA))
						System.out.println(eseguiComando);
					comandoErratoPerTroppiParametri = false;
				}
				if(metodiConUnParametro.contains(comando) && listaCorretta.size() == 2) // come sopra
				{
					Object[] parametri = {listaCorretta.get(1)};
					eseguiComando = (String)mondo.getGiocatore().getClass().getDeclaredMethod(comando,String.class).invoke(mondo.getGiocatore(),parametri);
					if(!eseguiComando.equals(STRINGA_VUOTA))
						System.out.println(eseguiComando);
					comandoErratoPerTroppiParametri = false;
				}
				if(metodiConDueParametri.contains(comando) && listaCorretta.size() == 3) // come sopra
				{
					Object[] parametri = {listaCorretta.get(1),listaCorretta.get(2)};
					eseguiComando = (String)mondo.getGiocatore().getClass().getDeclaredMethod(comando,String.class,String.class).invoke(mondo.getGiocatore(),parametri);
					if(!eseguiComando.equals(STRINGA_VUOTA))
						System.out.println(eseguiComando);
					comandoErratoPerTroppiParametri = false;
				}
				if(comandoErratoPerTroppiParametri)
					throw new NonFunzionaException(COMANDO_NON_VALIDO+Giocatore.PUNTO); // viene chiamata se fai tipo "guarda scrivania con vite" (Non possono funzionare ovviamente)...
				if(eseguiComando.contains(Giocatore.HAI_VINTO+Giocatore.PUNTO_ESCLAMATIVO))
				{
					giocataFastForward = false;
					break;
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	/**
	 * Metodo in overload che avvia il MotoreTestuale e quindi il "cuore" logico del progetto. In questo caso però vengono
	 * aggiunte alcune variabili grafiche: un Thread di inizio che aspetta alcune interazioni grafiche per far partire
	 * il tutto, alcune condizioni che chiudono il gioco in modo sicuro qualora venisse chiuso il Thread grafico e non
	 * si fosse finito il gioco, e verso la fine del metodo viene runnato di nuovo il Thread grafico che se è successo
	 * qualcosa nel "mondo logico" del gioco fa semplicemente un update grafico basandosi sulle stringhe che sono state
	 * generate dal motore testuale.
	 * Il metodo prende un SceneController in input per aggiornarlo ogni volta che c'è un update grafico da fare e così
	 * si aggiorna la logica grafica sempre legata a quella testuale.
	 * Le stringhe risultato del motore testuale non vengono solo usate per fare dei controlli ma vengono anche elaborate
	 * e passate dal sceneController che le mette a display in una TextArea così da avere sempre nozione testuale di ciò
	 * che è avvenuto nel mondo di gioco qualora non si fosse visto.
	 * In questo metodo rispetto all'altro il motore testuale dopo ogni azione del giocatore è sempre in attesa di una
	 * notifica da parte del motore grafico, così da riottenere l'accesso alle proprie risorse.
	 * @param mondo
	 * @param sceneController
	 * @throws InterruptedException
	 */
	public void avvia(Mondo mondo, SceneController sceneController) throws InterruptedException
	{
		synchronized ( SceneController.INIZIO_THREAD) { SceneController.INIZIO_THREAD.wait(); }

		if(sceneController.getExitBoolean()) // per chiudere l'applicazione in modo safe.
			return;

		boolean fermaGiocoFastFoward = false;
		boolean comandoErratoPerTroppiParametri = true;
		boolean direzioneComeComando = false;
		int contatore = 0;
		int contatoreFastFoward = 0;
		GiocatoreFx giocatoreFx = GiocatoreFxController.getGiocatoreFx();
		InventoryGiocatoreFx inventoryFx = giocatoreFx.getInventoryGiocatoreFx();
		String informazioni = tuttiIMetodi.stream().collect(Collectors.joining(Giocatore.VIRGOLA+Giocatore.SPAZIO));

		sceneController.getNotEditableTextLog().setText(mondo.getDescizione()+INVIO+TUTORIAL_TIP);
		sceneController.getNotEditableTextArea().setText(mondo.getDescizione()+INVIO+TUTORIAL_TIP);
		System.out.println(mondo.getDescizione());
		System.out.println(PROVA_A_SCRIVERE+DUE_PUNTI+Giocatore.SPAZIO+informazioni+Giocatore.PUNTO+INVIO+PER_ALCUNI_COMANDI_SCRIVI+Giocatore.PUNTO+INVIO+PER_COMANDI_COMPLESSI+Giocatore.PUNTO+INVIO+TI_CONSIGLIO_GUARDA+Giocatore.PUNTO+INVIO+SE_CAMBI_STANZA+Giocatore.PUNTO+INVIO+ALTRIMENTI_USA_ENTRA+Giocatore.PUNTO);

		while(true)
		{
			try
			{
				if(sceneController.getExitBoolean())
					break;
				comandoCorretto = false;
				if(fermaGiocoFastFoward) // Exception da implementare?
				{
					giocataFastForward = false;
					break;
				}
				if(giocataFastForward && contatoreFastFoward < righeFastFoward.size())
				{
					if(parteGraficaAvviata)
					{
						synchronized (SceneController.FF_THREAD) {
							SceneController.FF_THREAD.wait();
						}
						parteGraficaAvviata = false;
					}

					if(BUTTON_ESCI.getPressedBool())
						riga = ESCI;
					else
					{
						riga = righeFastFoward.get(contatoreFastFoward).toLowerCase();
						contatoreFastFoward++;
					}
				}
				else
				{
					if(contatoreFastFoward != 0 && contatoreFastFoward >= righeFastFoward.size())
					{
						fermaGiocoFastFoward = true;
						throw new NonFunzionaException(POSSIBILE_ERRORE_SCRIPT+Giocatore.PUNTO+NON_HAI_VINTO+Giocatore.PUNTO);
					}
					else
					{
						synchronized (SceneController.ED_TXT_FIELD_THREAD) { SceneController.ED_TXT_FIELD_THREAD.wait(); }
						riga = sceneController.getStringa();
					}
				}

				if(sceneController.getExitBoolean())
					break;

				if(riga.equals(ESCI))
				{
					Platform.runLater(new Runnable() {   // UI Thread
						public void run() {
							BUTTON_ESCI.fire();
						}
					});
					if(!BUTTON_EXIT_WINDOW_SI.getPressedBool())
					{
						synchronized (BUTTON_ESCI_THREAD) {
							BUTTON_ESCI_THREAD.wait();
						}
					}
					if(BUTTON_EXIT_WINDOW_SI.getPressedBool() || !BUTTON_EXIT_WINDOW_NO.getPressedBool())
						break;
					else
					{
						BUTTON_EXIT_WINDOW_NO.setPressedBool(false);
						continue;
					}
				}
				if(riga.length() >= 80)
					throw new NonFunzionaException(HAI_SCRITTO_TROPPO+Giocatore.VIRGOLA+Giocatore.SPAZIO+RITENTA+Giocatore.PUNTO);
				for(String carattereNonAmmesso : caratteriNonAmmessi)
					if(riga.contains(carattereNonAmmesso))
						throw new NonFunzionaException(IL_CARATTERE+Giocatore.SPAZIO+carattereNonAmmesso+Giocatore.SPAZIO+NON_AMMESSO+Giocatore.PUNTO);
				riga = riga.strip(); // se volessimo scrivere spazi all'inizio???
				contatore = 0;
				direzioneComeComando = false;
				for(String direzione : direzioni)
					if(riga.startsWith(direzione))
						direzioneComeComando = true;
				if(!direzioneComeComando)
					for(String metodo : tuttiIMetodi)
					{
						if((riga.startsWith(metodo)))
							break;
						else
						if(contatore == tuttiIMetodi.size()-1)
							throw new NonFunzionaException(PRIMA_PAROLA_COMANDO+DUE_PUNTI+INVIO+comandiValidi+Giocatore.PUNTO+INVIO+SCRIVI_DIREZIONE+DUE_PUNTI+Giocatore.SPAZIO+direzioniValide+Giocatore.PUNTO+INVIO+MAIUSCOLE_VALIDE+Giocatore.PUNTO);
						contatore++;
					}
				while(riga.contains(APOSTROFO))
				{
					riga = riga.replace(APOSTROFO, Giocatore.VOCALE_O+Giocatore.SPAZIO);
				}
				while(riga.contains(Giocatore.SPAZIO+Giocatore.SPAZIO))
				{
					riga = riga.replace(Giocatore.SPAZIO+Giocatore.SPAZIO, Giocatore.SPAZIO);
				}
				String articoloOPreposizioneOVocaleOdirezioneDoppia = STRINGA_VUOTA;
				for(String articoliEPreposizioni : articoliEPreposizioniIniziali)
				{
					if(riga.endsWith(articoliEPreposizioni)) // per risolvere caso doppioni sfortunati(parla aaaa aa aaaa a a a a aaaaaaa aa aa aa a)
						riga += Giocatore.SPAZIO;
					articoloOPreposizioneOVocaleOdirezioneDoppia = articoliEPreposizioni+articoliEPreposizioni;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, articoliEPreposizioni);

					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+articoliEPreposizioni+Giocatore.SPAZIO+articoliEPreposizioni+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+articoliEPreposizioni+Giocatore.SPAZIO);
					}
				}
				for(String preposizioneSecondaria : preposizioniSecondarie)
				{
					if(riga.endsWith(preposizioneSecondaria)) // per risolvere caso doppioni sfortunati(parla aaaa aa aaaa a a a a aaaaaaa aa aa aa a)
						riga += Giocatore.SPAZIO;
					articoloOPreposizioneOVocaleOdirezioneDoppia = preposizioneSecondaria+preposizioneSecondaria;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, preposizioneSecondaria);

					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+preposizioneSecondaria+Giocatore.SPAZIO+preposizioneSecondaria+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+preposizioneSecondaria+Giocatore.SPAZIO);
					}
				}
				for(String vocale : vocali)
				{
					if(riga.endsWith(vocale)) // per risolvere caso doppioni sfortunati(parla aaaa aa aaaa a a a a aaaaaaa aa aa aa a)
						riga += Giocatore.SPAZIO;
					articoloOPreposizioneOVocaleOdirezioneDoppia = vocale+vocale;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						//						System.out.println(riga);
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, vocale);

					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+vocale+Giocatore.SPAZIO+vocale+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						//						System.out.println(riga);
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+vocale+Giocatore.SPAZIO);
					}
				}
				for(String direzione : direzioni)
				{
					if(riga.endsWith(direzione)) // per risolvere caso doppioni sfortunati(vai ssss ss ssss s s s s sssssss ss ss ss s)
						riga += Giocatore.SPAZIO;
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+direzione+direzione;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						//						System.out.println("ek");
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+direzione);
					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = direzione+direzione+Giocatore.SPAZIO; //direzione+direzione+direzione
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						//						System.out.println("ek");
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, direzione+Giocatore.SPAZIO);
					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+direzione+direzione+Giocatore.SPAZIO; //direzione+direzione+direzione
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						//						System.out.println("ek");
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+direzione+Giocatore.SPAZIO);
					}
					articoloOPreposizioneOVocaleOdirezioneDoppia = Giocatore.SPAZIO+direzione+Giocatore.SPAZIO+direzione+Giocatore.SPAZIO;
					while(riga.contains(articoloOPreposizioneOVocaleOdirezioneDoppia))
					{
						riga = riga.replace(articoloOPreposizioneOVocaleOdirezioneDoppia, Giocatore.SPAZIO+direzione+Giocatore.SPAZIO);
					}
				}
				riga = riga.strip();
				String[] lavorazioneRiga = riga.split(Giocatore.SPAZIO);
				listaCorretta = new ArrayList<>();

				String nomeTecnicamenteCorretto = STRINGA_VUOTA;

				if(lavorazioneRiga.length == 2 && preposizioniSecondarie.contains(lavorazioneRiga[1])) //!lavorazioneRiga[1].equals("a") && !lavorazioneRiga[1].equals("ad")
					throw new NonFunzionaException(COMANDO_NON_VALIDO+Giocatore.PUNTO);

				for(int k = 0; k<lavorazioneRiga.length; k++)
				{
					String stringaDaControllare = lavorazioneRiga[k];
					if(k == 0)
					{
						listaCorretta.add(stringaDaControllare);
						continue;
					}
					if(!direzioni.contains(stringaDaControllare))
					{
						if(!vocali.contains(stringaDaControllare) && !articoliEPreposizioniIniziali.contains(stringaDaControllare) && !tuttiIMetodi.contains(stringaDaControllare) && !preposizioniSecondarie.contains(stringaDaControllare) && !lettereAlfabeto.contains(stringaDaControllare)) // && !personaggiConNome.contains(lavorazioneRiga[k])
						{
							if(personaggiConNome.contains(stringaDaControllare) && k == lavorazioneRiga.length-1) // caso gatto,venditore ecc... aggiungiamo questo nome cosi da dare messaggi normali ( di solito si elimina... "dai gatto neo a tom" diventa "dai neo a tom"...
								nomeTecnicamenteCorretto += stringaDaControllare+Giocatore.SPAZIO; // Generiamo il nome giusto aggiungendo spazi ( armadio luccicante...)
							if(!personaggiConNome.contains(stringaDaControllare))
								nomeTecnicamenteCorretto += stringaDaControllare+Giocatore.SPAZIO;
						}
					}
					else
						nomeTecnicamenteCorretto += stringaDaControllare+Giocatore.SPAZIO;
					if(preposizioniSecondarie.contains(stringaDaControllare) && !nomeTecnicamenteCorretto.equals(STRINGA_VUOTA)) // se abbiamo una preposizione la usiamo per terminare la creazione del nome e lo aggiungiamo alla listaCorretta...
					{
						listaCorretta.add(nomeTecnicamenteCorretto.substring(0, nomeTecnicamenteCorretto.length()-1));
						nomeTecnicamenteCorretto = "";
						continue;
					}
					if(articoliEPreposizioniPerCasiSpeciali.contains(stringaDaControllare) && !nomeTecnicamenteCorretto.equals(STRINGA_VUOTA))
					{
						listaCorretta.add(nomeTecnicamenteCorretto.substring(0, nomeTecnicamenteCorretto.length()-1));
						nomeTecnicamenteCorretto = STRINGA_VUOTA;
						continue;
					}
					if(articoliEPreposizioniIniziali.contains(stringaDaControllare) && !nomeTecnicamenteCorretto.equals(STRINGA_VUOTA) && lavorazioneRiga[0].equals(COMANDO_ENTRA) && stanzeConPossibiliArticoli.contains(lavorazioneRiga[k-1]))
					{
						nomeTecnicamenteCorretto += stringaDaControllare+SPAZIO; // aggiungiamo articolo(preposizione) alle stanze dove serve ( stanza di Zak...) nel momento in cui usiamo il comando "entra"...
						continue;
					}
					if(k == lavorazioneRiga.length-1 && !nomeTecnicamenteCorretto.equals(STRINGA_VUOTA))
						listaCorretta.add(nomeTecnicamenteCorretto.substring(0, nomeTecnicamenteCorretto.length()-1)); // meno uno per lo spazio finale( potevamo usare strip? Cosa è piu efficente?)
				}
				if(listaCorretta.size() == 1 && !metodiConZeroParametri.contains(listaCorretta.get(0)) && !direzioni.contains(listaCorretta.get(0)))
				{
					String comandiValidiDaSoli = metodiConZeroParametri.stream().collect(Collectors.joining(", "));
					throw new NonFunzionaException(COMANDO_NON_VALIDO+Giocatore.PUNTO+Giocatore.SPAZIO+COMANDI_SENZA_PARAMETRI+DUE_PUNTI+Giocatore.SPAZIO+comandiValidiDaSoli+Giocatore.PUNTO+INVIO+SCRIVI_NOME_SPECIFICO+Giocatore.PUNTO+INVIO+PER_COMANDI_COMPLESSI+Giocatore.PUNTO);
				}
				if(listaCorretta.size() > 3)
					throw new NonFunzionaException(COMANDO_NON_VALIDO+Giocatore.PUNTO+Giocatore.SPAZIO+INSERITI_TROPPI_NOMI+Giocatore.PUNTO);
				if(direzioni.contains(listaCorretta.get(0)) && listaCorretta.size() == 1) // necessario per avere il comando vai direzione anche con solo direzione...
				{
					listaCorretta.add(0, COMANDO_VAI);
					String direzioneCorretta = STRINGA_VUOTA;
					if(listaCorretta.get(1).substring(0, 1).equals(DIREZIONE_WEST)) // trasformo west in w
						direzioneCorretta = Giocatore.VOCALE_O; // trasformo w in o...
					else
						direzioneCorretta = listaCorretta.get(1).substring(0, 1); // altrimenti prendo tipo da est solo e ecc...
					listaCorretta.remove(1);
					listaCorretta.add(direzioneCorretta);
				}
				if(listaCorretta.size() == 2 && direzioni.contains(listaCorretta.get(1)) && listaCorretta.get(0).equals(COMANDO_VAI))
				{
					String direzioneCorretta = STRINGA_VUOTA;
					if(listaCorretta.get(1).substring(0, 1).equals(DIREZIONE_WEST)) // come sopra...
						direzioneCorretta = Giocatore.VOCALE_O;
					else
						direzioneCorretta = listaCorretta.get(1).substring(0, 1);
					listaCorretta.remove(1);
					listaCorretta.add(direzioneCorretta);
				}
				String comandoTestuale = listaCorretta.get(0);
				comandoErratoPerTroppiParametri = true;
				if(metodiConZeroParametri.contains(comandoTestuale) && listaCorretta.size() == 1) // invochiamo metodi che prendono zero parametri (ovviamente mettiamo mondo perchè in alucni metodi è necessario e stiamo usando reflection...)
				{
					Object[] parametri = {};
					eseguiComando = (String)mondo.getGiocatore().getClass().getDeclaredMethod(comandoTestuale).invoke(mondo.getGiocatore(),parametri);
					if(!eseguiComando.equals(STRINGA_VUOTA)) // condizione fittizia, potremmo non usarla...
						System.out.println(eseguiComando);
					comandoErratoPerTroppiParametri = false;
					comandoCorretto = true;
				}
				if(metodiConUnParametro.contains(comandoTestuale) && listaCorretta.size() == 2) // come sopra
				{
					Object[] parametri = {listaCorretta.get(1)};
					eseguiComando = (String)mondo.getGiocatore().getClass().getDeclaredMethod(comandoTestuale,String.class).invoke(mondo.getGiocatore(),parametri);
					if(!eseguiComando.equals(STRINGA_VUOTA))
						System.out.println(eseguiComando);
					comandoErratoPerTroppiParametri = false;
					comandoCorretto = true;
				}
				if(metodiConDueParametri.contains(comandoTestuale) && listaCorretta.size() == 3) // come sopra
				{
					Object[] parametri = {listaCorretta.get(1),listaCorretta.get(2)};
					eseguiComando = (String)mondo.getGiocatore().getClass().getDeclaredMethod(comandoTestuale,String.class,String.class).invoke(mondo.getGiocatore(),parametri);
					if(!eseguiComando.equals(STRINGA_VUOTA))
						System.out.println(eseguiComando);
					comandoErratoPerTroppiParametri = false;
					comandoCorretto = true;
				}
				if(comandoErratoPerTroppiParametri)
					throw new NonFunzionaException(COMANDO_NON_VALIDO+Giocatore.PUNTO); // viene chiamata se fai tipo "guarda scrivania con vite" (Non possono funzionare ovviamente)...
				if(eseguiComando.contains(Giocatore.HAI_VINTO+Giocatore.PUNTO_ESCLAMATIVO))
				{
					giocataFastForward = false;
					Platform.runLater(new Runnable() {
						public void run() {
							sceneController.chiudiIlSipario(giocatoreFx);
						}
					});
					break;
				}

				sceneController.setStringa(STRINGA_VUOTA);

				// Parte grafica
				if(comandoCorretto)
				{
					parteGraficaAvviata = true;

					// Aggiornamento parte grafica
					Platform.runLater(new Runnable() {   // UI Thread
						public void run()
						{
							StanzaFx stanzaFx = sceneController.getStanzaFx();
							boolean forgia = giocatoreFx.getGiocatore().getForgia(); // la booleana viene gestita dal motore testuale esclusivamente!

							if(eseguiComando.startsWith(SEI_ANDATO) || eseguiComando.startsWith(SEI_ENTRATO)
									|| eseguiComando.startsWith(TI_SPOSTI) || eseguiComando.startsWith(TI_TELETRASPORTI))
							{
								if(eseguiComando.equals(TI_TELETRASPORTI + PUNTO)) // caso gemma gialla vai a stanza linkata
								{
									StanzaFx stanzaFxGemmaGialla = sceneController.getStanzaFxGemmaGialla();

									sceneController.setupGemmaSpecialeTeleport(stanzaFx, stanzaFxGemmaGialla, eseguiComando, giocatoreFx, forgia);
									sceneController.setUltimaStanzaFxVisitata(stanzaFx);

									return;
								}

								if(stanzaFx.equals(sceneController.getStanzaFxGemmaGialla())) // caso gemma gialla torni da stanza linkata
								{
									StanzaFx ultimaStanzaFxVisitata = sceneController.getUltimaStanzaFxVisitata();

									sceneController.setupGemmaSpecialeTeleport(stanzaFx, ultimaStanzaFxVisitata, eseguiComando, giocatoreFx, forgia);

									return;
								}

								Stanza posizione = mondo.getGiocatore().getPosizione();
								for(StanzaFx stanzaAdiacenteFx : stanzaFx.getListaStanzeAdiacenti())
									if(stanzaAdiacenteFx.getStanza().getNome().equals(posizione.getNome()))
									{
										if((stanzaFx.getNome().equals(FUORI_CASA_STRING) ||
												stanzaFx.getNome().equals(NEGOZIETTO_STRING)) &&
												(stanzaAdiacenteFx.getNome().equals(FUORI_CASA_STRING) ||
														stanzaAdiacenteFx.getNome().equals(NEGOZIETTO_STRING)))
											sceneController.setupNavetta(stanzaFx, stanzaAdiacenteFx, eseguiComando, giocatoreFx, forgia);
										else {
											sceneController.unloadRoom(stanzaFx, stanzaAdiacenteFx, giocatoreFx, forgia);
											sceneController.setStanzaFx(stanzaAdiacenteFx);
											sceneController.loadRoom(stanzaAdiacenteFx, eseguiComando, giocatoreFx, forgia);
										}
										break;
									}
								return;
							}

							if(eseguiComando.startsWith(ZAK_DUE_PUNTI + SPAZIO + MA_DOVE_SONO_FINITO)) // caso gemma speciale prima volta
							{
								StanzaFx stanzaFxGemmaGialla = sceneController.getStanzaFxGemmaGialla();

								sceneController.setupGemmaSpecialeTeleport(stanzaFx, stanzaFxGemmaGialla, eseguiComando, giocatoreFx, forgia);
								sceneController.setUltimaStanzaFxVisitata(stanzaFx);

								return;
							}

							if(eseguiComando.startsWith(ZAK_DUE_PUNTI + SPAZIO + MA_CHE_SUCCESSO)) // caso puzzle risolto troll
							{
								sceneController.setupGoblinQuest(eseguiComando, giocatoreFx, inventoryFx, forgia);

								return;
							}

							// Display InventoryFx
							if(eseguiComando.contains(INVENTARIO_VUOTO) || eseguiComando.contains(OGGETTI_INVENTARIO))
							{
								sceneController.elaborateTextForNotEditableTextAreaAndLog(eseguiComando);

								if(MotoreTestuale.getInstance().getGiocataFastForward())
								{
									synchronized (SceneController.FF_THREAD) {
										SceneController.FF_THREAD.notify();
									}
									return;
								}
								sceneController.displayInventoryFx(inventoryFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(HAI_PRESO))
							{
								for(PersonaggioFx personaggioFx : stanzaFx.getListaPersonaggiFx())
								{
									String nomePersonaggioFx = personaggioFx.getNome();

									if(eseguiComando.contains(nomePersonaggioFx)) // contains è rischioso!
									{
										sceneController.removePersonaggioFxPreso(inventoryFx, personaggioFx, nomePersonaggioFx, eseguiComando);
										return;
									}
								}

								String sottoStringa = eseguiComando.substring(HAI_PRESO.length() + 1);

								String nomeOggetto = getStringaCorretta(sottoStringa);

								sceneController.removeOggettiFxPresi(inventoryFx, nomeOggetto, eseguiComando);
								return;
							}

							if(eseguiComando.startsWith(HAI_APERTO))
							{
								String sottoStringa = eseguiComando.substring(HAI_APERTO.length() + 1);

								String nome = getStringaCorretta(sottoStringa);

								// Apertura Porte
								if(eseguiComando.contains(PORTA_STRING) || eseguiComando.contains(BOTOLA_STRING))
								{
									String nomePorta = nome;

									sceneController.getOpenedDoorsAnimation(nomePorta, eseguiComando, giocatoreFx, forgia);
									return;
								}
								// Apertura Oggetti
								String nomeOggetto = nome;

								sceneController.getOpenedItemsAnimation(nomeOggetto, eseguiComando, giocatoreFx, forgia);
								return;
							}

							if(eseguiComando.startsWith(HAI_ROTTO))
							{
								String sottoStringa = eseguiComando.substring(HAI_ROTTO.length() + 1);

								String nomeOggetto = getStringaCorretta(sottoStringa) + SPAZIO + ROTTO;

								sceneController.getBrokenItemsAnimation(nomeOggetto, eseguiComando, giocatoreFx, inventoryFx, forgia);

								return;
							}

							// Display ForgeFx
							if(eseguiComando.startsWith(GLONDIR_DUE_PUNTI + SPAZIO + NON_CI_SONO_OGGETTI_FORGIABILI) || eseguiComando.startsWith(GLONDIR_DUE_PUNTI + SPAZIO + ECCO_OGGETTI_FORGIABILI))
							{
								sceneController.elaborateTextForNotEditableTextAreaAndLog(eseguiComando);

								if(MotoreTestuale.getInstance().getGiocataFastForward())
								{
									synchronized (SceneController.FF_THREAD) {
										SceneController.FF_THREAD.notify();
									}
									return;
								}

								for(PersonaggioFx personaggioFx : stanzaFx.getListaPersonaggiFx())
									if(personaggioFx.getNome().toLowerCase().equals(GLONDIR_STRING))
									{
										sceneController.displayInventoryFx(personaggioFx.getInventoryFx(), forgia);
										break;
									}

								return;
							}

							if(eseguiComando.startsWith(HAI_DATO + SPAZIO + IL_BLUEPRINT))
							{
								for(PersonaggioFx personaggioFx : stanzaFx.getListaPersonaggiFx())
									if(personaggioFx.getNome().toLowerCase().equals(GLONDIR_STRING))
									{
										sceneController.setupForgeableItems(eseguiComando, giocatoreFx, inventoryFx,
												personaggioFx.getInventoryFx(), forgia);
										break;
									}

								return;
							}

							if(eseguiComando.startsWith(GLONDIR_DUE_PUNTI + SPAZIO + TI_HO_FORGIATO))
							{
								sceneController.displayForgedItemsOnScreen(stanzaFx, riga, inventoryFx);

								return;
							}

							if(eseguiComando.startsWith(BOB_STRING + SPAZIO + E_CONTENTO))
							{
								sceneController.accarezzaBobAnimated(eseguiComando, forgia);

								return;
							}

							if(eseguiComando.startsWith(NEO_STRING + SPAZIO + NON_SI_TOCCA))
							{
								sceneController.animateNeoTouched(NEO_STRING, eseguiComando, forgia);

								return;
							}

							if(eseguiComando.startsWith(ZAK_DUE_PUNTI + SPAZIO + GNAM))
							{
								sceneController.animateCarneFresh(eseguiComando, forgia);

								return;
							}

							if(eseguiComando.startsWith(GOBLIN_DUE_PUNTI + SPAZIO + GRAZIE_PER))
							{
								sceneController.daiCarneGoblin(eseguiComando, giocatoreFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(HAI_LASCIATO + SPAZIO + IL_RAGNO))
							{
								sceneController.usaRagnoSuCespuglio(eseguiComando, giocatoreFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(TOM_UP_STRING + SPAZIO + USA_SHOTGUN))
							{
								sceneController.animateRagnoEnlarged(eseguiComando, giocatoreFx, inventoryFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(GUARDIANO_ALTERATO))
							{
								sceneController.animateGuardianoAlterato(eseguiComando, forgia);

								return;
							}

							if(eseguiComando.startsWith(TOM_UP_STRING + SPAZIO + CI_DA))
							{
								sceneController.removeOggettiFxVenditore(inventoryFx, TOM_UP_STRING);
								sceneController.setEmoteForTom(eseguiComando, forgia);

								return;
							}

							if(eseguiComando.startsWith(IL_SECCHIO + SPAZIO + SI_RIEMPIE_ACQUA))
							{
								sceneController.animatePozzo(eseguiComando, inventoryFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(ADESSO_HAI + SPAZIO + DELLE + SPAZIO + FRECCE_AVVELENATE))
							{
								sceneController.setFrecceNewImages(eseguiComando, inventoryFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(NONNO_DUE_PUNTI + SPAZIO + ODDIO + SPAZIO + GIOCATORE_STRING))
							{
								sceneController.animateTrollDeath(eseguiComando, giocatoreFx, inventoryFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(NONNO_DUE_PUNTI + SPAZIO + GRAZIE + SPAZIO + GIOCATORE_STRING + PUNTO_ESCLAMATIVO))
							{
								sceneController.animateGabbiaOpened(eseguiComando, inventoryFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(NONNO_DUE_PUNTI + SPAZIO + CHE_BELLO_RIVEDERTI_ZAK)) // Dialogo Standard Nonno
							{
								sceneController.giveBlueprintGemmaMulticoloreToPlayer(eseguiComando, inventoryFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(GOBLIN_DUE_PUNTI + SPAZIO + EH_HO_RUBATO))
							{
								sceneController.animateChiaveTeletrasportoSuGemmaGialla(inventoryFx, eseguiComando, forgia);

								return;
							}

							if(eseguiComando.startsWith(ZAK_DUE_PUNTI + SPAZIO + ACCIDENTI_CE)) // Skeleton Shield
							{
								sceneController.animateSkeletonShielded(eseguiComando, forgia);

								return;
							}

							if(eseguiComando.startsWith(HAI_SPENTO))
							{
								sceneController.spegniFuoco(eseguiComando, forgia, giocatoreFx);

								return;
							}

							if(eseguiComando.startsWith(NEO_STRING + SPAZIO + SPAVENTATO_DAL_RAGNO))
							{
								sceneController.animateTopoRagnoNeo(eseguiComando, forgia);

								return;
							}

							// Forge Mechanic
							if(eseguiComando.startsWith(GLONDIR_DUE_PUNTI + SPAZIO + NON_CI_POSSO_CREDERE))
							{
								sceneController.setupForgeMechanic(eseguiComando, inventoryFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(ZAK_DUE_PUNTI + SPAZIO + AH_STOLTO + SPAZIO + SCHELETRO_STRING))
							{
								sceneController.animateMinigunSkeleton(eseguiComando, giocatoreFx, inventoryFx, forgia);

								return;
							}

							if(eseguiComando.startsWith(GOBLIN_DUE_PUNTI + SPAZIO + BENE_SEI_RIUSCITO))
							{
								sceneController.retrieveChiaveTeletrasporto(eseguiComando, inventoryFx, forgia);

								return;
							}

							if(eseguiComando.contains(ATTIVAZIONE_TELETRASPORTO) || eseguiComando.startsWith(IL_TELETRASPORTO + SPAZIO + E_DI_NUOVO_ATTIVO)) // CASO TELETRASPORTO ATTIVATO PER LA PRIMA VOLTA
							{
								Stanza posizione = mondo.getGiocatore().getPosizione();
								for(StanzaFx stanzaAdiacenteFx : stanzaFx.getListaStanzeAdiacenti())
								{
									if(stanzaAdiacenteFx.getNome().equals(posizione.getNome()))
									{
										sceneController.setupTeleport(stanzaFx, stanzaAdiacenteFx, eseguiComando, giocatoreFx, inventoryFx, forgia);
										break;
									}
								}
								return;
							}

							if(eseguiComando.startsWith(ZAK_DUE_PUNTI + SPAZIO + COSA_E))
							{
								sceneController.rocciaTouched(eseguiComando, forgia);

								return;
							}

							if(eseguiComando.startsWith(GUARDIANO_CONTENTO))
							{
								sceneController.animateGuardianoContento(GUARDIANO_TESORO_STRING, eseguiComando, giocatoreFx);

								return;
							}

							if(eseguiComando.startsWith(HAI_SVITATO))
							{
								String sottoStringa = eseguiComando.substring(HAI_SVITATO.length() + 1);

								String nomeVite = getStringaCorretta(sottoStringa);

								sceneController.setViteFrame(nomeVite, eseguiComando, giocatoreFx, forgia);

								return;
							}
							sceneController.elaborateTextForNotEditableTextAreaAndLog(eseguiComando);
							synchronized (SceneController.FF_THREAD) { SceneController.FF_THREAD.notify();}
						}
					});
				}
			}
			catch(Exception ex)
			{
				String errore = ex.getMessage();
				Platform.runLater(new Runnable() {
					public void run() {
						sceneController.getNotEditableTextArea().setText(errore);
					}
				});
			}
		}
	}
	/**
	 * Metodo che, presa una sottostringa, restituisce la stringa corretta del nome di una porta o di un oggetto.
	 * @param sottoStringa
	 * @return
	 */
	private static String getStringaCorretta(String sottoStringa)
	{
		sottoStringa = sottoStringa.replace('\'', ' ');

		String[] arraySottoStringa = sottoStringa.split(SPAZIO);

		String nomePortaOrOggetto = arraySottoStringa[1];

		for(int i = 2; i < arraySottoStringa.length; i++)
		{
			nomePortaOrOggetto += SPAZIO + arraySottoStringa[i];
		}

		nomePortaOrOggetto = nomePortaOrOggetto.substring(0, nomePortaOrOggetto.length()-1);

		return nomePortaOrOggetto;
	}
	/**
	 * Getter per la giocataFastForward.
	 * @return giocataFastForward.
	 */
	public boolean getGiocataFastForward()
	{
		return giocataFastForward;
	}
	/**
	 * Getter per la rigaScritta dall'utente.
	 * @return riga.
	 */
	public String getRigaScritta()
	{
		return riga;
	}
}
