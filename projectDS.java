package Project1;


/*
"Employee Record Management System"
#Manal Almalki , Farah Alharbi
*/
import java.io.*;
import java.util.Scanner;

public class projectDS {
    
    private Node head;
    
      private  class Node{
        private Node next;
        private int ID;
        private String day;
        private String phone;
        private String address;
        private String name;
        private double hours;
        private double salary;
      
        
        public Node(int ID,String name,String day,String phone,String address,double hours,double salary){
            this.next=null;
            this.ID=ID;
            this.day=day;
            this.phone=phone;
            this.address=address;
            this.name=name;
            this.hours=hours;
            this.salary=salary;
           
        }
    }
      
      public static void main(String[] args) throws IOException {
            Scanner scanner1=new Scanner(System.in);
            projectDS emp=new projectDS();
            emp.read();
            int ID;
            int choice;
            String name,day,phone,address;
            double hours,salary;
            boolean n=true;
            
            
           while(n){
               System.out.println("\n~Employee Record Management System~\n");
                System.out.println("1- Insert employee record\n" +
                               "2- Delete employee record\n" +
                               "3- Update employee record\n" +
                               "4- Show all employees\n" +
                               "5- Search an employee\n" +
                               "6- Update the salary of an employee\n"+
                               "7- Save and Exit\n");
               System.out.println("Choose from the list: ");
               choice=scanner1.nextInt();
                switch(choice){
                    case 1:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do{
                            System.out.println("Enter employee ID *The length: 5* :");  
                            ID=scanner1.nextInt();
                        }while(String.valueOf(ID).length() != 5);
                        scanner1.nextLine();
                        if(emp.check(ID)){
                            System.out.println("**Error! Employee record already exists**\n");
                        }else{
                        System.out.println("Enter employee Name:");  
                        name=scanner1.nextLine();
                        
                        System.out.println("Enter First day of work:");  
                        day=scanner1.nextLine();
                        do{
                        System.out.println("Enter Phone number *The length: 10* :");  
                        phone=scanner1.nextLine();
                        }while(phone.length()!=10);
                        System.out.println("Enter Address:");  
                        address=scanner1.nextLine();
                        do{
                            System.out.println("Enter Work hours at least 32 :");  
                            hours=scanner1.nextDouble();
                        }while(hours<32);
                        System.out.println("Enter Salary:");  
                        salary=scanner1.nextDouble();                       
                        emp.insertEmp(ID, name, day, phone, address, hours, salary);    
                        
                           System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+"\n");
                        }
                      break;
                    case 2: 
                        System.out.println("Enter employee ID to delete :");
                        ID=scanner1.nextInt();
                        if(emp.deleteEmp(ID)==0)
                        {System.out.println("**Employee record has been deleted** ");
                           
                        }else{
                            System.out.println("**Employee record not found**\n");
                        }
                        break;
                    case 3:
                         System.out.println("Enter employee ID to Update :\n");
                         ID=scanner1.nextInt();
                         emp.updateEmp(ID);
                        
                        break;
                    case 4:
                        emp.show();
                        break;
                    case 5:
                System.out.println("Enter employee ID to Search :");
                        ID=scanner1.nextInt();
                        Node temp=emp.search(ID);
                        if(temp!=null){
                             System.out.println("Employee Information:\n- ID:"+ID+
                            "\n- Name:"+temp.name+"\n- First day of work: "+
                            temp.day+"\n- Phone number: "+temp.phone+"\n- Address :"
                            +temp.address+"\n- Work hours: "+temp.hours
                            +"\n- Salary: "+temp.salary);
                        }else{
                             System.out.println("**Employee record not found**\n" );
                        }
                        break;
                    case 6:
                         System.out.println("Enter employee ID to update salary :");
                        ID=scanner1.nextInt();
                        emp.updateSalary(ID);
                        
                     
                        break;
                    case 7:
                        emp.write();
                        n=false;
                        break;
                    default:
                        System.out.println("**wrong**\n");
                        
                        
                }
           }
            
    }
  
   public void insertEmp(int ID,String name,String day,String phone,
           String address,double hours,double salary){
      
       Node newn=new Node(ID,name,day,phone,address,hours,salary);
        if(head==null || newn.ID < head.ID){
            newn.next = head;
            head=newn;
            return;
        }
        else{
            Node temp=head;
            
            while (temp.next != null && newn.ID> temp.next.ID  ) {
               temp = temp.next;
            }
            
                newn.next=temp.next;
                temp.next = newn;
            
        }
      
    }
   
   boolean check(int ID){
       Node temp=head;
       if(head==null)
           return false;
       
        while(temp!=null&&head!=null){
            if(temp.ID==ID)
                return true;
            temp=temp.next;
        }
       return false;
   }
   
   public Node search(int ID){
        Node temp=head;
        while (temp!= null) {
              if(temp.ID == ID){
              break;
            }else{
                temp=temp.next;
            }
        }
        return temp;
       
   }
     public int deleteEmp(int ID){
         
         Node temp=head,prev=null;
         if (temp != null && temp.ID == ID) {
            head = temp.next; 
            return 0;
        }
         while (temp != null && temp.ID != ID) {
            prev = temp;
            temp = temp.next;
        }
        if(temp==null){
            return -1;
        }
        prev.next=temp.next;
        return 0;
   }
     
     public void show(){
        Node temp=head;
        while(temp!=null){
            System.out.println("Employee Information :\n- ID:"+temp.ID+
            "\n- Name:"+temp.name+"\n- First day of work: "+
            temp.day+"\n- Phone number: "+temp.phone+"\n- Address :"
            +temp.address+"\n- Work hours: "+temp.hours+"\n- Salary: "+temp.salary);
            temp=temp.next;
        }
    
     }
     
     public void updateSalary(int ID){
     
         Node temp= search(ID);
         if(temp!=null){
            if(temp.hours>32){
                       double extra=temp.hours-32;
                       double newsalary=extra*2;
                       temp.salary+=(temp.salary*newsalary/100);
                       System.out.println("Salary updated for "+"("+ID+")");
               }
            
         }
         else{
              System.out.println("**Employee not found**");
         }
      
     }

