/*
 * 1. Tomt rom - EmptyRoom - e
 * 2. S�kerom - SearchRoom - s
 * 3. Farerom - DangerRoom - d
 * 4. ComputerRoom - Lar deg se x antall rom forran deg
 * 5. SleepingRoom - PowerNapRoom - p - �ker livet ditt med en 
 * 6. LockedDoor1 - l1 - l�st oppover
 * 7. LockedDoor2 - l2 - l�st til h�yre
 * 8. LockedDoor3 - l3 - l�st nedover
 * 9. LockedDoor4 - l4 - l�st til venstre
 */

package spillprosjekt;

import java.util.ArrayList;

public class RoomFactory {

	ArrayList<NameRoom> rom = new ArrayList<NameRoom>();
	public RoomFactory(int[] antall){
		
		for(int i = 0; i < antall[0]; i++){
			rom.add(new NameRoom("e")); // e for empty.
		}
		
		for(int j = 0; j < antall[1]; j++){
			rom.add(new NameRoom("s")); // s for search.
		}
		
		for(int k = 0; k < antall[2]; k++){
			rom.add(new NameRoom("r")); //d for danger.
		}
		
		for(int a = 0; a < antall[3]; a++){
			rom.add(new NameRoom("c")); // c for computer.
  		}
		
		for(int b = 0; b < antall[4]; b++){
			rom.add(new NameRoom("p")); // p for powerNap.
		}
		
		for(int c = 0; c < antall[5]; c++){
			rom.add(new NameRoom("l")); // l for locked.
		}
	}
	
	public ArrayList<NameRoom> getNameRoom(){
		return this.rom;
	}
	
	public ArrayList<AbstractTile> getAbstractTile(){
		ArrayList<AbstractTile> listen = new ArrayList<AbstractTile>();
		for (NameRoom r : rom){
			listen.add(r);
		}
		return listen;
	}
}
