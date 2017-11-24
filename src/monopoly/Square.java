package monopoly;

public abstract class Square {
	// name of square and position
	private String name;
	private int position;
	// 20 pixel values
	// pixel 0 and 1 for first player image position
	// pixel 2 and 3 for second player image position
	// pixel 4 and 5 for third player image position
	// pixel 6 and 7 for fourth player image position
	// pixel 8 and 9 for owner image position
	// pixel 10 and 11 for hotel image position
	// pixel 12 and 13 for first house image position
	// pixel 14 and 15 for second house image position
	// pixel 16 and 17 for third house image position
	// pixel 18 and 19 for fourth house image position
	private int[] pixels = new int[20];
	
// constructor using name and position
	public Square(String name, int position) {
		this.name = name;
		this.position = position;
		createPixelsByPosition();
	}
// pixel calculation to set images on right position, please ignore this :)
	private void createPixelsByPosition() {
		// the go square
		if(position==39){
			pixels[0] = 720;
			pixels[1] = 605;
			
			pixels[2] = pixels[0]+40;
			pixels[3] = pixels[1];
			
			pixels[4] = pixels[0];
			pixels[5] = pixels[1]+30;
			
			pixels[6] = pixels[4]+40;
			pixels[7]= pixels[5];
		}
		// jail square
		else if(position==9){
			pixels[0] = 10;
			pixels[1] = 575;
			
			pixels[2] = pixels[0];
			pixels[3] = pixels[1]+25;
			
			pixels[4] = pixels[0];
			pixels[5] = pixels[3]+25;
			
			pixels[6] = pixels[0]+30;
			pixels[7]= pixels[5]+15;
			
			pixels[8] = 40;
			pixels[9]= 575;
			
			pixels[10]= pixels[8]+ 32;
			pixels[11]= pixels[9];
			
			pixels[12]= pixels[8];
			pixels[13]= pixels[9]+32;
			
			pixels[14]= pixels[8]+32;
			pixels[15]= pixels[13];
		}
		// first row
		else if(position<9){
			pixels[0] = 636-65*position;
			pixels[1]= 610;
			
			
			pixels[2] = pixels[0]+32;
			pixels[3] = pixels[1];
			
			pixels[4] = pixels[0];
			pixels[5] = pixels[1]+28;
			
			pixels[6] = pixels[4]+32;
			pixels[7]= pixels[5];
			
			pixels[8] = pixels[0]-2;
			pixels[9] = pixels[1]-53;
			
			pixels[18] = pixels[0]+17;
			pixels[19] = pixels[1]-34;
			
			pixels[10] = pixels[18]-20;
			pixels[11] = pixels[19];
			
			pixels[12] = pixels[18]-4;
			pixels[13] = pixels[19];
			
			pixels[14] = pixels[18]+10;
			pixels[15] = pixels[19];
			
			pixels[16] = pixels[18]+26;
			pixels[17] = pixels[19];
		}
		// left column
		else if(position<20){
			pixels[0] = 12;
			pixels[1]= 525-54*(position-10);
			
			
			pixels[2] = pixels[0]+32;
			pixels[3] = pixels[1];
			
			pixels[4] = pixels[0];
			pixels[5] = pixels[1]+28;
			
			pixels[6] = pixels[4]+32;
			pixels[7]= pixels[5];
			
			pixels[8] = pixels[0]+100;
			pixels[9] = pixels[1];
			
			pixels[18] = pixels[0]+73;
			pixels[19] = pixels[1]+19;
			
			
			pixels[10] = pixels[18]+6;
			pixels[11] = pixels[19]-22;
			
			pixels[12] = pixels[18]+6;
			pixels[13] = pixels[19]-10;
			
			pixels[14] = pixels[18]+6;
			pixels[15] = pixels[19]+2;
			
			pixels[16] = pixels[18]+6;
			pixels[17] = pixels[19]+14;
		}
		// top row
		else if(position<30){
			pixels[0] = 115+65*(position-20);
			pixels[1]= 10;
			
			
			pixels[2] = pixels[0]+32;
			pixels[3] = pixels[1];
			
			pixels[4] = pixels[0];
			pixels[5] = pixels[1]+28;
			
			pixels[6] = pixels[4]+32;
			pixels[7]= pixels[5];
			
			pixels[8] = pixels[0];
			pixels[9] = pixels[1]+83;
			
			pixels[18] = pixels[0]+17;
			pixels[19] = pixels[1]+65;
			
			pixels[10] = pixels[18]-20;
			pixels[11] = pixels[19];
			
			pixels[12] = pixels[18]-4;
			pixels[13] = pixels[19];
			
			pixels[14] = pixels[18]+10;
			pixels[15] = pixels[19];
			
			pixels[16] = pixels[18]+26;
			pixels[17] = pixels[19];
		}
		// right column
		else{
			pixels[0] = 740;
			pixels[1]= 96+54*(position-30);
			
			
			pixels[2] = pixels[0]+32;
			pixels[3] = pixels[1];
			
			pixels[4] = pixels[0];
			pixels[5] = pixels[1]+26;
			
			pixels[6] = pixels[4]+32;
			pixels[7]= pixels[5];
			
			pixels[8] = pixels[0]-70;
			pixels[9] = pixels[1];
			
			pixels[18] = pixels[0]-40;
			pixels[19] = pixels[1]+19;
			
			pixels[10] = pixels[18];
			pixels[11] = pixels[19]-22;
			
			pixels[12] = pixels[18];
			pixels[13] = pixels[19]-10;
			
			pixels[14] = pixels[18];
			pixels[15] = pixels[19]+2;
			
			pixels[16] = pixels[18];
			pixels[17] = pixels[19]+14;
		}
		
	}
// move function implemented by children
	public abstract void executeMove(Player player);
// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
}
