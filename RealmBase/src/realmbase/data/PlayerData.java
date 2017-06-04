package realmbase.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerData extends EntityData{

	private int maxHealth = 100;
	private int health = 100;
	private int maxMana = 100;
	private int mana = 100;
	private int xpGoal;
	private int xp;
	private int level = 1;
	private int[] slot = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private int[] backPack = { -1, -1, -1, -1, -1, -1, -1, -1 };
	private int attack;
	private int defense;
	private int speed = 5;
	private int accountId;
	private int vitality = 5;
	private int wisdom = 5;
	private int dexterity = 10;
	private int stars;
	private int realmGold;
	private int price;
	private boolean canEnterPortal;
	private int currentFame;
	private int healthBonus;
	private int manaBonus;
	private int attackBonus;
	private int defenseBonus;
	private int speedBonus;
	private int vitalityBonus;
	private int wisdomBonus;
	private int dexterityBonus;
	private int nameChangeRankRequired;
	private boolean nameRegistered;
	private int fame;
	private int fameGoal;
	private int glowingEffect;
	private String guild;
	private int guildRank;
	private int breath;
	private int healthpotCount = -1;
	private int manapotCount = -1;
	private int boolHasbackPack = -1;
	private int petSkinObjectType = -1;
	private String mapName;
	private boolean hasInc;
	
	public PlayerData(){}
	
	public PlayerData(short objectType, Status status){
		super(objectType, "", status);
		if(status!=null)loadStatData();
	}
	
	public void loadStatData(){
		for(StatData sd : this.status.getData())
			parseNewTICK(sd.id, sd.intValue, sd.stringValue);
	}
	
	public void parseNewTICK(int obf0, int obf1, String obf2) {
		if (obf0 == 0) {
			this.maxHealth = obf1;
		} else if (obf0 == 1) {
			this.health = obf1;
		} else if (obf0 == 3) {
			this.maxMana = obf1;
		} else if (obf0 == 4) {
			this.mana = obf1;
		} else if (obf0 == 5) {
			this.xpGoal = obf1;
		} else if (obf0 == 6) {
			this.xp = obf1;
		} else if (obf0 == 7) {
			this.level = obf1;
		} else if (obf0 == 8) { /* SLOTS */
			this.slot[0] = obf1;
		} else if (obf0 == 9) {
			this.slot[1] = obf1;
		} else if (obf0 == 10) {
			this.slot[2] = obf1;
		} else if (obf0 == 11) {
			this.slot[3] = obf1;
		} else if (obf0 == 12) {
			this.slot[4] = obf1;
		} else if (obf0 == 13) {
			this.slot[5] = obf1;
		} else if (obf0 == 14) {
			this.slot[6] = obf1;
		} else if (obf0 == 15) {
			this.slot[7] = obf1;
		} else if (obf0 == 16) {
			this.slot[8] = obf1;
		} else if (obf0 == 17) {
			this.slot[9] = obf1;
		} else if (obf0 == 18) {
			this.slot[11] = obf1;
		} else if (obf0 == 19) {
			this.slot[10] = obf1;
		} else if (obf0 == 20) {
			this.attack = obf1;
		} else if (obf0 == 21) {
			this.defense = obf1;
		} else if (obf0 == 22) {
			this.speed = obf1;
		} else if (obf0 == 26) {
			this.vitality = obf1;
		} else if (obf0 == 27) {
			this.wisdom = obf1;
		} else if (obf0 == 28) {
			this.dexterity = obf1;
		} else if (obf0 == 30) {
			this.stars = obf1;
		} else if (obf0 == 31) {
			this.name = obf2;
		} else if (obf0 == 35) {
			this.realmGold = obf1;
		} else if (obf0 == 36) {
			this.price = obf1;
		} else if (obf0 == 37) {
			this.canEnterPortal = Boolean.parseBoolean(obf2);
		} else if (obf0 == 38) {
			this.accountId = obf1;
		} else if (obf0 == 39) {
			this.currentFame = obf1; //fame you got when you died
		} else if (obf0 == 46) {
			this.healthBonus = obf1;
		} else if (obf0 == 47) {
			this.manaBonus = obf1;
		} else if (obf0 == 48) {
			this.attackBonus = obf1;
		} else if (obf0 == 49) {
			this.defenseBonus = obf1;
		} else if (obf0 == 50) {
			this.speedBonus = obf1;
		} else if (obf0 == 51) {
			this.vitalityBonus = obf1;
		} else if (obf0 == 52) {
			this.wisdomBonus = obf1;
		} else if (obf0 == 53) {
			this.dexterityBonus = obf1;
		} else if (obf0 == 55) {
			this.nameChangeRankRequired = obf1;
		} else if (obf0 == 56) {
			this.nameRegistered = Boolean.parseBoolean(obf2);
		} else if (obf0 == 57) {
			this.fame = obf1; //fame on this character
		} else if (obf0 == 58) {
			this.fameGoal = obf1;
		} else if (obf0 == 59) {
			this.glowingEffect = obf1;
		} else if (obf0 == 62) {
			this.guild = obf2;
		} else if (obf0 == 63) {
			this.guildRank = obf1;
		} else if (obf0 == 64) {
			this.breath = obf1;
		} else if (obf0 == 69) {
			this.healthpotCount = obf1;
		} else if (obf0 == 70) {
			this.manapotCount = obf1;
		} else if (obf0 == 79) { /* BACKPACK */
			this.backPack[0] = obf1;
		} else if (obf0 == 80) {
			this.backPack[1] = obf1;
		} else if (obf0 == 81) {
			this.backPack[2] = obf1;
		} else if (obf0 == 82) {
			this.backPack[3] = obf1;
		} else if (obf0 == 83) {
			this.backPack[4] = obf1;
		} else if (obf0 == 84) {
			this.backPack[5] = obf1;
		} else if (obf0 == 85) {
			this.backPack[6] = obf1;
		} else if (obf0 == 86) {
			this.backPack[7] = obf1;
		} else if (obf0 == 79) {
			this.boolHasbackPack = obf1;
		} else if (obf0 == 80) {
			this.petSkinObjectType = obf1;
		}
	}
}
