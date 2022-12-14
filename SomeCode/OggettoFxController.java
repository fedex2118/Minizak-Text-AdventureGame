package it.uniroma1.textadv.javaFxOggetti;

import static it.uniroma1.textadv.javaFxMain.ImageFxController.*;
import static it.uniroma1.textadv.javaFxMain.SpriteAnimation.*;
import static it.uniroma1.textadv.javaFxPersonaggi.PersonaggioFxController.*;
import static it.uniroma1.textadv.javaFxStanze.StanzaFxController.*;

import java.util.ArrayList;
import java.util.List;

import it.uniroma1.textadv.Mondo;
import it.uniroma1.textadv.javaFxMain.SceneController;
import it.uniroma1.textadv.javaFxMain.SpriteAnimation;
import it.uniroma1.textadv.javaFxStanze.StanzaFx;
import it.uniroma1.textadv.javaFxStanze.StanzaFxController;
import it.uniroma1.textadv.oggetti.GemmaSpeciale;
import it.uniroma1.textadv.oggetti.Oggetto;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
/**
 * Questa Classe si occupa di "gestire" tutte le componenti legate agli OggettiFx ovvero gli oggetti grafici, vengono qui
 * mantenute tutte le informazioni, immagini e animazioni legate ad essi e vengono inizializzati grazie al Mondo tutti.
 * Questi difatto ampliano quelli testuali dandogli funzionalità grafiche che altrimenti non avrebbero.
 * @author Federico Ziegler
 *
 */
public class OggettoFxController
{
	// Items Nomi
	public static final String SCRIVANIA_STRING = "scrivania";
	public static final String CHIAVE_STRING = "chiave";
	public static final String SALVADANAIO_STRING = "salvadanaio";
	public static final String SOLDI_STRING = "soldi";
	public static final String MARTELLO_STRING = "martello";
	public static final String CACCIAVITE_STRING = "cacciavite";
	public static final String TRONCHESI_STRING = "tronchesi";
	public static final String ARMADIO_LUCCICANTE_STRING = "armadio luccicante";
	public static final String CHIAVE_TELETRASPORTO_STRING = "chiave teletrasporto";
	public static final String POZZO_STRING = "pozzo";
	public static final String SECCHIO_STRING = "secchio";
	public static final String GEMMA_BLU_STRING = "gemma blu";
	public static final String GEMMA_ROSSA_STRING = "gemma rossa";
	public static final String GEMMA_VERDE_STRING = "gemma verde";
	public static final String GRANDE_CHIAVE_STRING = "grande chiave";
	public static final String CAMINO_STRING = "camino";
	public static final String CASSETTO_STRING = "cassetto";
	public static final String TESORO_STRING = "tesoro";
	public static final String MINIGUN_STRING = "minigun";
	public static final String ANELLO_RUBINO_STRING = "anello rubino";
	public static final String CARNE_MARCIA_STRING = "carne marcia";
	public static final String CARNE_FRESCA_STRING = "carne fresca";
	public static final String POZIONE_INGRANDIMENTO_STRING = "pozione ingrandimento";
	public static final String MONETE_STRING = "monete";
	public static final String VASO_STRING = "vaso";
	public static final String VASO_ROTTO_STRING = "vaso rotto";
	public static final String CIONDOLO_STRING = "ciondolo";
	public static final String BLUEPRINT_ARCO_STRING = "blueprint arco";
	public static final String BLUEPRINT_FRECCE_STRING = "blueprint frecce";
	public static final String PARETE_MISTERIOSA_STRING = "parete misteriosa";
	public static final String GABBIA_STRING = "gabbia";
	public static final String GEMMA_GIALLA_STRING = "gemma gialla";
	public static final String GEMMA_ARGENTEA_STRING = "gemma argentea";
	public static final String ROCCIA_STRING = "roccia";
	public static final String CESPUGLIO_STRING = "cespuglio";
	public static final String CHIAVE_GABBIA_STRING = "chiave gabbia";
	public static final String VELENO_STRING = "veleno";
	public static final String ARCO_STRING = "arco";
	public static final String STRANO_ARTEFATTO_STRING = "strano artefatto";
	public static final String FRECCE_STRING = "frecce";
	public static final String FRECCE_AVVELENATE_STRING = "frecce avvelenate";
	public static final String BLUEPRINT_GEMMA_MULTICOLORE_STRING = "blueprint gemma multicolore";
	public static final String GEMMA_MULTICOLORE_STRING = "gemma multicolore";
	
	// Items Path
	private static final String ITEMS_PATH = "/Sprites/Interactable/";

    // Items calcoli dimensione
    private static final double SCRIVANIA_DIM = 430;
    private static final double CHIAVE_DIM = 70;
    private static final double SALVADANAIO_DIM = 1100;
    private static final double CASH_DIM = 50;
    private static final double CACCIAVITE_DIM = 60;
    private static final double MARTELLO_DIM = 70;
    private static final double POZZO_DIM = 7500;
    private static final double SECCHIO_DIM = 60;
    private static final double TRONCHESI_DIM = 50;
    private static final double ARMADIO_LUCCICANTE_CHIUSO_DIM = 1500;
    private static final double ARMADIO_LUCCICANTE_APERTO_DIM = 1500;
    private static final double CHIAVE_TELETRASPORTO_DIM = 40;
    private static final double FUOCO_CAMINO_DIM = 1075;
    private static final double CAMINO_DIM = 1075;
    private static final double SECCHIOACQUA_SU_FUOCO_DIM = 700;
    private static final double GEMMA_DIM = 200;
    private static final double CASSETTO_DIM = 300;
    private static final double GRANDE_CHIAVE_DIM = 80;
    private static final double TESORO_DIM = 200;
    private static final double ANELLO_DIM = 20;
    private static final double BLUEPRINT_DIM = 50;
//    private static final double CESPUGLIO_RAGNO_DIM = 900;
//    private static final double PORTA_MISTERIOSA_DIM = 1000;
    private static final double ROCCIA_DIM = 700;
    private static final double POZIONE_INGRANDIMENTO_DIM = 60;
    private static final double ARROW_DIM = 100;
    private static final double CHIAVE_GABBIA_DIM = 50;
    private static final double CARNE_DIM = 700;
    private static final double PENDENTE_GOBLIN_DIM = 200;
    private static final double MONETE_DIM = 50;
    private static final double MINIGUN_DIM = 100;
    private static final double VASO_DUNGEON_DIM = 3300;
    private static final double VECCHIO_BAULE_DIM = 300;
    
