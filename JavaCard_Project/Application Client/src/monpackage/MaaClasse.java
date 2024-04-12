package monpackage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadT1Client;
import com.sun.javacard.apduio.CadTransportException;
import java.util.Date;








public class MaaClasse implements ActionListener {


	public static final byte CLA_MONAPPLET = (byte) 0xB0;
	public static final byte INS_INCREMENTER_COMPTEUR = 0x00;
	public static final byte INS_DECREMENTER_COMPTEUR = 0x01;
	public static final byte INS_INTERROGER_COMPTEUR = 0x02;
	public static final byte INS_INITIALISER_COMPTEUR = 0x03;
	public static final byte INS_PIN = 0x04;
	final static short SW_VERIFICATION_FAILED = 0x6300;
	final static short SW_MAXBALANCE = 0x6301;
	final static short SW_EXCEED_TRY_LIMIT = 0x6321;
	static CadT1Client cad;
    static Socket sckCarte;
    static Apdu apdu = new Apdu();
    //File
    String fichierTransactions = "file.txt";

	
    int i;
    int tentatives = 3;
    
    //Jframe 
    JFrame Bank;
    JFrame Choice;
    JFrame Check;
    JFrame Crediter;
    JFrame Debiter;
    JFrame ErrorCode;
    JFrame ErrorTentatives;
    JFrame Fail;
    JFrame Success;
    JFrame PerCrediter;
    JFrame FailTransaction;
    JFrame PerDebiter;
    JFrame Recu;
    //Bank 
    JButton btnValider;
	JPasswordField passwordField;
	JLabel lblPIN;
    //Check 
    JButton btnRetourner; 
    JButton btnQuit;
    JLabel lblSoldeActuel;
    JPanel contentPaneCheck;
    
    //Choice 
    JButton btnCrediter;
	JButton btnDebiter;
	JButton btnSolde;
	JButton btnQuitter;
	JPanel contentPaneChoice;
	JLabel lblTitleChoice;
    JLabel lblNewLabelChoice;
    JButton btnNewButton_1Recu;
	//Crediter 
	JButton btn10DT;
	JButton btn50DT;
	JButton btn20DT;
	JButton btn70DT;
	JButton btn40DT;
	JButton btn100DT;
	JPanel contentPaneCrediter;
	JButton btnNewButtonCrediter;
	JButton btnNewButtonPCrediter;
	//Debiter 
	JButton btn10DT_;
	JButton btn50DT_;
	JButton btn20DT_;
	JButton btn70DT_;
	JButton btn40DT_;
	JButton btn100DT_;
	JPanel contentPaneDebiter;
	JButton btnNewButtonPDebiter;
	//ErrorCode 
	JButton btnReessayer;
	JButton btnFin;
	JPanel contentPaneErrorCode;
	//ErrorTentatives
	JPanel contentPaneErrorTentatives;
	JButton btnQuitterTentatives;
	//Fail
	JButton btnQuitFail;
	JButton btnRetournerFail;
	JPanel contentPaneFail;
	//Success
	JPanel contentPaneSuccess;
	JButton btnRetourSucces;
	JButton btnQuitSucces;
	//PerCrediter
	JTextField textFieldPerCrediter;
	JButton btnQuitterPerCrediter;
	JButton btnNewButtonPerCrediter;
	//PerDebiter
	JTextField textFieldPerDebiter;
	JButton btnQuitterPerDebiter;
	JButton btnNewButtonPerDebiter;
	//FailTransaction
	JPanel contentPaneFailTransaction;
	JButton btnQuitterFailTransaction;
	JButton btnNewButtonRetournerTransaction;
	//Recu
	JPanel contentPaneRecu;
	JTextArea transactionsTextArea;
	JButton btnButtonRecuRetour;
	
