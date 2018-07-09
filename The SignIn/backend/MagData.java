package backend;

/*
 * @Author Kallan Dahn
 * 
 * Checks a magnetic stripe read, and sanitizes input to verify
 * proper expected string format.
 * protects against bad card read, and improperly formatted data.
 * 
 * Use getFName, getLName, getUIN, getCardDump to access values.
 * Accepts character array.
 * 
 */

public class MagData {

	private String fName = "";
	private String lName = "";
	private int UIN = 0;
	private boolean isValidCard = false;
	private boolean isMultiSwipe = false;
	private String cardDump = "";

	public MagData(char[] RawData) {

		cardDump = CreateString(RawData);
		isMultiSwipe = CheckSwipeCount();
		isValidCard = isCardValid();
		if (isValidCard == true) {

			fName = setFirstName();
			lName = setLastName();
			UIN = setUIN();
		}

		else {
			//Do nothing
		}
	}

	private String CreateString(char[] data) {
		String result = "";

		for (int i = 0; i < data.length; i++) {
			result = result + data[i];
		}

		return result;
	}

	
	//Checks to see if card swipe data is from a single card swipe.
	private boolean CheckSwipeCount(){
		int questionCount = 0;
		int semicolonCount = 0;
		int percentCount = 0;
		for(int i = 0; i < cardDump.length(); i++)
		{
			if(cardDump.charAt(i) == '?')
			{
				questionCount++;
			}
			if(cardDump.charAt(i) == ';')
			{
				semicolonCount++;
			}
			if(cardDump.charAt(i) == '%')
			{
				percentCount++;
			}
		}
		if(questionCount == 2 && semicolonCount == 1 && percentCount == 1)
		{
			return false;
		}
		
		return true;
	}
	
	
	
	
	private boolean isCardValid() {
		boolean result = false;

		if (cardDump.substring(0, 1).equals("%")
				&& cardDump.substring(cardDump.length() - 1).equals("?")
				&& cardDump.contains("?;") && cardDump.length() > 14 && isMultiSwipe == false) {
			result = true;
		} else {
			result = false;
			return result;
		}

		if (cardDump.substring(cardDump.indexOf('%'), cardDump.indexOf('?'))
				.contains(" ") == true
				&& cardDump.substring(cardDump.indexOf(';') + 1,
						cardDump.lastIndexOf('?') - 1).contains(" ") == false) {
			result = true;
		} else {
			result = false;
			return result;
		}

		if (numericsCheck()) {

		} else {
			result = false;
			return result;
		}
		return result;
	}

	private boolean numericsCheck() {
		boolean result = false;
		String digitCheckString = cardDump.substring(cardDump.indexOf(';') + 1,
				cardDump.lastIndexOf('?') - 1);
		for (int i = 0; i < digitCheckString.length(); i++) {
			if (digitCheckString.charAt(i) >= 0
					|| digitCheckString.charAt(i) <= 9) {
				result = true;
			} else {
				result = false;
				return result;
			}
		}
		return result;
	}

	private String setFirstName() {
		String result = "";

		result = cardDump.substring(1, cardDump.indexOf(' '));

		return result;
	}

	private String setLastName() {
		String result = "";

		result = cardDump.substring(cardDump.indexOf(' ') + 1,
				cardDump.indexOf('?'));

		return result;
	}

	private int setUIN() {
		String result = "";

		result = cardDump.substring(cardDump.indexOf("?;") + 8,
				cardDump.length());
		result = result.substring(0, result.length() - 3);

		return Integer.parseInt(result);
	}

	public String getFName() {
		return fName;
	}

	public String getLName() {
		return lName;
	}

	public int getUIN() {
		return UIN;
	}

	public boolean getIsValid() {
		return isValidCard;
	}

	public String getCardDump() {
		return cardDump;
	}
	public boolean getMultiSwipe()
	{
		return isMultiSwipe;
	}

}