public void updateEmp(int ID){
         if(!check(ID)){
             System.out.println("**Employee not found**");
         }
         else{
              Node temp=head;
                while(temp!=null){
                    if(temp.ID==ID){
                         break;
                    }
                    temp=temp.next;
                }
                         
             Scanner input=new Scanner(System.in);
             int choose;
             int cycle;
             String name;
             String day;
             String phone;
             String address;
             double hours,salary;
             do{
             System.out.println("~Choose information to update :~ \n");
             System.out.println("1. Employee name ");
             System.out.println("2. First day of work ");
             System.out.println("3. Phone number ");
             System.out.println("4. Address ");
             System.out.println("5. Work hours ");
             System.out.println("6. Salary");
             choose=input.nextInt();
             input.nextLine();
             switch(choose){
                 case 1: 
                     System.out.print("Enter new employee Name:");  
                     name=input.nextLine();
                     temp.name=name;
                     break;
                 case 2:
                     System.out.print("Enter new First day of work:");  
                     day=input.nextLine();
                     temp.day=day;
                     break;
                 case 3:
                      System.out.print("Enter new Phone number:");  
                      phone=input.nextLine();
                      temp.phone=phone;
                      break;
                 case 4:
                      System.out.print("Enter new Address:");  
                      address=input.nextLine();
                      temp.address=address;
                      break;
                 case 5:
                      System.out.print("Enter new Work hours:");  
                      hours=input.nextDouble();
                      temp.hours=hours;
                      
                      break;
                 case 6:
                       System.out.print("Enter new Salary:");  
                       salary=input.nextDouble();
                        temp.salary=salary;
                        updateSalary(ID);
                       break;
                 default : 
                     System.out.println("**Wrong**");
                     
                 }
                 System.out.println("\n~Enter 1 to update another data , otherwise enter -1 ~\n");
                 cycle=input.nextInt();
             }while(cycle==1);
         }          
     }
      
     public void write() throws IOException{
       File file = new File("EmployeeRecord.txt");
       FileWriter write = new FileWriter(file);
        Node temp=head;
        while(temp!=null){
            write.write(temp.ID+" "+temp.name+" "+temp.day+" "+temp.phone+" "
                    +temp.address+" "+temp.hours+" "+temp.salary+"\n");
            temp=temp.next;
        }   write.close();
   }
   public void read() throws FileNotFoundException{
            File file = new File("EmployeeRecord.txt");
            if(file.exists()){
            Scanner scanner2 = new Scanner(file);
            while(scanner2.hasNext()){
                    int ID=scanner2.nextInt();
                    String name=scanner2.next();
                    String day=scanner2.next();
                    String phone=scanner2.next();
                    String address=scanner2.next();
                    double hours=scanner2.nextDouble();
                    double salary=scanner2.nextDouble();
                    insertEmp(ID, name, day, phone, address, hours, salary);
              }
                 scanner2.close();
            }
   }
   
}
