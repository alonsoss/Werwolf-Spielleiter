package de.werwolf_spielleiter.constants;

/**
 * Class of all private constants of
 * the game which can't be accessed
 * by the user
 *
 * @author Eric De Ron
 */
public class PrivateGameConstants {
    /* ConfigLoader values */
    // Path where to store the config file
    public static final String CONFIG_PATH = System.getProperty("user.dir");
    // Name of the config file
    public static final String CONFIG_NAME = "config.json";

    /* JSONConfigLoader values */
    // Name for root JSONObject
    public static final String CONFIG_JSON_OBJECT = "settings";

    /* Chars */
    // Names for chars
    public static final String CHAR_VILLAGER_NAME = "Dorfbewohner";
    public static final String CHAR_HUNTER_NAME = "Jäger";
    public static final String CHAR_CUPID_NAME = "Amor";
    public static final String CHAR_SEER_NAME = "Seherin";
    public static final String CHAR_WITCH_NAME = "Hexe";
    public static final String CHAR_THIEF_NAME = "Dieb";
    public static final String CHAR_LITTLE_GIRL_NAME = "Kleines Mädchen";
    public static final String CHAR_WEREWOLF_NAME = "Werwolf";
    public static final String CHAR_WHITE_WEREWOLF_NAME = "Weißer Werwolf";
    public static final String CHAR_GREAT_WOLF_NAME = "Urwolf";
    public static final String CHAR_BIG_BAD_WOLF_NAME = "Großer, böser Wolf";
    public static final String CHAR_WILD_CHILD_NAME = "Wildes Kind";
    public static final String CHAR_FOX_NAME = "Fuchs";
    public static final String CHAR_DOG_WOLF_NAME = "Wolfshund";

    // Paths for char card images
    public static final String CHAR_CARD_BACK = "/images/backside.jpg";
    public static final String CHAR_CARD_FRONT_VILLAGER = "/images/villager_front.jpg";
    public static final String CHAR_CARD_FRONT_HUNTER = "/images/hunter_front.jpg";
    public static final String CHAR_CARD_FRONT_CUPID = "/images/cupid_front.jpg";
    public static final String CHAR_CARD_FRONT_SEER = "/images/seer_front.jpg";
    public static final String CHAR_CARD_FRONT_WITCH = "/images/witch_front.jpg";
    public static final String CHAR_CARD_FRONT_LITTLE_GIRL = "/images/littleGirl_front.jpg";
    public static final String CHAR_CARD_FRONT_THIEF = "/images/thief_front.jpg";
    public static final String CHAR_CARD_FRONT_WEREWOLF = "/images/werewolf_front.jpg";
    public static final String CHAR_CARD_FRONT_WHITE_WEREWOLF = "/images/white_werewolf_front.jpg";
    public static final String CHAR_CARD_FRONT_GREAT_WOLF = "/images/great_wolf_front.jpg";
    public static final String CHAR_CARD_FRONT_BIG_BAD_WOLF = "/images/bigBadWolf_front.jpg";
    public static final String CHAR_CARD_FRONT_WILD_CHILD = "/images/wild_child_front.jpg";
    public static final String CHAR_CARD_FRONT_FOX = "/images/fox_front.jpg";
    public static final String CHAR_CARD_FRONT_DOG_WOLF = "/images/dog_wolf_front.jpg";

    // Paths for trade sign images
    public static final String TRADE_SIGN_VAGABOND = "/images/sign_vagabond.jpg";
    public static final String TRADE_SIGN_FARMER = "/images/sign_farmer.jpg";
    public static final String TRADE_SIGN_CONFESSOR = "/images/sign_confessor.jpg";

    // Win text for fractions
    public static final String CHAR_VILLAGER_WIN_TEXT = "Die Dorfbewohner haben gewonnen!";
    public static final String CHAR_HUNTER_WIN_TEXT = "Es hat keine Fraktion gewonnen!";
    public static final String CHAR_CUPID_WIN_TEXT = "Das Liebespaar hat gewonnen!";
    public static final String CHAR_WEREWOLF_WIN_TEXT = "Die Werwölfe haben gewonnen!";
    public static final String CHAR_WHITE_WEREWOLF_WIN_TEXT = "Der Weiße Werwolf hat gewonnen!";