    MaaClasse() {

        Bank = new JFrame("Central Bank Of Tunisia");
        Check = new JFrame("Consulter Solde");
        Choice = new JFrame("Choice");
        Crediter = new JFrame("Crediter");
        Debiter = new JFrame("Debiter");
        ErrorTentatives = new JFrame("Carte Bloquée");
        ErrorCode = new JFrame("Error Code");
        Fail = new JFrame("Fail");
        Success = new JFrame("Success");
        PerCrediter = new JFrame("Crediter");
        PerDebiter = new JFrame("Debiter");
        FailTransaction = new JFrame("Fail Transaction");
        Recu = new JFrame("Recu");
       
        i=0;
        
        
    		/*** Bank JFrame ***/
    		
        	Bank = new JFrame();
        	Bank.setBounds(100,100,650, 400);
    		Bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		Bank.getContentPane().setLayout(null);
    		Bank.getContentPane().setBackground(new Color(245, 238, 200));
    		
    		JLabel lblNewLabel = new JLabel("");
    		lblNewLabel.setBounds(70, 114, 161, 176);
    		Bank.getContentPane().add(lblNewLabel);
    		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\bank (3).png"));
    		
    		btnValider = new JButton("Valider");
    		btnValider.setBounds(400, 239, 98, 31);
    		btnValider.setBackground(new Color (135, 196, 255));
    		Bank.getContentPane().add(btnValider);
    		
    		lblPIN = new JLabel("Votre Code PIN");
    		lblPIN.setFont(new Font("Tahoma", Font.BOLD, 15));
    		lblPIN.setBounds(349, 135, 228, 37);
    		Bank.getContentPane().add(lblPIN);
    		
    		passwordField = new JPasswordField();
    		passwordField.setBounds(349, 183, 203, 37);
    		Bank.getContentPane().add(passwordField);
    		
    		JLabel lblNewLabel_2 = new JLabel("Bank of Tunisia");
    		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabel_2.setBounds(111, 22, 470, 42);
    		Bank.getContentPane().add(lblNewLabel_2);
    		Bank.setResizable(false);

    		
    		/*** Choice JFrame ***/
    		
    		Choice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		Choice.setBounds(100, 100, 650, 400);
    		contentPaneChoice = new JPanel();
    		contentPaneChoice.setLayout(null);
    		contentPaneChoice.setBackground(new Color(245, 238, 200));
    		
    		lblTitleChoice = new JLabel("Bank of Tunisia");
    		lblTitleChoice.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblTitleChoice.setBounds(111, 22, 470, 42);
    		contentPaneChoice.add(lblTitleChoice);
    		btnCrediter = new JButton("Crediter");
    		btnCrediter.setBounds(41, 148, 138, 53);
    		btnCrediter.setBackground(new Color(135, 196, 255));
    		contentPaneChoice.add(btnCrediter);
    		
    		
    		btnDebiter = new JButton("Debiter");
    		btnDebiter.setBackground(new Color(135, 196, 255));
    		btnDebiter.setBounds(443, 148, 138, 53);
    		contentPaneChoice.add(btnDebiter);
    		
    		btnSolde = new JButton("Solde");
    		btnSolde.setBackground(new Color(135, 196, 255));
    		btnSolde.setBounds(41, 243, 138, 53);
    		contentPaneChoice.add(btnSolde);
    		
    		btnQuitter = new JButton("Quitter");
    		btnQuitter.setBackground(new Color(135, 196, 255));
    		btnQuitter.setBounds(443, 243, 132, 53);
    		contentPaneChoice.add(btnQuitter);
    		
    		lblNewLabelChoice = new JLabel("");
    		lblNewLabelChoice.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\passbook.png"));
    		lblNewLabelChoice.setBounds(246, 148, 164, 145);
    		contentPaneChoice.add(lblNewLabelChoice);
    		Choice.add(contentPaneChoice);
    		Choice.setResizable(false);
    		
    		btnNewButton_1Recu = new JButton("Recu");
    		btnNewButton_1Recu.setBackground(Color.RED);
    		btnNewButton_1Recu.setBounds(485, 327, 89, 23);
    		contentPaneChoice.add(btnNewButton_1Recu);
    		
    		
    		/*** Check JFrame ***/
    		Check.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		Check.setBounds(100, 100, 600, 399);
    		contentPaneCheck = new JPanel();
    		contentPaneCheck.setBorder(new EmptyBorder(5, 5, 5, 5));
    		contentPaneCheck.setBackground(new Color(245, 238, 200));
    		contentPaneCheck.setLayout(null);
    		
    		JLabel lblNewLabelCheck = new JLabel("");
    		lblNewLabelCheck.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\money (1).png"));
    		lblNewLabelCheck.setBounds(37, 173, 128, 128);
    		contentPaneCheck.add(lblNewLabelCheck);
    		
    		
    		JLabel lblNewLabel_2Check = new JLabel("Bank of Tunisia");
    		lblNewLabel_2Check.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabel_2Check.setBounds(111, 22, 470, 42);
    		contentPaneCheck.add(lblNewLabel_2Check);

    		lblSoldeActuel = new JLabel("");
    		lblSoldeActuel.setFont(new Font("Lucida Bright", Font.BOLD, 20));
    		lblSoldeActuel.setBounds(239, 130, 335, 42);
    		contentPaneCheck.add(lblSoldeActuel);
    		
    		JLabel lblNewLabel_3Check = new JLabel("");
    		lblNewLabel_3Check.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
    		lblNewLabel_3Check.setBounds(243, 196, 219, 36);
    		contentPaneCheck.add(lblNewLabel_3Check);
    		
    		btnRetourner = new JButton("Retourner");
    		btnRetourner.setBounds(239, 251, 98, 31);
    		btnRetourner.setBackground(new Color (162, 197, 121));
    		contentPaneCheck.add(btnRetourner);
    		
    		btnQuit = new JButton("Quitter");
    		btnQuit.setBackground(new Color (162, 197, 121));
    		btnQuit.setBounds(376, 251, 98, 31);
    		contentPaneCheck.add(btnQuit);
    		
    		Check.add(contentPaneCheck);
    		Check.setResizable(false);
    		
    		/*** Crediter JFrame ***/
    		
    		Crediter.setBounds(100, 100, 650, 400);
    		contentPaneCrediter = new JPanel();
    		contentPaneCrediter.setLayout(null);
    		contentPaneCrediter.setBackground(new Color(245, 238, 200));
    		
    		
    		JLabel lblNewLabel_2Crediter = new JLabel("Bank of Tunisia");
    		lblNewLabel_2Crediter.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabel_2Crediter.setBounds(111, 22, 470, 42);
    		contentPaneCrediter.add(lblNewLabel_2Crediter);
    		btn10DT = new JButton("10");
    		btn10DT.setBounds(41, 148, 138, 36);
    		btn10DT.setBackground(new Color(162, 197, 121));
    		contentPaneCrediter.add(btn10DT);
 
    		
    	    btn50DT = new JButton("50");
    		btn50DT.setBackground(new Color(162, 197, 121));
    		btn50DT.setBounds(443, 148, 138, 36);
    		contentPaneCrediter.add(btn50DT);
    		
    		btn20DT = new JButton("20");
    		btn20DT.setBackground(new Color(162, 197, 121));
    		btn20DT.setBounds(41, 222, 138, 36);
    		contentPaneCrediter.add(btn20DT);
    		
    		btn70DT = new JButton("70");
    		btn70DT.setBackground(new Color(162, 197, 121));
    		btn70DT.setBounds(443, 222, 132, 36);
    		contentPaneCrediter.add(btn70DT);
    		
    		JLabel lblNewLabelCrediter = new JLabel("");
    		lblNewLabelCrediter.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\wallet.png"));
    		lblNewLabelCrediter.setBounds(256, 182, 132, 145);
    		
    		contentPaneCrediter.add(lblNewLabelCrediter);
    		
    		btn40DT = new JButton("40");
    		btn40DT.setBackground(new Color(162, 197, 121));
    		btn40DT.setBounds(41, 291, 138, 36);
    		contentPaneCrediter.add(btn40DT);
    		
    		btn100DT = new JButton("100");
    		btn100DT.setBackground(new Color(162, 197, 121));
    		btn100DT.setBounds(443, 291, 138, 36);
    		contentPaneCrediter.add(btn100DT);
    		
    		JLabel lblNewLabel_1Crediter = new JLabel("Combien voulez vous crediter ?");
    		lblNewLabel_1Crediter.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
    		lblNewLabel_1Crediter.setBounds(169, 75, 304, 45);
    		contentPaneCrediter.add(lblNewLabel_1Crediter);
    		
    		btnNewButtonPCrediter = new JButton("Autre");
    		btnNewButtonPCrediter.setBounds(271, 155, 89, 23);
    		btnNewButtonPCrediter.setBackground(new Color(162, 197, 121));
    		contentPaneCrediter.add(btnNewButtonPCrediter);
    		
    		Crediter.add(contentPaneCrediter);
    		Crediter.setResizable(false);
    		
    		/*** Debiter JFrame ***/
    		
    		Debiter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		Debiter.setBounds(100, 100, 650, 400);
    		contentPaneDebiter = new JPanel();
    		contentPaneDebiter.setLayout(null);
    		contentPaneDebiter.setBackground(new Color(245, 238, 200));
    		
    		
    		JLabel lblNewLabel_2Debiter = new JLabel("Central Bank of Tunisia");
    		lblNewLabel_2Debiter.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabel_2Debiter.setBounds(111, 22, 470, 42);
    		contentPaneDebiter.add(lblNewLabel_2Debiter);
    	
    		btn10DT_ = new JButton("10");
    		btn10DT_.setBounds(41, 148, 138, 36);
    		btn10DT_.setBackground(new Color(162, 197, 121));
    		contentPaneDebiter.add(btn10DT_);
    		
 
    		
    		btn50DT_ = new JButton("50");
    		btn50DT_.setBackground(new Color(162, 197, 121));
    		btn50DT_.setBounds(443, 148, 138, 36);
    		contentPaneDebiter.add(btn50DT_);
    		
    	    btn20DT_ = new JButton("20");
    		btn20DT_.setBackground(new Color(162, 197, 121));
    		btn20DT_.setBounds(41, 222, 138, 36);
    		contentPaneDebiter.add(btn20DT_);
    		
    		btn70DT_ = new JButton("70");
    		btn70DT_.setBackground(new Color(162, 197, 121));
    		btn70DT_.setBounds(443, 222, 132, 36);
    		contentPaneDebiter.add(btn70DT_);
    		
    		JLabel lblNewLabelDebiter = new JLabel("");
    		lblNewLabelDebiter.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\debt.png"));
    		lblNewLabelDebiter.setBounds(256, 182, 132, 145);
    		contentPaneDebiter.add(lblNewLabelDebiter);
    		
    		btn40DT_ = new JButton("40");
    		btn40DT_.setBackground(new Color(162, 197, 121));
    		btn40DT_.setBounds(41, 291, 138, 36);
    		contentPaneDebiter.add(btn40DT_);
    		
    		btn100DT_ = new JButton("100");
    		btn100DT_.setBackground(new Color(162, 197, 121));
    		btn100DT_.setBounds(443, 291, 138, 36);
    		contentPaneDebiter.add(btn100DT_);
    		
    		JLabel lblNewLabel_1Debiter = new JLabel("Combien voulez vous debiter ?");
    		lblNewLabel_1Debiter.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
    		lblNewLabel_1Debiter.setBounds(169, 75, 304, 45);
    		contentPaneDebiter.add(lblNewLabel_1Debiter);
    		
    		btnNewButtonPDebiter = new JButton("Autre");
    		btnNewButtonPDebiter.setBounds(271, 155, 89, 23);
    		btnNewButtonPDebiter.setBackground(new Color(162, 197, 121));
    		contentPaneDebiter.add(btnNewButtonPDebiter);
    		
    		Debiter.add(contentPaneDebiter);
    		Debiter.setResizable(false);
    		
    		
    		/*** ErrorCode **/
    		
    		ErrorCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		ErrorCode.setBounds(100, 100, 600, 399);
    		contentPaneErrorCode = new JPanel();
    		contentPaneErrorCode.setBorder(new EmptyBorder(5, 5, 5, 5));
    		contentPaneErrorCode.setBackground(new Color(245, 238, 200));
    		contentPaneErrorCode.setLayout(null);
    		
    		JLabel lblNewLabelErrorCode = new JLabel("");
    		lblNewLabelErrorCode.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\close.png"));
    		lblNewLabelErrorCode.setBounds(52, 123, 128, 128);
    		contentPaneErrorCode.add(lblNewLabelErrorCode);
    		
    		
    		JLabel lblNewLabel_2ErrorCode = new JLabel("Bank of Tunisia");
    		lblNewLabel_2ErrorCode.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabel_2ErrorCode.setBounds(111, 22, 470, 42);
    		contentPaneErrorCode.add(lblNewLabel_2ErrorCode);

    		
    		JLabel lblNewLabel_1ErrorCode = new JLabel("Attention ! Password incorrecte");
    		lblNewLabel_1ErrorCode.setFont(new Font("Lucida Bright", Font.BOLD, 20));
    		lblNewLabel_1ErrorCode.setBounds(222, 156, 359, 42);
    		contentPaneErrorCode.add(lblNewLabel_1ErrorCode);
    		
    		JLabel lblNewLabel_3ErrorCode = new JLabel("");
    		lblNewLabel_3ErrorCode.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
    		lblNewLabel_3ErrorCode.setBounds(243, 196, 219, 36);
    		contentPaneErrorCode.add(lblNewLabel_3ErrorCode);
    		
    		btnReessayer = new JButton("Reessayer");
    		btnReessayer.setBounds(239, 251, 98, 31);
    		btnReessayer.setBackground(new Color (162, 197, 121));
    		contentPaneErrorCode.add(btnReessayer);
    		
    		btnFin = new JButton("Quitter");
    		btnFin.setBackground(new Color (162, 197, 121));
    		btnFin.setBounds(376, 251, 98, 31);
    		contentPaneErrorCode.add(btnFin);
    		
    		ErrorCode.add(contentPaneErrorCode);
    		ErrorCode.setResizable(false);
    		
    		
    		/*** ErrorTentatives ***/
    		
    		ErrorTentatives.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		ErrorTentatives.setBounds(100, 100, 756, 416);
    		contentPaneErrorTentatives = new JPanel();
    		contentPaneErrorTentatives.setBorder(new EmptyBorder(5, 5, 5, 5));
    		contentPaneErrorTentatives.setBackground(new Color(245, 238, 200));
    		contentPaneErrorTentatives.setLayout(null);
    		
    		JLabel lblNewLabelErrorTentatives = new JLabel("");
    		lblNewLabelErrorTentatives.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\warning.png"));
    		lblNewLabelErrorTentatives.setBounds(35, 128, 128, 128);
    		contentPaneErrorTentatives.add(lblNewLabelErrorTentatives);
    		
    		JLabel lblNewLabel_2ErrorTentatives = new JLabel("Bank of Tunisia");
    		lblNewLabel_2ErrorTentatives.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabel_2ErrorTentatives.setBounds(111, 22, 470, 42);
    		contentPaneErrorTentatives.add(lblNewLabel_2ErrorTentatives);

    		
    		JLabel lblNewLabel_1ErrorTentatives = new JLabel("Carte bloquée! Vous avez depassez le nombre d'essai");
    		lblNewLabel_1ErrorTentatives.setFont(new Font("Lucida Bright", Font.BOLD, 20));
    		lblNewLabel_1ErrorTentatives.setBounds(187, 157, 553, 42);
    		contentPaneErrorTentatives.add(lblNewLabel_1ErrorTentatives);
    		
    		JLabel lblNewLabel_3ErrorTentatives = new JLabel("");
    		lblNewLabel_3ErrorTentatives.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
    		lblNewLabel_3ErrorTentatives.setBounds(243, 196, 219, 36);
    		contentPaneErrorTentatives.add(lblNewLabel_3ErrorTentatives);
    		
    		btnQuitterTentatives = new JButton("Quitter");
    		btnQuitterTentatives.setBackground(new Color(240, 89, 65));
    		btnQuitterTentatives.setBounds(364, 244, 98, 31);
    		contentPaneErrorTentatives.add(btnQuitterTentatives);
    		
    		ErrorTentatives.add(contentPaneErrorTentatives);
    		ErrorTentatives.setResizable(false);
    		
    		/*** Fail ***/
    		
    		Fail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		Fail.setBounds(100, 100, 600, 399);
    		contentPaneFail = new JPanel();
    		contentPaneFail.setBorder(new EmptyBorder(5, 5, 5, 5));
    		contentPaneFail.setBackground(new Color(245, 238, 200));
    		contentPaneFail.setLayout(null);
    		
    		JLabel lblNewLabelFail = new JLabel("");
    		lblNewLabelFail.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\close.png"));
    		lblNewLabelFail.setBounds(52, 123, 128, 128);
    		contentPaneFail.add(lblNewLabelFail);
    		
    		
    		JLabel lblNewLabel_2Fail = new JLabel("Bank of Tunisia");
    		lblNewLabel_2Fail.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabel_2Fail.setBounds(111, 22, 470, 42);
    		contentPaneFail.add(lblNewLabel_2Fail);

    		
    		JLabel lblNewLabel_1Fail = new JLabel("Operation impossible !");
    		lblNewLabel_1Fail.setFont(new Font("Lucida Bright", Font.BOLD, 20));
    		lblNewLabel_1Fail.setBounds(241, 146, 359, 42);
    		contentPaneFail.add(lblNewLabel_1Fail);
    		
    		JLabel lblNewLabel_3Fail = new JLabel("");
    		lblNewLabel_3Fail.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
    		lblNewLabel_3Fail.setBounds(243, 196, 219, 36);
    		contentPaneFail.add(lblNewLabel_3Fail);
    		
    		btnRetournerFail = new JButton("Retouner");
    		btnRetournerFail.setBounds(239, 251, 98, 31);
    		btnRetournerFail.setBackground(new Color (162, 197, 121));
    		contentPaneFail.add(btnRetournerFail);
    		
    		btnQuitFail = new JButton("Quitter");
    		btnQuitFail.setBackground(new Color (162, 197, 121));
    		btnQuitFail.setBounds(376, 251, 98, 31);
    		contentPaneFail.add(btnQuitFail);
    		
    		Fail.add(contentPaneFail);
    		Fail.setResizable(false);
    		
    		/*** Success ***/
    		
    		Success.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		Success.setBounds(100, 100, 600, 399);
    		contentPaneSuccess = new JPanel();
    		contentPaneSuccess.setBorder(new EmptyBorder(5, 5, 5, 5));
    		contentPaneSuccess.setBackground(new Color(245, 238, 200));
    		contentPaneSuccess.setLayout(null);
    		
    		JLabel lblNewLabelSuccess = new JLabel("");
    		lblNewLabelSuccess.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\checked.png"));
    		lblNewLabelSuccess.setBounds(52, 123, 128, 128);
    		contentPaneSuccess.add(lblNewLabelSuccess);
    		
    		
    		JLabel lblNewLabel_2Success = new JLabel("Bank of Tunisia");
    		lblNewLabel_2Success.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabel_2Success.setBounds(111, 22, 470, 42);
    		contentPaneSuccess.add(lblNewLabel_2Success);

    		
    		JLabel lblNewLabel_1Success = new JLabel("Operation passée !");
    		lblNewLabel_1Success.setFont(new Font("Lucida Bright", Font.BOLD, 20));
    		lblNewLabel_1Success.setBounds(222, 156, 359, 42);
    		contentPaneSuccess.add(lblNewLabel_1Success);
    		
    		JLabel lblNewLabel_3Success = new JLabel("");
    		lblNewLabel_3Success.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
    		lblNewLabel_3Success.setBounds(243, 196, 219, 36);
    		contentPaneSuccess.add(lblNewLabel_3Success);
    		
    		btnRetourSucces = new JButton("Retour");
    		btnRetourSucces.setBounds(239, 251, 98, 31);
    		btnRetourSucces.setBackground(new Color (162, 197, 121));
    		contentPaneSuccess.add(btnRetourSucces);
    		
    		btnQuitSucces = new JButton("Quitter");
    		btnQuitSucces.setBackground(new Color (162, 197, 121));
    		btnQuitSucces.setBounds(376, 251, 98, 31);
    		contentPaneSuccess.add(btnQuitSucces);
    		
    		Success.add(contentPaneSuccess);
    		Success.setResizable(false);
    		
    		
    		/*** PerCrediter ***/
    		
    		PerCrediter = new JFrame();
    		PerCrediter.setBounds(100, 100, 650, 400);
    		PerCrediter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		PerCrediter.getContentPane().setLayout(null);
    		PerCrediter.getContentPane().setBackground(new Color(245, 238, 200));
    		
    		JLabel lblNewLabelImgPerCrediter = new JLabel("");
    		lblNewLabelImgPerCrediter.setBounds(70, 114, 161, 176);
    		PerCrediter.getContentPane().add(lblNewLabelImgPerCrediter);
    		lblNewLabelImgPerCrediter.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\money (2).png"));
    		
    		btnNewButtonPerCrediter = new JButton("Valider");
    		btnNewButtonPerCrediter.setBounds(349, 241, 98, 31);
    		btnNewButtonPerCrediter.setBackground(new Color(162, 197, 121));
    		PerCrediter.getContentPane().add(btnNewButtonPerCrediter);
    		
    		JLabel lblNewLabel_PerCrediter = new JLabel("Montant");
    		lblNewLabel_PerCrediter.setFont(new Font("Tahoma", Font.BOLD, 15));
    		lblNewLabel_PerCrediter.setBounds(349, 135, 228, 37);
    		PerCrediter.getContentPane().add(lblNewLabel_PerCrediter);
    		
    		JLabel lblNewLabelPerCrediter = new JLabel("Bank of Tunisia");
    		lblNewLabelPerCrediter.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabelPerCrediter.setBounds(111, 22, 470, 42);
    		PerCrediter.getContentPane().add(lblNewLabelPerCrediter);
    		
    		btnQuitterPerCrediter = new JButton("Quitter");
    		btnQuitterPerCrediter.setBackground(new Color(162, 197, 121));
    		btnQuitterPerCrediter.setBounds(465, 241, 98, 31);
    		PerCrediter.getContentPane().add(btnQuitterPerCrediter);
    		
    		textFieldPerCrediter = new JTextField();
    		textFieldPerCrediter.setBounds(349, 172, 214, 42);
    		PerCrediter.getContentPane().add(textFieldPerCrediter);
    		textFieldPerCrediter.setColumns(10);
    		
    		PerCrediter.getContentPane().add(textFieldPerCrediter);
    		PerCrediter.setResizable(false);
    		
    		/*** PerDebiiter ***/
    		
    		PerDebiter = new JFrame();
    		PerDebiter.setBounds(100, 100, 650, 400);
    		PerDebiter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		PerDebiter.getContentPane().setLayout(null);
    		PerDebiter.getContentPane().setBackground(new Color(245, 238, 200));
    		
    		JLabel lblNewLabelImgPerDebiter = new JLabel("");
    		lblNewLabelImgPerDebiter.setBounds(70, 114, 161, 176);
    		PerDebiter.getContentPane().add(lblNewLabelImgPerDebiter);
    		lblNewLabelImgPerDebiter.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\money (2).png"));
    		
    		btnNewButtonPerDebiter = new JButton("Valider");
    		btnNewButtonPerDebiter.setBounds(349, 241, 98, 31);
    		btnNewButtonPerDebiter.setBackground(new Color(162, 197, 121));
    		PerDebiter.getContentPane().add(btnNewButtonPerDebiter);
    		
    		JLabel lblNewLabel_PerDebiiter = new JLabel("Montant");
    		lblNewLabel_PerDebiiter.setFont(new Font("Tahoma", Font.BOLD, 15));
    		lblNewLabel_PerDebiiter.setBounds(349, 135, 228, 37);
    		PerDebiter.getContentPane().add(lblNewLabel_PerDebiiter);
    		
    		JLabel lblNewLabelPerDebiiter = new JLabel("Bank of Tunisia");
    		lblNewLabelPerDebiiter.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabelPerDebiiter.setBounds(111, 22, 470, 42);
    		PerDebiter.getContentPane().add(lblNewLabelPerDebiiter);
    		
    		btnQuitterPerDebiter = new JButton("Quitter");
    		btnQuitterPerDebiter.setBackground(new Color(162, 197, 121));
    		btnQuitterPerDebiter.setBounds(465, 241, 98, 31);
    		PerDebiter.getContentPane().add(btnQuitterPerDebiter);
    		
    		textFieldPerDebiter = new JTextField();
    		textFieldPerDebiter.setBounds(349, 172, 214, 42);
    		PerDebiter.getContentPane().add(textFieldPerDebiter);
    		textFieldPerDebiter.setColumns(10);
    		
    		PerDebiter.getContentPane().add(textFieldPerDebiter);
    		PerDebiter.setResizable(false);
    		
    		/*** Fail Transaction ***/
    		
    		FailTransaction.setBounds(100, 100, 600, 399);
    		contentPaneFailTransaction = new JPanel();
    		contentPaneFailTransaction.setBorder(new EmptyBorder(5, 5, 5, 5));
    		contentPaneFailTransaction.setBackground(new Color(245, 238, 200));
    		contentPaneFailTransaction.setLayout(null);
    		
    		JLabel lblNewLabelFailTransaction = new JLabel("");
    		lblNewLabelFailTransaction.setIcon(new ImageIcon("C:\\Users\\Utente\\Downloads\\close.png"));
    		lblNewLabelFailTransaction.setBounds(52, 123, 128, 128);
    		contentPaneFailTransaction.add(lblNewLabelFailTransaction);
    		
    		
    		JLabel lblNewLabel_2FailTransaction = new JLabel("Bank of Tunisia");
    		lblNewLabel_2FailTransaction.setFont(new Font("Times New Roman", Font.ITALIC, 40));
    		lblNewLabel_2FailTransaction.setBounds(111, 22, 470, 42);
    		contentPaneFailTransaction.add(lblNewLabel_2FailTransaction);

    		FailTransaction.setContentPane(contentPaneFailTransaction);
    		
    		JLabel lblNewLabel_1FailTransaction = new JLabel("Transaction impossible !");
    		lblNewLabel_1FailTransaction.setFont(new Font("Lucida Bright", Font.BOLD, 20));
    		lblNewLabel_1FailTransaction.setBounds(241, 146, 359, 42);
    		contentPaneFailTransaction.add(lblNewLabel_1FailTransaction);
    		
    		JLabel lblNewLabel_FailTransaction = new JLabel("");
    		lblNewLabel_FailTransaction.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
    		lblNewLabel_FailTransaction.setBounds(243, 196, 219, 36);
    		contentPaneFailTransaction.add(lblNewLabel_FailTransaction);
    		
    		btnNewButtonRetournerTransaction = new JButton("Retouner");
    		btnNewButtonRetournerTransaction.setBounds(239, 251, 98, 31);
    		btnNewButtonRetournerTransaction.setBackground(new Color (162, 197, 121));
    		contentPaneFailTransaction.add(btnNewButtonRetournerTransaction);
    		
    		btnQuitterFailTransaction = new JButton("Quitter");
    		btnQuitterFailTransaction.setBackground(new Color (162, 197, 121));
    		btnQuitterFailTransaction.setBounds(376, 251, 98, 31);
    		contentPaneFailTransaction.add(btnQuitterFailTransaction);
    		
    		/*** Recu ***/
    		
    		Recu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		Recu.setBounds(100, 100, 265, 370);
    		contentPaneRecu = new JPanel();
    		contentPaneRecu.setBorder(new EmptyBorder(5, 5, 5, 5));
    		Recu.setContentPane(contentPaneRecu);
    		contentPaneRecu.setLayout(null); 		
    		transactionsTextArea = new JTextArea();
    		transactionsTextArea.setBounds(0, 0, 249, 295);
    		
    		
    		JScrollPane scrollPane = new JScrollPane(transactionsTextArea); // Wrap JTextArea in JScrollPane
            scrollPane.setBounds(0, 0, 249, 295);
            contentPaneRecu.add(scrollPane);
    		btnButtonRecuRetour = new JButton("Retour");
    		btnButtonRecuRetour.setBounds(83, 306, 89, 23);
    		contentPaneRecu.add(btnButtonRecuRetour);
    		
    		Recu.setResizable(false);
    		
    		
    		
    		btnValider.addActionListener(this);
    		btnRetourner.addActionListener(this);
    		btnQuit.addActionListener(this);
    		btnCrediter.addActionListener(this);
    		btnDebiter.addActionListener(this);
    		btnSolde.addActionListener(this);
    		btnQuitter.addActionListener(this);
    		btn10DT.addActionListener(this);
    		btn50DT.addActionListener(this);
            btn20DT.addActionListener(this);
            btn70DT.addActionListener(this);
            btn40DT.addActionListener(this);
            btn100DT.addActionListener(this);
            btn10DT_.addActionListener(this);
            btn50DT_.addActionListener(this);
            btn20DT_.addActionListener(this);
            btn70DT_.addActionListener(this);
            btn40DT_.addActionListener(this);
            btn100DT_.addActionListener(this);
            btnNewButtonPDebiter.addActionListener(this);
            btnReessayer.addActionListener(this);
            btnFin.addActionListener(this);
            btnQuitterTentatives.addActionListener(this);
            btnQuitFail.addActionListener(this);
            btnRetournerFail.addActionListener(this);
            btnRetourSucces.addActionListener(this);
            btnQuitSucces.addActionListener(this);
            btnNewButtonPerCrediter.addActionListener(this);
            btnQuitterPerCrediter.addActionListener(this);
            btnNewButtonPCrediter.addActionListener(this);
            btnQuitterPerDebiter.addActionListener(this);
            btnNewButtonPerDebiter.addActionListener(this);
            btnQuitterFailTransaction.addActionListener(this);
        	btnNewButtonRetournerTransaction.addActionListener(this);
        	btnNewButton_1Recu.addActionListener(this);
        	btnButtonRecuRetour.addActionListener(this);
    		Bank.setVisible(true);

            }
    
    
    /**Enregistrer Transaction **/
    