    private static final double SCRIVANIA_X = calculateX(calculateImageFxDimX(SCRIVANIA_DIM));
    private static final double SCRIVANIA_Y = calculateImageFxDimY(SCRIVANIA_DIM);
    private static final double CHIAVE_X = calculateX(calculateImageFxDimX(CHIAVE_DIM));
    private static final double CHIAVE_Y = calculateImageFxDimY(CHIAVE_DIM);
    private static final double SALVADANAIO_X = calculateX(calculateImageFxDimX(SALVADANAIO_DIM));
    private static final double SALVADANAIO_Y = calculateImageFxDimY(SALVADANAIO_DIM);
    private static final double CASH_X = calculateX(calculateImageFxDimX(CASH_DIM));
    private static final double CASH_Y = calculateImageFxDimY(CASH_DIM);
    private static final double CACCIAVITE_X = calculateX(calculateImageFxDimX(CACCIAVITE_DIM));
    private static final double CACCIAVITE_Y = calculateImageFxDimY(CACCIAVITE_DIM);
    private static final double MARTELLO_X = calculateX(calculateImageFxDimX(MARTELLO_DIM));
    private static final double MARTELLO_Y = calculateImageFxDimY(MARTELLO_DIM);
    private static final double POZZO_X = calculateX(calculateImageFxDimX(POZZO_DIM));
    private static final double POZZO_Y = calculateImageFxDimY(POZZO_DIM);
    private static final double SECCHIO_X = calculateX(calculateImageFxDimX(SECCHIO_DIM));
    private static final double SECCHIO_Y = calculateImageFxDimY(SECCHIO_DIM);
    private static final double TRONCHESI_X = calculateX(calculateImageFxDimX(TRONCHESI_DIM));
    private static final double TRONCHESI_Y = calculateImageFxDimY(TRONCHESI_DIM);
    private static final double ARMADIO_LUCCICANTE_CHIUSO_X = calculateX(calculateImageFxDimX(ARMADIO_LUCCICANTE_CHIUSO_DIM));
    private static final double ARMADIO_LUCCICANTE_CHIUSO_Y = calculateImageFxDimY(ARMADIO_LUCCICANTE_CHIUSO_DIM);
    private static final double ARMADIO_LUCCICANTE_APERTO_X = calculateX(calculateImageFxDimX(ARMADIO_LUCCICANTE_APERTO_DIM));
    private static final double ARMADIO_LUCCICANTE_APERTO_Y = calculateImageFxDimY(ARMADIO_LUCCICANTE_APERTO_DIM);
    private static final double CHIAVE_TELETRASPORTO_X = calculateX(calculateImageFxDimX(CHIAVE_TELETRASPORTO_DIM));
    private static final double CHIAVE_TELETRASPORTO_Y = calculateImageFxDimY(CHIAVE_TELETRASPORTO_DIM);
    private static final double FUOCO_CAMINO_X = calculateX(calculateImageFxDimX(FUOCO_CAMINO_DIM));
    private static final double FUOCO_CAMINO_Y = calculateImageFxDimY(FUOCO_CAMINO_DIM);
    private static final double CAMINO_X = calculateX(calculateImageFxDimX(CAMINO_DIM));
    private static final double CAMINO_Y = calculateImageFxDimY(CAMINO_DIM);
    private static final double SECCHIOACQUA_SU_FUOCO_X = calculateX(calculateImageFxDimX(SECCHIOACQUA_SU_FUOCO_DIM));
    private static final double SECCHIOACQUA_SU_FUOCO_Y = calculateImageFxDimY(SECCHIOACQUA_SU_FUOCO_DIM);
    private static final double GEMMA_X = calculateX(calculateImageFxDimX(GEMMA_DIM));
    private static final double GEMMA_Y = calculateImageFxDimY(GEMMA_DIM);
    private static final double CASSETTO_X = calculateX(calculateImageFxDimX(CASSETTO_DIM));
    private static final double CASSETTO_Y = calculateImageFxDimY(CASSETTO_DIM);
    private static final double GRANDE_CHIAVE_X = calculateX(calculateImageFxDimX(GRANDE_CHIAVE_DIM));
    private static final double GRANDE_CHIAVE_Y = calculateImageFxDimY(GRANDE_CHIAVE_DIM);
    private static final double TESORO_X = calculateX(calculateImageFxDimX(TESORO_DIM));
    private static final double TESORO_Y = calculateImageFxDimY(TESORO_DIM);
    private static final double ANELLO_X = calculateX(calculateImageFxDimX(ANELLO_DIM));
    private static final double ANELLO_Y = calculateImageFxDimY(ANELLO_DIM);
    private static final double BLUEPRINT_X = calculateX(calculateImageFxDimX(BLUEPRINT_DIM));
    private static final double BLUEPRINT_Y = calculateImageFxDimY(BLUEPRINT_DIM);
//    private static final double CESPUGLIO_RAGNO_X = calculateX(calculateImageFxDimX(CESPUGLIO_RAGNO_DIM));
//    private static final double CESPUGLIO_RAGNO_Y = calculateImageFxDimY(CESPUGLIO_RAGNO_DIM);
//    private static final double PORTA_MISTERIOSA_X = calculateX(calculateImageFxDimX(PORTA_MISTERIOSA_DIM));
//    private static final double PORTA_MISTERIOSA_Y = calculateImageFxDimY(PORTA_MISTERIOSA_DIM);
    private static final double ROCCIA_X = calculateX(calculateImageFxDimX(ROCCIA_DIM));
    private static final double ROCCIA_Y = calculateImageFxDimY(ROCCIA_DIM);
    private static final double POZIONE_INGRANDIMENTO_X = calculateX(calculateImageFxDimX(POZIONE_INGRANDIMENTO_DIM));
    private static final double POZIONE_INGRANDIMENTO_Y = calculateImageFxDimY(POZIONE_INGRANDIMENTO_DIM);
    private static final double ARROW_X = calculateX(calculateImageFxDimX(ARROW_DIM));
    private static final double ARROW_Y = calculateImageFxDimY(ARROW_DIM);
    private static final double CHIAVE_GABBIA_X = calculateX(calculateImageFxDimX(CHIAVE_GABBIA_DIM));
    private static final double CHIAVE_GABBIA_Y = calculateImageFxDimY(CHIAVE_GABBIA_DIM);
    private static final double CARNE_X = calculateX(calculateImageFxDimX(CARNE_DIM));
    private static final double CARNE_Y = calculateImageFxDimY(CARNE_DIM);
    private static final double PENDENTE_GOBLIN_X = calculateX(calculateImageFxDimX(PENDENTE_GOBLIN_DIM));
    private static final double PENDENTE_GOBLIN_Y = calculateImageFxDimY(PENDENTE_GOBLIN_DIM);
    private static final double MONETE_X = calculateX(calculateImageFxDimX(MONETE_DIM));
    private static final double MONETE_Y = calculateImageFxDimY(MONETE_DIM);
    private static final double MINIGUN_X = calculateX(calculateImageFxDimX(MINIGUN_DIM));
    private static final double MINIGUN_Y = calculateImageFxDimY(MINIGUN_DIM);
    private static final double VASO_DUNGEON_X = calculateX(calculateImageFxDimX(VASO_DUNGEON_DIM));
    private static final double VASO_DUNGEON_Y = calculateImageFxDimY(VASO_DUNGEON_DIM);
    private static final double VECCHIO_BAULE_X = calculateX(calculateImageFxDimX(VECCHIO_BAULE_DIM));
//    private static final double VECCHIO_BAULE_Y = calculateImageFxDimY(VECCHIO_BAULE_DIM);
	
	// Items Sprite Attributes
	// Scrivania
    private static final int SCRIVANIA_COUNT = 2;
    private static final int SCRIVANIA_COLUMNS = 2;
    private static final int SCRIVANIA_OFFSET_X = 0;
    private static final int SCRIVANIA_OFFSET_Y = 0;
    private static final double SCRIVANIA_WIDTH = Math.round(SCRIVANIA_X / SCRIVANIA_COUNT); // 2 frame = diviso 2!
    private static final double SCRIVANIA_HEIGHT = SCRIVANIA_Y;
    private static final double SCRIVANIA_FRAME_MILLIS = 100;
    private static final int SCRIVANIA_FRAME_TO_WAIT = 1;
	
	// Salvadanaio
    private static final int SALVADANAIO_COUNT = 11;
    private static final int SALVADANAIO_COLUMNS = 11;
    private static final int SALVADANAIO_OFFSET_X = 0;
    private static final int SALVADANAIO_OFFSET_Y = 0;
    private static final double SALVADANAIO_WIDTH = Math.round(SALVADANAIO_X / SALVADANAIO_COUNT);
    private static final double SALVADANAIO_HEIGHT = SALVADANAIO_Y;
    private static final double SALVADANAIO_FRAME_MILLIS = 100;
    private static final int SALVADANAIO_FRAME_TO_WAIT = 5;
    
    // Pozzo
    private static final int POZZO_COUNT = 35;
    private static final int POZZO_COLUMNS = 35;
    private static final int POZZO_OFFSET_X = 0;
    private static final int POZZO_OFFSET_Y = 0;
    private static final double POZZO_WIDTH = Math.round(POZZO_X / POZZO_COUNT);
    private static final double POZZO_HEIGHT = POZZO_Y;
    private static final double POZZO_FRAME_MILLIS = 200;
    
    // Armadio Luccicante Chiuso
    private static final int ARMADIO_LUCCICANTE_COUNT = 4;
    private static final int ARMADIO_LUCCICANTE_COLUMNS = 4;
    private static final int ARMADIO_LUCCICANTE_OFFSET_X = 0;
    private static final int ARMADIO_LUCCICANTE_OFFSET_Y = 0;
    private static final double ARMADIO_LUCCICANTE_WIDTH = Math.round(ARMADIO_LUCCICANTE_CHIUSO_X / ARMADIO_LUCCICANTE_COUNT);
    private static final double ARMADIO_LUCCICANTE_HEIGHT = ARMADIO_LUCCICANTE_CHIUSO_Y;
    private static final double ARMADIO_LUCCICANTE_FRAME_MILLIS = 300;
    
    // Armadio Luccicante Aperto
    private static final int ARMADIO_LUCCICANTE_APERTO_COUNT = 4;
    private static final int ARMADIO_LUCCICANTE_APERTO_COLUMNS = 4;
    private static final int ARMADIO_LUCCICANTE_APERTO_OFFSET_X = 0;
    private static final int ARMADIO_LUCCICANTE_APERTO_OFFSET_Y = 0;
    private static final double ARMADIO_LUCCICANTE_APERTO_WIDTH = Math.round(ARMADIO_LUCCICANTE_APERTO_X / ARMADIO_LUCCICANTE_APERTO_COUNT);
    private static final double ARMADIO_LUCCICANTE_APERTO_HEIGHT = ARMADIO_LUCCICANTE_APERTO_Y;
    private static final double ARMADIO_LUCCICANTE_APERTO_FRAME_MILLIS = 300;
    
    // Fuoco Camino
    private static final int FUOCO_CAMINO_COUNT = 7;
    private static final int FUOCO_CAMINO_COLUMNS = 7;
    private static final int FUOCO_CAMINO_OFFSET_X = 0;
    private static final int FUOCO_CAMINO_OFFSET_Y = 0;
    private static final double FUOCO_CAMINO_WIDTH = Math.round(FUOCO_CAMINO_X / FUOCO_CAMINO_COUNT);
    private static final double FUOCO_CAMINO_HEIGHT = FUOCO_CAMINO_Y;
    private static final double FUOCO_CAMINO_FRAME_MILLIS = 200;
    
    // Camino
    private static final int CAMINO_COUNT = 7;
    private static final int CAMINO_COLUMNS = 7;
    private static final int CAMINO_OFFSET_X = 0;
    private static final int CAMINO_OFFSET_Y = 0;
    private static final double CAMINO_WIDTH = Math.round(CAMINO_X / CAMINO_COUNT);
    private static final double CAMINO_HEIGHT = CAMINO_Y;
    private static final double CAMINO_FRAME_MILLIS = 200;
    
    // Secchio Acqua su Fuoco (Camino)
    private static final int SECCHIOACQUA_SU_FUOCO_COUNT = 5;
    private static final int SECCHIOACQUA_SU_FUOCO_COLUMNS = 5;
    private static final int SECCHIOACQUA_SU_FUOCO_OFFSET_X = 0;
    private static final int SECCHIOACQUA_SU_FUOCO_OFFSET_Y = 0;
    private static final double SECCHIOACQUA_SU_FUOCO_WIDTH = Math.round(SECCHIOACQUA_SU_FUOCO_X / SECCHIOACQUA_SU_FUOCO_COUNT);
    private static final double SECCHIOACQUA_SU_FUOCO_HEIGHT = SECCHIOACQUA_SU_FUOCO_Y;
    private static final double SECCHIOACQUA_SU_FUOCO_FRAME_MILLIS = 200;
    
