package com.utez.kanban.kanban.infrastructure.persistence;

import java.sql.Connection;

public class S {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("🔥 CONECTADO A MYSQL CORRECTAMENTE 🔥");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