    /* Player */
    public static final String PLAYER_DEFAULT_NAME = "Spieler";
    public static final String PLAYER_ALIVE = "alive";
    public static final String PLAYER_DEAD = "dead";

    /* Role */
    public static final String ROLE_WEREWOLF_NAME = "Werwolf";
    public static final String ROLE_WHITE_WEREWOLF_NAME = "Weißer Werwolf";
    public static final String ROLE_GREAT_WOLF_NAME = "Urwolf";
    public static final String ROLE_BIG_BAD_WOLF_NAME = "Großer, böser Wolf";
    public static final String ROLE_HUNTER_NAME = "Jäger";
    public static final String ROLE_CUPID_NAME = "Amor";
    public static final String ROLE_SEER_NAME = "Seherin";
    public static final String ROLE_WITCH_NAME = "Hexe";
    public static final String ROLE_LOVERS_NAME = "Liebespaar";
    public static final String ROLE_SHERIFF_NAME = "Hauptmann";
    public static final String ROLE_LITTLE_GIRL_NAME = "Kleines Mädchen";
    public static final String ROLE_THIEF_NAME = "Dieb";
    public static final String ROLE_WILD_CHILD_NAME = "Wildes Kind";
    public static final String ROLE_FOX_NAME = "Fuchs";
    public static final String ROLE_DOG_WOLF_NAME = "Wolfshund";

    /* Victim */

    public static final String VICTIM_LOVER_REASON = "Liebeskummer";
    public static final String VICTIM_WITCH_REASON = "Hexe Opfer";
    public static final String VICTIM_WEREWOLF_REASON = "Werwolf Opfer";
    public static final String VICTIM_BIG_BAD_WOLF_REASON = "Großer, böser Wolf Opfer";
    public static final String VICTIM_WHITE_WEREWOLF_REASON = "Weißer Werwolf Opfer";
    public static final String VICTIM_HUNTER_REASON = "Jäger Opfer";
    public static final String VICTIM_LYNCH_REASON = "Lynch Opfer";

    /* Model */
    public static final String MODEL_NOT_ENOUGH_CARDS_THIEF = "Zu wenige Karten für den Dieb ausgewählt!";

    /* Fraction */
    public static final String FRACTION_ERROR_FALSE_WEREWOLF_VICTIM = "Das Werwolf Opfer wurde falsch gewählt, es darf nicht in einen Werwolf verliebt sein. ";

    /* WitchViewModel */
    public static final String WITCH_NO_VICTIM_SELECTED = "Du hast kein Opfer ausgewählt. ";
    public static final String WITCH_CANT_HEAL = "Du kannst nicht mehr heilen. ";
    public static final String WITCH_CANT_KILL = "Du kannst nicht mehr töten. ";
    public static final String WITCH_IS_WEREWOLF_VICTIM = "Du bist Werwolf Opfer!";
    public static final String WITCH_LOVER_IS_VICTIM = "Dein Liebespartner ist Werwolf Opfer";
    public static final String WITCH_NO_WEREWOLF_VICTIM = "Es gibt kein Werwolf Opfer. ";
    public static final String WITCH_INFO_STYLE_LBL = "label-info";

    /* GreatWolfViewModel */
    public static final String GREAT_WOLF_NO_WEREWOLF_VICTIM = "Es gibt kein Werwolf Opfer, das du infizieren könntest. ";
    public static final String GREAT_WOLF_HAS_INFECTED = "Du hast schon infiziert";

    /* VotingViewModel */
    public static final String VOTING_ALL_VOTED = "Es sind alle Stimmen abgegeben.";
    public static final String VOTING_SHERIFF_CHOOSES = "Der Hauptmann entscheidet wer stirbt.";

    /* SheriffViewModel */
    // Style
    public static final String SHERIFF_STYLE_LBL = "label-info";

    public static final int SHERIFF_SHERIFF_VOTES = 4;
    public static final String SHERIFF_SELECT_NEW_SHERIFF = "Auswahl\nNachfolger Hauptmann";
    public static final String SHERIFF_SELECT_1_PLAYER = "Noch 1 Spieler\nauswählen";
    public static final String SHERIFF_SELECT_X_PLAYERS = "Noch x Spieler auswählen";
    public static final String SHERIFF_X_VOTES = "noch X stimmen abgeben";
    public static final String SHERIFF_MAX_SELECTED = "Es dürfen keine weiteren Spieler ausgewählt werden.";
    public static final String SHERIFF_MAX_VOTED = "Es sind alle Stimmen abgegeben.";