    // Gemma Rossa
    private static final int GEMMA_ROSSA_COUNT = 7;
    private static final int GEMMA_ROSSA_COLUMNS = 7;
    private static final int GEMMA_ROSSA_OFFSET_X = 0;
    private static final int GEMMA_ROSSA_OFFSET_Y = 0;
    private static final double GEMMA_ROSSA_WIDTH = Math.round(GEMMA_X / GEMMA_ROSSA_COUNT);
//    private static final double GEMMA_ROSSA_HEIGHT = GEMMA_Y;
    private static final double GEMMA_ROSSA_FRAME_MILLIS = 200;
    
    // Gemma Blu
    private static final int GEMMA_BLU_COUNT = 7;
    private static final int GEMMA_BLU_COLUMNS = 7;
    private static final int GEMMA_BLU_OFFSET_X = 0;
    private static final int GEMMA_BLU_OFFSET_Y = 0;
    private static final double GEMMA_BLU_WIDTH = Math.round(GEMMA_X / GEMMA_BLU_COUNT);
//    private static final double GEMMA_BLU_HEIGHT = GEMMA_Y;
    private static final double GEMMA_BLU_FRAME_MILLIS = 200;
    
    // Gemma Verde
    private static final int GEMMA_VERDE_COUNT = 7;
    private static final int GEMMA_VERDE_COLUMNS = 7;
    private static final int GEMMA_VERDE_OFFSET_X = 0;
    private static final int GEMMA_VERDE_OFFSET_Y = 0;
    private static final double GEMMA_VERDE_WIDTH = Math.round(GEMMA_X / GEMMA_VERDE_COUNT);
//    private static final double GEMMA_VERDE_HEIGHT = GEMMA_Y;
    private static final double GEMMA_VERDE_FRAME_MILLIS = 200;
    
    // Cassetto
    private static final int CASSETTO_COUNT = 2;
    private static final int CASSETTO_COLUMNS = 2;
    private static final int CASSETTO_OFFSET_X = 0;
    private static final int CASSETTO_OFFSET_Y = 0;
    private static final double CASSETTO_WIDTH = Math.round(CASSETTO_X / CASSETTO_COUNT);
    private static final double CASSETTO_HEIGHT = CASSETTO_Y;
    private static final double CASSETTO_FRAME_MILLIS = 100;
    private static final int CASSETTO_FRAME_TO_WAIT = 1;
    
    // Cespuglio Ragno
    private static final int CESPUGLIO_RAGNO_COUNT = 5;
    private static final int CESPUGLIO_RAGNO_COLUMNS = 5;
    private static final int CESPUGLIO_RAGNO_OFFSET_X = 0;
    private static final int CESPUGLIO_RAGNO_OFFSET_Y = 0;
    private static final double CESPUGLIO_RAGNO_WIDTH = ROOM_X_CALCULATED;
    private static final double CESPUGLIO_RAGNO_HEIGHT = ROOM_IMAGE_Y;
    private static final double CESPUGLIO_RAGNO_FRAME_MILLIS = 100;
    
    // Porta Misteriosa
    private static final int PORTA_MISTERIOSA_COUNT = 5;
    private static final int PORTA_MISTERIOSA_COLUMNS = 5;
    private static final int PORTA_MISTERIOSA_OFFSET_X = 0;
    private static final int PORTA_MISTERIOSA_OFFSET_Y = 0;
    private static final double PORTA_MISTERIOSA_WIDTH = ROOM_X_CALCULATED;
    private static final double PORTA_MISTERIOSA_HEIGHT = ROOM_IMAGE_Y;
    private static final double PORTA_MISTERIOSA_FRAME_MILLIS = 150;
    private static final int PORTA_MISTERIOSA_FRAME_TO_WAIT = 3;
    
    // Gemma Argentea
    private static final int GEMMA_ARGENTEA_COUNT = 7;
    private static final int GEMMA_ARGENTEA_COLUMNS = 7;
    private static final int GEMMA_ARGENTEA_OFFSET_X = 0;
    private static final int GEMMA_ARGENTEA_OFFSET_Y = 0;
    private static final double GEMMA_ARGENTEA_WIDTH = Math.round(GEMMA_X / GEMMA_ARGENTEA_COUNT);
//    private static final double GEMMA_ARGENTEA_HEIGHT = GEMMA_Y;
    private static final double GEMMA_ARGENTEA_FRAME_MILLIS = 200;
    
    // Roccia
    private static final int ROCCIA_COUNT = 8;
    private static final int ROCCIA_COLUMNS = 8;
    private static final int ROCCIA_OFFSET_X = 0;
    private static final int ROCCIA_OFFSET_Y = 0;
    private static final double ROCCIA_WIDTH = Math.round(ROCCIA_X / ROCCIA_COUNT);
    private static final double ROCCIA_HEIGHT = GEMMA_Y;
    private static final double ROCCIA_FRAME_MILLIS = 200;
    
    // Gemma Verde
    private static final int GEMMA_SPECIALE_COUNT = 7;
    private static final int GEMMA_SPECIALE_COLUMNS = 7;
    private static final int GEMMA_SPECIALE_OFFSET_X = 0;
    private static final int GEMMA_SPECIALE_OFFSET_Y = 0;
    private static final double GEMMA_SPECIALE_WIDTH = Math.round(GEMMA_X / GEMMA_VERDE_COUNT);
//    private static final double GEMMA_SPECIALE_HEIGHT = GEMMA_Y;
    private static final double GEMMA_SPECIALE_FRAME_MILLIS = 200;
    
    // Carne
    private static final int CARNE_COUNT = 5;
    private static final int CARNE_COLUMNS = 5;
    private static final int CARNE_OFFSET_X = 0;
    private static final int CARNE_OFFSET_Y = 0;
    private static final double CARNE_WIDTH = Math.round(CARNE_X / CARNE_COUNT);
//    private static final double CARNE_HEIGHT = CARNE_Y;
    private static final double CARNE_FRAME_MILLIS = 200;
    
    // Vaso Dungeon
    private static final int VASO_DUNGEON_COUNT = 16;
    private static final int VASO_DUNGEON_COLUMNS = 16;
    private static final int VASO_DUNGEON_OFFSET_X = 0;
    private static final int VASO_DUNGEON_OFFSET_Y = 0;
    private static final double VASO_DUNGEON_WIDTH = Math.round(VASO_DUNGEON_X / VASO_DUNGEON_COUNT);
    private static final double VASO_DUNGEON_HEIGHT = CARNE_Y;
    private static final double VASO_DUNGEON_FRAME_MILLIS = 100;
    private static final int VASO_DUNGEON_FRAME_TO_WAIT = 11;
    
    // Veleno Ragno
    private static final int VELENO_RAGNO_COUNT = 5;
    private static final int VELENO_RAGNO_COLUMNS = 5;
    private static final int VELENO_RAGNO_OFFSET_X = 0;
    private static final int VELENO_RAGNO_OFFSET_Y = 0;
    private static final double VELENO_RAGNO_WIDTH = Math.round(SPIDER_ENLARGED_X / VELENO_RAGNO_COUNT);
    private static final double VELENO_RAGNO_HEIGHT = SPIDER_ENLARGED_Y;
    private static final double VELENO_RAGNO_FRAME_MILLIS = 150;
    
    // Tutorial
    // Vecchio Baule
    // Veleno Ragno
    private static final int VECCHIO_BAULE_COUNT = 2;
    private static final int VECCHIO_BAULE_COLUMNS = 2;
    private static final int VECCHIO_BAULE_OFFSET_X = 0;
    private static final int VECCHIO_BAULE_OFFSET_Y = 0;
    private static final double VECCHIO_BAULE_WIDTH = Math.round(VECCHIO_BAULE_X / VECCHIO_BAULE_COUNT);
    private static final double VECCHIO_BAULE_HEIGHT = SPIDER_ENLARGED_Y;
    private static final double VECCHIO_BAULE_FRAME_MILLIS = 100;
    
