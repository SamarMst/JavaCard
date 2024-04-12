package monpackage;

import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.OwnerPIN;
import javacard.framework.Util;
import javacard.framework.ISO7816;


public class MonApplet extends Applet {
	/*Constants*/
	public static final byte CLA_MONAPPLET = (byte) 0xB0;
	public static final byte INS_INCREMENTER_COMPTEUR = 0x00;
	public static final byte INS_DECREMENTER_COMPTEUR = 0x01;
	public static final byte INS_INTERROGER_COMPTEUR = 0x02;
	public static final byte INS_INITIALISER_COMPTEUR = 0x03;
	public static final byte INS_PIN = 0x04;
	public final static short ROUGE =- 500;
	private byte[] PIN = { (byte) 2, (byte) 0,(byte) 0,(byte) 2 };
	public final static byte MAX_ERROR_PIN = (byte) 0x03;
	final static short SW_VERIFICATION_FAILED = 0x6300;
	final static short SW_MAXBALANCE = 0x6301;
	final static short SW_EXCEED_TRY_LIMIT = 0x6321;
	public final static byte MAX_PIN_LENGTH = (byte) 0x04;
	private static final short MAX_VALUE = 30000;


	 /* Attributs */
	 private short solde;
	 OwnerPIN pin;
	
	
	/* Constructeur */
	 
	private MonApplet() {
		solde=0;
		pin = new OwnerPIN(MAX_ERROR_PIN, MAX_PIN_LENGTH);

		// Initialization parametre pin
		pin.update(PIN,(short) 0, (byte) 0x04);

	}
	
	public static void install(byte bArray[], short bOffset, byte bLength) 
		throws ISOException {
		
		new MonApplet().register();
		
	
	}
	

	public void process(APDU apdu) throws ISOException {
		// TODO Auto-generated method stub
		byte[] buffer = apdu.getBuffer();
		if (this.selectingApplet()) return;
		if (buffer[ISO7816.OFFSET_CLA] != CLA_MONAPPLET) {
		ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
		}
		switch (buffer[ISO7816.OFFSET_INS]) {
		case INS_INCREMENTER_COMPTEUR:
			
			crediter(apdu);;
		break;
		case INS_DECREMENTER_COMPTEUR:
			         debiter(apdu);
		break;
		case INS_INTERROGER_COMPTEUR:
			short le = apdu.setOutgoing();
			apdu.setOutgoingLength((byte) 2);
			buffer[0] = (byte) (solde >> 8);
			buffer[1] = (byte) (solde & 0xFF);	
			Util.setShort(buffer, (short)0, solde);
			apdu.sendBytes((short) 0, (short) 2);
		break;
		case INS_INITIALISER_COMPTEUR:
			solde = (short)0;
			break;
		case INS_PIN:
			verify(apdu);
			break;
		default:
			ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);}}
	
		
	public void crediter(APDU apdu){
		byte[] buffer = apdu.getBuffer();
		apdu.setIncomingAndReceive();
		byte numBytes = buffer[ISO7816.OFFSET_LC];
	    short creditAmount1 = 0;

	    for (short i = 0; i < numBytes; i++) {
	        creditAmount1 += (short) (buffer[(short) (ISO7816.OFFSET_CDATA + i)] & 0xFF);
	    }

		if((short) (solde + creditAmount1)> MAX_VALUE ) {
			ISOException.throwIt( SW_MAXBALANCE );	
		}
		solde =(short) (solde + creditAmount1);
	}
	void debiter(APDU apdu) {
		byte[] buffer = apdu.getBuffer();
		apdu.setIncomingAndReceive();
		byte numBytes = buffer[ISO7816.OFFSET_LC];
	    short creditAmount1 = 0;

	    for (short i = 0; i < numBytes; i++) {
	        creditAmount1 += (short) (buffer[(short) (ISO7816.OFFSET_CDATA + i)] & 0xFF);
	    }
		if((short) (solde - creditAmount1)< ROUGE  ) {
			ISOException.throwIt(ISO7816.SW_WRONG_DATA  );	
		}
		else {
		solde =(short) (solde - creditAmount1);
	}}
	
	private void verify(APDU apdu) {
		byte[] buffer = apdu.getBuffer();
		// retrieve the PIN data for validation.
		byte byteRead = (byte) (apdu.setIncomingAndReceive());
		// check pin
		// the PIN data is read into the APDU buffer
		// at the offset ISO7816.OFFSET_CDATA
		// the PIN data length = byteRead
		if(pin.getTriesRemaining()==(byte) 1 && pin.check(buffer, ISO7816.OFFSET_CDATA, byteRead) == false )
			ISOException.throwIt(SW_EXCEED_TRY_LIMIT);
		if (pin.check(buffer, ISO7816.OFFSET_CDATA, byteRead) == false)
			ISOException.throwIt(SW_VERIFICATION_FAILED);}}
			
		

       