    /* PlayerVictimViewModel */
    // Style
    public static final String PLAYER_VICTIM_STYLE_LBL = "label-text";

    public static final String PLAYER_VICTIM_NIGHT = "Opfer der Nacht:";
    public static final String PLAYER_VICTIM_DAY = "Opfer des Lynches:";
    public static final String PLAYER_VICTIM_NO_ROLE = "Keine";
    public static final String PLAYER_VICTIM_NEXT_BUTTON = "Weiter";

    /* HunterViewModel */
    // Style
    public static final String HUNTER_STYLE_LBL = "label-info";

    public static final String HUNTER_SELECT_1_PLAYER = "Noch 1 Spieler\nauswählen";
    public static final String HUNTER_CONFIRM_BUTTON = "Bestätigen";

    /* CupidViewModel */
    public static final String CUPID_MAX_SELECTED = "Genug Spieler ausgewählt. ";
    public static final String CUPID_SELECT_1_PLAYER = "Noch 1 Spieler auswählen! ";
    public static final String CUPID_SELECT_2_PLAYERS = "Noch 2 Spieler auswählen! ";

    /* ThiefViewModel */
    public static final String THIEF_CAN_SELECT = "Der Dieb kann eine Karte auswählen. ";
    public static final String THIEF_MUST_SELECT = "Der Dieb muss eine Karte auswählen. ";

    /* WildChildViewModel */
    // Style
    public static final String WILD_CHILD_STYLE_LBL_TITLE = "label-header";
    public static final String WILD_CHILD_STYLE_LBL_TEXT = "label-text";
    public static final String WILD_CHILD_STYLE_LBL_INFO = "label-info";

    public static final String WILD_CHILD_SELECT_1_PLAYER = "Noch 1 Spieler auswählen";
    public static final String WILD_CHILD_SELECT = "Ausgewählt: ";

    /* FoxViewModel */
    public static final String FOX_CHECK_AVAILABLE = "Einen Spieler auswählen um ihn und seine Nachbarn zu prüfen.";
    public static final String FOX_CHECK_NOT_AVAILABLE = "Es können keine Spieler mehr überprüft werden!";
    public static final String FOX_CHECK_WEREWOLF_FOUND = "Einer der Spieler ist ein Werwolf!";
    public static final String FOX_CHECK_NO_WEREWOLF_FOUND = "Keiner der Spieler ist ein Werwolf!";

    /* CardSelectViewModel */
    public static final String CARD_SELECT_CORRECT_AMOUNT = "Du hast die richtige Anzahl Karten ausgewählt.";

    /* TradeSelectViewModel */
    public static final String TRADE_SELECT_CORRECT_AMOUNT = "Du hast die richtige Anzahl Berufe ausgewählt.";

    /* PlayerNameViewModel */
    public static final String PLAYER_NAME_RESET_BUTTON = "Reset";
    public static final String PLAYER_NAME_LAST_BUTTON = "Zurück";
    public static final String PLAYER_NAME_NEXT_BUTTON = "Weiter";
    public static final String PLAYER_NAME_DEFAULT_NAME = "Spieler ";