    // Items Png
    private static final String SCRIVANIA_PNG = ITEMS_PATH + "Scrivania" + PNG;
    private static final String CHIAVE_PNG = ITEMS_PATH + "Chiave" + PNG;
    private static final String SALVADANAIO_PNG = ITEMS_PATH + "Salvadanaio" + PNG;
    private static final String CASH_PNG = ITEMS_PATH + "Cash" + PNG;
    private static final String CACCIAVITE_PNG = ITEMS_PATH + "Cacciavite" + PNG;
    private static final String MARTELLO_PNG = ITEMS_PATH + "Martello" + PNG;
    private static final String POZZO_PNG = ITEMS_PATH + "Pozzo" + PNG;
    private static final String SECCHIO_PNG = ITEMS_PATH + "Secchio" + PNG;
    private static final String TRONCHESI_PNG = ITEMS_PATH + "Tronchesi" + PNG;
    private static final String ARMADIO_LUCCICANTE_CHIUSO_PNG = ITEMS_PATH + "ArmadioLuccicanteChiuso" + PNG;
    private static final String ARMADIO_LUCCICANTE_APERTO_PNG = ITEMS_PATH + "ArmadioLuccicanteAperto" + PNG;
    private static final String CHIAVE_TELETRASPORTO_PNG = ITEMS_PATH + "ChiaveTeletrasporto" + PNG;
    private static final String FUOCO_CAMINO_PNG = ITEMS_PATH + "FuocoCamino" + PNG;
    private static final String CAMINO_PNG = ITEMS_PATH + "Camino" + PNG;
    private static final String SECCHIOACQUA_SU_FUOCO_PNG = ITEMS_PATH + "SecchioAcquaSuFuoco" + PNG;
    private static final String GEMMA_ROSSA_PNG = ITEMS_PATH + "GemmaRossa" + PNG;
    private static final String GEMMA_ROSSA_EFFECT_PNG = ITEMS_PATH + "GemmaRossaEffect" + PNG;
    private static final String GEMMA_BLU_PNG = ITEMS_PATH + "GemmaBlu" + PNG;
    private static final String GEMMA_BLU_WALL_PNG = ITEMS_PATH + "GemmaBluWall" + PNG;
    private static final String GEMMA_VERDE_PNG = ITEMS_PATH + "GemmaVerde" + PNG;
    private static final String CASSETTO_PNG = ITEMS_PATH + "Cassetto" + PNG;
    private static final String GRANDE_CHIAVE_PNG = ITEMS_PATH + "GrandeChiave" + PNG;
    private static final String TESORO_PNG = ITEMS_PATH + "Tesoro" + PNG;
    private static final String ANELLO_PNG = ITEMS_PATH + "Anello" + PNG;
    private static final String BLUEPRINT_PNG = ITEMS_PATH + "Blueprint" + PNG;
    private static final String CESPUGLIO_RAGNO_PNG = ITEMS_PATH + "CespuglioRagnatela" + PNG;
    private static final String PORTA_MISTERIOSA_PNG = ITEMS_PATH + "PortaMisteriosa" + PNG;
    private static final String GEMMA_ARGENTEA_PNG = ITEMS_PATH + "GemmaArgentea" + PNG;
    private static final String ROCCIA_PNG = ITEMS_PATH + "MuroSegreto" + PNG;
    private static final String GEMMA_SPECIALE_PNG = ITEMS_PATH + "GemmaSpeciale" + PNG;
    private static final String POZIONE_INGRANDIMENTO_PNG = ITEMS_PATH + "PozioneIngrandimento" + PNG;
    private static final String ARROW_PNG = ITEMS_PATH + "Arrow" + PNG;
    private static final String CHIAVE_GABBIA_PNG = ITEMS_PATH + "ChiaveGabbia" + PNG;
    private static final String CARNE_ROTTEN_PNG = ITEMS_PATH + "RottenMeat" + PNG;
    private static final String CARNE_FRESH_PNG = ITEMS_PATH + "FreshMeat" + PNG;
    private static final String PENDENTE_GOBLIN_PNG = ITEMS_PATH + "PendenteGoblin" + PNG;
    private static final String MONETE_PNG = ITEMS_PATH + "Monete" + PNG;
    private static final String MINIGUN_PNG = ITEMS_PATH + "Minigun" + PNG;
    private static final String VASO_DUNGEON_PNG = ITEMS_PATH + "Vaso" + PNG;
    private static final String VELENO_RAGNO_PNG = ITEMS_PATH + "VelenoRagno" + PNG;
    
    // Tutorial
    private static final String VECCHIO_BAULE_PNG = ITEMS_PATH + "VecchioBaule" + PNG;
    
    // Items Image
    private static final Image SCRIVANIA_IMAGE = new Image(OggettoFxController.class.getResource(SCRIVANIA_PNG).toExternalForm(), applicaCorrezioneMaxX(SCRIVANIA_WIDTH, SCRIVANIA_COUNT), SCRIVANIA_Y, true, false);
    private static final Image CHIAVE_IMAGE = new Image(OggettoFxController.class.getResource(CHIAVE_PNG).toExternalForm(), CHIAVE_X, CHIAVE_Y, true, false);
    private static final Image SALVADANAIO_IMAGE = new Image(OggettoFxController.class.getResource(SALVADANAIO_PNG).toExternalForm(), applicaCorrezioneMaxX(SALVADANAIO_WIDTH, SALVADANAIO_COUNT), SALVADANAIO_Y, true, false);
    private static final Image CASH_IMAGE = new Image(OggettoFxController.class.getResource(CASH_PNG).toExternalForm(), CASH_X, CASH_Y, true, false);
    private static final Image CACCIAVITE_IMAGE = new Image(OggettoFxController.class.getResource(CACCIAVITE_PNG).toExternalForm(), CACCIAVITE_X, CACCIAVITE_Y, true, false);
    private static final Image MARTELLO_IMAGE = new Image(OggettoFxController.class.getResource(MARTELLO_PNG).toExternalForm(), MARTELLO_X, MARTELLO_Y, true, false);
    private static final Image POZZO_IMAGE = new Image(OggettoFxController.class.getResource(POZZO_PNG).toExternalForm(), applicaCorrezioneMaxX(POZZO_WIDTH, POZZO_COUNT), POZZO_Y, true, false);
    private static final Image SECCHIO_IMAGE = new Image(OggettoFxController.class.getResource(SECCHIO_PNG).toExternalForm(), SECCHIO_X, SECCHIO_Y, true, false);
    private static final Image TRONCHESI_IMAGE = new Image(OggettoFxController.class.getResource(TRONCHESI_PNG).toExternalForm(), TRONCHESI_X, TRONCHESI_Y, true, false);
    private static final Image ARMADIO_LUCCICANTE_CHIUSO_IMAGE = new Image(OggettoFxController.class.getResource(ARMADIO_LUCCICANTE_CHIUSO_PNG).toExternalForm(), applicaCorrezioneMaxX(ARMADIO_LUCCICANTE_WIDTH, ARMADIO_LUCCICANTE_COUNT), ARMADIO_LUCCICANTE_CHIUSO_Y, true, false);
    private static final Image ARMADIO_LUCCICANTE_APERTO_IMAGE = new Image(OggettoFxController.class.getResource(ARMADIO_LUCCICANTE_APERTO_PNG).toExternalForm(), applicaCorrezioneMaxX(ARMADIO_LUCCICANTE_APERTO_WIDTH, ARMADIO_LUCCICANTE_APERTO_COUNT), ARMADIO_LUCCICANTE_APERTO_Y, true, false);
    private static final Image CHIAVE_TELETRASPORTO_IMAGE = new Image(OggettoFxController.class.getResource(CHIAVE_TELETRASPORTO_PNG).toExternalForm(), CHIAVE_TELETRASPORTO_X, CHIAVE_TELETRASPORTO_Y, true, false);
    private static final Image FUOCO_CAMINO_IMAGE = new Image(OggettoFxController.class.getResource(FUOCO_CAMINO_PNG).toExternalForm(), applicaCorrezioneMaxX(FUOCO_CAMINO_WIDTH, FUOCO_CAMINO_COUNT), FUOCO_CAMINO_Y, true, false);
    private static final Image CAMINO_IMAGE = new Image(OggettoFxController.class.getResource(CAMINO_PNG).toExternalForm(), applicaCorrezioneMaxX(CAMINO_WIDTH, CAMINO_COUNT), CAMINO_Y, true, false);
    private static final Image SECCHIOACQUA_SU_FUOCO_IMAGE = new Image(OggettoFxController.class.getResource(SECCHIOACQUA_SU_FUOCO_PNG).toExternalForm(), applicaCorrezioneMaxX(SECCHIOACQUA_SU_FUOCO_WIDTH, SECCHIOACQUA_SU_FUOCO_COUNT), SECCHIOACQUA_SU_FUOCO_Y, true, false);
    private static final Image GEMMA_ROSSA_IMAGE = new Image(OggettoFxController.class.getResource(GEMMA_ROSSA_PNG).toExternalForm(), applicaCorrezioneMaxX(GEMMA_ROSSA_WIDTH, GEMMA_ROSSA_COUNT), GEMMA_Y, true, false);
    private static final Image GEMMA_ROSSA_EFFECT_IMAGE = new Image(OggettoFxController.class.getResource(GEMMA_ROSSA_EFFECT_PNG).toExternalForm(), GEMMA_ROSSA_IMAGE.getWidth(), GEMMA_Y, true, false);
    private static final Image GEMMA_BLU_IMAGE = new Image(OggettoFxController.class.getResource(GEMMA_BLU_PNG).toExternalForm(), applicaCorrezioneMaxX(GEMMA_BLU_WIDTH, GEMMA_BLU_COUNT), GEMMA_Y, true, false);
    private static final Image GEMMA_BLU_WALL_IMAGE = new Image(OggettoFxController.class.getResource(GEMMA_BLU_WALL_PNG).toExternalForm(), applicaCorrezioneMaxX(GEMMA_BLU_WIDTH, GEMMA_BLU_COUNT), GEMMA_Y, true, false);
    private static final Image GEMMA_VERDE_IMAGE = new Image(OggettoFxController.class.getResource(GEMMA_VERDE_PNG).toExternalForm(), applicaCorrezioneMaxX(GEMMA_VERDE_WIDTH, GEMMA_VERDE_COUNT), GEMMA_Y, true, false);
    private static final Image CASSETTO_IMAGE = new Image(OggettoFxController.class.getResource(CASSETTO_PNG).toExternalForm(), applicaCorrezioneMaxX(CASSETTO_WIDTH, CASSETTO_COUNT), CASSETTO_Y, true, false);
    private static final Image GRANDE_CHIAVE_IMAGE = new Image(OggettoFxController.class.getResource(GRANDE_CHIAVE_PNG).toExternalForm(), GRANDE_CHIAVE_X, GRANDE_CHIAVE_Y, true, false);
    private static final Image TESORO_IMAGE = new Image(OggettoFxController.class.getResource(TESORO_PNG).toExternalForm(), TESORO_X, TESORO_Y, true, false);
    private static final Image ANELLO_IMAGE = new Image(OggettoFxController.class.getResource(ANELLO_PNG).toExternalForm(), ANELLO_X, ANELLO_Y, true, false);
    private static final Image BLUEPRINT_IMAGE = new Image(OggettoFxController.class.getResource(BLUEPRINT_PNG).toExternalForm(), BLUEPRINT_X, BLUEPRINT_Y, true, false);
    private static final Image CESPUGLIO_RAGNO_IMAGE = new Image(OggettoFxController.class.getResource(CESPUGLIO_RAGNO_PNG).toExternalForm(), ROOM_X_CALCULATED * CESPUGLIO_RAGNO_COUNT, ROOM_IMAGE_Y, true, false);
    private static final Image PORTA_MISTERIOSA_IMAGE = new Image(OggettoFxController.class.getResource(PORTA_MISTERIOSA_PNG).toExternalForm(), ROOM_X_CALCULATED * PORTA_MISTERIOSA_COUNT, ROOM_IMAGE_Y, true, false);
    private static final Image GEMMA_ARGENTEA_IMAGE = new Image(OggettoFxController.class.getResource(GEMMA_ARGENTEA_PNG).toExternalForm(), applicaCorrezioneMaxX(GEMMA_ARGENTEA_WIDTH, GEMMA_ARGENTEA_COUNT), GEMMA_Y, true, false);
    private static final Image ROCCIA_IMAGE = new Image(OggettoFxController.class.getResource(ROCCIA_PNG).toExternalForm(), applicaCorrezioneMaxX(ROCCIA_WIDTH, ROCCIA_COUNT), ROCCIA_Y, true, false);
    private static final Image GEMMA_SPECIALE_IMAGE = new Image(OggettoFxController.class.getResource(GEMMA_SPECIALE_PNG).toExternalForm(), applicaCorrezioneMaxX(GEMMA_SPECIALE_WIDTH, GEMMA_SPECIALE_COUNT), GEMMA_Y, true, false);
    private static final Image POZIONE_INGRANDIMENTO_IMAGE = new Image(OggettoFxController.class.getResource(POZIONE_INGRANDIMENTO_PNG).toExternalForm(), POZIONE_INGRANDIMENTO_X, POZIONE_INGRANDIMENTO_Y, true, false);
    private static final Image ARROW_IMAGE = new Image(OggettoFxController.class.getResource(ARROW_PNG).toExternalForm(), ARROW_X, ARROW_Y, true, false);
    private static final Image CHIAVE_GABBIA_IMAGE = new Image(OggettoFxController.class.getResource(CHIAVE_GABBIA_PNG).toExternalForm(), CHIAVE_GABBIA_X, CHIAVE_GABBIA_Y, true, false);
    private static final Image CARNE_ROTTEN_IMAGE = new Image(OggettoFxController.class.getResource(CARNE_ROTTEN_PNG).toExternalForm(), applicaCorrezioneMaxX(CARNE_WIDTH, CARNE_COUNT), CARNE_Y, true, false);
    private static final Image CARNE_FRESH_IMAGE = new Image(OggettoFxController.class.getResource(CARNE_FRESH_PNG).toExternalForm(), CARNE_ROTTEN_IMAGE.getWidth(), CARNE_Y, true, false);
    private static final Image PENDENTE_GOBLIN_IMAGE = new Image(OggettoFxController.class.getResource(PENDENTE_GOBLIN_PNG).toExternalForm(), PENDENTE_GOBLIN_X, PENDENTE_GOBLIN_Y, true, false);
    private static final Image MONETE_IMAGE = new Image(OggettoFxController.class.getResource(MONETE_PNG).toExternalForm(), MONETE_X, MONETE_Y, true, false);
    private static final Image MINIGUN_IMAGE = new Image(OggettoFxController.class.getResource(MINIGUN_PNG).toExternalForm(), MINIGUN_X, MINIGUN_Y, true, false);
    private static final Image VASO_DUNGEON_IMAGE = new Image(OggettoFxController.class.getResource(VASO_DUNGEON_PNG).toExternalForm(), applicaCorrezioneMaxX(VASO_DUNGEON_WIDTH, VASO_DUNGEON_COUNT), VASO_DUNGEON_Y, true, false);
    private static final Image VELENO_RAGNO_IMAGE = new Image(OggettoFxController.class.getResource(VELENO_RAGNO_PNG).toExternalForm(), applicaCorrezioneMaxX(VELENO_RAGNO_WIDTH, VELENO_RAGNO_COUNT), VELENO_RAGNO_HEIGHT, true, false);
    
