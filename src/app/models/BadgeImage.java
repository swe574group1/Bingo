package models;

import java.util.ArrayList;
import java.util.List;

public class BadgeImage {
	public String 	image1="/public/images/newbieg.jpg";
	public String image2="/public/images/Autobiographerg.jpg";
	public String image3="/public/images/Comentatorg.jpg";
	public String image4="/public/images/Servicemang.jpg";
	public String image5="/public/images/Raterg.jpg";
	public String image6="/public/images/Socialg.jpg";
	public String image7="/public/images/Populistg.jpg";
	public String image8="/public/images/Fivesterg.png";
	public String image9="/public/images/Gurug.png";

	

	public String getImage1() {
		return image1;
	}
	
	public void setNewbee() {
		image1="/public/images/newbie.jpg";

		}
	
	public void setAutobiographer() {
		
		image2="/public/images/Autobiographer.jpg";
	

		}
	
	public void setComentator() {

		image3="/public/images/Comentator.jpg";
			}
	public void setServiceman() {
	
		image4="/public/images/Serviceman.jpg";

		}
	public void setFivester() {

		image8="/public/images/Fivester.png";
	
		}
	public void setGuru() {

		image8="/public/images/Fivester.png";
	
		}
	public void setSocial() {

		image6="/public/images/Social.jpg";

		}
	public void setPopulist() {
	
		image7="/public/images/Populist.jpg";

		}
	
	public void setRater() {
	
		image5="/public/images/Rater.jpg";

		}
	
	
	
	
	


	public String getImage2() {
		return image2;
	}



	public void setImage2(String image2) {
		this.image2 = image2;
	}



	public String getImage3() {
		return image3;
	}



	public void setImage3(String image3) {
		this.image3 = image3;
	}



	public String getImage4() {
		return image4;
	}



	public void setImage4(String image4) {
		this.image4 = image4;
	}



	public String getImage5() {
		return image5;
	}



	public void setImage5(String image5) {
		this.image5 = image5;
	}



	public String getImage6() {
		return image6;
	}



	public void setImage6(String image6) {
		this.image6 = image6;
	}



	public String getImage7() {
		return image7;
	}



	public void setImage7(String image7) {
		this.image7 = image7;
	}



	public String getImage8() {
		return image8;
	}



	public void setImage8(String image8) {
		this.image8 = image8;
	}



	public String getImage9() {
		return image9;
	}



	public void setImage9(String image9) {
		this.image9 = image9;
	}
	
	


	public List<String> getAllImage(){
		List Imagelist = new ArrayList();
		Imagelist.add(getImage1());
		Imagelist.add(getImage2());
		Imagelist.add(getImage3());
		Imagelist.add(getImage4());
		Imagelist.add(getImage5());
		Imagelist.add(getImage6());
		Imagelist.add(getImage7());
		Imagelist.add(getImage8());
		Imagelist.add(getImage9());
		
		return Imagelist;
		
	}
	



}
