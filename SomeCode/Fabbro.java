package it.uniroma1.textadv.personaggi;

/**
 * Questa classe rappresenta il personaggio di tipo fabbro.
 * @author Maximiliano Ziegler
 *
 */
public class Fabbro extends Personaggio {
	/**
	 * È l'articolo che si riferisce al nome del personaggio.
	 */
	private String articolo = "il ";
	/**
	 * È l'articolo indeterminativo che si riferisce al nome del personaggio.
	 */
	private String articoloIndeterminativo = "un ";
	/**
	 * Indica se il personaggio è prendibile dall'inizio del gioco o meno ( ovviamente ci riferiamo al personaggio oggetto).
	 */
	private boolean inizialmentePrendibile;
	/**
	 * È la descrizione del personaggio ( utile per far sapere al giocatore con quale personaggio ha a che fare, ovviamente con parte grafica si capisce anche senza).
	 */
	private String descrizione = " (il fabbro)";
	/**
	 * È il dialogo iniziale del personaggio. Viene mostrato la prima volta che il giocatore parla con il personaggio.
	 */
	private String dialogoIniziale = "Ah, tu devi essere Zak, il vecchio mi ha parlato di te.\nDiceva che non ti avrei mai conosciuto, che non avresti mai scoperto questo posto.\nBe a quanto pare si sbagliava.\nComunque è un piacere conoscerti, io sono Glondir, e sono un fabbro.";
	/**
	 * È il dialogo standard ( ovvero è il dialogo che viene mostrato al giocatore ogni volta che il giocatore parla con il personaggio dopo la prima volta che ci ha parlato).
	 */
	private String dialogoStandard = "Putroppo non posso forgiare niente per te. Lavoro solo su commissione del vecchio.";
	
	/**
	 * Costruttore del personaggio fabbro.
	 * @param nome
	 */
	public Fabbro( String nome)
	{
		super(nome);
	}
	
	@Override
	public String getArticolo() 
	{
		return articolo;
	}
	
	@Override
	public String getDialogoIniziale() 
	{
		return dialogoIniziale;
	}
	@Override
	public String getDialogoStandard() 
	{
		return dialogoStandard;
	}
	
	@Override
	public String getDescrizione()
	{
		return descrizione;
	}
	
	@Override
	public boolean getPrendibile()
	{
		return inizialmentePrendibile;
	}
	
	@Override
	public String getArticoloIndeterminativo()
	{
		return articoloIndeterminativo;
	}
	
	@Override
	public void setArticoloIndeterminativo(String articoloIndeterminativo)
	{
		this.articoloIndeterminativo = articoloIndeterminativo;
	}
	
	@Override
	public void setDialogoStandard(String dialogoStandard)
	{
		this.dialogoStandard = dialogoStandard;
	}
	
	@Override
	public void setDialogoIniziale(String dialogoIniziale)
	{
		this.dialogoIniziale = dialogoIniziale;
	}
	
	

}