    // Tutorial
    private static final Image VECCHIO_BAULE_IMAGE = new Image(OggettoFxController.class.getResource(VECCHIO_BAULE_PNG).toExternalForm(), applicaCorrezioneMaxX(VECCHIO_BAULE_WIDTH, VECCHIO_BAULE_COUNT), VECCHIO_BAULE_HEIGHT, true, false);
    
    // Items ImageView
    public static final ImageView SCRIVANIA_IMAGE_VIEW = new ImageView(SCRIVANIA_IMAGE);
    public static final ImageView CHIAVE_IMAGE_VIEW = new ImageView(CHIAVE_IMAGE);
    public static final ImageView SALVADANAIO_IMAGE_VIEW = new ImageView(SALVADANAIO_IMAGE);
    public static final ImageView CASH_IMAGE_VIEW = new ImageView(CASH_IMAGE);
    public static final ImageView CACCIAVITE_IMAGE_VIEW = new ImageView(CACCIAVITE_IMAGE);
    public static final ImageView MARTELLO_IMAGE_VIEW = new ImageView(MARTELLO_IMAGE);
    public static final ImageView POZZO_IMAGE_VIEW = new ImageView(POZZO_IMAGE);
    public static final ImageView SECCHIO_IMAGE_VIEW = new ImageView(SECCHIO_IMAGE);
    public static final ImageView TRONCHESI_IMAGE_VIEW = new ImageView(TRONCHESI_IMAGE);
    public static final ImageView ARMADIO_LUCCICANTE_CHIUSO_IMAGE_VIEW = new ImageView(ARMADIO_LUCCICANTE_CHIUSO_IMAGE);
    public static final ImageView ARMADIO_LUCCICANTE_APERTO_IMAGE_VIEW = new ImageView(ARMADIO_LUCCICANTE_APERTO_IMAGE);
    public static final ImageView CHIAVE_TELETRASPORTO_IMAGE_VIEW = new ImageView(CHIAVE_TELETRASPORTO_IMAGE);
    public static final ImageView FUOCO_CAMINO_IMAGE_VIEW = new ImageView(FUOCO_CAMINO_IMAGE);
    public static final ImageView CAMINO_IMAGE_VIEW = new ImageView(CAMINO_IMAGE);
    public static final ImageView SECCHIOACQUA_SU_FUOCO_IMAGE_VIEW = new ImageView(SECCHIOACQUA_SU_FUOCO_IMAGE);
    public static final ImageView GEMMA_ROSSA_IMAGE_VIEW = new ImageView(GEMMA_ROSSA_IMAGE);
    public static final ImageView GEMMA_ROSSA_EFFECT_IMAGE_VIEW = new ImageView(GEMMA_ROSSA_EFFECT_IMAGE);
    public static final ImageView GEMMA_BLU_IMAGE_VIEW = new ImageView(GEMMA_BLU_IMAGE);
    public static final ImageView GEMMA_BLU_WALL_IMAGE_VIEW = new ImageView(GEMMA_BLU_WALL_IMAGE);
    public static final ImageView GEMMA_VERDE_IMAGE_VIEW = new ImageView(GEMMA_VERDE_IMAGE);
    public static final ImageView CASSETTO_IMAGE_VIEW = new ImageView(CASSETTO_IMAGE);
    public static final ImageView GRANDE_CHIAVE_IMAGE_VIEW = new ImageView(GRANDE_CHIAVE_IMAGE);
    public static final ImageView TESORO_IMAGE_VIEW = new ImageView(TESORO_IMAGE);
    public static final ImageView ANELLO_IMAGE_VIEW = new ImageView(ANELLO_IMAGE);
    public static final ImageView BLUEPRINT_FRECCE_IMAGE_VIEW = new ImageView(BLUEPRINT_IMAGE);
    public static final ImageView CESPUGLIO_RAGNO_IMAGE_VIEW = new ImageView(CESPUGLIO_RAGNO_IMAGE);
    public static final ImageView PORTA_MISTERIOSA_IMAGE_VIEW = new ImageView(PORTA_MISTERIOSA_IMAGE);
    public static final ImageView GEMMA_ARGENTEA_IMAGE_VIEW = new ImageView(GEMMA_ARGENTEA_IMAGE);
    public static final ImageView ROCCIA_IMAGE_VIEW = new ImageView(ROCCIA_IMAGE);
    public static final ImageView GEMMA_SPECIALE_IMAGE_VIEW = new ImageView(GEMMA_SPECIALE_IMAGE);
    public static final ImageView BLUEPRINT_ARCO_IMAGE_VIEW = new ImageView(BLUEPRINT_IMAGE);
    public static final ImageView POZIONE_INGRANDIMENTO_IMAGE_VIEW = new ImageView(POZIONE_INGRANDIMENTO_IMAGE);
    public static final ImageView ARROW_IMAGE_VIEW = new ImageView(ARROW_IMAGE);
    public static final ImageView CHIAVE_GABBIA_IMAGE_VIEW = new ImageView(CHIAVE_GABBIA_IMAGE);
    public static final ImageView CARNE_ROTTEN_IMAGE_VIEW = new ImageView(CARNE_ROTTEN_IMAGE);
    public static final ImageView CARNE_FRESH_IMAGE_VIEW = new ImageView(CARNE_FRESH_IMAGE);
    public static final ImageView PENDENTE_GOBLIN_IMAGE_VIEW = new ImageView(PENDENTE_GOBLIN_IMAGE);
    public static final ImageView MONETE_IMAGE_VIEW = new ImageView(MONETE_IMAGE);
    public static final ImageView MINIGUN_IMAGE_VIEW = new ImageView(MINIGUN_IMAGE);
    public static final ImageView VASO_DUNGEON_IMAGE_VIEW = new ImageView(VASO_DUNGEON_IMAGE);
    public static final ImageView VELENO_RAGNO_IMAGE_VIEW = new ImageView(VELENO_RAGNO_IMAGE);
    
