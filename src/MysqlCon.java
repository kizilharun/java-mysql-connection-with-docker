import java.util.Scanner;
import java.sql.*;

    class MysqlCon {
        static final String driver = "com.mysql.cj.jdbc.Driver";
        static final String url = "jdbc:mysql://192.168.99.100:3306/hello_java?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        static final String user = "demo_java";
        static final String pass = "1234";

        static Connection con = null ;
        static Statement stmt = null ;
        protected static void getConnection(){
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url,user,pass);
                stmt = con.createStatement();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        protected static void print(String sql){
            try {
                String query = sql ;
                getConnection();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    if(id != 0 )
                        System.out.println("ID: " + id + "   | Name: " + name + "   | Age : " + age);
                }
                rs.close();
                con.close();
                stmt.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        public static void insert(int id , String name , int age){
            try {
                getConnection();
                String sql = "INSERT INTO emp " +
                        "VALUES ("+id+","+name+" ,"+age+")";
                stmt.executeUpdate(sql);
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        public static void delete(int id1){
            try{
                getConnection();
                String sql = "DELETE from emp" +  " WHERE id = " +id1  ;
                stmt.executeUpdate(sql);
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }

        protected static void update(int id , int u_id , String u_name , int u_age){
            try{
                getConnection();
                String sql = "UPDATE emp "+ "SET id ="+u_id+", name= "+u_name+", age = "+u_age+" Where id="+id;
                System.out.println(sql);
                stmt.executeUpdate(sql);
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }

        protected static void sort(String sort_byDA ,String sort_byİA){
            try {
                getConnection();
                String sql = "SELECT id, name, age FROM emp "+" ORDER BY "+sort_byİA+ " "+ sort_byDA;
                print(sql);
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        public static void main(String[] args) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("\n#For Print Query -> press 1 \n#For Insert -> press 2 \n#For Sort Query -> press 3 \n#For Delete -> press 4 \n#For Update -> press 5 \n#For Exit -> press -1");
            int expression = 0;
            while(expression != -1){
                expression = myObj.nextInt();
                switch(expression){
                    case 1 :
                        String sql = "Select * from emp" ;
                        print(sql);
                        System.out.println("\n#For Print Query -> press 1 \n#For Insert -> press 2 \n#For Sort Query -> press 3 \n#For Delete -> press 4 \n#For Update -> press 5 \n#For Exit -> press -1");
                        break;

                    case 2 :
                        Scanner myObj_ = new Scanner(System.in);
                        System.out.println("insert name:");
                        String name = "\"" + myObj_.nextLine() +"\"";
                        System.out.println("insert id:");
                        int id = myObj_.nextInt();
                        System.out.println("insert age:");
                        int age = myObj_.nextInt();
                        insert(id,name,age);
                        System.out.println("\n#For Print Query -> press 1 \n#For Insert -> press 2 \n#For Sort Query -> press 3 \n#For Delete -> press 4 \n#For Update -> press 5 \n#For Exit -> press -1");                        break;

                    case 3 :
                        Scanner myObj1 = new Scanner(System.in);
                        Scanner myObj2 = new Scanner(System.in);
                        System.out.println("for sort by DESC enter 'DESC' , by ASC enter 'ASC' ");
                        String sort_by = myObj1.nextLine() ;
                        if(!(sort_by.equals("DESC") || sort_by.equals("ASC")) ) {
                            System.out.println("Wrong Sort");
                            System.exit(0);
                        }
                        System.out.println("for sort by id enter 'id' , by age enter 'age' ");
                        String sort_by1 = myObj2.nextLine() ;
                        if(!(sort_by1.equals("id")  || sort_by1.equals("age"))){
                            System.out.println("İnvalid input");
                            System.exit(0);
                        }
                        sort(sort_by,sort_by1);
                        System.out.println("\n#For Print Query -> press 1 \n#For Insert -> press 2 \n#For Sort Query -> press 3 \n#For Delete -> press 4 \n#For Update -> press 5 \n#For Exit -> press -1");                        break;

                    case 4 :
                        Scanner myObj3 = new Scanner (System.in);
                        System.out.println("Enter id who you want to delete ");
                        int id1 = myObj3.nextInt() ;
                        delete(id1);
                        System.out.println("\n#For Print Query -> press 1 \n#For Insert -> press 2 \n#For Sort Query -> press 3 \n#For Delete -> press 4 \n#For Update -> press 5 \n#For Exit -> press -1");                        break;
                    case 5 :
                        Scanner myObj4 = new Scanner(System.in);
                        Scanner myObj5 = new Scanner(System.in);
                        System.out.println("Enter id who you want to update ");
                        int id2 = myObj4.nextInt();
                        System.out.println("Enter new id");
                        int n_id = myObj4.nextInt();
                        System.out.println("Enter new age");
                        int n_age = myObj4.nextInt();
                        System.out.println("Enter new name");
                        String n_name = "\"" + myObj5.nextLine() +"\"";
                        update(id2,n_id,n_name,n_age);
                        System.out.println("\n#For Print Query -> press 1 \n#For Insert -> press 2 \n#For Sort Query -> press 3 \n#For Delete -> press 4 \n#For Update -> press 5 \n#For Exit -> press -1");                        break;
                    case -1 :
                        System.out.println("Program Finished");
                        break;
                    default:
                        System.out.println("invalid expression");
                        break;

                }}

        }
    }



