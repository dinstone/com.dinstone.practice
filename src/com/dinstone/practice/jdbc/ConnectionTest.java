
package com.dinstone.practice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

public class ConnectionTest {

    /**
     * <ul>
     * failover url </br>
     * jdbc:oracle:thin:@[HOST_NAME]:[PORT]/[SERVICE_NAME]</br>
     * jdbc:oracle:thin:@[HOST_NAME]:[PORT]:[SID]</br>
     * <li>-Durl=
     * "jdbc:oracle:thin:@(description=(address_list= (address=(host=172.17.21.227) (protocol=tcp)(port=1521))(address=(host=172.17.21.228)(protocol=tcp) (port=1521)) (load_balance=yes)(failover=yes))(connect_data=(service_name=ccit6)))"
     * </li>
     * <li>-Duser="drug_user"</li>
     * <li>-Dpassword="drugdrug"</li>
     * </ul>
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            int twt = 60;
            if (args.length > 0) {
                try {
                    twt = Integer.parseInt(args[0]);
                } catch (Exception e) {
                }
            }
            System.out.println("this time is " + twt + " second");

            String url = System.getProperty("url");
            String user = System.getProperty("user");
            String password = System.getProperty("password");
            System.out.println("url = " + url);
            // String url =
            // "jdbc:oracle:thin:@(description=(address_list= (address=(host=172.17.21.227) (protocol=tcp)(port=1521))(address=(host=172.17.21.228)(protocol=tcp) (port=1521)) (load_balance=yes)(failover=yes))(connect_data=(service_name=ccit6)))";
            // String user = "drug_user";
            // String password = "drugdrug";

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            String sql = "select 1 from dual";
            st.executeQuery(sql);
            System.out.println("first query at " + new Date());

            int stt = 0;
            while (stt < twt) {
                Thread.sleep(1000);
                stt++;

                System.out.print(" " + stt);
                if (stt % 10 == 0) {
                    System.out.println();
                }

                if (conn.isClosed()) {
                    System.out.println();
                    System.out.println("connection is closed");
                    break;
                }
            }

            System.out.println();
            System.out.println("second query at" + new Date());
            st.executeQuery(sql);

            System.out.println("close");
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