    private void enregistrerTransaction(String operation) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fichierTransactions, true))) {
            String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
            writer.println(operation + " - " +  date);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**Charger Transaction **/
    
    private void chargerTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichierTransactions))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                transactionsTextArea.append(ligne + "\n");
            }
        } catch (IOException e) {
            // Le fichier n'existe probablement pas encore, pas d'erreur ici.
        }
    }
    
    
   


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MaaClasse m = new MaaClasse();

        try {

            sckCarte = new Socket("localhost", 9025);
            sckCarte.setTcpNoDelay(true);
            BufferedInputStream input = new
            BufferedInputStream(sckCarte.getInputStream());
            BufferedOutputStream output = new
            BufferedOutputStream(sckCarte.getOutputStream());
            cad = new CadT1Client(input, output);
        }
        catch (Exception e1) {
            System.out.println("Erreur : impossible de se connecter a la Javacard");
            return;
        }
        /* Mise sous tension de la carte */
        try {
            cad.powerUp();
        }
        catch (Exception e1) {

            System.out.println("Erreur lors de l'envoi de la commande Powerup a la Javacard");
            return;
        }


        apdu.command[Apdu.CLA] = (byte) 0xB0;
        apdu.command[Apdu.INS] = (byte)0xA4;
        apdu.command[Apdu.P1] = 0x04;
        apdu.command[Apdu.P2] = 0x00;
        byte[] appletAID = { 0x19,0x34,0x12,0x34,0x56,0x10,0x00,0x01 };
        apdu = new Apdu();
        apdu.command[Apdu.CLA] = MaaClasse.CLA_MONAPPLET;
        apdu.command[Apdu.P1] = 0x00;
        apdu.command[Apdu.P2] = 0x00;
        apdu.setLe(0x7f);
        apdu.setDataIn(appletAID);


        try {
            cad.exchangeApdu(apdu);
        }
        catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        catch (CadTransportException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        if (apdu.getStatus() != 0x9000) {
            System.out.println("Erreur lors de la selection de l'applet");
            System.exit(0);
        }

    }

    
        public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
        	
    // Connection à la carte	
        if (e.getSource() == btnValider) {

            String t = passwordField.getText();
            
            // Verification de mot de passe de la carte
           
          
        
            byte[] donnees;
			
			if (t.length() == 4) {
				apdu.command[Apdu.INS] = MaaClasse.INS_PIN;
		        
			    byte x1 = Byte.parseByte(Character.toString(t.charAt(0)));
			    byte x2 = Byte.parseByte(Character.toString(t.charAt(1)));
			    byte x3 = Byte.parseByte(Character.toString(t.charAt(2)));
			    byte x4 = Byte.parseByte(Character.toString(t.charAt(3)));
			    apdu.setLe((byte) 0x7f);
	            
			     donnees = new byte[]{x1, x2, x3, x4};
			    apdu.setDataIn(donnees);}

			    // Assuming apdu is an instance of Apdu
			    else {
			    	apdu.command[Apdu.INS] = MaaClasse.INS_PIN;
			        
			    	apdu.setLe((byte) 0x7f);
		            
					  donnees = new byte[]{1, 1, 1, 1};
					 apdu.setDataIn(donnees);}
			  
			   
			    try {
					cad.exchangeApdu(apdu);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				  if (apdu.getStatus() ==SW_VERIFICATION_FAILED ) {
					  ErrorCode.setVisible(true);
		                Bank.setVisible(false);
		                passwordField.setText("");
		            }
	            else if (apdu.getStatus() == SW_EXCEED_TRY_LIMIT ) {
	            	ErrorTentatives.setVisible(true);
	                Bank.setVisible(false);

	            }
	            else {
	            	
	            	 Bank.setVisible(false);
	            	 Choice.setVisible(true);
	            	 
	            }
	           
        }

			if(e.getSource() == btnQuitterPerCrediter) {
				System.exit(0);
			}
			
			

			
			
			
			 if(e.getSource() == btnNewButton_1Recu) {
				/******************************************************/
				// Charger les transactions existantes depuis le fichier
			     chargerTransactions();
				 Recu.setVisible(true);
				 Choice.setVisible(false);
				 
		        	
		        }
			
        
        //ErrorCode 
        if(e.getSource() == btnReessayer ) {
        	
            Bank.setVisible(true);
            ErrorCode.setVisible(false);
        }
        if(e.getSource() == btnFin ) {
        	System.exit(0);
        }
        //ErrorTentatives
        if(e.getSource() == btnQuitterTentatives ) {
        	System.exit(0);
        }
        
   //PerDebiter     
if (e.getSource()==btnNewButtonPerDebiter) {
	String i=textFieldPerDebiter .getText();
	int r=Integer.parseInt(i);
	if(r<=15000) {
	if( r>127) 
	{
	 apdu.command[Apdu.INS] = MaaClasse.INS_DECREMENTER_COMPTEUR;
  
	 int number_bytes = 1;
		int montantSave=r;
		while(montantSave > 127)
		{
			number_bytes++;
			montantSave-=127;
		}
		
		byte[] montant = new byte[number_bytes];
		int counter=0;
		montantSave=r;
		while(montantSave > 127)
		{
			montant[counter]=(byte)127;
			montantSave-=127;
			counter++;
		}
		montant[counter]=(byte)montantSave;
   	
	apdu.setLe((byte) 0x7f);
	
    System.out.println(montant[0]);
    System.out.println(montant[1]);
	apdu.setDataIn(montant);
	textFieldPerDebiter.setText("");}
	else if(r<128 && r>=0) {
		byte b = (byte)r;
        apdu.command[Apdu.INS] = MaaClasse.INS_DECREMENTER_COMPTEUR;
        byte[] donnees = new byte[1];
        donnees[0] = b;
        apdu.setDataIn(donnees);
        textFieldPerDebiter.setText("");}

	
	try {
		cad.exchangeApdu(apdu);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (CadTransportException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  
	if (apdu.getStatus() != 0x9000)   {
		Fail.setVisible(true);
		PerDebiter.setVisible(false);
	}
    else {
    	String operation = "Débiter: "+i+"DT";
       
        enregistrerTransaction(operation);
       Success.setVisible(true);
       PerDebiter.setVisible(false);

    }
   
    
	}
	
        
    	else {
    		textFieldPerDebiter.setText("");
        	PerDebiter.setVisible(false);
        	FailTransaction.setVisible(true);
        	
        	
        }
        }
        //Check Solde
   
        if (e.getSource() == btnSolde) {
        	  if(i==0) {
        		  i++;
           	   apdu.command[Apdu.INS]= MaaClasse.INS_INITIALISER_COMPTEUR;
					
					try {
						cad.exchangeApdu(apdu);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CadTransportException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
            apdu.command[Apdu.INS] = MaaClasse.INS_INTERROGER_COMPTEUR;
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (apdu.getStatus() != 0x9000) {
                System.out.println("Erreur : status word different de 0x9000");
            }
            else {

                Check.setVisible(true);
                Choice.setVisible(false);
            
                byte[] responseData = apdu.getDataOut(); // Assuming this is how you retrieve response data
                short balance = (short) ((responseData[0] << 8) | (responseData[1] & 0xFF));

                lblSoldeActuel.setText("Votre Solde actuel est : "+ balance);

            }
        }
        if(e.getSource() == btnCrediter) {
        	if(i==0) {
      		  i++;
         	   apdu.command[Apdu.INS]= MaaClasse.INS_INITIALISER_COMPTEUR;
					byte[] donnees = new byte[1];
					donnees[0] = (byte)0;
					apdu.setDataIn(donnees);
					try {
						cad.exchangeApdu(apdu);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CadTransportException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
    Crediter.setVisible(true);
    Choice.setVisible(false);
    }
        //PerCrediter
        if(e.getSource()== btnNewButtonPerCrediter) {
        	
        	String i=textFieldPerCrediter.getText();
        	int r=Integer.parseInt(i);
        	if(r<=15000) {
        	if( r>127) 
        	{
        	 apdu.command[Apdu.INS] = MaaClasse.INS_INCREMENTER_COMPTEUR;
	      
        	 int number_bytes = 1;
				int montantSave=r;
				while(montantSave > 127)
				{
					number_bytes++;
					montantSave-=127;
				}
				
				byte[] montant = new byte[number_bytes];
				int counter=0;
				montantSave=r;
				while(montantSave > 127)
				{
					montant[counter]=(byte)127;
					montantSave-=127;
					counter++;
				}
				montant[counter]=(byte)montantSave;
		   	
			apdu.setLe((byte) 0x7f);
			
		    System.out.println(montant[0]);
		    System.out.println(montant[1]);
			apdu.setDataIn(montant);
			textFieldPerCrediter.setText("");}
        	else if(r<128 && r>=0) {
        		byte b = (byte)r;
                apdu.command[Apdu.INS] = MaaClasse.INS_INCREMENTER_COMPTEUR;
                byte[] donnees = new byte[1];
                donnees[0] = b;
                apdu.setDataIn(donnees);}
        
        	
	    	try {
				cad.exchangeApdu(apdu);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      
	        if (apdu.getStatus() != 0x9000) {
                System.out.println("Erreur : status word different de 0x9000");
            }
	        if(apdu.getStatus()== SW_MAXBALANCE ) {
	        	textFieldPerCrediter.setText("");
	        	PerCrediter.setVisible(false);
	        	FailTransaction.setVisible(true);
	        }
            else {
            	 String operation = "Créditer: "+i+"DT";
             
                 enregistrerTransaction(operation);
                 Success.setVisible(true);
                 PerCrediter.setVisible(false);
              
              
            	}
        	}
        	
               
                
            	else {
            		textFieldPerCrediter.setText("");
    	        	PerCrediter.setVisible(false);
    	        	FailTransaction.setVisible(true);
    	        	
    	        	
    	        }
            }
            
        	
        	
        	
        
        if(e.getSource()==btnQuitterFailTransaction)    {
        	System.exit(0);
        }
        if(e.getSource()==btnNewButtonRetournerTransaction)    {
        	FailTransaction.setVisible(false);
        	Choice.setVisible(true);
        	
        }
                
         
    if(e.getSource()==btnDebiter)    {
    	if(i==0) {
  		  i++;
     	   apdu.command[Apdu.INS]= MaaClasse.INS_INITIALISER_COMPTEUR;
				byte[] donnees = new byte[1];
				donnees[0] = (byte)0;
				apdu.setDataIn(donnees);
				try {
					cad.exchangeApdu(apdu);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
    	Debiter.setVisible(true);
    	Choice.setVisible(false);
    }
    
    //PerDebiter
    if(e.getSource() == btnNewButtonPDebiter) {
    	
    	Debiter.setVisible(false);
    	PerDebiter.setVisible(true);
    }
    
    
    
        if (e.getSource() == btn10DT) {

            byte b = (byte)10;
            apdu.command[Apdu.INS] = MaaClasse.INS_INCREMENTER_COMPTEUR;
            byte[] donnees = new byte[1];
            donnees[0] = b;
            apdu.setDataIn(donnees);
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (apdu.getStatus() != 0x9000) {
                System.out.println("Erreur : status word different de 0x9000");
            }
            if(apdu.getStatus()== SW_MAXBALANCE ) {
	        	textFieldPerCrediter.setText("");
	        	Crediter.setVisible(false);
	        	FailTransaction.setVisible(true);
	        }
            else {
            	String operation = "Crédit: 10DT";
                transactionsTextArea.append(operation + "\n");
                enregistrerTransaction(operation);
               Success.setVisible(true); 
               Crediter.setVisible(false);

            }
        }
        
        if (e.getSource() == btn20DT) {
            apdu.command[Apdu.INS] = MaaClasse.INS_INCREMENTER_COMPTEUR;
            byte[] donnees = new byte[1];
            donnees[0] = (byte)20;
            apdu.setDataIn(donnees);
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (apdu.getStatus() != 0x9000) {
                System.out.println("Erreur : status word different de 0x9000");
            }
            if(apdu.getStatus()== SW_MAXBALANCE ) {
	        	textFieldPerCrediter.setText("");
	        	Crediter.setVisible(false);
	        	FailTransaction.setVisible(true);
	        }
            else {
            	String operation = "Créditer: 20DT";
              
                enregistrerTransaction(operation);
               Success.setVisible(true);
               Crediter.setVisible(false);
               
                  
            }
        }
        if (e.getSource() == btn40DT) {
            apdu.command[Apdu.INS] = MaaClasse.INS_INCREMENTER_COMPTEUR;
            byte[] donnees = new byte[1];
            donnees[0] = (byte)40;
            apdu.setDataIn(donnees);
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (apdu.getStatus() != 0x9000) {
                System.out.println("Erreur : status word different de 0x9000");
            }
            if(apdu.getStatus()== SW_MAXBALANCE ) {
	        	textFieldPerCrediter.setText("");
	        	Crediter.setVisible(false);
	        	FailTransaction.setVisible(true);
	        }
            else {
            	String operation = "Créditer: 40DT";
              
                enregistrerTransaction(operation);
               Success.setVisible(true);
               Crediter.setVisible(false);
              
                  
            }
        }
        if (e.getSource() == btn70DT) {
            apdu.command[Apdu.INS] = MaaClasse.INS_INCREMENTER_COMPTEUR;
            byte[] donnees = new byte[1];
            donnees[0] = (byte)70;
            apdu.setDataIn(donnees);
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (apdu.getStatus() != 0x9000) {
                System.out.println("Erreur : status word different de 0x9000");
            }
            if(apdu.getStatus()== SW_MAXBALANCE ) {
	        	textFieldPerCrediter.setText("");
	        	Crediter.setVisible(false);
	        	FailTransaction.setVisible(true);
	        }
            else {
            	String operation = "Créditer: 70DT";
            
                enregistrerTransaction(operation);
               Success.setVisible(true);
               Crediter.setVisible(false);
               
                  
            }
        }
        if (e.getSource() == btn50DT) {
            apdu.command[Apdu.INS] = MaaClasse.INS_INCREMENTER_COMPTEUR;
            byte[] donnees = new byte[1];
            donnees[0] = (byte)50;
            apdu.setDataIn(donnees);
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (apdu.getStatus() != 0x9000)  {
        		Fail.setVisible(true);
        		Crediter.setVisible(false);
        	}
            
            else {

            	String operation = "Créditer: 50DT";
            
                enregistrerTransaction(operation);
               Success.setVisible(true);
               Crediter.setVisible(false);
               
                  
            }
        }
        
        if(e.getSource() == btnNewButtonPCrediter) {
        	Crediter.setVisible(false);
        	PerCrediter.setVisible(true);
        }
        
        if (e.getSource() == btnRetourSucces) {

               Success.setVisible(false);
               Crediter.setVisible(false);
               Choice.setVisible(true);
              
        }
        if (e.getSource() == btn100DT) {
            apdu.command[Apdu.INS] = MaaClasse.INS_INCREMENTER_COMPTEUR;
            byte[] donnees = new byte[1];
            donnees[0] = (byte)100;
            apdu.setDataIn(donnees);
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (apdu.getStatus() != 0x9000) {
                System.out.println("Erreur : status word different de 0x9000");
            }
            else {

            	String operation = "Créditer: 100DT";
           
                enregistrerTransaction(operation);
               Success.setVisible(true);
             
            }
            if(apdu.getStatus()== SW_MAXBALANCE ) {
	        	textFieldPerCrediter.setText("");
	        	PerCrediter.setVisible(false);
	        	FailTransaction.setVisible(true);
	        }
        }
        if (e.getSource() == btnQuitSucces) {
        	System.exit(0);
           
     }
        if(e.getSource()==btnRetourner) {
        	  Choice.setVisible(true);
        	  Check.setVisible(false);
        }
        
        if (e.getSource() == btn10DT_) {

            apdu.command[Apdu.INS] = MaaClasse.INS_DECREMENTER_COMPTEUR;
            byte[] donnees = new byte[1];
            donnees[0] = (byte)10;
            apdu.setDataIn(donnees);
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println(apdu.getStatus());
          
			if (apdu.getStatus() != 0x9000 )  {
	                Fail.setVisible(true);
					Debiter.setVisible(false);
			}
            else {

            	
            	String operation = "Débiter: 10DT";
           
                enregistrerTransaction(operation);
               Success.setVisible(true); 
            	 Debiter.setVisible(false);
            	
                    
            }
	            
        }
        if (e.getSource() == btn50DT_) {

            

            byte b = (byte)50;

            apdu.command[Apdu.INS] = MaaClasse.INS_DECREMENTER_COMPTEUR;
            byte[] donnees = new byte[1];
            donnees[0] = b;
            apdu.setDataIn(donnees);
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
           
			if (apdu.getStatus() != 0x9000 )  {
				Fail.setVisible(true);
				Debiter.setVisible(false);
			}
            else {

            	String operation = "Débiter: 50DT";
         
                enregistrerTransaction(operation);
               Success.setVisible(true); 
            	 Debiter.setVisible(false);
              
               

            }
        }
if (e.getSource() == btn20DT_) {

            

            byte b = (byte)20;

            apdu.command[Apdu.INS] = MaaClasse.INS_DECREMENTER_COMPTEUR;
            byte[] donnees = new byte[1];
            donnees[0] = b;
            apdu.setDataIn(donnees);
            try {
                cad.exchangeApdu(apdu);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (CadTransportException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
          
			if (apdu.getStatus() != 0x9000)   {
				Fail.setVisible(true);
				Debiter.setVisible(false);
			}
            else {

            	String operation = "Débiter: 20DT";
           
                enregistrerTransaction(operation);
               Success.setVisible(true); 
            	 Debiter.setVisible(false);
              
               
            }
        }
if (e.getSource() == btn70DT_) {

    

    byte b = (byte)70;

    apdu.command[Apdu.INS] = MaaClasse.INS_DECREMENTER_COMPTEUR;
    byte[] donnees = new byte[1];
    donnees[0] = b;
    apdu.setDataIn(donnees);
    try {
        cad.exchangeApdu(apdu);
    }
    catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
    catch (CadTransportException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }

	if (apdu.getStatus() != 0x9000)  {
		Fail.setVisible(true);
		Debiter.setVisible(false);
	}
    else {

    	String operation = "Débiter: 70DT";
      
        enregistrerTransaction(operation);
       Success.setVisible(true); 
    	 Debiter.setVisible(false);
       
       

    }
}
	if (e.getSource() == btn40DT_) {

	    apdu.command[Apdu.INS] = MaaClasse.INS_DECREMENTER_COMPTEUR;
	    byte[] donnees = new byte[1];
	    donnees[0] = 40;
	    apdu.setDataIn(donnees);
	    try {
	        cad.exchangeApdu(apdu);
	    }
	    catch (IOException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	    catch (CadTransportException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	   
		if (apdu.getStatus() != 0x9000 )  {
			Fail.setVisible(true);
			Debiter.setVisible(false);
		}
	    else {
	
	    	String operation = "Débiter: 40DT";
   
            enregistrerTransaction(operation);
           Success.setVisible(true); 
        	 Debiter.setVisible(false);
	       
           
	
	    }
	}
	if (e.getSource() == btn100DT_) {
	
	    byte b = (byte)100;
	
	    apdu.command[Apdu.INS] = MaaClasse.INS_DECREMENTER_COMPTEUR;
	    byte[] donnees = new byte[1];
	    donnees[0] = b;
	    apdu.setDataIn(donnees);
	    try {
	        cad.exchangeApdu(apdu);
	    }
	    catch (IOException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	    catch (CadTransportException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	 
	   
		if (apdu.getStatus() != 0x9000 )  {
			Fail.setVisible(true);
			Debiter.setVisible(false);
		}
	    else {
	
	    	String operation = "Débiter: 100DT";
          
            enregistrerTransaction(operation);
           Success.setVisible(true); 
        	 Debiter.setVisible(false);
	       
           
	    }
	}
	
		if(e.getSource() == btnQuitFail) {
			System.exit(0);
		}
		if(e.getSource() == btnRetournerFail) {
			 Choice.setVisible(true);
		     Fail.setVisible(false);
			
		}
		if(e.getSource() == btnQuitterPerDebiter) {
			System.exit(0);
		}
		
		if( e.getSource() == btnQuitter) {
			System.exit(0);
		}
	        
       
        if(e.getSource() == btnQuit) {
        	System.exit(0);
        }
        
       if(e.getSource() == btnButtonRecuRetour) {
    	   Choice.setVisible(true);
		     Recu.setVisible(false);
       }
        
        
     } 
}