    /* SceneManager */
    // FXML
    public static final String SCENE_MANAGER_WEREWOLF_IMAGE = "/images/werewolf_front.jpg";
    public static final String SCENE_MANAGER_FXML_PLAYER_COUNT = "/view/player_amount_selector.fxml";
    public static final String SCENE_MANAGER_FXML_PLAYER_NAME = "/view/player_name_selector.fxml";
    public static final String SCENE_MANAGER_FXML_CARD_SELECT = "/view/card_selector.fxml";
    public static final String SCENE_MANAGER_FXML_TRADE_SELECT = "/view/trade_selector.fxml";
    public static final String SCENE_MANAGER_FXML_BOARD_LAYOUT = "/view/BoardLayout.fxml";
    public static final String SCENE_MANAGER_FXML_START_NIGHT = "/view/startNight.fxml";
    public static final String SCENE_MANAGER_FXML_VOTING = "/view/votingCenter.fxml";
    public static final String SCENE_MANAGER_FXML_PLAYER_VICTIM = "/view/player_victims.fxml";
    public static final String SCENE_MANAGER_FXML_SEER = "/view/seerScene.fxml";
    public static final String SCENE_MANAGER_FXML_LOVERS = "/view/loversScene.fxml";
    public static final String SCENE_MANAGER_FXML_CHOOSING_WEREWOLF = "/view/choosingWerwolf.fxml";
    public static final String SCENE_MANAGER_FXML_CHOOSING_LYNCH = "/view/choosingLynch.fxml";
    public static final String SCENE_MANAGER_FXML_CHOOSING_SHERIFF = "/view/choosingSheriff.fxml";
    public static final String SCENE_MANAGER_FXML_VOTING_SHERIFF = "/view/votingSheriff.fxml";
    public static final String SCENE_MANAGER_FXML_WITCH = "/view/witchScene.fxml";
    public static final String SCENE_MANAGER_FXML_CUPID = "/view/cupidScene.fxml";
    public static final String SCENE_MANAGER_FXML_THIEF = "/view/thiefScene.fxml";
    public static final String SCENE_MANAGER_FXML_WHITE_WEREWOLF = "/view/whiteWerewolfScene.fxml";
    public static final String SCENE_MANAGER_FXML_GREAT_WOLF = "/view/greatWolf_scene.fxml";
    public static final String SCENE_MANAGER_FXML_BIG_BAD_WOLF = "/view/bigBadWolfScene.fxml";
    public static final String SCENE_MANAGER_FXML_WILD_CHILD = "/view/wildChildScene.fxml";
    public static final String SCENE_MANAGER_FXML_FOX = "/view/foxScene.fxml";
    public static final String SCENE_MANAGER_FXML_DOG_WOLF = "/view/dog_wolf_scene.fxml";
    public static final String SCENE_MANAGER_FXML_CONFESSOR = "/view/confessorScene.fxml";
    public static final String SCENE_MANAGER_FXML_NO_NIGHT_VICTIMS = "/view/noNightVictims.fxml";

    /* BoardLayoutViewModel */
    // fxml
    public final static String BOARD_LAYOUT_FXML_PLAYER_LAYOUT = "/view/PlayerLayout.fxml";
    public final static String BOARD_LAYOUT_FXML_WINNER_LAYOUT = "/view/WinnerLayout.fxml";

    // html
    public final static String BOARD_LAYOUT_HTML_INTRODUCTION = "html/anleitung.html";
    public final static String BOARD_LAYOUT_HTML_INFO = "html/info.html";

    // Variables
    public final static double BOARD_LAYOUT_SPLIT_DIVIDER = 8.0;
    public final static int BOARD_LAYOUT_TOP_END_FACTOR = 4;
    public final static int BOARD_LAYOUT_RIGHT_START_FACTOR = 4;
    public final static int BOARD_LAYOUT_RIGHT_END_FACTOR = 5;
    public final static int BOARD_LAYOUT_BOTTOM_START_FACTOR = 5;
    public final static int BOARD_LAYOUT_BOTTOM_END_FACTOR = 8;
    public final static double BOARD_LAYOUT_OFFSET_WIDTH = 5.0;
    public final static double BOARD_LAYOUT_OFFSET_HEIGHT = 5.0;

    /* PlayerLayoutViewModel */
    // style
    public final static String PLAYER_LAYOUT_PANE_STYLE = "background";
    public final static String PLAYER_LAYOUT_PANE_HIGHLIGHT = "highlight";
    public final static String PLAYER_LAYOUT_PANE_AWAKE = "awake";
    public final static String PLAYER_LAYOUT_PANE_AWAKE_PASSIVE = "awake-passive";
    public final static String PLAYER_LAYOUT_PANE_WEREWOLF_VICTIM = "werewolf-victim";
    public final static String PLAYER_LAYOUT_PANE_SHERIFF = "sheriff";
    public final static String PLAYER_LAYOUT_PANE_LABEL_LOVER = "label-lover";
    public final static String PLAYER_LAYOUT_PANE_DEAD = "dead";
    /* CardSelectViewModel */
    // RadioButtons
    public final static String SHERIFF_RADIO = "Sheriff";
    public final static String TRADE_RADIO = "Trade";
    /* TradeSelectViewModel*/
    // trades
    public final static String TRADE_VAGABOND_NAME = "Vagabund";
    public final static String TRADE_FARMER_NAME = "Bauer";
    public final static String TRADE_CONFESSOR_NAME = "Beichtvater";
}