    // Tutorial
    public static final ImageView VECCHIO_BAULE_IMAGE_VIEW = new ImageView(VECCHIO_BAULE_IMAGE);
    public static final ImageView BLUEPRINT_TUTORIAL_IMAGE_VIEW = new ImageView(BLUEPRINT_IMAGE);
    
    // Items SpriteAnimation
    // Scrivania	
	public static final SpriteAnimation SCRIVANIA_SPRITE_ANIMATION = new SpriteAnimation(
			SCRIVANIA_IMAGE_VIEW,
			Duration.millis(SCRIVANIA_FRAME_MILLIS * SCRIVANIA_COUNT),
			SCRIVANIA_COUNT, SCRIVANIA_COLUMNS,
			SCRIVANIA_OFFSET_X, SCRIVANIA_OFFSET_Y,
			SCRIVANIA_WIDTH, SCRIVANIA_HEIGHT,
			SCRIVANIA_FRAME_MILLIS, SCRIVANIA_FRAME_TO_WAIT);
	
	// Salvadanaio
	public static final SpriteAnimation SALVADANAIO_SPRITE_ANIMATION = new SpriteAnimation(
			SALVADANAIO_IMAGE_VIEW,
			Duration.millis(SALVADANAIO_FRAME_MILLIS * SALVADANAIO_COUNT),
			SALVADANAIO_COUNT, SALVADANAIO_COLUMNS,
			SALVADANAIO_OFFSET_X, SALVADANAIO_OFFSET_Y,
			SALVADANAIO_WIDTH, SALVADANAIO_HEIGHT,
			SALVADANAIO_FRAME_MILLIS, SALVADANAIO_FRAME_TO_WAIT);
	
	// Pozzo
	public static final SpriteAnimation POZZO_SPRITE_ANIMATION = new SpriteAnimation(
			POZZO_IMAGE_VIEW,
			Duration.millis(POZZO_FRAME_MILLIS * POZZO_COUNT),
			POZZO_COUNT, POZZO_COLUMNS,
			POZZO_OFFSET_X, POZZO_OFFSET_Y,
			POZZO_WIDTH, POZZO_HEIGHT,
			POZZO_FRAME_MILLIS);
	
	// Armadio Luccicante Chiuso
	public static final SpriteAnimation ARMADIO_LUCCICANTE_SPRITE_ANIMATION = new SpriteAnimation(
			ARMADIO_LUCCICANTE_CHIUSO_IMAGE_VIEW,
			Duration.millis(ARMADIO_LUCCICANTE_FRAME_MILLIS * ARMADIO_LUCCICANTE_COUNT),
			ARMADIO_LUCCICANTE_COUNT, ARMADIO_LUCCICANTE_COLUMNS,
			ARMADIO_LUCCICANTE_OFFSET_X, ARMADIO_LUCCICANTE_OFFSET_Y,
			ARMADIO_LUCCICANTE_WIDTH, ARMADIO_LUCCICANTE_HEIGHT,
			ARMADIO_LUCCICANTE_FRAME_MILLIS);
	
	// Armadio Luccicante Aperto
	public static final SpriteAnimation ARMADIO_LUCCICANTE_APERTO_SPRITE_ANIMATION = new SpriteAnimation(
			ARMADIO_LUCCICANTE_APERTO_IMAGE_VIEW,
			Duration.millis(ARMADIO_LUCCICANTE_APERTO_FRAME_MILLIS * ARMADIO_LUCCICANTE_APERTO_COUNT),
			ARMADIO_LUCCICANTE_APERTO_COUNT, ARMADIO_LUCCICANTE_APERTO_COLUMNS,
			ARMADIO_LUCCICANTE_APERTO_OFFSET_X, ARMADIO_LUCCICANTE_APERTO_OFFSET_Y,
			ARMADIO_LUCCICANTE_APERTO_WIDTH, ARMADIO_LUCCICANTE_APERTO_HEIGHT,
			ARMADIO_LUCCICANTE_APERTO_FRAME_MILLIS);
	
	// Fuoco Camino
	public static final SpriteAnimation FUOCO_CAMINO_SPRITE_ANIMATION = new SpriteAnimation(
			FUOCO_CAMINO_IMAGE_VIEW,
			Duration.millis(FUOCO_CAMINO_FRAME_MILLIS * FUOCO_CAMINO_COUNT),
			FUOCO_CAMINO_COUNT, FUOCO_CAMINO_COLUMNS,
			FUOCO_CAMINO_OFFSET_X, FUOCO_CAMINO_OFFSET_Y,
			FUOCO_CAMINO_WIDTH, FUOCO_CAMINO_HEIGHT,
			FUOCO_CAMINO_FRAME_MILLIS);
	
	// Camino
	public static final SpriteAnimation CAMINO_SPRITE_ANIMATION = new SpriteAnimation(
			CAMINO_IMAGE_VIEW,
			Duration.millis(CAMINO_FRAME_MILLIS * CAMINO_COUNT),
			CAMINO_COUNT, CAMINO_COLUMNS,
			CAMINO_OFFSET_X, CAMINO_OFFSET_Y,
			CAMINO_WIDTH, CAMINO_HEIGHT,
			CAMINO_FRAME_MILLIS);
	
	// Secchio Acqua su Fuoco (Camino)
	public static final SpriteAnimation SECCHIOACQUA_SU_FUOCO_SPRITE_ANIMATION = new SpriteAnimation(
			SECCHIOACQUA_SU_FUOCO_IMAGE_VIEW,
			Duration.millis(SECCHIOACQUA_SU_FUOCO_FRAME_MILLIS * SECCHIOACQUA_SU_FUOCO_COUNT),
			SECCHIOACQUA_SU_FUOCO_COUNT, SECCHIOACQUA_SU_FUOCO_COLUMNS,
			SECCHIOACQUA_SU_FUOCO_OFFSET_X, SECCHIOACQUA_SU_FUOCO_OFFSET_Y,
			SECCHIOACQUA_SU_FUOCO_WIDTH, SECCHIOACQUA_SU_FUOCO_HEIGHT,
			SECCHIOACQUA_SU_FUOCO_FRAME_MILLIS);
	
	// Gemma Rossa
	public static final SpriteAnimation GEMMA_ROSSA_SPRITE_ANIMATION = new SpriteAnimation(
			GEMMA_ROSSA_IMAGE_VIEW,
			Duration.millis(GEMMA_ROSSA_FRAME_MILLIS * GEMMA_ROSSA_COUNT),
			GEMMA_ROSSA_COUNT, GEMMA_ROSSA_COLUMNS,
			GEMMA_ROSSA_OFFSET_X, GEMMA_ROSSA_OFFSET_Y,
			GEMMA_ROSSA_WIDTH, GEMMA_ROSSA_WIDTH,
			GEMMA_ROSSA_FRAME_MILLIS);
	
	// Gemma Rossa Effect
	public static final SpriteAnimation GEMMA_ROSSA_EFFECT_SPRITE_ANIMATION = new SpriteAnimation(
			GEMMA_ROSSA_EFFECT_IMAGE_VIEW,
			Duration.millis(GEMMA_ROSSA_FRAME_MILLIS * GEMMA_ROSSA_COUNT),
			GEMMA_ROSSA_COUNT, GEMMA_ROSSA_COLUMNS,
			GEMMA_ROSSA_OFFSET_X, GEMMA_ROSSA_OFFSET_Y,
			GEMMA_ROSSA_WIDTH, GEMMA_ROSSA_WIDTH,
			GEMMA_ROSSA_FRAME_MILLIS);
	
	// Gemma Blu
	public static final SpriteAnimation GEMMA_BLU_SPRITE_ANIMATION = new SpriteAnimation(
			GEMMA_BLU_IMAGE_VIEW,
			Duration.millis(GEMMA_BLU_FRAME_MILLIS * GEMMA_BLU_COUNT),
			GEMMA_BLU_COUNT, GEMMA_BLU_COLUMNS,
			GEMMA_BLU_OFFSET_X, GEMMA_BLU_OFFSET_Y,
			GEMMA_BLU_WIDTH, GEMMA_BLU_WIDTH,
			GEMMA_BLU_FRAME_MILLIS);
	
	// Gemma Blu Wall
	public static final SpriteAnimation GEMMA_BLU_WALL_SPRITE_ANIMATION = new SpriteAnimation(
			GEMMA_BLU_WALL_IMAGE_VIEW,
			Duration.millis(GEMMA_BLU_FRAME_MILLIS * GEMMA_BLU_COUNT),
			GEMMA_BLU_COUNT, GEMMA_BLU_COLUMNS,
			GEMMA_BLU_OFFSET_X, GEMMA_BLU_OFFSET_Y,
			GEMMA_BLU_WIDTH, GEMMA_BLU_WIDTH,
			GEMMA_BLU_FRAME_MILLIS);
	
