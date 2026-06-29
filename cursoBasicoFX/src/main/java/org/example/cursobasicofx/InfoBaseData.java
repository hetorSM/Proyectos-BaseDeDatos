package org.example.cursobasicofx;

public class InfoBaseData {
     final String URL = "jdbc:mysql://localhost:3306/conectados";
     final String USER = "hector";
     final String PASSWORD = "hector";
     final String ClassforName = "com.mysql.cj.jdbc.Driver";
     final String TABLA1 = "usuarios";
     final String TABLA1_COLUMS[] = new String[]{"usuario_id", "usuario_nickname", "usuario_password"};

    public InfoBaseData() {
    }

    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getClassforName() {
        return ClassforName;
    }

    public String getTABLA1() {
        return TABLA1;
    }

    public String[] getTABLA1_COLUMS() {
        return TABLA1_COLUMS;
    }
}
