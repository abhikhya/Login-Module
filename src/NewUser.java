//import statement
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Arrays;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.StringUtils;
 
public class NewUser extends JFrame //create class NewUser
{
	private JPanel contentPane; //declare variable
	private JTextField username;
	private JTextField username1;
	private JTextField email;
	private JTextField age;	 
	String[] sexes = new String[] {"Male","Female","Other"};
	JComboBox<String> sex;
	private JTextField phone;
	private JPasswordField set_pwd;
	private JPasswordField password;
	private JPasswordField confirm_pwd;
	private JButton btnSignup;
	private JButton btnLogin;

	protected java.lang.String Spassword;
	
	// database URL
	static final String DB_URL = "jdbc:mysql://localhost/user_data";
	
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "Abcd1234!";
	protected static final String String = null;

	public static void main(String[] args) // main method
	{
		//NewUser frame = new NewUser();
		//frame.setVisible(true);
		EventQueue.invokeLater(new Runnable()
		{
			public void run() //define run method
			{
				try  //try block
				{
					//create NewUser frame object
					NewUser frame = new NewUser();
                                        //set NewUser frame visible
					frame.setVisible(true);
				} 
				catch (Exception e) //catch block
				{
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the frame.
	 */
	public NewUser() //create constructor
	{	
		//set title
		setTitle("New User Login");
		//set close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set bounds of frame
		setBounds(100, 100, 750, 450);
		//create object of JPanel
		contentPane = new JPanel();
                //set contentPane border
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//set ContentPane with new object
		setContentPane(contentPane);
		//set contentPane layout is null
		contentPane.setLayout(null);
        // create text field for user
		username = new JTextField();
		username.setBounds(488, 51, 109, 20);
		contentPane.add(username);
		username.setColumns(10);
		//for login side username
		username1 = new JTextField();
		username1.setBounds(158, 100, 139, 20);
		contentPane.add(username1);
		username1.setColumns(10);
		//email
		email = new JTextField();
		email.setBounds(488, 81, 109, 20);
		contentPane.add(email);
		email.setColumns(10);
		//age
		age= new JTextField();
		age.setBounds(488, 111, 109, 20);
		contentPane.add(age);
		age.setColumns(10);
		//sex
		//sex= new JTextField();		
		sex = new JComboBox<>(sexes);
		sex.setEditable(false);
		sex.setBounds(488, 141, 109, 20);
		contentPane.add(sex);
		//sex.setColumns(10);
		//phone
		phone= new JTextField();
		phone.setBounds(488, 171, 109, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		//set password
		set_pwd= new JPasswordField();
		set_pwd.setBounds(488, 201, 109, 20);
		contentPane.add(set_pwd);
		set_pwd.setColumns(10);
		//login side password
		password= new JPasswordField();
		password.setBounds(158, 131, 139, 20);
		contentPane.add(password);
		password.setColumns(10);
		//confirm password
		confirm_pwd= new JPasswordField();
		confirm_pwd.setBounds(488, 231, 109, 20);
		contentPane.add(confirm_pwd);
		confirm_pwd.setColumns(10);
		
		//label the username
		JLabel lblUserName = new JLabel("User Name");
		//set bounds for label
		lblUserName.setBounds(360, 54, 86, 14);
		//add into contentPane
		contentPane.add(lblUserName);
		//login side username
		JLabel lblUserName1 = new JLabel("User Name");
		lblUserName1.setBounds(60, 96, 86, 14);
		contentPane.add(lblUserName1);
		
		//lable email
		JLabel lblemail = new JLabel("Email");
		lblemail.setBounds(360, 84, 86, 14);
		contentPane.add(lblemail);
		
		JLabel lblage = new JLabel("Age");
		lblage.setBounds(360, 114, 86, 14);
		contentPane.add(lblage);
		
		JLabel lblsex = new JLabel("Sex");
		lblsex.setBounds(360, 144, 86, 14);
		contentPane.add(lblsex);
		
		JLabel lblphone = new JLabel("Phone");
		lblphone.setBounds(360, 174, 86, 14);
		contentPane.add(lblphone);
		
		JLabel lblsetpwd = new JLabel("Create Password");
		lblsetpwd.setBounds(360, 204, 116, 14);
		contentPane.add(lblsetpwd);
		
		//login side password
		JLabel passwordlb= new JLabel("Password");
		passwordlb.setBounds(60, 128, 116, 14);
		contentPane.add(passwordlb);
		
		//lable the text field
		JLabel lblconfpwd = new JLabel("Confirm Password");
		//set bounds for label
		lblconfpwd.setBounds(360, 239, 116, 14);
		//add into contentPane
		contentPane.add(lblconfpwd);
		
                //create button signup
		btnSignup = new JButton("SignUp");
		//add event handler on SignUp button
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
				{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				btnLogin.setEnabled(true);
				Wrapper conn = null;
				
				
				try
				{
					String Username = username1.getText().trim();
					ResultSet result;
					System.out.println("Connecting to a selected database...");
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					System.out.println("Connected database successfully...");
					String retrieved_pwd = "select set_pwd from user_data.user_details where username = '"+Username+"'";
					PreparedStatement preparedStatement = ((Connection)conn).prepareStatement(retrieved_pwd);
					
					result = preparedStatement.executeQuery();  
					char[] ret= {};

					System.out.println("Password = "+retrieved_pwd);//print on console
					while (result.next()) {
						
					  ret= result.getString(1).toCharArray();
					}
					System.out.println(Username);
					System.out.println(StringUtils.isNullOrEmpty(Username));
					System.out.println(password.getPassword());
					System.out.println(result);
					
					
					if (StringUtils.isNullOrEmpty(Username) || password.getPassword().length == 0)
					{
						JOptionPane.showMessageDialog(null," name or password fields is Empty","Error",JOptionPane.ERROR_MESSAGE);
						//btnSignup.setEnabled(true);
					}
					
					if(!(Arrays.equals(password.getPassword(),ret)))
					{
						JOptionPane.showMessageDialog(null, "The Passwords do not match","Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JFrame f2 = new JFrame("Welcome");			
						f2.setVisible(true);
					}
				}
				catch (SQLException se) 
				{
					//handle errors for JDBC
					se.printStackTrace();
				}
			catch (Exception a) //catch block
				{
					a.printStackTrace();
				}
				
			}
			
				});
		
		btnSignup.addActionListener(new ActionListener() 
                {
			@Override
		        public void actionPerformed(ActionEvent e) 
                {				
		        	btnSignup.setEnabled(true);
				//Create wrapper object and define it null
				 Wrapper conn = null;
				try  //try block
                { 	
		//get values using getText() method
		String Username = username.getText().trim();
		String Email = email.getText().trim();
		int Age = Integer.parseInt(age.getText().trim());
		String Sex = (String) sex.getSelectedItem();
		long Phone = Long.parseLong(phone.getText().trim());	
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String PHONE_REGEX = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";


        // check condition it field equals to blank throw error message
		if (StringUtils.isNullOrEmpty(Username) || set_pwd.getPassword().length == 0 ||confirm_pwd.getPassword().length == 0)
		{
			JOptionPane.showMessageDialog(null," name or password fields is Empty","Error",JOptionPane.ERROR_MESSAGE);
		}
		if(StringUtils.isNullOrEmpty(Email) || StringUtils.isNullOrEmpty(age.getText().trim()) || StringUtils.isNullOrEmpty(phone.getText().trim()))
		{
			JOptionPane.showMessageDialog(null,"Email or age empty","Error",JOptionPane.ERROR_MESSAGE);
		}
		if (Email.matches(EMAIL_REGEX) == false)
		{
			JOptionPane.showMessageDialog(null,"Enter a valid Email","Error",JOptionPane.ERROR_MESSAGE);
		}
		if (phone.getText().matches(PHONE_REGEX) == false)
		{
			JOptionPane.showMessageDialog(null,"Enter a valid Phone Number","Error",JOptionPane.ERROR_MESSAGE);
		}
		if(!(Arrays.equals(set_pwd.getPassword(),confirm_pwd.getPassword())))
		{
			JOptionPane.showMessageDialog(null, "The Passwords do not match","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			//btnSignup.setEnabled(true);
				//else  //else insert query is run properly
                //{
				
				String IQuery = "INSERT INTO user_data.user_details(username,email,age,sex,phone,set_pwd,confirm_pwd)"+"VALUES(?,?,?,?,?,?,?)";
							
					System.out.println(IQuery);//print on console
					System.out.println("Connecting to a selected database...");
				
				//STEP 3: Open a connection
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
					System.out.println("Connected database successfully...");
					PreparedStatement preparedStatement = ((Connection)conn).prepareStatement(IQuery);
					preparedStatement.setString(1,Username);
					preparedStatement.setString(2,Email);
					preparedStatement.setInt(3, Age);
					preparedStatement.setString(4, Sex);
					preparedStatement.setLong(5, Phone);
					preparedStatement.setString(6, new String(set_pwd.getPassword()));
					preparedStatement.setString(7, new String(confirm_pwd.getPassword()));
					preparedStatement.executeUpdate();  
				//((Connection)conn).createStatement().executeUpdate(IQuery);//select the rows
					// define SMessage variable
					String SMessage = "Record added for "+Username;
					
                                       // create dialog ox which is print message
	                    JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
					//close connection
					((java.sql.Connection)conn).close();
				}				
			}
			catch (SQLException se) 
			{
				//handle errors for JDBC
				se.printStackTrace();
			}
		catch (Exception a) //catch block
			{
				a.printStackTrace();
			}
                }
                });
		//set bound for SignUp button
		btnSignup.setBounds(430, 285, 89, 23);
		//add button into contentPane
		contentPane.add(btnSignup);
		btnLogin.setBounds(115, 171, 89, 23);
		//add button into contentPane
		contentPane.add(btnLogin);
			
	}

}