	// Gemma Verde
	public static final SpriteAnimation GEMMA_VERDE_SPRITE_ANIMATION = new SpriteAnimation(
			GEMMA_VERDE_IMAGE_VIEW,
			Duration.millis(GEMMA_VERDE_FRAME_MILLIS * GEMMA_VERDE_COUNT),
			GEMMA_VERDE_COUNT, GEMMA_VERDE_COLUMNS,
			GEMMA_VERDE_OFFSET_X, GEMMA_VERDE_OFFSET_Y,
			GEMMA_VERDE_WIDTH, GEMMA_VERDE_WIDTH,
			GEMMA_VERDE_FRAME_MILLIS);
	
	// Cassetto
	public static final SpriteAnimation CASSETTO_SPRITE_ANIMATION = new SpriteAnimation(
			CASSETTO_IMAGE_VIEW,
			Duration.millis(CASSETTO_FRAME_MILLIS * CASSETTO_COUNT),
			CASSETTO_COUNT, CASSETTO_COLUMNS,
			CASSETTO_OFFSET_X, CASSETTO_OFFSET_Y,
			CASSETTO_WIDTH, CASSETTO_HEIGHT,
			CASSETTO_FRAME_MILLIS, CASSETTO_FRAME_TO_WAIT);
	
	// Cespuglio Ragno
	public static final SpriteAnimation CESPUGLIO_RAGNO_SPRITE_ANIMATION = new SpriteAnimation(
			CESPUGLIO_RAGNO_IMAGE_VIEW,
			Duration.millis(CESPUGLIO_RAGNO_FRAME_MILLIS * CESPUGLIO_RAGNO_COUNT),
			CESPUGLIO_RAGNO_COUNT, CESPUGLIO_RAGNO_COLUMNS,
			CESPUGLIO_RAGNO_OFFSET_X, CESPUGLIO_RAGNO_OFFSET_Y,
			CESPUGLIO_RAGNO_WIDTH, CESPUGLIO_RAGNO_HEIGHT,
			CESPUGLIO_RAGNO_FRAME_MILLIS);
	
	// Porta Misteriosa
	public static final SpriteAnimation PORTA_MISTERIOSA_SPRITE_ANIMATION = new SpriteAnimation(
			PORTA_MISTERIOSA_IMAGE_VIEW,
			Duration.millis(PORTA_MISTERIOSA_FRAME_MILLIS * PORTA_MISTERIOSA_COUNT),
			PORTA_MISTERIOSA_COUNT, PORTA_MISTERIOSA_COLUMNS,
			PORTA_MISTERIOSA_OFFSET_X, PORTA_MISTERIOSA_OFFSET_Y,
			PORTA_MISTERIOSA_WIDTH, PORTA_MISTERIOSA_HEIGHT,
			PORTA_MISTERIOSA_FRAME_MILLIS, PORTA_MISTERIOSA_FRAME_TO_WAIT);
	
	// Gemma Argentea
	public static final SpriteAnimation GEMMA_ARGENTEA_SPRITE_ANIMATION = new SpriteAnimation(
			GEMMA_ARGENTEA_IMAGE_VIEW,
			Duration.millis(GEMMA_ARGENTEA_FRAME_MILLIS * GEMMA_ARGENTEA_COUNT),
			GEMMA_ARGENTEA_COUNT, GEMMA_ARGENTEA_COLUMNS,
			GEMMA_ARGENTEA_OFFSET_X, GEMMA_ARGENTEA_OFFSET_Y,
			GEMMA_ARGENTEA_WIDTH, GEMMA_ARGENTEA_WIDTH,
			GEMMA_ARGENTEA_FRAME_MILLIS);
	
	// Muro Segreto
	public static final SpriteAnimation MURO_SEGRETO_SPRITE_ANIMATION = new SpriteAnimation(
			ROCCIA_IMAGE_VIEW,
			Duration.millis(ROCCIA_FRAME_MILLIS * ROCCIA_COUNT),
			ROCCIA_COUNT, ROCCIA_COLUMNS,
			ROCCIA_OFFSET_X, ROCCIA_OFFSET_Y,
			ROCCIA_WIDTH, ROCCIA_HEIGHT,
			ROCCIA_FRAME_MILLIS);
	
	// Gemma Speciale
	public static final SpriteAnimation GEMMA_SPECIALE_SPRITE_ANIMATION = new SpriteAnimation(
			GEMMA_SPECIALE_IMAGE_VIEW,
			Duration.millis(GEMMA_SPECIALE_FRAME_MILLIS * GEMMA_SPECIALE_COUNT),
			GEMMA_SPECIALE_COUNT, GEMMA_SPECIALE_COLUMNS,
			GEMMA_SPECIALE_OFFSET_X, GEMMA_SPECIALE_OFFSET_Y,
			GEMMA_SPECIALE_WIDTH, GEMMA_SPECIALE_WIDTH,
			GEMMA_SPECIALE_FRAME_MILLIS);
	
	// Carne Rotten
	public static final SpriteAnimation CARNE_ROTTEN_SPRITE_ANIMATION = new SpriteAnimation(
			CARNE_ROTTEN_IMAGE_VIEW,
			Duration.millis(CARNE_FRAME_MILLIS * CARNE_COUNT),
			CARNE_COUNT, CARNE_COLUMNS,
			CARNE_OFFSET_X, CARNE_OFFSET_Y,
			CARNE_WIDTH, CARNE_WIDTH,
			CARNE_FRAME_MILLIS);
	
	// Carne Fresh
	public static final SpriteAnimation CARNE_FRESH_SPRITE_ANIMATION = new SpriteAnimation(
			CARNE_FRESH_IMAGE_VIEW,
			Duration.millis(CARNE_FRAME_MILLIS * CARNE_COUNT),
			CARNE_COUNT, CARNE_COLUMNS,
			CARNE_OFFSET_X, CARNE_OFFSET_Y,
			CARNE_WIDTH, CARNE_WIDTH,
			CARNE_FRAME_MILLIS);
	
	// Vaso Dungeon
	public static final SpriteAnimation VASO_DUNGEON_SPRITE_ANIMATION = new SpriteAnimation(
			VASO_DUNGEON_IMAGE_VIEW,
			Duration.millis(VASO_DUNGEON_FRAME_MILLIS * VASO_DUNGEON_COUNT),
			VASO_DUNGEON_COUNT, VASO_DUNGEON_COLUMNS,
			VASO_DUNGEON_OFFSET_X, VASO_DUNGEON_OFFSET_Y,
			VASO_DUNGEON_WIDTH, VASO_DUNGEON_HEIGHT,
			VASO_DUNGEON_FRAME_MILLIS, VASO_DUNGEON_FRAME_TO_WAIT);
	
	// Veleno Ragno
	public static final SpriteAnimation VELENO_RAGNO_SPRITE_ANIMATION = new SpriteAnimation(
			VELENO_RAGNO_IMAGE_VIEW,
			Duration.millis(VELENO_RAGNO_FRAME_MILLIS * VELENO_RAGNO_COUNT),
			VELENO_RAGNO_COUNT, VELENO_RAGNO_COLUMNS,
			VELENO_RAGNO_OFFSET_X, VELENO_RAGNO_OFFSET_Y,
			VELENO_RAGNO_WIDTH, VELENO_RAGNO_HEIGHT,
			VELENO_RAGNO_FRAME_MILLIS);
	
