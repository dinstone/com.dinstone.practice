/*
 * Copyright (C) 2011~2012 dinstone <dinstone@163.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.dinstone.practice.exception;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbResouceHandle {

    public void dataAccessCode() {
        Connection conn = null;
        Statement statment = null;
        try {
            conn = getConnection();
            statment = conn.createStatement();
            // some code that throws SQLException
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statment != null) {
                try {
                    statment.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    /**
     * Description:
     * 
     * @return
     */
    private Connection getConnection() {
        // TODO Auto-generated method stub
        return null;
    }

}
