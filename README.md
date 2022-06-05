# Attendance_System_java
Used Java to build the Desktop Application. JAVAFX is used for GUI and MySQL for backend
What is needed to run Project
1.	You must have XMAPP installed and MYSQL and Apache servers up and running like this.

![image](https://user-images.githubusercontent.com/60143938/172049476-3c1fdc09-9d41-4bb9-afda-538a80090e71.png)


2.	You must have phpMyAdmin account and must have following tables with following columns. Tables:

![image](https://user-images.githubusercontent.com/60143938/172049507-d46e39e0-058c-44e3-9295-8bff3c1d0382.png)


Columns in ‘absent’ table:
 
![image](https://user-images.githubusercontent.com/60143938/172049516-c1c06a3a-a2f2-484e-9ccd-deebc6044468.png)

 

Columns in ‘admin’ table:

Note: You should fix a name password for username and password in admin table as it will be fixed and only through that, you will be able to access admin panel. In this case,I named it username=admin, password=admin

![image](https://user-images.githubusercontent.com/60143938/172049520-9e1d61c5-da4d-4337-8726-2578c0e8c7a9.png)

![image](https://user-images.githubusercontent.com/60143938/172049530-df179326-c3cc-446a-b1ae-c9f59f708064.png)


Columns in ‘present table’:

Note: Make id column as auto-increment.

![image](https://user-images.githubusercontent.com/60143938/172049545-62aa8728-1e3e-4131-b685-e76915404492.png)


Columns in ‘request_leave’ table:

![image](https://user-images.githubusercontent.com/60143938/172049556-39d8e02c-3de1-4b7c-9870-1e6a97dd571f.png)


Columns in ‘users’ table
 
![image](https://user-images.githubusercontent.com/60143938/172049562-b599c925-695b-49f5-8e33-0947d7a3f49e.png)

 

3.	Recommended IDE is Intellij but you can use any IDE as long as it has JAVAFX plugin and JConnector to connect to database.


How to run the project
1.	Extract the rar file and you will get the project.
2.	Open the classes main,GUI and MyConnection in src folder of extracted file.
3.	Run main file and you will see this pop up.
 
 ![image](https://user-images.githubusercontent.com/60143938/172049571-a1ace389-b1fa-4e80-8c24-78e3eec5b5d1.png)

 
4.	Go to Resgistration and register.
 

![image](https://user-images.githubusercontent.com/60143938/172049577-6241e777-5fea-4598-bcf4-78ecceb96813.png)


![image](https://user-images.githubusercontent.com/60143938/172049587-bbf521b3-b0df-4fa3-b73d-361d0e6a21bd.png)



5.	Now, click on back page by Clicking ‘Back’ and go to login page by clicking on login button.
 
6.	Now enter the credentials that you entered to register and click login.
 
![image](https://user-images.githubusercontent.com/60143938/172049599-f87e8892-8b3d-45a1-85b8-21b4811cf5c6.png)



7.	You will see following page and options that you can do.(you will see a default pic that you have to save in D Disk of your computer with name “default.jpg” and this will be the deault pic of your id).
 
 
 ![image](https://user-images.githubusercontent.com/60143938/172049607-c6e1d313-38e9-484d-a35c-85a4d974d4fb.png)

 
 
8.	When you click Present, Absent and Request Leave , you attendance for today is marked and you can’t mark your attendance again today.


![image](https://user-images.githubusercontent.com/60143938/172049613-5d6a8eb8-79e9-4fe6-9c2d-7526e244180c.png)



Trying to mark attendance again:

 


9.	View Button will show you attendance of everyone.
 
 ![image](https://user-images.githubusercontent.com/60143938/172049620-d8ff0dfd-07b7-4f4e-b30f-38d8f016f0fb.png)



10.	Request Leave will give you a page to write to your request of leave. It must be greater than 20 words.
 
 
 ![image](https://user-images.githubusercontent.com/60143938/172049629-ff81b9bd-126a-4247-aa9c-231c7d9cb146.png)

 
 
11.	You can change your default pic by clicking on change pic button.
 
 
 ![image](https://user-images.githubusercontent.com/60143938/172049642-c3fbdd82-66df-40b6-8f87-a133f5af9346.png)

 

After selecting the pic and clicking ok, re-login to see the changes.


![image](https://user-images.githubusercontent.com/60143938/172049649-6dc8cadd-0a48-491a-917e-07e324fb9782.png)


 
12.	To login to admin panel, enter credentials that you saved in admin table in database. In my case, it’s


![image](https://user-images.githubusercontent.com/60143938/172049654-aa95df4f-9c8d-4177-8c4b-28ecc756b2e4.png)


13.	Click Login and you will see following information Displayed.

 ![image](https://user-images.githubusercontent.com/60143938/172049666-e8826f04-3590-4c7a-9583-c75174ac7aaa.png)

 
14.	“View Attendance Record of Every Student” button will display attendance of every student. You can add, delete, and edit info every student. Remember, changes will take place the next time you click on button” View Attendance of Record of Every Student”.

![image](https://user-images.githubusercontent.com/60143938/172049672-fb6683e2-0c82-47c7-b306-a3fa646f1c97.png)


15.	“Create a report of user attendance” will give you a panel where you can create a report from one date to another.

 
 ![image](https://user-images.githubusercontent.com/60143938/172049678-20807207-2dd4-4f29-b030-07a197a9c1b9.png)


![image](https://user-images.githubusercontent.com/60143938/172049688-350c8863-a9ca-406b-851e-9411b58522f7.png)



16.	“View Leaves Button” will display no. of leaves that students have done.
 
 
 ![image](https://user-images.githubusercontent.com/60143938/172049695-dd239fcc-6809-43ca-93a4-c6367605cb16.png)

 
17.	“Grade” button will display grades. If student has attendance greater than 26,then he/she will have A grade. Between 10-26 will give B grade and less than will give C grade.

![image](https://user-images.githubusercontent.com/60143938/172049705-80b9b5be-439b-4608-963a-b9a0181d0682.png)