	// Tutorial
	// Vecchio Baule
	public static final SpriteAnimation VECCHIO_BAULE_SPRITE_ANIMATION = new SpriteAnimation(
			VECCHIO_BAULE_IMAGE_VIEW,
			Duration.millis(VECCHIO_BAULE_FRAME_MILLIS * VECCHIO_BAULE_COUNT),
			VECCHIO_BAULE_COUNT, VECCHIO_BAULE_COLUMNS,
			VECCHIO_BAULE_OFFSET_X, VECCHIO_BAULE_OFFSET_Y,
			VECCHIO_BAULE_WIDTH, VECCHIO_BAULE_HEIGHT,
			VECCHIO_BAULE_FRAME_MILLIS);
	/**
	 * La lista statica contenente tutti gli OggettiFx del gioco. Questa viene ridotta nel tempo di quegli oggetti che sono
	 * prendibili e non più utilizzabili per questioni di efficienza.
	 */
	private static List<OggettoFx> listaOggettiFx = new ArrayList<>();
	/**
	 * Metodo che preso un Mondo e i suoi Oggetti testuali (logici) per ognuno inizializza la controparte grafica con tanto
	 * di immagine animazione ecc...
	 * @param mondo
	 */
	public static void setupOggettiFx(Mondo mondo)
	{
		mondo.getOggetti().forEach(oggetto -> setSpriteForOggettiFx(oggetto));
	}
	/**
	 * Metodo che preso un oggetto grazie al nome crea (simple factory) un oggetto grafico che lo contiene assieme a tutte
	 * le altre componenti grafiche necessarie.
	 * @param oggetto
	 */
	private static void setSpriteForOggettiFx(Oggetto oggetto)
	{
		String nomeOggetto = oggetto.getNome().toLowerCase();
		
		switch(nomeOggetto)
		{
		case SCRIVANIA_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, SCRIVANIA_IMAGE_VIEW, SCRIVANIA_SPRITE_ANIMATION, false, true));
		case CHIAVE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, CHIAVE_IMAGE_VIEW, new ToggleButton()));
		case SALVADANAIO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, SALVADANAIO_IMAGE_VIEW, SALVADANAIO_SPRITE_ANIMATION, false, true));
		case SOLDI_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, CASH_IMAGE_VIEW, new ToggleButton()));
		case MARTELLO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, MARTELLO_IMAGE_VIEW, new ToggleButton()));
		case CACCIAVITE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, CACCIAVITE_IMAGE_VIEW, new ToggleButton()));
		case TRONCHESI_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, TRONCHESI_IMAGE_VIEW, new ToggleButton()));
		case ARMADIO_LUCCICANTE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, ARMADIO_LUCCICANTE_CHIUSO_IMAGE_VIEW, ARMADIO_LUCCICANTE_SPRITE_ANIMATION, true, true));
		case CHIAVE_TELETRASPORTO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, CHIAVE_TELETRASPORTO_IMAGE_VIEW, new ToggleButton(), false));
		case POZZO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, POZZO_IMAGE_VIEW, POZZO_SPRITE_ANIMATION, false, false));
		case SECCHIO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, SECCHIO_IMAGE_VIEW, new ToggleButton()));
		case GEMMA_BLU_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, GEMMA_BLU_IMAGE_VIEW, GEMMA_BLU_SPRITE_ANIMATION, true, new ToggleButton()));
		case GEMMA_ROSSA_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, GEMMA_ROSSA_EFFECT_IMAGE_VIEW, GEMMA_ROSSA_EFFECT_SPRITE_ANIMATION, true, new ToggleButton()));
		case GEMMA_VERDE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, GEMMA_VERDE_IMAGE_VIEW, GEMMA_VERDE_SPRITE_ANIMATION, true, new ToggleButton()));
		case GRANDE_CHIAVE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, GRANDE_CHIAVE_IMAGE_VIEW, new ToggleButton()));
		case CAMINO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, FUOCO_CAMINO_IMAGE_VIEW, FUOCO_CAMINO_SPRITE_ANIMATION, true, true));
		case CASSETTO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, CASSETTO_IMAGE_VIEW, CASSETTO_SPRITE_ANIMATION, false, true));
		case TESORO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, TESORO_IMAGE_VIEW, new ToggleButton()));
		case MINIGUN_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, MINIGUN_IMAGE_VIEW, new ToggleButton()));
		case ANELLO_RUBINO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, ANELLO_IMAGE_VIEW, new ToggleButton()));
		case CARNE_MARCIA_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, CARNE_ROTTEN_IMAGE_VIEW, CARNE_ROTTEN_SPRITE_ANIMATION, true, new ToggleButton()));
		case POZIONE_INGRANDIMENTO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, POZIONE_INGRANDIMENTO_IMAGE_VIEW, new ToggleButton()));
		case MONETE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, MONETE_IMAGE_VIEW, new ToggleButton()));
		case VASO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, VASO_DUNGEON_IMAGE_VIEW, VASO_DUNGEON_SPRITE_ANIMATION, false, true));
		case CIONDOLO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, PENDENTE_GOBLIN_IMAGE_VIEW, new ToggleButton()));
		case BLUEPRINT_ARCO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, BLUEPRINT_ARCO_IMAGE_VIEW, new ToggleButton()));
		case BLUEPRINT_FRECCE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, BLUEPRINT_FRECCE_IMAGE_VIEW, new ToggleButton()));
		case PARETE_MISTERIOSA_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, PORTA_MISTERIOSA_IMAGE_VIEW, PORTA_MISTERIOSA_SPRITE_ANIMATION, false, true));
		case GABBIA_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, NONNO_IDLE_CAGE_IMAGE_VIEW, NONNO_IDLE_CAGE_SPRITE_ANIMATION, true, false));
		case GEMMA_GIALLA_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, GEMMA_SPECIALE_IMAGE_VIEW, GEMMA_SPECIALE_SPRITE_ANIMATION, true, new ToggleButton()));
		case GEMMA_ARGENTEA_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, GEMMA_ARGENTEA_IMAGE_VIEW, GEMMA_ARGENTEA_SPRITE_ANIMATION, true, new ToggleButton()));
		case ROCCIA_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, ROCCIA_IMAGE_VIEW, MURO_SEGRETO_SPRITE_ANIMATION, false, true));
		case CESPUGLIO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, CESPUGLIO_RAGNO_IMAGE_VIEW, CESPUGLIO_RAGNO_SPRITE_ANIMATION, false, true));
		case CHIAVE_GABBIA_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, CHIAVE_GABBIA_IMAGE_VIEW, new ToggleButton()));
		case VELENO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, VELENO_RAGNO_IMAGE_VIEW, VELENO_RAGNO_SPRITE_ANIMATION, false, false));
		case ARCO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, false));
		case STRANO_ARTEFATTO_STRING -> listaOggettiFx.add(new OggettoFx(oggetto));
		case FRECCE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, false));
		case BLUEPRINT_GEMMA_MULTICOLORE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, false));
		case GEMMA_MULTICOLORE_STRING -> listaOggettiFx.add(new OggettoFx(oggetto, false));
//		default -> System.out.println("OggettoAncoraNonImplementato: -> " + nomeOggetto); // debug
		}
	}
	/**
	 * Metodo che aggiunge alle StanzeFx gli OggettiFx associati.
	 * @param listaStanzeFx
	 */
	public static void setupOggettiFxForStanzeFx(List<StanzaFx> listaStanzeFx)
	{
		for (StanzaFx stanzaFx : listaStanzeFx)
			for(Oggetto oggetto : stanzaFx.getStanza().getOggettiStanza())
				for(OggettoFx oggettoFx : listaOggettiFx)
				{
					if(oggettoFx.getNome().equals(oggetto.getNome()))
					{
						stanzaFx.getListaOggettiFx().add(oggettoFx);
						break;
					}
				}
	}
	/**
	 * Il nome del metodo potrebbe confondere, ma quel che fa il metodo è proprio eslusivamente gestire l'ordine con cui
	 * vengono aggiunte gli oggettiFx alla piccola stanza misteriosa.
	 * Questo metodo è necessario per avere alcune immagini sopra ad altre e altre sotto per una migliore interazione.
	 * Non sarebbe stato necessario se l'ordine non fosse stato importante, ma in questo esclusivo caso lo è.
	 * @param listaStanzeFx
	 */
	public static void setupPiccolaStanzaMisteriosaOrderOfDisplay(List<StanzaFx> listaStanzeFx)
	{
		List<OggettoFx> listaOggettiDaShiftare = new ArrayList<>();
		StanzaFx piccolaStanzaMisteriosaFx = null;
		
		for (StanzaFx stanzaFx : listaStanzeFx)   // Il camino deve sempre venire prima della gemma rossa!
			if(stanzaFx.getNome().equals(PICCOLA_STANZA_MISTERIOSA_STRING)) // deve caricarsi prima il muro segreto!
			{
				piccolaStanzaMisteriosaFx = stanzaFx;
				for(OggettoFx oggettoFx : stanzaFx.getListaOggettiFx())
				{
					if(oggettoFx.getNome().equals(CAMINO_STRING) || oggettoFx.getNome().equals(ROCCIA_STRING))
						listaOggettiDaShiftare.add(oggettoFx);
				}
			}
		
		if(piccolaStanzaMisteriosaFx != null)
		{
			piccolaStanzaMisteriosaFx.getListaOggettiFx().removeAll(listaOggettiDaShiftare);
			for(OggettoFx oggettoFx : listaOggettiDaShiftare)
					piccolaStanzaMisteriosaFx.getListaOggettiFx().add(0, oggettoFx);
		}
	}
	/**
	 * Metodo che aggiungea OggettiFx gli OggettFx che sono contenuti in altri basandosi sulle interazioni che hanno i
	 * loro Oggetto testuali (logici).
	 */
	public static void setupOggettiFxInterniAdOggettiFx()
	{
		for(OggettoFx oggettoFx : listaOggettiFx)
		{	
			if(!oggettoFx.getIsContenitore())   // Se non contiene nulla è inutile proseguire
				continue;
			
			for(OggettoFx possibileOggettoFxInterno : listaOggettiFx)
			{
				if(oggettoFx.equals(possibileOggettoFxInterno))  // Se è lo stesso oggetto non può contenere se stesso!
					continue;
				if(oggettoFx.getOggetto().getInterazioneOggetto() == null)
					continue;
				if(oggettoFx.getOggetto().getInterazioneOggetto().equals(possibileOggettoFxInterno.getOggetto()))
				{
					oggettoFx.setOggettoFxInterno(possibileOggettoFxInterno);
					break;
				}
			}
		}
	}
	/**
	 * Metodo che preso il SceneController tramite la lista degli OggettiFx se la gemma gialla esiste allora fa si che il
	 * sceneController imposti la StanzaFx legata ad essa.
	 * Tutto funziona perché a livello testuale questa stanza è già stata posta in precedenza nell'Oggetto = GemmaSpeciale.
	 * @param sceneController
	 */
	public static void setupStanzaFxGemmaGiallaForSceneController(SceneController sceneController)
	{
		for(OggettoFx oggettoFx : listaOggettiFx)
			if(oggettoFx.getNome().equals(GEMMA_GIALLA_STRING))
			{
				GemmaSpeciale gemmaSpeciale = (GemmaSpeciale)oggettoFx.getOggetto();
				for(StanzaFx stanzaFx : StanzaFxController.getListaStanzeFxGemmaGialla())
					if(stanzaFx.getNome().equals(gemmaSpeciale.getZonaMisteriosa().getNome()))
							sceneController.setStanzaFxGemmaGialla(stanzaFx);
			}
	}
	/**
	 * Getter per la lista contenente tutti gli OggettiFx.
	 * @return listaOggettiFx.
	 */
	public static List<OggettoFx> getListaOggettiFx()
	{
		return listaOggettiFx;
	}
}
