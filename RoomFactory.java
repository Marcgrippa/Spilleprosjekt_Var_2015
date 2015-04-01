/*
 * 1. Tomt rom - EmptyRoom.
 * 2. Søkerom - SearchRoom.
 * 3. 
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
			rom.add(new NameRoom("d")); //d for danger.
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
